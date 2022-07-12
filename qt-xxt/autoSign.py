from itertools import count
import sys
from PyQt5.QtWidgets import *
from PyQt5.uic import *
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
import mainw


class ui_as(QWidget):
    def get2id():
        import mainw
        se = mainw.getSession()
        import requests
        html = se.get("https://mooc1-2.chaoxing.com/visit/courses")
    # # print(html.text)
    #     from bs4 import BeautifulSoup
    #     soup = BeautifulSoup(html.text, 'lxml')
    #     data = soup.select(
    #         "body > div > div > div.ulDiv > ul > li > div > h3 > a")
        # url = []
        # for i in data:
        #     url.append(
        #         "https://mooc1-2.chaoxing.com{}".format(i['href']))
        # print(url)

        # url="https://mooc1-2.chaoxing.com/visit/stucoursemiddle?courseid=204253603&clazzid=8427614&vc=1&cpi=92859105"
        "http://mobilelearn.chaoxing.com/v2/apis/active/student/activelist?fid=0&courseId=223349305&classId=52412489"
        import re
        courseid = re.findall(r'courseid=[0-9]+', html.text)
        clazzid = re.findall(r'clazzid=[0-9]+', html.text)
        # print(len(clazzid)==len(courseid))
        dic = dict(zip(courseid, clazzid))
        return dic

    def getactivityid(courseid, clazzid):
        clazzid = "clazzid=52412489"
        courseid = "courseid=223349305"
        url = "http://mobilelearn.chaoxing.com/v2/apis/active/student/activelist?fid=0&{0}&{1}".format(
            courseid, clazzid)
        url = url.replace("clazzid", "classId")
        url = url.replace("courseid", "courseId")
        import requests
        import mainw
        se = mainw.getSession()
        html = se.get(url)
        live=html.text.split('"status":2')[0]
        # print(html.text.count('"status":1'))
        import re
        a_l=re.findall(r'"id":[0-9]+',live)
        print(a_l)

        # print(url)

    def __init__(self):
        super().__init__()
        self.setWindowTitle("例子")
        self.resize(300, 270)
        layout = QVBoxLayout()
        listView = QListView()  # 创建一个listview对象
        slm = QStringListModel()  # 创建mode
        self.qList = ['Item 1', 'Item 2', 'Item 3', 'Item 4']  # 添加的数组数据
        slm.setStringList(self.qList)  # 将数据设置到model
        listView.setModel(slm)  # 绑定 listView 和 model
        # listView.clicked.connect(self.clickedlist)		 #listview 的点击事件
        layout.addWidget(listView)  # 将list view添加到layout
        self.setLayout(layout)  # 将lay 添加到窗口


if __name__ == "__main__":
    app = QApplication(sys.argv)
    win = ui_as()
    win.show()
    sys.exit(app.exec_())
