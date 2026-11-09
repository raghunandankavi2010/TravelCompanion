#!/usr/bin/env python3
"""
Adds OpenStreetMap attractions and tools/local_places.csv to app/src/main/assets/places/karnataka_places.json.

OSM data is ODbL 1.0 (attribution "© OpenStreetMap contributors", shown in the app). It fills gaps Wikidata leaves:
zoos, theme/water/snow parks, viewpoints, waterfalls, beaches, forts, ruins and temples tagged as heritage or
tourist sites. Ordinary places of worship are skipped (tens of thousands); add local favourites to
tools/local_places.csv instead.

Run after tools/fetch_places.py and before tools/build_districts.py.
"""
import csv
import json
import math
import pathlib
import subprocess

ROOT = pathlib.Path(__file__).resolve().parent.parent
PLACES = ROOT / "app/src/main/assets/places/karnataka_places.json"

QUERY = """
[out:json][timeout:180];
area["ISO3166-2"="IN-KA"]->.ka;
(
  nwr(area.ka)["tourism"~"^(attraction|theme_park|zoo|aquarium|museum|viewpoint|gallery)$"]["name"];
  nwr(area.ka)["leisure"~"^(water_park|nature_reserve)$"]["name"];
  nwr(area.ka)["natural"~"^(waterfall|beach)$"]["name"];
  nwr(area.ka)["waterway"="waterfall"]["name"];
  nwr(area.ka)["historic"~"^(castle|fort|archaeological_site|ruins|monument)$"]["name"];
  nwr(area.ka)["amenity"="place_of_worship"]["name"]["heritage"];
  nwr(area.ka)["amenity"="place_of_worship"]["name"]["wikidata"];
);
out center tags;
"""


def category(tags):
    tourism, leisure, natural, historic = (tags.get(k, "") for k in ("tourism", "leisure", "natural", "historic"))
    if tourism == "theme_park" or leisure == "water_park" or "snow" in tags["name"].lower() and tourism == "attraction":
        return "Amusement"
    if tourism in ("zoo", "aquarium") or leisure == "nature_reserve":
        return "Wildlife"
    if tourism in ("museum", "gallery"):
        return "Museum"
    if natural == "waterfall" or tags.get("waterway") == "waterfall":
        return "Waterfall"
    if natural == "beach":
        return "Beach"
    if historic in ("castle", "fort"):
        return "Fort"
    if tags.get("amenity") == "place_of_worship":
        return "Temple"
    if historic:
        return "Heritage"
    if tourism == "viewpoint" or natural == "peak":
        return "Hill"
    if leisure in ("park", "garden"):
        return "Garden"
    if natural == "water" or tags.get("water"):
        return "Lake"
    return "Attraction"


def commons_file(tags):
    for key in ("wikimedia_commons", "image"):
        value = tags.get(key, "")
        if value.startswith("File:"):
            return value[5:]
        if "commons.wikimedia.org/wiki/File:" in value:
            return value.split("File:", 1)[1]
    return ""


def close(a, b, meters=500):
    dy = (a["lat"] - b["lat"]) * 111_000
    dx = (a["lng"] - b["lng"]) * 111_000 * math.cos(math.radians(a["lat"]))
    return dx * dx + dy * dy < meters * meters


def norm(name):
    return "".join(ch for ch in name.lower() if ch.isalnum())


def main():
    places = json.loads(PLACES.read_text())
    places = [p for p in places if not p["id"].startswith(("osm:", "local:"))]  # re-runnable
    by_qid = {p["id"] for p in places}

    cache = ROOT / "build/geo/osm.json"  # delete to re-download; Overpass rate-limits repeated heavy queries
    if not cache.exists():
        cache.parent.mkdir(parents=True, exist_ok=True)
        subprocess.run(
            ["curl", "-sS", "--fail-with-body", "--max-time", "300", "--retry", "3", "--retry-all-errors",
             "-A", "NammaTravelCompanionBuild/1.0 (raghunandan2005@gmail.com)",
             "--data-urlencode", "data=" + QUERY, "-o", str(cache), "https://overpass-api.de/api/interpreter"],
            check=True,
        )
    try:
        elements = json.loads(cache.read_text())["elements"]
    except (ValueError, KeyError):
        cache.unlink()
        raise SystemExit("Overpass returned no data (usually rate limiting). Wait a few minutes and run again.")
    added = 0
    for element in elements:
        tags = element["tags"]
        if "place" in tags or "highway" in tags:
            continue  # towns and roads that some mappers also tag as attractions
        name = tags.get("name:en") or tags["name"]
        center = element.get("center", element)
        if tags.get("wikidata") in by_qid:
            continue  # already have it from Wikidata, with a better description
        place = {
            "id": f"osm:{element['type'][0]}{element['id']}",
            "name": name,
            "description": tags.get("description", ""),
            "category": category(tags),
            "lat": round(center["lat"], 5),
            "lng": round(center["lon"], 5),
            "district": "",
            "wikipediaTitle": tags.get("wikipedia", "").removeprefix("en:") if tags.get("wikipedia", "").startswith("en:") else "",
            "image": commons_file(tags),
            "worldHeritage": False,
            "source": "osm",
        }
        if any(norm(p["name"]) == norm(name) and close(p, place) for p in places):
            continue
        places.append(place)
        added += 1
    print(f"OpenStreetMap: +{added} places")

    local = 0
    with open(ROOT / "tools/local_places.csv", newline="") as f:
        for row in csv.DictReader(line for line in f if not line.startswith("#")):
            places.append({
                "id": f"local:{norm(row['name'])}",
                "name": row["name"],
                "description": row["description"],
                "category": row["category"],
                "lat": float(row["lat"]) if row["lat"] else 0.0,
                "lng": float(row["lng"]) if row["lng"] else 0.0,
                "district": "",
                "districtId": row["district_id"],
                "wikipediaTitle": "",
                "image": "",
                "worldHeritage": False,
                "source": "local",
            })
            local += 1
    print(f"local_places.csv: +{local} places")
    PLACES.write_text(json.dumps(places, ensure_ascii=False, separators=(",", ":")))
    print(f"{len(places)} places total")


if __name__ == "__main__":
    main()
