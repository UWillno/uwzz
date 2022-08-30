import QtQuick 2.5
import QtQuick.Controls 2.5

ApplicationWindow {
    visible: true
    width: 300
    title: "关于Uwzz"

    Column {
        anchors.centerIn: parent
        Text {
            //                anchors.centerIn: parent
            width: 270
            text: "  花费几天瞎写赶工出来的，也是为了自用，本来打算复刻zazhi，实在是没工夫也没需求，qt相关的虽然想学但不利于就业，大四了，在武汉民办也没啥前途，要为日后讨饭做准备。爬虫一旦网页有些小变动可能就会失效，不要指望长期使用，我也不可能及时更新。仅供学习使用。2022/8/14"
            color:"green"
            maximumLineCount: 100
            wrapMode: Text.WrapAnywhere
        }


        Row {

            //            anchors.centerIn: parent
            Button {
                id: giteelink
                text: '<font color="green">项目链接：<u><font color="red">gitee.com/uwillno/uwzz</font></u></font>\n'
                flat: true
                onClicked: {
                    window.view_gitee_url()
                }
            }
            Button {
                id: qq
                text: '<font color="purple">交流/反馈</font>'
                flat: true
                onClicked: {
                    window.view_qq_url()
                }
            }

        }

    }
}
