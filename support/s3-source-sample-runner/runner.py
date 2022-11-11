#!/usr/bin/env python3

import sys
import time
import requests
import threading

base_url = sys.argv[1]

orders_url = "{}/orders".format(base_url)

print('Orders URL: {}'.format(orders_url))

def runner_task():
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


number_of_threads = 10

for thread_number in range(number_of_threads):
    thread = threading.Thread(target=runner_task, daemon=True)
    thread.start()

while True:
    time.sleep(60)
