import json

printj = lambda j: print(json.dumps(j, indent=4)) # print some beautiful JSON


# create an array (if not exist) and return a JSON to this structure
def add_array(json):
    if not "items" in json:
        json["type"] = "array"
        json["validators"] = ["array"]
        json["items"] = {}
    return json["items"]

# create an object (if not exist) and return a JSON to this structure
def add_object(json, key):
    if not "properties" in json:
        json["type"] = "object"
        json["validators"] = ["object"]
        json["properties"] = {}
    if not key in json["properties"]:
        json["properties"][key] = {}

    return json["properties"][key]

def add_leaf(json, key, validators):
    json[key] = { "type": "leaf", "validators": [ v for v in validators.split('|') ] if validators else [] }
    return json

# verify that an object is correctly initiated before adding some things
def verif_object(json):
    if not "properties" in json:
        json["type"] = "object"
        json["validators"] = ["object"]
        json["properties"] = {}

def expand_validator(json):
    output = {}
    for keys in json: # loop through lines
        key = keys.split('.')
        latest = output # will be used to connect a new part to the last added element on the tree
        for i in range(len(key)): # loop through elements of the line
            # case for the first keys (but not the last ones)
            if i == 0 and i != len(key)-1:
                if key[i] != "*":
                    if not key[i] in latest:
                        latest[key[i]] = {}
                    latest = latest[key[i]]

                if key[i] == "*":
                    if not "properties" in latest:
                        latest["type"] = "object"
                        latest["properties"] = {}

            # case for all keys (not the 1st and last one)
            elif i < len(key)-1:
                if key[i] == "*":
                    latest = add_array(latest)
                else:
                    latest = add_object(latest, key[i])

            # case for the last keys
            else:
                if key[i] == "*":
                    add_array(latest)
                    add_leaf(latest, "items", json[keys])

                elif json[keys] == "array":
                    if not key[i] in latest:
                        latest[key[i]] = {}
                    latest = latest[key[i]]

                elif json[keys] == "object":
                    pass

                elif json[keys].split('|')[0] == "object":
                    latest = add_object(latest, key[i])
                    verif_object(latest)
                    latest = latest["properties"]
                    objs = json[keys].split(':')[1].split(',')
                    for obj in objs:
                        add_leaf(latest, obj, None) # create a leaf for each keys

                # other cases, for example a "string"
                else:
                    if output == {}:
                        latest = add_leaf(latest, key[i], json[keys])
                    else:
                        verif_object(latest)
                        latest = add_leaf(latest["properties"], key[i], json[keys])

    return output
