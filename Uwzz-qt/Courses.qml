import QtQuick 2.15
//import Uwzz.xxt 1.0
//import Uwzz.xxt.course 1.0

ListView {
    id:listview
  model: model1
  ListModel {
    id:model1
    ListElement{
      name:"asd"
      aaa:"https://p.ananas.chaoxing.com/star3/270_160/a127268d37a33f44146662bc6c89fb76.png"
//    aaa:"https://ddgobkiprc33d.cloudfront.net/bfdb2533-84e9-45a1-956a-106722433d3f.png"
    }
  }

  delegate: Item {

      Text {

          text: model.name

      }
      Image {

          source: model.aaa
//          on
      }

  }

//  onModelChanged: {
//      console(internalModel.columnCount())
//  }
//  function a(){
//      model.append()
//  }
}



//ListView {
//    Text {
//        id: name
//        text: qsTr("text")
//    }
//    Xxt {
//        id: xxt
//    }

//    anchors.fill: parent
//    Rectangle {
//        width: 100
//        height: 100
//        color: "red"

//        MouseArea {
//            anchors.fill: parent
//            onClicked: {
//                console.log(xxt.courses().data)
//                console.log(xxt.coursesList)
////                console.log(Course.classId)
//            }
//        }
//    }

//    model: xxt.courses()

//    delegate: Item {
//        width: parent.width
//        height: col.height
//        Column {
//            id: col
//            Text {
//                id: nameLabel
//                text: modelData.name;
//            }

//        }

//    }


//}


//Item {
//    anchors.fill: parent
//    Text {
//        anchors.fill: parent
//        id: name
//        text: qsTr("text")
//    }

////    Component {
////        id: component


////    }

////    ListView {
////        anchors.fill: parent

////        model: {

////        }
////    }

////    Xxt {

////        id: uwzz

////        onCoursesRefreshed: {
////            console.log(data)
////        }
////    }

//}

