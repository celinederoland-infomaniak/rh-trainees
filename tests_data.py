# some inputs for the tests
data_in = [
    # item 1
    {
    "a.*.y.t": "integer",
    "a.*.y.u": "integer",
    "a.*.z": "object|keys:w,o",
    "b": "array",
    "b.c": "string",
    "b.d": "object",
    "b.d.e": "integer|min:5",
    "b.d.f": "string"
    },

    # item 2
    {
      "a.*.b": "object|keys:c,d"
    },

    # item 3
    {
      "a.*.b": "string",
      "x.y.z": "integer|max:23",
    },
]

# wanted result
data_out = [
    # item 1
    {
        "a": {
          "type": "array",
          "validators": [
            "array"
          ],
          "items": {
            "type": "object",
            "validators": [
              "object"
            ],
            "properties": {
              "y": {
                "type": "object",
                "validators": [
                  "object"
                ],
                "properties": {
                  "t": {
                    "type": "leaf",
                    "validators": [
                      "integer"
                    ]
                  },
                  "u": {
                    "type": "leaf",
                    "validators": [
                      "integer"
                    ]
                  }
                }
              },
              "z": {
                "type": "object",
                "validators": [
                  "object"
                ],
                "properties": {
                  "w": {
                    "type": "leaf",
                    "validators": []
                  },
                  "o": {
                    "type": "leaf",
                    "validators": []
                  }
                }
              }
            }
          }
        },
        "b": {
          "type": "object",
          "validators": [
            "object"
          ],
          "properties": {
            "c": {
              "validators": [
                "string"
              ],
              "type": "leaf"
            },
            "d": {
              "type": "object",
              "validators": [
                "object"
              ],
              "properties": {
                "e": {
                  "validators": [
                    "integer",
                    "min:5"
                  ],
                  "type": "leaf"
                },
                "f": {
                  "validators": [
                    "string"
                  ],
                  "type": "leaf"
                }
              }
            }
          }
        }
      },


    # item 2
    {
        "a": {
            "type": "array",
            "validators": [
                "array"
            ],
            "items": {
                "type": "object",
                "validators": [
                    "object"
                ],
                "properties": {
                    "b": {
                        "type": "object",
                        "validators": [
                            "object"
                        ],
                        "properties": {
                            "c": {
                                "type": "leaf",
                                "validators": []
                            },
                            "d": {
                                "type": "leaf",
                                "validators": []
                            }
                        }
                    }
                }
            }
        }
    },

    # item 3
    {
        "a": {
            "type": "array",
            "validators": [
                "array"
            ],
            "items": {
                "type": "object",
                "validators": [
                    "object"
                ],
                "properties": {
                    "b": {
                        "type": "leaf",
                        "validators": [
                            "string"
                        ]
                    }
                }
            }
        },
        "x": {
            "properties": {
                "y": {
                    "type": "object",
                    "validators": [
                        "object"
                    ],
                    "properties": {
                        "z": {
                            "type": "leaf",
                            "validators": [
                                "integer",
                                "max:23"
                            ]
                        }
                    }
                }
            }
        }
    },
]
