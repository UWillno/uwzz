from PyQt6.QtWidgets import QLabel, QDialog, QLineEdit, QHBoxLayout, QVBoxLayout, QPushButton, QMessageBox

from HttpToXXT_uw import HttpToXXT


class LoginDialog(QDialog):
    def __init__(self):
        super(LoginDialog, self).__init__()
        self.setWindowTitle("登录")
        label1 = QLabel("账号：")
        label2 = QLabel("密码：")
        self.lineedit_username = QLineEdit()
        self.lineedit_password = QLineEdit()
        self.lineedit_password.setEchoMode(QLineEdit.EchoMode.Password)
        hbox1 = QHBoxLayout()
        hbox1.addWidget(label1)
        hbox1.addWidget(self.lineedit_username)
        hbox2 = QHBoxLayout()
        hbox2.addWidget(label2)
        hbox2.addWidget(self.lineedit_password)
        vbox = QVBoxLayout()
        button_ok = QPushButton("登录")
        vbox.addLayout(hbox1)
        vbox.addLayout(hbox2)
        vbox.addWidget(button_ok)
        self.setLayout(vbox)
        self.setStyleSheet('color:green')
        self.setWindowOpacity(0.9)
        button_ok.clicked.connect(self.login)
        # self.xxt=HttpToXXT()

    def login(self):
        username = self.lineedit_username.text().strip()
        password = self.lineedit_password.text().strip()
        self.xxt = HttpToXXT(username, password)
        if self.xxt.login():
            QMessageBox.information(self, "信息", "登录成功！请重启程序！")
            # os.execv()
            self.close()
        else:
            QMessageBox.warning(self, "错误", "账号或密码错误！")
