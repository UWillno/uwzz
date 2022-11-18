#ifndef HTTPTOXXT_H
#define HTTPTOXXT_H

#include <QObject>
#include <QtNetwork>
#include <QSettings>
#include <QThread>
#include <QSettings>
#include <documentparse.h>


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

//    QQmlListProperty<Course> coursesList;

signals:
    void loginS();
    void loginF();
    void UsernameChanged();
    void PasswordChanged();
    void coursesRefreshed(QList<Course*> data);
    void courseDetail(int index);

public slots:
    void logout();
    void login();
    void save();
    void loginStatus();
    void getCourses();
//    QQmlListProperty<Course> courses();

private:
    QString m_username;
    QString m_password;
    QSettings *settings;
    QThread *m_thread;
    QNetworkAccessManager *m_manager;
    QNetworkReply *m_reply;
    DocumentParse *m_dp;
    QList<Course*> courseList;

    //    QRegExp *m_regexp;
    //    QNetworkRequest *m_request;

};

#endif // HTTPTOXXT_H
