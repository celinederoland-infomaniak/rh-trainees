from tests_data import *
from utils import expand_validator, printj

# compare the test inputs with the wanted output
for i in range(len(data_in)):
    print("Test n°{} --> {}".format(i+1, data_out[i] == expand_validator(data_in[i])))
