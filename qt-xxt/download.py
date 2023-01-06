from distutils import core
from importlib.resources import path
from urllib import response
import requests
import mainw
import mainw

def downCourseImage(url):
    se=mainw.getSession()
    import requests
    # url="https://p.ananas.chaoxing.com/star3/270_160c/7a8997b8c96730d25f13bf51a9c6c197.png"
    response=se.get(url)
    name=url.split('/')[-1]
    import os
    path="images"
    if not os.path.exists(path):
        os.mkdir(path)
    with open("images/{}".format(name),"wb") as code:
        code.write(response.content)
    # print(name)
