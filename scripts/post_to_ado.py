import requests, argparse, base64, os

def post_comment(pr_id, markdown):
    url = f"{os.getenv('SYSTEM_COLLECTIONURI')}{os.getenv('SYSTEM_TEAMPROJECT')}" \
          f"/_apis/git/repositories/{os.getenv('BUILD_REPOSITORY_ID')}" \
          f"/pullRequests/{pr_id}/threads?api-version=7.1-preview.1"
    payload = {
      "comments": [{"parentCommentId": 0,
                    "content": markdown,
                    "commentType": 1}],
      "status": 1
    }
    pat = base64.b64encode((":{}".format(os.getenv("ADO_PAT"))).encode()).decode()
    headers = {"Authorization": f"Basic {pat}"}
    requests.post(url, json=payload, headers=headers)

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('--pr')
    parser.add_argument('--file')
    args = parser.parse_args()
    with open(args.file, encoding="utf‑8") as f:
        md = f.read()
    post_comment(args.pr, md)
