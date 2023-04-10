import QtQuick 2.15
import QtQuick.Controls 2.15 as Controls
import QtQuick.Layouts 1.15
import org.kde.kirigami 2.19 as Kirigami

Kirigami.OverlaySheet {
    id:infoSheet
    property alias cxid: cxidTextField.text
    property alias name: nameTextField.text

    header: Kirigami.Heading {
        text: "信息"
    }
    Kirigami.FormLayout {
        Controls.TextField {
            id:cxidTextField
            Kirigami.FormData.label: "超星ID"
            text: ss.cxid
        }

        Controls.TextField {
            id:nameTextField
            Kirigami.FormData.label: "姓名"
            text: ss.name
            echoMode: Controls.TextField.Password
        }

        Controls.Button {
            Layout.fillWidth: true
            text: "保存"
            onClicked: {
                ss.name = name
                ss.cxid = cxid
                close()
            }
        }
    }

}
