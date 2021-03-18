from utils import printj, expand_validator
from flask import Flask, request
import json

app = Flask(__name__)

@app.route('/', methods = ['GET', 'POST'])
def root():
    return "Nothing to see here 😋"

@app.route('/expand_validator', methods = ['GET'])
def expand_validator_get():
    return "Nothing to see here 😋"

@app.route('/expand_validator', methods = ['POST'])
def expand_validator_route():
    try:
        return { "ok": True, "data": expand_validator(request.json) }
    except Exception:
        return { "ok": False, "reason": "The input wasn't in the expected format." }

if __name__ == '__main__':
    app.run(host='0.0.0.0')
