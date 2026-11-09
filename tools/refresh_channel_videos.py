#!/usr/bin/env python3
"""
Writes app/src/main/assets/youtube/channel_videos.json (video id + title) for the app's video sections.

Video IDs come from YouTube Data API v3 when YOUTUBE_API_KEY is set (all uploads of the channel), otherwise from
tools/channel_video_ids.txt. Titles come from YouTube's public oEmbed endpoint (no key needed).
The API key is only used here at build time; it is never shipped in the app.

Run:  python3 tools/refresh_channel_videos.py
"""
import json
import os
import pathlib
import subprocess
import urllib.parse

CHANNEL_ID = "UCnBJaoGj6EUNvTlIqLWGG8A"
ROOT = pathlib.Path(__file__).resolve().parent.parent


def get_json(url):
    # curl uses the OS certificate store, so this also works behind TLS-inspecting proxies.
    out = subprocess.run(["curl", "-sSf", "--max-time", "30", url], capture_output=True, check=True).stdout
    return json.loads(out)


def ids_from_api(key):
    playlist = "UU" + CHANNEL_ID[2:]  # every channel's uploads playlist
    ids, page = [], ""
    while True:
        data = get_json(
            "https://www.googleapis.com/youtube/v3/playlistItems?part=contentDetails&maxResults=50"
            f"&playlistId={playlist}&key={key}&pageToken={page}"
        )
        ids += [item["contentDetails"]["videoId"] for item in data["items"]]
        page = data.get("nextPageToken")
        if not page:
            return ids


def ids_from_file():
    lines = (ROOT / "tools/channel_video_ids.txt").read_text().splitlines()
    return [line.split()[0] for line in lines if line.strip() and not line.startswith("#")]


def main():
    key = os.environ.get("YOUTUBE_API_KEY")
    ids = ids_from_api(key) if key else ids_from_file()
    videos = []
    for vid in ids:
        info = get_json("https://www.youtube.com/oembed?format=json&url=" +
                        urllib.parse.quote(f"https://www.youtube.com/watch?v={vid}"))
        videos.append({"id": vid, "title": info["title"]})
        print(vid, info["title"])
    out = ROOT / "app/src/main/assets/youtube/channel_videos.json"
    out.parent.mkdir(parents=True, exist_ok=True)
    out.write_text(json.dumps(videos, ensure_ascii=False, indent=1))
    print(f"{len(videos)} videos -> {out.relative_to(ROOT)}")


if __name__ == "__main__":
    main()
