
import requests
import json

username = 'VladMelnik'
password = 'ATBB7gU69ZJVbxZTtZ9L2J6TPtzE0581081C'
team = 'panorays'  # the team name whose repositories you want to get

url = f'https://api.bitbucket.org/2.0/repositories/{team}'

repositories = []

while url is not None:
    response = requests.get(url, auth=(username, password))
    data = json.loads(response.text)
    for repo in data['values']:
        repositories.append(repo['name'])
    url = data.get('next', None)  # Get the next page URL, if it exists

print("\\|".join(repositories))
