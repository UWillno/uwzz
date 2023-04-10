import QtQuick 2.15
import QtQuick.Controls 2.15 as Controls
import QtQuick.Layouts 1.15
import org.kde.kirigami 2.19 as Kirigami
import org.kde.KUwzz 1.0
import QtWebView 1.0
import QtQuick.Dialogs

Kirigami.Page {
    id: page
    title: i18n("KUwzz")

    //    globalToolBarItem:Kirigami.ToolBarApplicationHeader
    //    header: Controls.Button {
    //    text: "asd"

    //    }
    contextualActions:[
        Kirigami.Action {
            text: "后退"
            onTriggered: {
                // do stuff
                if(webview.canGoBack) webview.goBack()
            }
        },
        Kirigami.Action {
            text: "前进"
            onTriggered: {
                // do stuff
                if(webview.canGoForward) webview.goForward()
                webview.cookies
            }
        }
    ]
    //    header: Controls.ToolBar {


    //    }
    ColumnLayout {
        anchors.fill: parent

        StackLayout {
            id:stackLayout

            currentIndex: tabbar.currentIndex
            Layout.fillWidth: true
            height: parent.height
            //                anchors.fill: parent
            WebView {
                id:webview
                url:"https://www.chaoxing.com"

                onUrlChanged: {
                    db.log(webview.url)
                    //                console.log(webview.url)
                    webview.runJavaScript("document.cookie",function(result){
                        db.log(JSON.stringify(result),"cookie")
                    })
                }
            }

        }
        Controls.TabBar {
            id:tabbar
            //            width: parent.width
            Layout.fillWidth: true
            Controls.TabButton {
                text: "Test"
            }
        }
    }






    //    Controls.StackLayout {
    //    StackLayout {
    ////        anchors.fill: parent
    //        width: parent.height
    //        WebView {


    //            url:"https://www.baidu.com"

    //        }
    //        WebView {
    ////            anchors.fill: parent

    //            url:"https://www.baidu.com"

    //        }
    //    }

    //    }


    //    actions.main: Kirigami.Action {
    //        id: addAction
    //        icon.name: "list-add"
    //        text: i18nc("@action:button", "Add kountdown")
    //        onTriggered: kountdownModel.append({
    //            name: "Kirigami Action added card!",
    //            description: "Congratulations, your Kirigami Action works!",
    //            date: 1000
    //        })
    //    }
    //        actions.main: Kirigami.Action {
    //            text: i18n("Plus One")
    //            icon.name: "list-add"
    //            tooltip: i18n("Add one to the counter")
    //            onTriggered: {
    //                counter += 1
    //            }
    //        }

    //        ColumnLayout {
    //            width: page.width

    //            anchors.centerIn: parent

    //            Kirigami.Heading {
    //                Layout.alignment: Qt.AlignCenter
    //                text: counter == 0 ? i18n("Hello, World!") : counter
    //            }

    //            Controls.Button {
    //                Layout.alignment: Qt.AlignHCenter
    //                text: "+ 1"
    //                onClicked: counter += 1
    //            }
    //        }
}
