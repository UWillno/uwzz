#include "httptoxxt.h"



HttpToXxt::HttpToXxt(QObject *parent)
    : QObject{parent}
{
    m_manager = new QNetworkAccessManager(this);
    m_dp=new DocumentParse(this);
    settings = new QSettings("./config.ini",QSettings::IniFormat,this);
    m_username = settings->value("username").toString();
    m_password = settings->value("password").toString();
    m_request.setRawHeader("user-agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Mobile Safari/537.36 Edg/107.0.1418.52");
    m_request.setHeader(QNetworkRequest::ContentTypeHeader,"application/x-www-form-urlencoded");
    qInfo() << m_username;
    qInfo() << m_password;
    if(m_username!="" || m_password != "")
        login();

}
//https://passport2-api.chaoxing.com/v11/loginregister?cx_xxt_passport=json&uname=17671056113&code=zhj775825

void HttpToXxt::login()
{
    QString url("https://passport2-api.chaoxing.com/v11/loginregister?cx_xxt_passport=json&uname="+m_username+"&code="+m_password);
    qInfo() << url;
    //    m_thread = new QThread(this);
    //    m_thread.
    //    url=QUrl::toPercentEncoding(url);
    m_request.setUrl(url);

    //    QSslConfiguration config;
    //    QSslConfiguration conf = m_request->sslConfiguration();
    //    conf.setPeerVerifyMode(QSslSocket::VerifyNone);
    //    conf.setProtocol(QSsl::AnyProtocol);
    //    m_request->setSslConfiguration(conf);

    connect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::loginStatus);
    //    m_reply=m_manager->get(QNetworkRequest(url));
    m_reply=m_manager->get(m_request);
}

void HttpToXxt::save()
{
    settings->clear();
    settings->setValue("username",m_username);
    settings->setValue("password",m_password);
    settings->sync();
}

void HttpToXxt::loginStatus()
{
    qInfo() << "asd ";
    QString result=m_reply->readAll();
    //    qInfo() << result;
    if(result.contains("账号或密码错误")){
        emit loginF();
    }
    else if(result.contains("验证通过")){
        save();
        emit loginS();
        disconnect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::loginStatus);
        connect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::getCourses);
        //        m_reply=m_manager->get(QNetworkRequest(QUrl("https://mooc1-2.chaoxing.com/visit/courses/study?isAjax=true")));
        m_request.setUrl(QUrl("https://mooc1-2.chaoxing.com/visit/courses/study?isAjax=true"));
        m_reply=m_manager->get(m_request);
    }else{
        qInfo() << "未知错误？？";
    }
    //    qInfo() << QString(m_reply->rawHeader("Set-Cookie"));
    //    m_request = new QNetworkRequest(QUrl("www.chaoxing.com"));

}



void HttpToXxt::getCourses()
{
    //
    //    QRegExp rx("(?<=title=\\\\\")([\\s\\S]*?/)/(?=\\\\\">/)");
    //       ex("(?<=clazzid=)([0-9]+)")
    //    QRegExp rx("(?<=blank\\\\\" title=\\\\\")([\\s\\S]*?)(?=\\\\\">)");

    //    QStringList list;
    QString result=m_reply->readAll();
    courseList=m_dp->parseCourse(result);

    disconnect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::getCourses);
    emit coursesRefreshed(courseList);
    this->setUid();

    //    QQmlListProperty<Course> qmlCourses(this,&courseList);
    //    emit coursesRefreshed(qmlCourses);


    //    qInfo() << R"((?<=blank\\" title=\\")([\s\S]*?)(?=\\">))";
    //    int pos = 0;

    //    qInfo() << list;
    //    qInfo()<< result;
    //    QRegExp rx(QRegExp::escape("(?<=clazzid=)(\\d+)"));
    //    rx.setPatternSyntax(QRegExp::Wildcard);

    //    qInfo() << rx.isValid();
    //    rx.setPatternSyntax(QRegExp::Wildcard);
    //    QRegExp rx("(?<=blank\\" title=\\")([\s\S]*?)(?=\\">)");
    //    QString str = "Offsets: 12 14 99 231 7";
    //    QStringList list;

    //    while ((pos = rx.indexIn(str, pos)) != -1) {
    //        list << rx.cap(1);
    //        pos += rx.matchedLength();
    //    }
    //    QRegularExpression re("(?<=clazzid=)(\\d+)");
    //    QRegularExpressionMatchIterator i = re.globalMatch(result);

    //    while (i.hasNext()) {
    //        QRegularExpressionMatch match = i.next();
    //        list << match.captured(1);
    //    }

    //    qInfo() << list;
    // pos will be 9, 14, 18 and finally 24; count will end up as 4


}

void HttpToXxt::getActivityList(int index)
{
    QString url("https://widget-course.chaoxing.com/widget/weixin/stucourse/activelist?DB_STRATEGY=COURSEID&STRATEGY_PARA=courseId&fid=31430&courseId="+courseList[index]->getCourseId()+"&classId="+courseList[index]->getClassId()+"&activeTypes=2%2C4%2C5%2C11%2C23%2C42%2C43%2C14%2C51%2C35%2C44%2C48");
    currentIndex=index;
    m_request.setUrl(url);
    m_reply=m_manager->get(m_request);
    connect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::parseActivities);
}

void HttpToXxt::parseActivities()
{
    if(m_dp->parseActivities(m_reply->readAll(),courseList[currentIndex])){
        emit this->onActivityParseFinished();
    }
    disconnect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::parseActivities);

}

void HttpToXxt::getAcademic(int index)
{
    currentIndex=index;
    QString url("https://stat2-ans.chaoxing.com/study-data/sign");
    m_request.setUrl(url);
    QByteArray data(("clazzid="+this->courseList[index]->getClassId()+"&courseid="+this->courseList[index]->getCourseId()).toUtf8());
    connect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::getSignStatistics);
    m_reply=m_manager->post(m_request,data);


}

void HttpToXxt::getSignStatistics()
{
    if(m_reply->error()) return;
    //    qInfo()<<QString(m_reply->readAll());
    m_dp->parseSignStatistics(m_reply->readAll());
    //        emit signStatisticsSignal();
    disconnect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::getSignStatistics);

    QString url("https://mobilelearn.chaoxing.com/v2/apis/integral/getIntegralList?DB_STRATEGY=COURSEID&STRATEGY_PARA=courseId&pageSize=200&page=1&classId="+courseList[currentIndex]->getClassId()+"&courseId="+courseList[currentIndex]->getCourseId());
    m_request.setUrl(url);
    qInfo() << url;
    connect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::getPointsStatistics);
    m_reply=m_manager->get(m_request);
}

void HttpToXxt::getPointsStatistics()
{
    //    m_reply->readAll()
    disconnect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::getPointsStatistics);
    m_dp->parsePointsStatistics(m_reply->readAll(),courseList[currentIndex]);
    //    emit onStatisticsParsed();
    getHomeworkStatistics();

}

void HttpToXxt::getHomeworkStatistics()
{
    QEventLoop loop;
    connect(m_manager,&QNetworkAccessManager::finished,&loop,&QEventLoop::quit);
    QString url("https://stat2-ans.chaoxing.com/work-stastics/works?clazzid=" +courseList[currentIndex]->getClassId() + "&courseid=" + courseList[currentIndex]->getCourseId() + "&page=1&pageSize=200&order=0");
    qInfo() << url;

    m_request.setUrl(url);
    m_reply=m_manager->get(m_request);
    loop.exec();
    QByteArray data1=m_reply->readAll();

    url="https://stat2-ans.chaoxing.com/work-stastics/student-works?clazzid=" +courseList[currentIndex]->getClassId() + "&courseid=" + courseList[currentIndex]->getCourseId() + "&page=1&pageSize=200";
    m_request.setUrl(url);
    qInfo()<<url;
    m_reply=m_manager->get(m_request);
    loop.exec();
    QByteArray data2=m_reply->readAll();
    //    QByteArray data2;
    disconnect(m_manager,&QNetworkAccessManager::finished,&loop,&QEventLoop::quit);
    m_dp->parseHomeworkStatistics(data1,data2,courseList[currentIndex]);
    emit onStatisticsParsed();

}

const QString &HttpToXxt::getName() const
{
    return name;
}

void HttpToXxt::setName(const QString &newName)
{
    if (name == newName)
        return;
    name = newName;
    emit nameChanged();
}

int HttpToXxt::getUid() const
{
    return uid;
}

void HttpToXxt::setUid()
{
    QEventLoop loop;
    QString url("https://stat2-ans.chaoxing.com/study-data/sign");
    m_request.setUrl(url);
    QByteArray data(("clazzid="+this->courseList[0]->getClassId()+"&courseid="+this->courseList[0]->getCourseId()).toUtf8());
    QObject::connect(m_manager,&QNetworkAccessManager::finished,&loop,&QEventLoop::quit);
    m_reply=m_manager->post(m_request,data);
    loop.exec();
    QJsonDocument document=QJsonDocument::fromJson(m_reply->readAll());
    qInfo() << document;
    QJsonObject object=document.object();
    if(!object.isEmpty()){
        uid = object.value("uid").toInt();
        //       qInfo() << uid;
        //       qInfo() <<  object.value("uid");
    }
    url="https://sso.chaoxing.com/apis/login/userLogin4Uname.do";
    m_request.setUrl(url);
    m_reply=m_manager->get(m_request);
    loop.exec();
    //    QStringList nameList;
    QRegularExpression re;
    re.setPattern("(?<=\",\"name\":\")([\\s\\S]*?)(?=\",\")");
    //    qInfo() << re.isValid();
    QRegularExpressionMatchIterator i = re.globalMatch(QString(m_reply->readAll()));
    while (i.hasNext()) {
        QRegularExpressionMatch match = i.next();
        name = match.captured(1);
        qInfo() << name;
        break;
    }
    QObject::disconnect(m_manager,&QNetworkAccessManager::finished,&loop,&QEventLoop::quit);
}

const QList<Course *> &HttpToXxt::getCourseList() const
{
    return courseList;
}

void HttpToXxt::setCourseList(const QList<Course *> &newCourseList)
{
    if (courseList == newCourseList)
        return;
    courseList = newCourseList;
    emit courseListChanged();
}

DocumentParse *HttpToXxt::dp() const
{
    return m_dp;
}

void HttpToXxt::setDp(DocumentParse *newDp)
{
    if (m_dp == newDp)
        return;
    m_dp = newDp;
    emit dpChanged();
}

//QQmlListProperty<Course> HttpToXxt::courses()
//{
//    return QQmlListProperty<Course>(this,&this->courseList);
//}
const QString &HttpToXxt::password() const
{
    return m_password;
}

void HttpToXxt::setPassword(const QString &newPassword)
{
    m_password = newPassword;
}

void HttpToXxt::logout()
{
    //    disconnect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::getCourses);
    //    connect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::loginStatus);
}

const QString &HttpToXxt::username() const
{
    return m_username;
}

void HttpToXxt::setUsername(const QString &newUsername)
{
    m_username = newUsername;
}
