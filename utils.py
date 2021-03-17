import json

printj = lambda j: print(json.dumps(j, indent=4)) # print some beautiful JSON


TYPE_ARRAY = "array"
TYPE_OBJECT = "object"
TYPE_LEAF = "leaf"

def add_tree_part(json, type, key):
    if type == TYPE_ARRAY:
        if len(json) == 0:
            json["type"] = "array"
            json["validators"] = ["array"]
            json["items"] = {"type": "object", "validators": ["object"], "properties": {}}
        return json["items"]

    elif type == TYPE_OBJECT:
        if not key in json["properties"]:
            json["properties"][key] = {"type": "object", "validators": ["object"], "properties": {}}
        return json["properties"][key]


def add_leaf(json, key, validators):
        if not key in json["properties"]:
            json["properties"][key] = {"type": "leaf", "validators": [validators]}

def expand_validator(json):
    output = {}
    for keys in json:
        key = keys.split('.')
        latest = None
        for i in range(len(key)):
            if i == 0:
                if key[i] not in output:
                    output[key[i]] = {}
            elif key[i] == '*':
                latest = add_tree_part(output[key[i-1]], TYPE_ARRAY, None)
            else:
                if i < len(key)-1:
                    latest = add_tree_part(latest, TYPE_OBJECT, key[i])
                else:
                    add_leaf(latest, key[i], json[keys])

    return output
