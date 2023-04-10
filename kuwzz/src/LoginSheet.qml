import QtQuick 2.15
import QtQuick.Controls 2.15 as Controls
import QtQuick.Layouts 1.15
import org.kde.kirigami 2.19 as Kirigami

Kirigami.OverlaySheet {
    id:loginSheet
    property alias phone: phoneTextField.text
    property alias password: passwordTextField.text

    header: Kirigami.Heading {
        text: "登录"
    }
    Kirigami.FormLayout {
        Controls.TextField {
            id:phoneTextField
            Kirigami.FormData.label: "手机号"
            text: ss.phone
        }

        Controls.TextField {
            id:passwordTextField
            Kirigami.FormData.label: "密码"
            text: ss.password
            echoMode: Controls.TextField.Password
        }

        Controls.Button {
            Layout.fillWidth: true
            text: "登录"
            onClicked: {
                ss.phone = phone
                ss.password = password
                close()
            }
        }
    }

}
