import os
import sys
from unittest import result
from PyQt5.QtWidgets import *
from PyQt5.uic import *
import json
import requests
import mainw


def write_cookies(cookies):
    cookies = requests.utils.dict_from_cookiejar(cookies)
    with open("cookies.txt", "w+") as f:
        f.write(json.dumps(cookies))


def read_cookies():
    # import json
    with open("cookies.txt", "r") as f:
        load_cookies = json.loads(f.read())
    # import requests
    # import login
    # cookies = requests.utils.cookiejar_from_dict(load_cookies)
    # data = requests.get(
    #     "https://mooc2-ans.chaoxing.com/mycourse/stu?courseid=226652311&clazzid=59181264&cpi=92859105&enc=bd233c51bc06b2300c59c860bd510ad1&t=1657118607886&pageHeader=0&v=2", cookies=cookies)
    # print(data.text)
    return requests.utils.cookiejar_from_dict(load_cookies)


def login(uname, pwd):
    uname = str(uname)
    pwd = str(pwd)
    # import requests
    # uname = "123123123"
    # pwd = "123123123"
    url = "https://passport2-api.chaoxing.com/v11/loginregister?cx_xxt_passport=json&uname={0}&code={1}".format(
        uname, pwd)
    html = requests.get(url)
    if "验证通过" in html.text:
        print("登录成功")
        save_info(uname, pwd)
        write_cookies(html.cookies)
        return True
    else:
        print("登录失败")
        return False


def save_info(uname, pwd):
    # conn=sqlite3.connect("info.db")
    with open("info.txt", "w+") as f:
        f.write(uname+"\n"+pwd)


class login_ui(QDialog):
    def __init__(self):
        super().__init__()
        self = loadUi("login.ui", self)
        self.show()

    def accept(self) -> None:
        username = self.usernameEdit.text()
        password = self.passwordEdit.text()
        # print(username)
        # print(password)
        if login(username, password):
            self.close()
            QMessageBox(QMessageBox.Information,"登录成功","请重新启动程序").exec()
        else:
            QMessageBox(QMessageBox.Critical, '错误', '帐号或密码错误').exec()
        # return super().accept()
