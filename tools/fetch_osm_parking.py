#!/usr/bin/env python3
"""
Writes app/src/main/assets/essentials/osm_parking.json: up to 3 nearest OpenStreetMap parking lots (amenity=parking,
nodes and ways) within 800 m of every place in karnataka_places.json. The Visit Essentials card falls back to this
for places without hand-verified parking in visit_essentials.json.

OSM data is ODbL 1.0 (attribution "© OpenStreetMap contributors", shown in the app).

Run after tools/fetch_osm_places.py (needs the final place list).
"""
import json
import math
import pathlib
import subprocess
import time

ROOT = pathlib.Path(__file__).resolve().parent.parent
PLACES = ROOT / "app/src/main/assets/places/karnataka_places.json"
OUT = ROOT / "app/src/main/assets/essentials/osm_parking.json"
CACHE = ROOT / "build/geo/parking"  # one file per batch; delete to re-download
RADIUS_M = 800
BATCH = 50
PAUSE_S = 5  # between Overpass calls, to stay polite


def query(batch):
    arounds = "".join(f'nw(around:{RADIUS_M},{p["lat"]},{p["lng"]})["amenity"="parking"];' for p in batch)
    return f"[out:json][timeout:180];({arounds});out center tags;"


def fetch(index, batch):
    cache = CACHE / f"batch_{index:03}.json"
    if not cache.exists():
        subprocess.run(
            ["curl", "-sS", "--fail-with-body", "--max-time", "300", "--retry", "3", "--retry-all-errors",
             "--retry-delay", "30", "-A", "NammaTravelCompanionBuild/1.0 (raghunandan2005@gmail.com)",
             "--data-urlencode", "data=" + query(batch), "-o", str(cache), "https://overpass-api.de/api/interpreter"],
            check=True,
        )
        time.sleep(PAUSE_S)
    try:
        return json.loads(cache.read_text())["elements"]
    except (ValueError, KeyError):
        cache.unlink()
        raise SystemExit("Overpass returned no data (usually rate limiting). Wait a few minutes and run again.")


def distance_m(lat1, lng1, lat2, lng2):
    p1, p2 = math.radians(lat1), math.radians(lat2)
    a = math.sin((p2 - p1) / 2) ** 2 + math.cos(p1) * math.cos(p2) * math.sin(math.radians(lng2 - lng1) / 2) ** 2
    return 2 * 6_371_000 * math.asin(math.sqrt(a))


def nearest(place, lots, limit=3):
    found = []
    for lot in lots:
        d = distance_m(place["lat"], place["lng"], lot["lat"], lot["lng"])
        if d <= RADIUS_M:
            found.append({**lot, "distanceM": round(d)})
    return sorted(found, key=lambda lot: lot["distanceM"])[:limit]


def main():
    places = [p for p in json.loads(PLACES.read_text()) if p["lat"] or p["lng"]]
    CACHE.mkdir(parents=True, exist_ok=True)
    lots = {}
    batches = [places[i:i + BATCH] for i in range(0, len(places), BATCH)]
    for index, batch in enumerate(batches):
        print(f"batch {index + 1}/{len(batches)}", flush=True)
        for e in fetch(index, batch):
            center = e.get("center", e)
            tags = e.get("tags", {})
            lots[(e["type"], e["id"])] = {
                "name": tags.get("name") or "Parking",
                "lat": round(center["lat"], 5),
                "lng": round(center["lon"], 5),
                "fee": tags.get("fee", ""),
            }
    # ponytail: O(places × lots) scan, fine for a few thousand lots; add a grid index if it gets slow
    result = {p["id"]: n for p in places if (n := nearest(p, lots.values()))}
    OUT.parent.mkdir(parents=True, exist_ok=True)
    OUT.write_text(json.dumps({"attribution": "© OpenStreetMap contributors, ODbL", "places": result},
                              ensure_ascii=False, separators=(",", ":")))
    print(f"{len(lots)} lots, {len(result)}/{len(places)} places with parking, {OUT.stat().st_size:,} bytes")


if __name__ == "__main__":
    assert round(distance_m(12.0, 77.0, 12.0 + 800 / 111_195, 77.0)) == 800
    main()
