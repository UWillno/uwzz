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
