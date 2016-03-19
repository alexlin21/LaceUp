import geopy
import geopy.distance
import datetime
import logging
from logging.handlers import RotatingFileHandler

from flask import Flask, request, jsonify
app = Flask(__name__)

from pymongo import MongoClient
connection = MongoClient()
db = connection['laceup']
users = db['users']
challenges = db['challenges']

TIME_FORMAT = '%Y-%m-%dT%H:%M:%S'


@app.route("/")
def hello():
	return "Hello, World!"

# POST:
#   email
@app.route("/login", methods = ['POST'])
def login():
    username = request.args.get('email')
    document = find_user(username)
    if document:
        # user exists
        print("Login...")
    else:
        return "No Account"

    return jsonify(email=document['email'], facebook_token=document['facebook-token'])

@app.route("/new-user", methods = ['POST'])
def new_user():
    username = request.args.get('email')
    token = request.args.get('facebook-token')
    if find_user(username):
        # Email Already Exists
        print("Account Exists...")
    else:
        print("Adding new user: " + username)
        db.users.insert_one(
            {
                "email": username,
                "facebook-token": token
            }
        )

    return jsonify(email=username, facebook_token=token)

# POST:
#   name
#   description
#   deadline (See TIME_FORMAT)
#   image
#   latitude
#   longitude
@app.route("/new-challenge", methods = ['POST'])
def new_challenge():
    name = request.args.get('name')
    description = request.args.get('description')
    deadline = request.args.get('deadline')
    deadline = datetime.datetime.strptime(deadline, TIME_FORMAT)
    image = request.args.get('image')
    latitude = request.args.get('latitude')
    longitude = request.args.get('longitude')
    if find_challenge(name):
        # Challenge Already Exists
        print("Challenge Exists...")
    else:
        db.challenges.insert_one(
            {
                "name": name,
                "description": description,
                "deadline": deadline.strftime(TIME_FORMAT),
                "image": image,
                "location": {
                    "longitude": longitude,
                    "latitude": latitude
                }
            }
        )
    return jsonify(name=name, description=description)

# GET:
#   user_latitude
#   user_longitude
#   user_radius
@app.route("/get-challenges", methods = ['GET'])
def get_challenges():
    now = datetime.datetime.now()
    user_latitude = request.args.get('user_latitude')
    user_longitude = request.args.get('user_longitude')
    user_radius = request.args.get('user_radius')
    events_in_range = []
    for challenge in challenges.find({"deadline": {"$gt": now.strftime(TIME_FORMAT)}}).sort("deadline"):
        challenge['_id'] = str(challenge['_id'])
        event_location = challenge['location']
        event_coordinates = geopy.Point(event_location['latitude'], event_location['longitude'])
        user_coordinates = geopy.Point(user_latitude, user_longitude)
        distance =  geopy.distance.distance(event_coordinates, user_coordinates).mi
        if float(distance) <= float(user_radius):
            events_in_range.append(challenge)
    return jsonify(results=events_in_range)

# POST:
#   event_id
#   user_email
# TODO: Looks up bases on name. Switch to id
@app.route("/accept-challenge", methods=['POST'])
def accept_challenge():
    event_name = request.args.get('name')
    user_email = request.args.get('user_email')
    challenge = db.challenges.find_one({"name": event_name})
    challenges.update(
        { '_id' : challenge['_id']},
        {   "$addToSet": {'going': user_email}}
    )
    return "Hopefully it worked"

def find_user(email):
    return db.users.find_one({"email": email})

def find_challenge(name):
    return db.challenges.find_one({"name": name})

if __name__ == "__main__":
    handler = RotatingFileHandler('server.log', maxBytes=10000, backupCount=1)
    handler.setLevel(logging.INFO)
    app.logger.addHandler(handler)
    app.run()
