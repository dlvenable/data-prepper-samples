#!/usr/bin/env python

import sys
import time
import requests

base_url = sys.argv[1]

orders_url = "{}/orders".format(base_url)

print('Orders URL: {}'.format(orders_url))

while True:
    response = requests.get(orders_url)

    print('GET /orders: {}'.format(response.status_code))

    if response.status_code == 200:
        list_orders = response.json()

        print(list_orders)

        orders = list_orders['orders']

        order_ids = list(map(lambda order: order['orderId'], orders))

        for order_id in order_ids:
            order_url = "{}/{}".format(orders_url, order_id)
            requests.get(order_url)
            requests.put(order_url)
            requests.delete(order_url)

    time.sleep(0.5)
