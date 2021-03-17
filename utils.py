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
        if not 'properties' in json:
            json["properties"] = {}
        if not key in json["properties"]:
            json["properties"][key] = {"type": "object", "validators": ["object"], "properties": {}}
        return json["properties"][key]


def add_leaf(json, key, validators):
        if validators == "array":
            add_tree_part(json, TYPE_OBJECT, key)
        elif not key in json["properties"]:
            json["properties"][key] = {"type": "leaf", "validators": [ v for v in validators.split('|')] }

def expand_validator(json):
    output = {}
    for keys in json:
        key = keys.split('.')
        latest = output
        for i in range(len(key)):
            if i == 0 and not i == len(key)-1:
                if key[i] not in output:
                    output[key[i]] = {}
                latest = output[key[i]]
            elif key[i] == '*':
                latest = add_tree_part(output[key[i-1]], TYPE_ARRAY, None)
            else:
                if len(key) == 1 and json[keys] == "array":
                    tmp = {}
                    add_tree_part(tmp, TYPE_OBJECT, key[i])
                    output[key[i]] = tmp['properties'][key[i]]
                elif i == len(key)-1:
                    if json[keys] != "object":
                        add_leaf(latest, key[i], json[keys])
                    elif json[keys] == "object":
                        latest = add_tree_part(latest, TYPE_OBJECT, key[i])
                else:
                    if not key[i] in latest:
                        add_tree_part(latest, TYPE_OBJECT, key[i])
                    latest = latest['properties'][key[i]]


    return output
