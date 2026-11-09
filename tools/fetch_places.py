#!/usr/bin/env python3
"""
Builds app/src/main/assets/places/karnataka_places.json from Wikidata.

Wikidata data is CC0 (public domain), so names, descriptions, coordinates and categories can ship in the app
without attribution. Photos are NOT bundled: the app loads them from Wikimedia Commons at runtime and credits
Commons, and long descriptions come from Wikipedia (CC BY-SA) with a "From Wikipedia" link.

Run:  python3 tools/fetch_places.py
"""
import json
import pathlib
import subprocess
import urllib.parse

ROOT = pathlib.Path(__file__).resolve().parent.parent

# Wikidata class -> category shown in the app. Direct instance-of only (P31), which keeps the query fast.
CATEGORIES = {
    "Q842402": "Temple",          # Hindu temple
    "Q44539": "Temple",           # temple
    "Q160742": "Temple",          # abbey / monastery
    "Q32815": "Temple",           # mosque
    "Q16970": "Temple",           # church building
    "Q57821": "Fort",             # fortification
    "Q1785071": "Fort",           # fort
    "Q16560": "Palace",           # palace
    "Q34038": "Waterfall",        # waterfall
    "Q46169": "Wildlife",         # national park
    "Q20268": "Wildlife",         # wildlife sanctuary (Q20268 = nature reserve?) kept for coverage
    "Q473972": "Wildlife",        # protected area
    "Q179049": "Wildlife",        # nature reserve
    "Q23397": "Lake",             # lake
    "Q131681": "Lake",            # reservoir
    "Q40080": "Beach",            # beach
    "Q8502": "Hill",              # mountain
    "Q54050": "Hill",             # hill
    "Q1307276": "Hill",           # hill station
    "Q33506": "Museum",           # museum
    "Q839954": "Heritage",        # archaeological site
    "Q2232001": "Heritage",       # rock-cut architecture / cave temple
    "Q35509": "Heritage",         # cave
    "Q4989906": "Heritage",       # monument
    "Q167346": "Garden",          # botanical garden
    "Q22698": "Garden",           # park
    "Q23442": "Island",           # island
    "Q162875": "Heritage",        # mausoleum (Gol Gumbaz, Ibrahim Rauza)
    "Q860861": "Heritage",        # sculpture (Gommateshwara)
    "Q1779653": "Heritage",       # colossal statue
    "Q1377575": "Wildlife",       # wildlife refuge (Bhadra)
}

EXTRAS_QUERY = """
SELECT ?item ?itemLabel ?itemDescription ?coord ?image ?districtLabel ?article ?whs WHERE {
  VALUES ?article { %s }
  ?article schema:about ?item .
  ?item wdt:P625 ?coord .
  OPTIONAL { ?item wdt:P18 ?image . }
  OPTIONAL { ?item wdt:P131 ?district . }
  OPTIONAL { ?item wdt:P1435 wd:Q9259 . BIND(true AS ?whs) }
  SERVICE wikibase:label { bd:serviceParam wikibase:language "en". }
}
"""

# Everything in a box around Karnataka (fast spatial index). Items from neighbouring states are dropped later by
# tools/build_districts.py, which keeps only points inside the district polygons; a P131* filter here times out.
QUERY = """
SELECT ?item ?itemLabel ?itemDescription ?coord ?image ?districtLabel ?article ?whs WHERE {
  SERVICE wikibase:box {
    ?item wdt:P625 ?coord .
    bd:serviceParam wikibase:cornerSouthWest "Point(74.0 11.5)"^^geo:wktLiteral .
    bd:serviceParam wikibase:cornerNorthEast "Point(78.6 18.5)"^^geo:wktLiteral .
  }
  ?item wdt:P31 wd:%s .
  ?article schema:about ?item ; schema:isPartOf <https://en.wikipedia.org/> .
  OPTIONAL { ?item wdt:P18 ?image . }
  OPTIONAL { ?item wdt:P131 ?district . }
  OPTIONAL { ?item wdt:P1435 wd:Q9259 . BIND(true AS ?whs) }
  SERVICE wikibase:label { bd:serviceParam wikibase:language "en". }
}
"""


def run_query(query):
    # curl uses the OS certificate store, so this also works behind TLS-inspecting proxies.
    body = subprocess.run(
        ["curl", "-sS", "--fail-with-body", "--max-time", "120",
         "-A", "NammaTravelCompanionBuild/1.0 (raghunandan2005@gmail.com)",
         "-H", "Accept: application/sparql-results+json",
         "--data-urlencode", "query=" + query, "https://query.wikidata.org/sparql"],
        capture_output=True,
    )
    if body.returncode != 0:
        return None
    return json.loads(body.stdout)["results"]["bindings"]


def main():
    places = {}
    for class_id, category in CATEGORIES.items():
        rows = run_query(QUERY % class_id) or run_query(QUERY % class_id)  # one retry: timeouts are often transient
        print(f"{category:10} {class_id:9} {'timeout/error' if rows is None else len(rows)}")
        for r in rows or []:
            add_row(places, r, category)

    extras = [line.split("|") for line in (ROOT / "tools/extra_places.txt").read_text().splitlines()
              if line.strip() and not line.startswith("#")]
    article = lambda title: "<https://en.wikipedia.org/wiki/" + urllib.parse.quote(title, safe=",") + ">"
    category_by_url = {article(t)[1:-1]: c for t, c in extras}
    rows = run_query(EXTRAS_QUERY % " ".join(article(t) for t, _ in extras)) or []
    for r in rows:
        add_row(places, r, category_by_url.get(r["article"]["value"], "Hill"))
    found = {r["article"]["value"] for r in rows}
    missing = [t for t, _ in extras if article(t)[1:-1] not in found]
    print(f"extras: {len(found)}/{len(extras)} resolved" + (f"; not found: {', '.join(missing)}" if missing else ""))

    out = sorted(places.values(), key=lambda p: (not p["worldHeritage"], not p["image"], p["name"]))
    path = ROOT / "app/src/main/assets/places/karnataka_places.json"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(out, ensure_ascii=False, separators=(",", ":")))
    print(f"{len(out)} places -> {path}")


def add_row(places, r, category):
    qid = r["item"]["value"].rsplit("/", 1)[1]
    name = r["itemLabel"]["value"]
    if name == qid:  # no English label
        return
    image = urllib.parse.unquote(r["image"]["value"].rsplit("/", 1)[1]) if "image" in r else ""
    lng, lat = r["coord"]["value"].removeprefix("Point(").removesuffix(")").split(" ")
    place = places.setdefault(qid, {
        "id": qid,
        "name": name,
        "description": r.get("itemDescription", {}).get("value", ""),
        "category": category,
        "lat": round(float(lat), 5),
        "lng": round(float(lng), 5),
        "district": r.get("districtLabel", {}).get("value", ""),
        "wikipediaTitle": urllib.parse.unquote(r["article"]["value"].rsplit("/wiki/", 1)[1]),
        "image": image,
        "worldHeritage": "whs" in r,
    })
    place["image"] = place["image"] or image
    place["worldHeritage"] = place["worldHeritage"] or "whs" in r


if __name__ == "__main__":
    main()
