
from itertools import count
import sys
from PyQt5.QtWidgets import *
from PyQt5.uic import *
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
import mainw

class ui_as(QWidget):
    a=["asd"]

    def getactivityid(self,courseid="0", clazzid="0"):
            
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
        a_l.pop()
        uid=se.get("https://sso.chaoxing.com/apis/login/userLogin4Uname.do").text
        # print(uid)
        # if re.findall(r'"puid":[0-9]+',uid)[0].split(":")[1]:
        # uid = re.findall(r'"puid":[0-9]+',uid)[0].split(":")[1]
        uid = "0"


        print(a_l)
        clazzid=clazzid.split("=")[1]
        courseid=courseid.split("=")[1]
        for id in a_l:
            # print("https://mobilelearn.chaoxing.com/widget/sign/pcStuSignController/preSign?activeId="+str(id))
            id=id.split(":")[1]
            act=se.get("https://mobilelearn.chaoxing.com/widget/sign/pcStuSignController/preSign?activeId="+id)
            # print(act.text)
            # if "手势签到" in act.text:
            #     print(id+"未签到")
            presignurl=""
            if "签到成功" in act.text:
                continue
            elif "手势" in act.text:
                se.get("https://mobilelearn.chaoxing.com/v2/apis/sign/signIn?activeId="+id)
                self.qlist.add("adsasd")
                print("手势签到")
            elif "位置" in act.text:
                url0 ="https://mobilelearn.chaoxing.com/newsign/preSign?${0}&${1}&activePrimaryId=${2}&general=1&sys=1&ls=1&appType=15&&tid=&uid=${3}&ut=s".format(courseid,clazzid,id,uid)
                
                print("位置签到")
            elif "扫码" in act.text:
                print("二维码签到")
            elif "签到码" in act.text:
                print ("签到码签到")
                se.get("https://mobilelearn.chaoxing.com/v2/apis/sign/signIn?activeId="+id)
            else:
                print("未知签到")
     #  val url0 ="https://mobilelearn.chaoxing.com/newsign/preSign?$courseId&$classId&activePrimaryId=$paid&general=1&sys=1&ls=1&appType=15&&tid=&uid=$uid&ut=s"
            


        # print(url)

    
    def __init__(self):
        super().__init__()
        self.setWindowTitle("例子")
        self.resize(300, 270)
        layout = QVBoxLayout()
        listView = QListView()  # 创建一个listview对象
        slm = QStringListModel()  # 创建mode
        self.qList = self.a  # 添加的数组数据
        slm.setStringList(self.qList)  # 将数据设置到model
        listView.setModel(slm)  # 绑定 listView 和 model
        # listView.clicked.connect(self.clickedlist)		 #listview 的点击事件
        layout.addWidget(listView)  # 将list view添加到layout
        self.setLayout(layout)  # 将lay 添加到窗口
        self.getactivityid(self)

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

    


if __name__ == "__main__":
    app = QApplication(sys.argv)
    win = ui_as()
    win.show()
    sys.exit(app.exec_())
