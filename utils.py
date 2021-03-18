import json

printj = lambda j: print(json.dumps(j, indent=4)) # print some beautiful JSON

TYPE_ARRAY = "array"
TYPE_OBJECT = "object"

# add some part to the tree : could be an array or an object
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

# add a leaf to the tree
def add_leaf(json, key, validators):
        if validators == "array":
            add_tree_part(json, TYPE_OBJECT, key)
        elif not key in json["properties"]:
            json["properties"][key] = {"type": "leaf", "validators": [ v for v in validators.split('|')] if validators else [] } # add the leaf and create the validators

def expand_validator(json):
    output = {}
    for keys in json: # loop through lines
        key = keys.split('.')
        latest = output # will be used to connect a new part to the last added element on the tree
        for i in range(len(key)): # loop through elements of the line
            # create the first key
            if i == 0 and not i == len(key)-1:
                if key[i] not in output:
                    output[key[i]] = {}
                latest = output[key[i]]
            elif key[i] == '*': # deal with the array type
                latest = add_tree_part(output[key[i-1]], TYPE_ARRAY, None)
            else:
                # deal with array creation
                if len(key) == 1 and json[keys] == "array":
                    tmp = {}
                    add_tree_part(tmp, TYPE_OBJECT, key[i])
                    output[key[i]] = tmp['properties'][key[i]]
                # deal with the last element of the line
                elif i == len(key)-1:
                    if json[keys] == "object": # object type
                        latest = add_tree_part(latest, TYPE_OBJECT, key[i])
                    elif json[keys].split('|')[0] == "object": # object and key type (eg: "object|keys:w,o")
                        latest = add_tree_part(latest, TYPE_OBJECT, key[i])
                        objs = json[keys].split(':')[1].split(',')
                        [ add_leaf(latest, obj, None) for obj in objs ] # create a leaf for each keys
                    else:
                        add_leaf(latest, key[i], json[keys]) # basic creation of a leaf
                else:
                    # add a new object to the tree
                    if not key[i] in latest:
                        add_tree_part(latest, TYPE_OBJECT, key[i])
                    latest = latest['properties'][key[i]] # update latest

    return output
