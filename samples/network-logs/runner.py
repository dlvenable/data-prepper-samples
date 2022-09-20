#!/usr/bin/env python

import os

for n in range(20):
    network_log_file = open("fluent-bit/logs/sample.log", "a")

    network_log_file.write("127.0.0.1 10.0.0128 29855 2356" + os.linesep)
    network_log_file.write("127.0.0.1 10.0.0128 29855 7897" + os.linesep)
    network_log_file.flush()

network_log_file.close()
