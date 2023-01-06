import sys
from threading import Thread
import threading
from time import pthread_getcpuclockid
import time
import os
import os.path
from PyQt5.QtWidgets import *
from PyQt5.uic import *
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
from requests import request
import requests
import requests_toolbelt
import login
from pathlib import Path
from bs4 import BeautifulSoup
from concurrent.futures import ProcessPoolExecutor, as_completed
import download
import shutil

def rm(path):
    shutil.rmtree(path)

def getSession():
    headers={"User-Agent":
	"Mozilla/5.0 (X11; Linux x86_64; rv:91.0) Gecko/20100101 Firefox/91.0"}
    se=requests.session()
    se.headers=headers
    se.cookies=login.read_cookies()
    return se

class customQListWidgetItem(QListWidgetItem):
    def __init__(self, name, img):
        super().__init__()
        # 自定义item中的widget 用来显示自定义的内容
        self.widget = QWidget()
        # 用来显示name
        self.nameLabel = QLabel()
        self.nameLabel.setText(name)
        self.nameLabel.setStyleSheet("color:rgb(84,255,159,255);font-size:25px;font-weight:bold;font-family:Roman times;")
        # 用来显示avator(图像)
        self.avatorLabel = QLabel()
        se=getSession()
        # image=se.get(img).content
        # photo=QPixmap()
        # photo.loadFromData(image)
        # 设置图像源 和 图像大小
        # img=img.split("/")[-1]
        # img="images/{}".format(img)
        print(img)
        self.avatorLabel.setPixmap(QPixmap(img).scaled(270,160))
        # 设置布局用来对nameLabel和avatorLabel进行布局
        self.hbox = QHBoxLayout()
        self.hbox.addWidget(self.avatorLabel)
        self.hbox.addWidget(self.nameLabel)
        self.hbox.addStretch(1)
        # 设置widget的布局
        self.widget.setLayout(self.hbox)
        # 设置自定义的QListWidgetItem的sizeHint，不然无法显示
        self.setSizeHint(self.widget.sizeHint())

def getCourseList():
    se=getSession()
    html=se.get("https://mooc1-2.chaoxing.com/visit/courses")
    # print(html.text)
    soup=BeautifulSoup(html.text,'lxml')
    data=soup.select("body > div > div > div.ulDiv > ul > li > div > h3 > a")
    course_name=[]
    course_image=[]
    img_data=soup.select("body > div > div > div.ulDiv > ul > li > div.Mcon1img.httpsClass > a > img")
    # print(img_data)
    for item in img_data:
        course_image.append(item['src'])
    for item in data:
        # print(item['href'])
        course_name.append(item.get_text())
        # print(item.get_text())
    return course_name, course_image


class mainW(QMainWindow):

    def makeItem(self,name,img):
        # name,img=getCourseList()
        if "https" in str(img):
            image=img
        else:
            image="https://mooc1-2.chaoxing.com/images/img_default.png"
        print(image)
        download.downCourseImage(image)
        image="images/{}".format(image.split('/')[-1])
        item=customQListWidgetItem(name,image)
        self.courseList.addItem(item)
        self.courseList.setItemWidget(item, item.widget)
    
    # def reset():
    #     # self.courseList.clear()
    #     rm("images")
        

    def courselist_init(self):
        pool=ProcessPoolExecutor(max_workers=10)
        name,img=getCourseList()
        num=len(name)
        path="images"
        if not os.path.exists(path):
            # bar=pyqtbar()
            for i in range(num-1):
                pool.submit(self.makeItem(name[i],img[i]),i)
                # bar.set_value(i,num,i)
            pool.shutdown()
            # bar.close
        else:
            for i in range(num-1):
                image="images/{}".format(img[i].split('/')[-1])
                item=customQListWidgetItem(name[i],image)
                self.courseList.addItem(item)
                self.courseList.setItemWidget(item, item.widget)
        # self.courseList.itemClicked.connect(lambda item: print(item.nameLabel.text()))

    def update(self):
        self.courseList.clear()
        threading.Thread(target={
        shutil.rmtree("images")
        })
        self.setWindowTitle("刷新中！")
        self.courselist_init()
        self.setWindowTitle("刷新完成！")

    # def autosign(self):

        

    def __init__(self):
        super().__init__()
        self = loadUi("mainwindow.ui", self)
        self.refesh_action.triggered.connect(self.update)
        # self.autosign_action.triggered.connect(self.autosign)
        self.courselist_init()
        self.show()

    
   

        
def main():
    app = QApplication(sys.argv)
    path="info.txt"
    if os.path.exists("info.txt"):
        with open("info.txt",'r') as f:
            l=f.readlines()
            uname=l[0].strip("\n")
            pwd=l[1].strip("\n")
            if login.login(uname,pwd):
                MainWindow=mainW()
    else:
        MainWindow=login.login_ui()
    # import requests
    # import login
    # cookies = login.read_cookies()
    # data = requests.get("www.chaoxing.com").cookies(cookies)
    # print(data.text)
    
    
    # # MainWindow=ui_couseList()
    # # name,img=getCourseList()
    # # item=customQListWidgetItem(name[0],"1.png")
    # # item1=customQListWidgetItem(name[1],"1.png")
    # # MainWindow.addItem(item)
    # # MainWindow.setItemWidget(item,item.widget)
    sys.exit(app.exec_())
    
if __name__ == '__main__':
    main()