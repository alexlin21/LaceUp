from flask import Flask, request, jsonify
app = Flask(__name__)

from pymongo import MongoClient
connection = MongoClient()
db = connection['laceup']
users = db['users']


import logging
from logging.handlers import RotatingFileHandler

@app.route("/")
def hello():
	return "Hello, World!"

@app.route("/login", methods = ['POST'])
def login():
    print("Loging In...")
    username = request.args.get('email')
    document = find_user(username)
    if document:
        print("user exists")
        print(document['email'])
        print(document['facebook-token'])
    else:
        return "No Account"

    return jsonify(email=document['email'], facebook_token=document['facebook-token'])

@app.route("/new-user", methods = ['POST'])
def new_user():
    print("New User...")
    username = request.args.get('email')
    token = request.args.get('facebook-token')
    print("User: " + username)
    print("Token: " + token)

    if find_user(username):
        print("Existing user: " + username)
    else:
        print("Adding new user: " + username)
        db.users.insert_one(
            {
                "email": username,
                "facebook-token": token
            }
        )

    return jsonify(email=username, facebook_token=token)

def find_user(email):
    return db.users.find_one({"email": email})

if __name__ == "__main__":
    handler = RotatingFileHandler('server.log', maxBytes=10000, backupCount=1)
    handler.setLevel(logging.INFO)
    app.logger.addHandler(handler)
    app.run()
