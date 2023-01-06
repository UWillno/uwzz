#include "documentparse.h"

DocumentParse::DocumentParse(QObject *parent)
    : QObject{parent}
{

}

QList<Course*> DocumentParse::parseCourse(QString &document)
{
    QRegularExpression re("(?<=clazzid=)(\\d+)");
    QRegularExpressionMatchIterator i = re.globalMatch(document);
    QStringList classIdList;
    while (i.hasNext()) {
        QRegularExpressionMatch match = i.next();
        classIdList << match.captured(1);
        i.next();
    }
    qInfo()<< classIdList;
    re.setPattern("(?<=courseid=)(\\d+)");
    i = re.globalMatch(document);
    QStringList courseIdList;
    while (i.hasNext()) {
        QRegularExpressionMatch match = i.next();
        courseIdList << match.captured(1);
        i.next();
    }
    qInfo()<< courseIdList;
    QStringList nameList;
    re.setPattern("(?<=blank\\\" title=\\\")([\\s\\S]*?)(?=\\\">)");
    i = re.globalMatch(document);
    while (i.hasNext()) {
        QRegularExpressionMatch match = i.next();
        nameList << match.captured(1);
    }
    qInfo()<< nameList;

    //    (?<=imagedata=')[(\s\S)]*?(?=' width)
    QStringList imageList;
    re.setPattern("(?<=imagedata=')([(\\s\\S)]*?)(?=' width)");
    i = re.globalMatch(document);
    while (i.hasNext()) {
        QRegularExpressionMatch match = i.next();
        if(match.captured(1)=="/img/img_default.png")
            imageList << "https://mooc1-2.chaoxing.com/images/img_default.png";
        else
            imageList << match.captured(1);
    }
    qInfo()<< imageList;


    QList<Course*> courseList;
    for(int j=0; j<nameList.size(); j++){
        Course *temp = new Course(nameList[j],classIdList[j],courseIdList[j],imageList[j]);
        courseList << temp;
    }

    //    qInfo() << courseList;

    return courseList;


}

bool DocumentParse::parseActivities(QByteArray data,Course *course)
{
    QJsonDocument document=QJsonDocument::fromJson(data);
    QJsonObject object=document.object();
    QJsonArray listarray=((object["data"].toObject())["activeList"]).toArray();
    //    qInfo() << object;
    course->activitylist.clear();
    if(!listarray.isEmpty()){
        for(int i=0;i<listarray.size();i++){
            object=listarray[i].toObject();
            QString nameOne=object["nameOne"].toString();
            int userStatus=object["userStatus"].toInt();
            int otherId=object["otherId"].toInt();
            int type = object["type"].toInt();
            int attendNum=object["attendNum"].toInt();
            int activeType=object["activeType"].toInt();
            qint64 startTime=object["startTime"].toInteger();
            qint64 endTime= object["endTime"].toInteger();
            qint64 id=object["id"].toInteger();
            int status=object["status"].toInt();
            course->activitylist.append(new Activity(nameOne,userStatus,otherId,type,attendNum,activeType,startTime,endTime,id,status,course));

            //          qInfo() << object;
            //          qInfo() << object["userStatus"].toInt();

            //          list.append();
            //          Activity  *activity=new Activity(object["userStatus"]toString(),object["otherId"],object["type"],object["attendNum"],object["activeType"],object["startTime"],object["endTime"],object["id"],object["status"],this);

        }
        //        qInfo() << course->activitylist;
        //        emit this->onActivityParseFinished();
        return true;
    }
    return false;
}

bool DocumentParse::parseSignStatistics(QByteArray data)
{
    QJsonDocument document=QJsonDocument::fromJson(data);
    QJsonObject object=document.object();
    //        qInfo() << object;

    if(!object.isEmpty()){

        signSL.clear();
        QVariant signPer=object.value("signPer");
        QVariant allCount=object.value("allCount");
        QVariant attendanceCount=object.value("attendanceCount");
        if(!signPer.isNull() && !allCount.isNull() && !attendanceCount.isNull()){
            qInfo() << "asdasdasdasd";
            signSL.append(allCount);
            signSL.append(attendanceCount);
            signSL.append(signPer);
            return true;
        }
    }
    return false;
}

bool DocumentParse::parsePointsStatistics(QByteArray data, Course *course)
{
    QJsonDocument document=QJsonDocument::fromJson(data);
    QJsonObject object=document.object();
    QJsonArray listarray=((object["data"].toObject())["list"]).toArray();
    //    QPointer<Points> points=new Points(this);
    if(!listarray.isEmpty()){
        course->PointsList.clear();
        foreach (QJsonValueConstRef ref, listarray) {
            QJsonObject po=ref.toObject();
            //        qInfo() << po;
            QString name=po["name"].toString();
            int uid=po["uid"].toInt();
            double score=po["score"].toDouble();
            double quizScore=po["quizScore"].toDouble();
            double answerScore=po["answerScore"].toDouble();
            double pingfenScore=po["pingfenScore"].toDouble();
            double pickScore=po["pickScore"].toDouble();
            double discussScore=po["discussScore"].toDouble();
            double voteScore=po["voteScore"].toDouble();
            double taskScore=po["taskScore"].toDouble();
            double teacherScore=po["teacherScore"].toDouble();
            double addScore=po["addScore"].toDouble();
            Points *p=new Points(name,uid,score,quizScore,answerScore,pingfenScore,pickScore,discussScore,voteScore,taskScore,teacherScore,addScore,course);
            course->PointsList.append(p);
        }
        return true;

    }
    return false;
}

bool DocumentParse::parseHomeworkStatistics(QByteArray data1,QByteArray data2,Course *course)
{
    //    qInfo() << QJsonDocument::fromJson(data1);
    //    qInfo() << QJsonDocument::fromJson(data2);
    QJsonArray hwlist=QJsonDocument::fromJson(data1).object().value("data").toArray();
    if(!hwlist.isEmpty()){
        course->HomeWorkList.clear();
        foreach (QJsonValueConstRef h, hwlist) {
            QJsonObject object = h.toObject();
            HomeWork* hw = new HomeWork(object.value("avgScore").toString().toDouble(),
                                        object.value("questionNum").toInt(),
                                        object.value("score").toString().toDouble(),
                                        object.value("totalPublish").toInt(),
                                        object.value("workName").toString(),
                                        object.value("workSubmit").toInt()
                                        ,course);
            course->HomeWorkList.append(hw);
        }
    }

    QJsonArray hwplist=QJsonDocument::fromJson(data2).object().value("data").toArray();
    if(!hwplist.isEmpty()){
        course->HomeWorkPointsList.clear();
//        qInfo()<<hwplist;
        foreach (QJsonValueConstRef h, hwplist) {
            QJsonObject o = h.toObject();
            HomeWorkPoints *hwp=new HomeWorkPoints(
                        o.value("userName").toString(),
                        o.value("workMarked").toInt() ,
                        o.value("workSubmited").toInt() ,
                        o.value("avg").toString().toDouble() ,
                        o.value("completeNum").toInt() ,
                        o.value("max").toString().toDouble(),
                        o.value("min").toString().toDouble(),
                        course);
            course->HomeWorkPointsList.append(hwp);
        }
    }
    return true;
}
