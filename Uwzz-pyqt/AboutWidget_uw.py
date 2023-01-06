# from PyQt6.QtWidgets import QWidget
import webbrowser

from PyQt6.QtCore import QObject, pyqtSlot


class AboutWidget(QObject):
    def __init__(self):
        super().__init__()


    @pyqtSlot()
    def view_gitee_url(self):
        webbrowser.open("https://gitee.com/uwillno/uwzz", new=0, autoraise=True)

    @pyqtSlot()
    def view_qq_url(self):
        webbrowser.open("https://jq.qq.com/?_wv=1027&k=lkdxXTcq", new=0, autoraise=True)
