import QtQuick
import QtQuick.Controls
import QtQuick.Layouts

ApplicationWindow {
    width: 640
    height: 480
    visible: true
    title: qsTr("Uwzz")
//QQuickImageProvider
    Image {
        id: name
        source: "image://headers/http://p.ananas.chaoxing.com/star3/240_130c/669ca80d6a0c5f74835bb936a41aabca.jpg"

    }



    footer: ToolBar {

        RowLayout {
            anchors.fill: parent
            ToolButton {
                text: "课程"
            }
            Label {
                Layout.fillWidth: true
                elide: Label.ElideRight
                horizontalAlignment: Qt.AlignHCenter
                verticalAlignment: Qt.AlignVCenter
                text: "uwzz"
            }

            ToolButton{
                text: "个人信息"
            }
        }
    }


}
