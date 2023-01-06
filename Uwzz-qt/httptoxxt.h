#ifndef HTTPTOXXT_H
#define HTTPTOXXT_H

#include <QObject>
#include <QtNetwork>
#include <QSettings>
//#include <QThread>
#include <QSettings>
#include <documentparse.h>
#include <QByteArray>
//#include <QThreadPool>


class HttpToXxt : public QObject
{
    Q_OBJECT

    Q_PROPERTY(QString m_username READ username WRITE setUsername NOTIFY UsernameChanged)

    Q_PROPERTY(QString m_password READ password WRITE setPassword NOTIFY PasswordChanged)

    //    Q_PROPERTY (QQmlListProperty<Course> coursesList READ courses)
    //    Q_INVOKABLE
public:
    explicit HttpToXxt(QObject *parent = nullptr);

    const QString &username() const;
    void setUsername(const QString &newUsername);

    const QString &password() const;
    void setPassword(const QString &newPassword);
    int currentIndex;
    //    QQmlListProperty<Course> coursesList;

    DocumentParse *dp() const;
    void setDp(DocumentParse *newDp);

    const QList<Course *> &getCourseList() const;
    void setCourseList(const QList<Course *> &newCourseList);

    int getUid() const;
    void setUid();

    const QString &getName() const;
    void setName(const QString &newName);

signals:
    void loginS();
    void loginF();
    void UsernameChanged();
    void PasswordChanged();
    void coursesRefreshed(QList<Course*> data);
    void courseDetail(int index);
    void onActivityParseFinished();
    void dpChanged();
    void onStatisticsParsed();
    void courseListChanged();

    void nameChanged();

public slots:
    void logout();
    void login();
    void save();
    void loginStatus();
    void getCourses();
    void getActivityList(int index);
    void parseActivities();
    void getAcademic(int index);
    void getSignStatistics();
    void getPointsStatistics();
    void getHomeworkStatistics();
    //    QQmlListProperty<Course> courses();

private:
    QString m_username;
    QString m_password;
    QSettings *settings;
//    QThread *m_thread;
    QNetworkAccessManager *m_manager;
    QNetworkReply *m_reply;
    DocumentParse *m_dp;
    QList<Course*> courseList;
    int uid;
    QString name;
    //    QRegExp *m_regexp;
    QNetworkRequest m_request;

    Q_PROPERTY(DocumentParse *dp READ dp WRITE setDp NOTIFY dpChanged)
    Q_PROPERTY(QList<Course *> courseList READ getCourseList WRITE setCourseList NOTIFY courseListChanged)
    Q_PROPERTY(QString name READ getName WRITE setName NOTIFY nameChanged)
};

#endif // HTTPTOXXT_H
