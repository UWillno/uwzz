// SPDX-License-Identifier: GPL-2.0-or-later
// SPDX-FileCopyrightText: 2023 UWillno <uwillno@outlook.com>

import QtQuick 2.15
import QtQuick.Controls 2.15 as Controls
import QtQuick.Layouts 1.15
import org.kde.kirigami 2.19 as Kirigami
import org.kde.KUwzz 1.0
import QtWebView 1.0

import Qt.labs.settings 1.1

Kirigami.ApplicationWindow {
    id: root

    Settings {
        id:ss
        property string phone
        property string password
        property int cxid
        property string name


        function refresh(){

            const xhr = new  XMLHttpRequest()
            xhr.open("GET","https://passport2-api.chaoxing.com/v11/loginregister?cx_xxt_passport=json&uname="+phone+"&code="+password,true)
            xhr.onload = function(e){
//                console.log("login:"+xhr.responseText)
            }

        }

        onPasswordChanged: {
            refresh()

        }

        onPhoneChanged: {
            refresh()
        }
    }


    title: i18n("KUwzz")

    minimumWidth: Kirigami.Units.gridUnit * 20
    minimumHeight: Kirigami.Units.gridUnit * 20

    onClosing: App.saveWindowGeometry(root)

    onWidthChanged: saveWindowGeometryTimer.restart()
    onHeightChanged: saveWindowGeometryTimer.restart()
    onXChanged: saveWindowGeometryTimer.restart()
    onYChanged: saveWindowGeometryTimer.restart()

    Component.onCompleted: App.restoreWindowGeometry(root)

    // This timer allows to batch update the window size change to reduce
    // the io load and also work around the fact that x/y/width/height are
    // changed when loading the page and overwrite the saved geometry from
    // the previous session.
    Timer {
        id: saveWindowGeometryTimer
        interval: 1000
        onTriggered: App.saveWindowGeometry(root)
    }

    property int counter: 0



    globalDrawer: Kirigami.GlobalDrawer {
        title: i18n("KUwzz")
        titleIcon: "applications-graphics"
        //        isMenu: !root.isMobile
        actions: [

            Kirigami.Action {
                text: i18n("登录")
                icon.name:"kde"
                onTriggered:{
                    const sheet = Qt.createComponent(Qt.resolvedUrl('qrc:LoginSheet.qml'),parent).createObject()
                    sheet.open()
                }
            },

            Kirigami.Action {
                text: i18n("信息配置")
                icon.name: "list-add"
                onTriggered: {
                    //                    counter += 1
                    //                    pageStack.layars.push('qrc:XxtWebPage.qml')
                }
            },
            Kirigami.Action {
                text: i18n("About KUwzz")
                icon.name: "help-about"
                onTriggered: pageStack.layers.push('qrc:About.qml')
            },
            Kirigami.Action {
                text: i18n("Quit")
                icon.name: "application-exit"
                onTriggered: Qt.quit()
            }
        ]
    }

    Controls.TabBar {


    }

    contextDrawer: Kirigami.ContextDrawer {
        id: contextDrawer
    }



    pageStack.initialPage:  Qt.resolvedUrl('qrc:XxtWebPage.qml')

}
