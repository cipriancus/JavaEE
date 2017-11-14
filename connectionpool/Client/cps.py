import requests
import random
import string
import threading
import time

barrier = threading.Barrier(50)

def send_request(id):
    barrier.wait()

    url_str = 'http://localhost:8080/pool?method=cps'

    N = 8

    key = ''.join(random.choices(string.ascii_uppercase + string.digits, k=N))

    value = ''.join(random.choices(string.ascii_uppercase + string.digits, k=N))

    data_str = {
        'key': key,
        'value': value
    }

    with requests.session() as s:
        s.get(url_str)
        response = s.post(url=url_str, data=data_str)
        print(response.text)


def barrier_threads():
    t = []
    for i in range(0, 100):
        t += [threading.Thread(target=send_request,args=(i,))]

    for iterator in t:
        iterator.start()

    for iterator in t:
        iterator.join()


start_time=time.time()* 1000
barrier_threads()
print("Over all execution is ",int(time.time()* 1000-start_time))
