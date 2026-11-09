#!/usr/bin/env python3
"""
Builds the district map and tags every place with its district.

Input:  geoBoundaries India ADM2 (2021) and ADM3 (2018) simplified GeoJSON, ODbL 1.0
        (https://www.geoboundaries.org, source: Local Government Directory, Govt of India).
Output: app/src/main/assets/maps/karnataka_districts.json  (ODbL 1.0, see THIRD_PARTY_NOTICES.md)
        app/src/main/assets/places/karnataka_places.json gets a "districtId" per place.

The 2021 data still shows 30 districts, so Vijayanagara (formed 2021) is rebuilt from its six taluks, and
Ballari and Davanagere lose those taluks.

Run after tools/fetch_places.py:
    pip install -r tools/requirements.txt
    python3 tools/build_districts.py
"""
import json
import pathlib
import subprocess

from shapely.geometry import Point, mapping, shape
from shapely.ops import unary_union

ROOT = pathlib.Path(__file__).resolve().parent.parent
CACHE = ROOT / "build/geo"
SOURCES = {
    "adm2": "https://github.com/wmgeolab/geoBoundaries/raw/9469f09/releaseData/gbOpen/IND/ADM2/geoBoundaries-IND-ADM2_simplified.geojson",
    "adm3": "https://github.com/wmgeolab/geoBoundaries/raw/9469f09/releaseData/gbOpen/IND/ADM3/geoBoundaries-IND-ADM3_simplified.geojson",
}

# geoBoundaries name -> app district id (matches KarnatakaDistricts.kt)
ADM2_IDS = {
    "Bagalkot": "bagalkote", "Bangalore": "bengaluru_urban", "Bangalore Rural": "bengaluru_rural",
    "Belgaum": "belagavi", "Bellary": "ballari", "Bidar": "bidar", "Bijapur": "vijayapura",
    "Chamarajanagar": "chamarajanagar", "Chikkaballapura": "chikkaballapura", "Chikmagalur": "chikkamagaluru",
    "Chitradurga": "chitradurga", "Dakshina Kannada": "dakshina_kannada", "Davanagere": "davanagere",
    "Dharwad": "dharwad", "Gadag": "gadag", "Gulbarga": "kalaburagi", "Hassan": "hassan", "Haveri": "haveri",
    "Kodagu": "kodagu", "Kolar": "kolar", "Koppal": "koppal", "Mandya": "mandya", "Mysore": "mysuru",
    "Raichur": "raichur", "Ramanagara": "bengaluru_south", "Shimoga": "shivamogga", "Tumkur": "tumakuru",
    "Udupi": "udupi", "Uttara Kannada": "uttara_kannada", "Yadgir": "yadgir",
}
VIJAYANAGARA_TALUKS = {"Hosapete", "Kudligi", "Hagaribommanahalli", "KOTTURU", "Hadagalli", "Harapanahalli"}

SIMPLIFY_DEGREES = 0.006   # ~600 m: plenty for a phone-sized map, keeps the asset small
SNAP_DEGREES = 0.01        # ~1 km: places sitting on a border (rounding in source data) snap to the nearest district


def load(name):
    path = CACHE / f"{name}.geojson"
    if not path.exists():
        CACHE.mkdir(parents=True, exist_ok=True)
        subprocess.run(["curl", "-sSfL", "-o", str(path), SOURCES[name]], check=True)
    return json.loads(path.read_text())["features"]


def main():
    shapes = {ADM2_IDS[f["properties"]["shapeName"]]: shape(f["geometry"])
              for f in load("adm2") if f["properties"]["shapeName"] in ADM2_IDS}
    assert len(shapes) == 30, f"expected 30 source districts, got {len(shapes)}"

    vijayanagara_taluks = [shape(f["geometry"]) for f in load("adm3")
                           if f["properties"]["shapeName"] in VIJAYANAGARA_TALUKS
                           and shape(f["geometry"]).representative_point().within(
                               shapes["ballari"].buffer(0.05).union(shapes["davanagere"].buffer(0.05)))]
    assert len(vijayanagara_taluks) == 6, f"expected 6 Vijayanagara taluks, got {len(vijayanagara_taluks)}"
    vijayanagara = unary_union(vijayanagara_taluks).buffer(0.002).buffer(-0.002)
    shapes["vijayanagara"] = vijayanagara
    shapes["ballari"] = shapes["ballari"].difference(vijayanagara)
    shapes["davanagere"] = shapes["davanagere"].difference(vijayanagara)

    districts = []
    for district_id, geom in sorted(shapes.items()):
        simple = geom.simplify(SIMPLIFY_DEGREES, preserve_topology=True)
        polys = [simple] if simple.geom_type == "Polygon" else list(simple.geoms)
        polys = [p for p in polys if p.area > 0.0005]  # drop slivers left by the difference()
        label = geom.representative_point()
        districts.append({
            "id": district_id,
            "label": [round(label.x, 3), round(label.y, 3)],
            "polygons": [[[round(x, 3), round(y, 3)] for x, y in p.exterior.coords] for p in polys],
        })
    out = ROOT / "app/src/main/assets/maps/karnataka_districts.json"
    out.parent.mkdir(parents=True, exist_ok=True)
    out.write_text(json.dumps(districts, separators=(",", ":")))
    print(f"{len(districts)} districts -> {out.relative_to(ROOT)} ({out.stat().st_size // 1024} KB)")

    places_path = ROOT / "app/src/main/assets/places/karnataka_places.json"
    places = json.loads(places_path.read_text())
    kept = []
    for place in places:
        if place.get("districtId") and not place["lat"]:  # hand-added without coordinates
            kept.append(place)
            continue
        point = Point(place["lng"], place["lat"])
        district_id = next((d for d, g in shapes.items() if g.contains(point)), None)
        if district_id is None:
            nearest = min(shapes.items(), key=lambda kv: kv[1].distance(point))
            district_id = nearest[0] if nearest[1].distance(point) < SNAP_DEGREES else None
        if district_id:  # drop places that turned out to be outside Karnataka
            place["districtId"] = district_id
            kept.append(place)
    places_path.write_text(json.dumps(kept, ensure_ascii=False, separators=(",", ":")))
    print(f"{len(kept)}/{len(places)} places tagged with a district")


if __name__ == "__main__":
    main()
