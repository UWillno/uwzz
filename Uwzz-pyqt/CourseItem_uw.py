import random
import re
import time
from urllib.parse import quote

from PyQt6.QtCore import pyqtSignal, QThread
from PyQt6.QtGui import QMovie
from PyQt6.QtWidgets import QListWidgetItem, QLabel, QCheckBox, QHBoxLayout, QWidget, QPushButton, QDialog, \
    QLineEdit, QVBoxLayout


class CourseItem(QListWidgetItem):
    def __init__(self, name, course_id, class_id, xxt):
        super().__init__()
        self.thread = None
        self.sign_detail = ""
        # self.mw = mainwindow
        self.list_activities = []
        self.widget = QWidget()
        self.class_id = class_id
        self.course_id = course_id
        self.xxt = xxt
        self.name = name
        self.session = xxt.session
        self.label = QLabel()
        self.label.setText(self.name)
        self.checkbox = QCheckBox()
        # self.label_sign_detail = QLabel()
        # self.label_sign_detail.setScaledContents(True)
        # movie = QMovie("hm.gif")
        # movie.setScaledSize(QSize())
        # # self.label_sign_detail.setSpeed(444)
        # self.label_sign_detail.setMovie(movie)
        # movie.start()
        self.label_sign_detail = QLabel("自动签到中……")
        self.label_sign_detail.setStyleSheet("text-decoration:underline;color:blue;")
        self.label_sign_detail.setVisible(False)
        self.hbox = QHBoxLayout()
        self.hbox.addWidget(self.checkbox)
        self.hbox.addWidget(self.label)
        self.hbox.addWidget(self.label_sign_detail)
        self.checkbox.setVisible(False)
        self.widget.setLayout(self.hbox)
        self.setSizeHint(self.widget.sizeHint())



        # global course_list
        # course_list.append(self)
        # print(course_list)

    def select_items(self):
        self.checkbox.setVisible(True)
        # self.get_activities()

    def get_activities(self):
        url = "http://mobilelearn.chaoxing.com/v2/apis/active/student/activelist?fid=0&courseId={0}&classId={1}".format(
            self.course_id, self.class_id)
        # print(url)
        html = self.session.get(url)
        if "请勿频繁操作" in html.text:
            # print("频繁操作")
            if self.thread is not None:
                self.xxt.autosign_detail += str(time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime()))
                sleep_time=random.randint("240,600")
                self.xxt.autosign_detail += "频繁操作，线程休眠{}分钟\n".format(str(sleep_time/60))
                self.thread.sleep(sleep_time)
        else:
            exist = html.text.rfind('"status":1')
            # self.label_sign_detail.mouseReleaseEvent = self.show_sign_detail
            if exist != -1:
                live = html.text[:exist]
                self.list_activities = re.findall(r'"id":[0-9]+', live)
                # if self.list_activities:
                # self.list_activities.pop()
                self.list_activities = [i.split(":")[1] for i in self.list_activities]
            else:
                pass
                # self.get_activities()
                # print("课程无有效活动！")
                # self.list_activities = []
                # self.
            self.autosign()

        # if '"status":2' in html.text:
        #     live = html.text.split('"status":2')[0]
        #     self.list_activities = re.findall(r'"id":[0-9]+', live)
        #     if self.list_activities:
        #         self.list_activities.pop()
        #         self.list_activities = [i.split(":")[1] for i in self.list_activities]
        #     # print(list_activities)

    def start_autosign(self):
        if self.checkbox.isChecked():
            self.label_sign_detail.setVisible(True)
            # print(self.name)
            # print(self.course_id)
            # print(self.class_id)
            self.get_activities()
            # print(self.list_activities)
            # self.autosign()
        self.checkbox.setVisible(False)

    def autosign(self):
        if self.thread is not None:
            pass
        else:
            self.thread = AutosignThread(self)
            self.thread.qr_signal.connect(self.show_qr_sign_dialog)
            self.thread.start()
        # while len(self.list_activities) != 0:
        # for id in self.list_activities:
        #     activity = self.session.get(
        #         "https://mobilelearn.chaoxing.com/widget/sign/pcStuSignController/preSign?activeId={}".format(id))
        #     print("https://mobilelearn.chaoxing.com/widget/sign/pcStuSignController/preSign?activeId={}".format(id))
        #     if "签到成功" in activity.text:
        #         self.list_activities.remove(id)
        #         continue
        #     elif "手势" in activity.text or "签到码" in activity.text:
        #         self.static_sign(id)
        #     elif "位置" in activity.text:
        #         print("位置签到")
        #     elif "扫码" in activity.text:
        #         print("二维码签到")
        #     elif "签到码" in activity.text:
        #         print("签到码签到")
        #     else:
        #         print("未知签到")

    # print(self.sign_detail)

    def static_sign(self, id):
        # 手势 或 签到码签到
        html = self.session.get("https://mobilelearn.chaoxing.com/v2/apis/sign/signIn?activeId={}".format(id))
        # if "签到成功" in self.session.get(
        #         "https://mobilelearn.chaoxing.com/v2/apis/sign/signIn?activeId={}".format(id)).text:
        # print("https://mobilelearn.chaoxing.com/v2/apis/sign/signIn?activeId={}".format(id))
        activity = self.session.get(
            "https://mobilelearn.chaoxing.com/widget/sign/pcStuSignController/preSign?activeId={}".format(id))
        if "签到成功" in activity.text:
            self.xxt.autosign_detail += self.name
            self.xxt.autosign_detail += str(time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime()))
            self.xxt.autosign_detail += "手势或数字码签到\n"
            self.list_activities.remove(id)
        else:
            self.xxt.autosign_detail += self.name
            self.xxt.autosign_detail += str(time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime()))
            self.xxt.autosign_detail += "失败：软件可能失效\n"

    def pre_sign(self, id):
        url = "https://mobilelearn.chaoxing.com/newsign/preSign?courseId={0}&classId={1}&activePrimaryId={2}&general=1&sys=1&ls=1&appType=15&&tid=&uid={3}&ut=s".format(
            self.course_id, self.class_id, id, self.xxt.uid)
        # print(url)
        self.session.get(url)

    def show_qr_sign_dialog(self, id):
        # print(id)
        if not self.xxt.qr_skip:
            self.dialog = QDialog()
            self.dialog.setWindowTitle("{}的二维码签到".format(self.name))
            label = QLabel("请扫描二维码并输入ENC部分内容：")
            lineedit_enc = QLineEdit()
            vbox = QVBoxLayout()
            vbox.addWidget(label)
            vbox.addWidget(lineedit_enc)
            hbox = QHBoxLayout()
            button_yes = QPushButton("确定")
            button_skip = QPushButton("跳过所有二维码签到")
            hbox.addWidget(button_yes)
            hbox.addWidget(button_skip)
            vbox.addLayout(hbox)
            button_yes.clicked.connect(lambda: self.qr_sign(id, lineedit_enc.text()))
            button_skip.clicked.connect(lambda: self.skip_all_qr_sign(self.dialog))
            self.dialog.setLayout(vbox)
            self.dialog.setStyleSheet('color:green')
            self.dialog.setWindowOpacity(0.9)
            self.dialog.show()
        else:
            pass
            # print("已跳过二维码签到！！！！！")

    def skip_all_qr_sign(self, dialog):
        self.xxt.qr_skip = True
        dialog.close()
        # print(self.xxt.qr_skip)

    def qr_sign(self, id, enc):
        enc = enc.strip()
        self.dialog.destroy()
        self.pre_sign(id)
        url = "https://mobilelearn.chaoxing.com/pptSign/stuSignajax?enc={0}&name={1}&activeId={2}&uid={3}&clientip=&useragent=&latitude=-1&longitude=-1&fid=0&appType=15".format(
            enc, quote(self.xxt.name), id, self.xxt.uid)
        # print(url)
        html = self.session.get(url)
        if "success" in html.text:
            self.xxt.autosign_detail += self.name
            self.xxt.autosign_detail += str(time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime()))
            self.xxt.autosign_detail += "二维码签到\n"
            self.list_activities.remove(id)
        else:
            self.xxt.autosign_detail += self.name
            self.xxt.autosign_detail += str(time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime()))
            self.xxt.autosign_detail += "失败：软件可能失效\n"
        # print(enc)

    def photo_sign(self, id):
        self.pre_sign(id)
        if self.xxt.sign_info[3] == "":
            url = "https://mobilelearn.chaoxing.com/pptSign/stuSignajax?activeId={}".format(id)
        else:
            url = "https://mobilelearn.chaoxing.com/pptSign/stuSignajax?activeId={0}&objectId={1}".format(id,
                                                                                                          self.xxt.photo)
        # print(url)
        html = self.session.get(url)
        if "success" in html.text:
            self.xxt.autosign_detail += self.name
            self.xxt.autosign_detail += str(time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime()))
            self.xxt.autosign_detail += "拍照签到\n"
            self.list_activities.remove(id)
        else:
            self.xxt.autosign_detail += self.name
            self.xxt.autosign_detail += str(time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime()))
            self.xxt.autosign_detail += "失败：软件可能失效\n"
        # print("拍照签到")
        # print(html.text)

    def locate_sign(self, id):
        # 获取指定签到的位置
        url = "https://mobilelearn.chaoxing.com/v2/apis/active/getPPTActiveInfo?activeId={}".format(id)
        # print(url)
        html = self.session.get(url)
        # print(html.text)
        location_text = re.findall(r'\"locationText":\".*?\"', html.text)
        location_longitude = re.findall(r'\"locationLongitude\":\".*?\"', html.text)
        location_latitude = re.findall(r'\"locationLatitude\":\".*?\"', html.text)
        # print(location_latitude)
        # print(location_longitude)
        # print(location_text)
        if location_latitude != [] and location_longitude != [] and location_text != []:
            text = location_text[0].split('"')[-2]
            # print(text)
            lon = location_longitude[0].split('"')[-2]
            # print(lon)
            lat = location_latitude[0].split('"')[-2]
            # print(lat)
            self.pre_sign(id)
            url1 = "https://mobilelearn.chaoxing.com/pptSign/stuSignajax?address={0}&activeId={1}&latitude={2}&longitude={3}&fid=0&appType=15&ifTiJiao=1".format(
                quote(text), id, lat, lon)
            # print(url1)
            html1 = self.session.get(url1)
            # print(html1.text)
            # print("指定位置签到成功？")
            if "success" in html1.text:
                self.xxt.autosign_detail += self.name
                self.xxt.autosign_detail += str(time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime()))
                self.xxt.autosign_detail += "指定位置签到\n"
                self.list_activities.remove(id)
            else:
                self.xxt.autosign_detail += self.name
                self.xxt.autosign_detail += str(time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime()))
                self.xxt.autosign_detail += "失败：软件可能失效\n"
        else:
            text = self.xxt.sign_info[0]
            # print(text)
            lon = self.xxt.sign_info[1]
            # print(lon)
            lat = self.xxt.sign_info[2]
            # print(lat)
            self.pre_sign(id)
            url1 = "https://mobilelearn.chaoxing.com/pptSign/stuSignajax?address={0}&activeId={1}&latitude={2}&longitude={3}&fid=0&appType=15&ifTiJiao=1".format(
                quote(text), id, lat, lon)
            # print(url1)
            html1 = self.session.get(url1)
            # print(html1.text)
            # print("指定位置签到成功？")
            if "success" in html1.text:
                self.xxt.autosign_detail += self.name
                self.xxt.autosign_detail += str(time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime()))
                self.xxt.autosign_detail += "随意位置签到\n"
                self.list_activities.remove(id)
            else:
                self.xxt.autosign_detail += self.name
                self.xxt.autosign_detail += str(time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime()))
                self.xxt.autosign_detail += "失败：软件可能失效\n"
            # print("随意位置签到！")

    # self.pre_sign(id)
    # print(url0)
    # print()

    # def show_sign_detail(self):
    #     self.mw.show_sign_detail(self.sign_detail)


# class DetailLabel(QLabel):
#     clicked_signal = pyqtSignal()
#
#     def __init__(self):
#         super().__init__()
#
#     def mouseReleaseEvent(self, QMouseEvent):
#         self.clicked_signal.emit()
#
#     def connect_customized_slot(self, func):
#         self.clicked_signal.connect(func)


class AutosignThread(QThread):
    qr_signal = pyqtSignal(str)

    def __init__(self, course):
        super(AutosignThread, self).__init__()
        self.course = course

    def run(self):
        while True:
            self.course.get_activities()
            if self.course.list_activities:
                for aid in self.course.list_activities:
                    self.sleep(random.randint(1, 10))
                    activity = self.course.session.get(
                        "https://mobilelearn.chaoxing.com/widget/sign/pcStuSignController/preSign?activeId={}".format(
                            aid))
                    # print("https://mobilelearn.chaoxing.com/widget/sign/pcStuSignController/preSign?activeId={}".format(
                    #     aid))
                    if "签到成功" in activity.text:
                        self.course.list_activities.remove(aid)
                        continue
                    elif "手势" in activity.text or "签到码" in activity.text:
                        self.course.static_sign(aid)
                    elif "位置" in activity.text:
                        self.course.locate_sign(aid)
                        # print("位置签到")
                    elif "扫码" in activity.text:
                        # print("二维码签到")
                        self.qr_signal.emit(aid)
                    elif "拍照" in activity.text:
                        # print("拍照签到")
                        self.course.photo_sign(aid)
                    else:
                        pass
                        # print("非签到活动")
            self.sleep(random.randint(20, 40))
