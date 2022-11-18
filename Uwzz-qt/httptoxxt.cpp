#include "httptoxxt.h"



HttpToXxt::HttpToXxt(QObject *parent)
    : QObject{parent}
{
    m_manager = new QNetworkAccessManager(this);
    m_dp=new DocumentParse(this);
    settings = new QSettings("./config.ini",QSettings::IniFormat,this);
    m_username = settings->value("username").toString();
    m_password = settings->value("password").toString();
    qInfo() << m_username;
    qInfo() << m_password;
    connect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::loginStatus);
    if(m_username!="" || m_password != "")
        login();

}

void HttpToXxt::login()
{
    QString url("https://passport2-api.chaoxing.com/v11/loginregister?cx_xxt_passport=json&uname="+m_username+"&code="+m_password);
    qInfo() << url;
    //    m_thread = new QThread(this);
    //    m_thread.
    //    url=QUrl::toPercentEncoding(url);
    //    m_request=new QNetworkRequest(url);

    //    QSslConfiguration config;
    //    QSslConfiguration conf = m_request->sslConfiguration();
    //    conf.setPeerVerifyMode(QSslSocket::VerifyNone);
    //    conf.setProtocol(QSsl::AnyProtocol);
    //    m_request->setSslConfiguration(conf);


    m_reply=m_manager->get(QNetworkRequest(url));
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
        m_reply=m_manager->get(QNetworkRequest(QUrl("https://mooc1-2.chaoxing.com/visit/courses/study?isAjax=true")));
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

    QStringList list;
    QString result=m_reply->readAll();
    courseList=m_dp->parseCourse(result);


    emit coursesRefreshed(courseList);

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
    disconnect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::getCourses);
    connect(m_manager,&QNetworkAccessManager::finished,this,&HttpToXxt::loginStatus);
}

const QString &HttpToXxt::username() const
{
    return m_username;
}

void HttpToXxt::setUsername(const QString &newUsername)
{
    m_username = newUsername;
}
