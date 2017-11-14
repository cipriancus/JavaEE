import requests
import random
import string
import threading

barrier = threading.Barrier(50)


def send_request(id):
    barrier.wait()

    url_str = 'http://localhost:8080/pool?method=cp'

    N = 8

    key = ''.join(random.choices(string.ascii_uppercase + string.digits, k=N))

    value = ''.join(random.choices(string.ascii_uppercase + string.digits, k=N))

    data_str = {
        'key': key,
        'value': value
    }

    response = requests.post(url=url_str, data=data_str)
    print(response.text)


def barrier_threads():
    t = []
    for i in range(0, 100):
        t += [threading.Thread(target=send_request,args=(i,))]

    for iterator in t:
        iterator.start()

    for iterator in t:
        iterator.join()


barrier_threads()
