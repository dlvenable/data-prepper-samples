#!/usr/bin/env python

import os
from datetime import datetime
import random
import time

datetimes = []
source_ips = []
destination_ips = []
source_ports = []

endpoints = [
    '/search',
    '/widgets/7819',
    '/widgets/4509',
    '/widgets/3008',
    '/uploads',
    '/home',
    '/user/me'
]

for n in range(10):
    datetime = datetime.now()
    source_port = long(random.choice(range(10000,59999)))
    source_ip = "{0}.{1}.{2}.{3}".format(
        random.choice(range(100, 255)),
        random.choice(range(100, 255)),
        random.choice(range(100, 255)),
        random.choice(range(100, 255)))
    destination_ip = "65.80.{0}.{1}".format(
        random.choice(range(100, 255)),
        random.choice(range(100, 255)))

    #time.sleep(0.2)

    datetimes.append(datetime)
    source_ips.append(source_ip)
    destination_ips.append(destination_ip)
    source_ports.append(source_port)


network_log_file = open("fluent-bit/logs/sample.log", "a")

for x in range(3):
    for n in range(len(datetimes)):
        remainder = ""
        if x == 0:
            remainder = "TLSv1.2 ECDHE-RSA-AES128-GCM-SHA25"
        if x == 1:
            endpoint = endpoints[random.choice(range(len(endpoints)))]
            remainder = "'GET {0}' HTTP/2.0".format(endpoint)
        if x == 2:
            remainder = "200 " + str(random.choice(range(1024, 2096)))

        datetime = datetimes[n]
        source_ip = source_ips[n]
        destination_ip = destination_ips[n]
        source_port = source_ports[n]

        iso_datetime = datetime.isoformat() + "Z"
        # TODO: Add timestamp to front
        network_log_file.write(
            "name=compute.example.org example.org {1}:{2} -> {3}:443 {4}"
                .format(iso_datetime, source_ip, source_port, destination_ip, remainder) + os.linesep)

    network_log_file.flush()

network_log_file.close()
