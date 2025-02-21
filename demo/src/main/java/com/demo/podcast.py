import json
import os
import requests
import asyncio
ELEVENLABS_API_KEY = os.getenv("ELEVENLABS_API_KEY", None)
assert ELEVENLABS_API_KEY is not None, "Please set ELEVENLABS_API_KEY environment variable"
ARTICLE_HTML = [
    "<html>",
    "<body>",
    "<div>",
    """
    <p>
    ElevenL
    </p>
    <p>
    Our re
    </p>
    <p>
    Ou
    </p>
    <p>
    We develop our 
    </p>
    """,
    "</div>",
    "</body>",
    "</html>",
]

MAX_POLLING_RETRIES = 60

async def main():
    response = requests.post(
        f"https://api.elevenlabs.io/v1/projects/podcast/",
        data={
            "host_voice_id": "iP95p4xoKVk53GoZ742B", # TODO: Replace with host voice of your choice
            "guest_voice_id": "cgSgspJ2msm6clMCkdW9", # TODO: Replace with guest voice of your choice
            "default_model_id": "eleven_multilingual_v2", # TODO: Replace with model of your choice
            "quality_preset": "standard",
        },
        files={
            "from_document": (
                "article.html",
                "\n".join(ARTICLE_HTML),
                "text/html",
            ),
        },
        headers={"xi-api-key": ELEVENLABS_API_KEY},
    )
    if response.status_code != 200:
        print(f"Error encountered, status: {response.status_code}, content: {response.text}")

    response.raise_for_status()

    print("CREATING PODCAST")
    print(response.json())
    project_id = response.json()["project_id"]

    retry = 0
    while retry < MAX_POLLING_RETRIES:
        response = requests.get(
            f"https://api.elevenlabs.io/v1/projects/{project_id}",
            headers={"xi-api-key": ELEVENLABS_API_KEY},
        )
        response.raise_for_status()
        if response.json()['state'] == 'default':
            break
        print(f"WAITING FOR PODCAST TO BE CREATED {retry=} progress={response.json()['creation_meta']['creation_progress'] * 100}%")
        retry += 1
        await asyncio.sleep(10)
    if retry >= MAX_POLLING_RETRIES:
        raise TimeoutError("Podcast is still not created")
    print("CREATED PODCAST, TRIGGERING PODCAST AUDIO GENERATION")
    requests.post(
        f"https://api.elevenlabs.io/v1/projects/{project_id}/convert",
        headers={"xi-api-key": ELEVENLABS_API_KEY},
    )
    retry = 0
    while retry < MAX_POLLING_RETRIES:
        print(f"WAITING FOR PODCAST AUDIO TO BE GENERATED {retry=}")
        response = requests.get(
            f"https://api.elevenlabs.io/v1/projects/{project_id}",
            headers={"xi-api-key": ELEVENLABS_API_KEY},
        )
        response.raise_for_status()
        if response.json()['state'] == 'default':
            break
        retry += 1
        await asyncio.sleep(10)

    if retry >= MAX_POLLING_RETRIES:
        raise TimeoutError("Podcast audio is still not generated")

    print("PODCAST AUDIO GENERATED, TRIGGERING DOWNLOAD")
    response = requests.get(
        f"https://api.elevenlabs.io/v1/projects/{project_id}/snapshots",
        headers={"xi-api-key": ELEVENLABS_API_KEY},
    )
    response.raise_for_status()
    snapshot_id = response.json()['snapshots'][0]['project_snapshot_id']
    path = "podcast.mp3"

    with requests.post(
            f"https://api.elevenlabs.io/v1/projects/{project_id}/snapshots/{snapshot_id}/stream",
            headers={"xi-api-key": ELEVENLABS_API_KEY},
            stream=True
    ) as response:
        response.raise_for_status()
        with open(path, "wb") as file:
            for chunk in response.iter_content(chunk_size=8192):  # Download in chunks of 8KB
                if chunk:  # Filter out keep-alive chunks
                    file.write(chunk)
    print(f"PODCAST AUDIO DOWNLOADED {path=}")
if __name__ == "__main__":
    loop = asyncio.get_event_loop()
    loop.run_until_complete(main())