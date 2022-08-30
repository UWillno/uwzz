from PyQt6.QtWidgets import QFileDialog, QDialog, QVBoxLayout, QLabel, QHBoxLayout, QLineEdit, QPushButton, QCheckBox

from HttpToXXT_uw import save_info


class SignInfoDialog(QDialog):

    def __init__(self, xxt):
        super().__init__()
        # if xxt.sign_info is None:
        #     sign_info = []
        # self.sign_info = sign_info
        # print(xxt.sign_info)
        self.xxt = xxt
        self.setWindowTitle("设置签到信息")
        vbox = QVBoxLayout()
        hbox1 = QHBoxLayout()
        hbox2 = QHBoxLayout()
        hbox3 = QHBoxLayout()
        label1 = QLabel("随意位置签到地址：")
        self.locate_text_lineedit = QLineEdit()
        label2 = QLabel("经度：")
        self.lon_lineedit = QLineEdit()  # 经度
        self.lat_lineedit = QLineEdit()  # 纬度
        self.label_photo = QLabel("当前拍照签到图片:{}".format(xxt.sign_info[3]))
        label3 = QLabel("纬度：")
        hbox1.addWidget(label1)
        hbox1.addWidget(self.locate_text_lineedit)
        hbox2.addWidget(label2)
        hbox2.addWidget(self.lon_lineedit)
        hbox3.addWidget(label3)
        hbox3.addWidget(self.lat_lineedit)
        # vbox1 = QVBoxLayout()
        button_photo = QPushButton("更换")
        # vbox1.addWidget(self.label_photo)
        # vbox1.addWidget(button_photo)
        button_ok = QPushButton("保存")
        hbox4 = QHBoxLayout()
        hbox4.addWidget(button_photo)
        hbox5 = QHBoxLayout()
        self.checkbox_skip = QCheckBox("跳过二维码签到")
        vbox.addLayout(hbox1)
        vbox.addLayout(hbox2)
        vbox.addLayout(hbox3)
        vbox.addWidget(self.label_photo)
        vbox.addLayout(hbox4)
        label_ex = QLabel("不选择或取消选择图片时，将会不提交图片完成拍照签到")
        label_ex.setStyleSheet("color:red")
        vbox.addWidget(label_ex)
        hbox5.addWidget(self.checkbox_skip)
        hbox5.addWidget(button_ok)
        vbox.addLayout(hbox5)

        # if xxt.sign_info:
        self.locate_text_lineedit.setText(xxt.sign_info[0])
        self.lon_lineedit.setText(xxt.sign_info[1])
        self.lat_lineedit.setText(xxt.sign_info[2])
        # self.sign_info_dialog.accept()
        self.setLayout(vbox)
        self.show()
        self.setStyleSheet('color:green')
        self.setWindowOpacity(0.9)
        if self.xxt.qr_skip:
            self.checkbox_skip.setChecked(True)
        else:
            self.checkbox_skip.setChecked(False)
        button_ok.clicked.connect(self.update_sign_info)
        button_photo.clicked.connect(self.select_photo)

    def update_sign_info(self):
        # print(self.lon_lineedit.text())

        # if not xxt.sign_info:
        #     xxt.sign_info.append(self.locate_text_lineedit.text())
        #     xxt.sign_info.append(self.lon_lineedit.text())
        #     xxt.sign_info.append(self.lat_lineedit.text())
        # else:
        # print(xxt.sign_info)
        self.xxt.sign_info[0] = self.locate_text_lineedit.text()
        self.xxt.sign_info[1] = self.lon_lineedit.text()
        self.xxt.sign_info[2] = self.lat_lineedit.text()
        if self.checkbox_skip.isChecked():
            self.xxt.qr_skip = True
        else:
            self.xxt.qr_skip = False
        save_info(self.xxt.username, self.xxt.password, self.xxt.sign_info[0], self.xxt.sign_info[1],
                  self.xxt.sign_info[2],
                  self.xxt.sign_info[3], self.xxt.qr_skip)
        if self.xxt.sign_info[3] != "":
            self.xxt.upload2pan()
        self.close()
        # print(xxt.sign_info)

    def select_photo(self):
        file_dir, ok = QFileDialog.getOpenFileName(self, "选择照片", 'C:/')
        if ok:
            # print(file_dir)
            self.xxt.sign_info[3] = file_dir
            # print( self.xxt.sign_info[3])
            self.label_photo.setText("当前拍照签到图片为:{}".format(self.xxt.sign_info[3]))
        # self.xxt.sign_info[3] = file_dir[0]
