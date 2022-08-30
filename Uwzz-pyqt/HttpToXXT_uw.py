import re
from urllib.parse import quote

import requests
from bs4 import BeautifulSoup
from CourseItem_uw import CourseItem


def save_info(username, password, text, lon, lat, dir, skip):
    # conn=sqlite3.connect("info.db")
    with open("info.txt", "w+") as f:
        f.write("{0}\n{1}\n{2}\n{3}\n{4}\n{5}\n{6}".format(username, password, text, lon, lat, dir, skip))


class HttpToXXT:
    course_list = []
    autosign_detail = ""

    def __init__(self, username=None, password=None, text=None, lon=None, lat=None, dir=None, skip=None):
        self.name = None
        self.qr_skip = False
        self.photo = None
        self.sign_info = ["武汉", "44", "44", ""]
        if text is not None and lon is not None and lat is not None and dir is not None and skip is not None:
            self.sign_info[0] = text
            self.sign_info[1] = lon
            self.sign_info[2] = lat
            self.sign_info[3] = dir
            self.qr_skip = skip
        self.uid = None
        self.session = None
        self.cookies = None
        self.username = username
        self.password = password
        if self.login():
            # print("登录成功！")
            self.init_user()
            self.get2id()
        else:
            pass

    def login(self):
        url = "https://passport2-api.chaoxing.com/v11/loginregister?cx_xxt_passport=json&uname={0}&code={1}".format(
            quote(self.username), quote(self.password))
        html = requests.get(url)
        if "验证通过" in html.text:
            # print("登录成功")
            cookies = html.cookies
            headers = {"User-Agent":
                           "Mozilla/5.0 (X11; Linux x86_64; rv:91.0) Gecko/20100101 Firefox/91.0"}
            self.session = requests.session()
            self.session.headers = headers
            self.session.cookies = html.cookies
            save_info(self.username, self.password, self.sign_info[0], self.sign_info[1], self.sign_info[2],
                      self.sign_info[3], self.qr_skip)

            # self.write_cookies(html.cookies)
            return True
        else:
            # print("登录失败")
            return False

    def init_user(self):
        html = self.session.get("https://sso.chaoxing.com/apis/login/userLogin4Uname.do")
        puid = re.findall(r'"puid":[0-9]+', html.text)
        self.uid = puid[0].split(':')[1]
        name = re.findall(r'\"name\":\".*?\"', html.text)
        self.name = name[0].split('"')[-2]
        # print(self.uid)

    def upload2pan(self):
        # data=requests.get(self.sign_info[3])
        # print(data)
        # data={}
        url = 'https://pan-yz.chaoxing.com/upload?_token=5d2e8d0aaa92e3701398035f530c4155'
        data = {"puid": "80421235"}
        # files = {'img':}
        img = {"file": (self.sign_info[3].split('/')[-1], open(self.sign_info[3], 'rb'))}
        response = requests.post(
            url=url, data=data, files=img)
        # print("照片预览：https://p.ananas.chaoxing.com/star3/origin/" +
        #       response.json().get('objectId') + ".jpg")
        # self.sign_info[3] = response.json().get('objectId') + ".jpg"
        self.photo = response.json().get('objectId') + ".jpg"
        # html=self.session.post(url,files=files)
        # print(html.txt)

    def get2id(self):
        form={"courseType":"1","courseFolderId": "0","courseFolderSize": "1"}

        html = self.session.post("https://mooc1-2.chaoxing.com/visit/courses",data=form)

        soup = BeautifulSoup(html.text, 'lxml')
        # soup = BeautifulSoup(html.text, 'html.parser')
        data = soup.select("body > div > div > div.ulDiv > ul > li > div > h3 > a")
        course_name = []
        # course_image = []
        # img_data = soup.select("body > div > div > div.ulDiv > ul > li > div.Mcon1img.httpsClass > a > img")
        # print(img_data)
        # for item in img_data:
        #     course_image.append(item['src'])
        for item in data:
            # print(item['href'])
            course_name.append(item.get_text())
            # print(item.get_text())
        # print(course_name)
        course_id = re.findall(r'courseid=[0-9]+', html.text)
        clazz_id = re.findall(r'clazzid=[0-9]+', html.text)
        # for i in range(0,len(course_id)-1,2):
        # print(len(clazz_id)==len(course_id))
        for i in range(0, len(course_name)):
            self.course_list.append(
                CourseItem(course_name[i], course_id[2 * i].split("=")[1], clazz_id[2 * i].split("=")[1], self))
        # dic = dict(zip(course_id, clazz_id))
        # print(dic)
        # for i in range(0, len(dic)):
        #     # print(course_name[i]+str(course_id[i])+str(clazz_id[i]))
        #     self.course_list.append(
        #         CourseItem(course_name[i], course_id[i].split("=")[1], clazz_id[i].split("=")[1], self))

        # return dic

    # def get_course_list(self):
    #     html = self.session.get("https://mooc1-2.chaoxing.com/visit/courses")
    #     # print(html.text)
    #     soup = BeautifulSoup(html.text, 'lxml')
    #     data = soup.select("body > div > div > div.ulDiv > ul > li > div > h3 > a")
    #     course_name = []
    #     course_image = []
    #     img_data = soup.select("body > div > div > div.ulDiv > ul > li > div.Mcon1img.httpsClass > a > img")
    #     # print(img_data)
    #     # for item in img_data:
    #     #     course_image.append(item['src'])
    #     for item in data:
    #         # print(item['href'])
    #         course_name.append(item.get_text())
    #         # print(item.get_text())
    #     return course_name, course_image
