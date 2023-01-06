#ifndef ACTIVITY_H
#define ACTIVITY_H

#include <QObject>

class Activity : public QObject
{
    Q_OBJECT
public:
    explicit Activity(
            QString nameOne,
            int userStatus,
            int otherId,
            int type,
            int attendNum,
            int activeType,
            qint64 startTime,
            qint64 endTime,
            qint64 id,
            int status,
            QObject *parent = nullptr);

    const QString &getNameOne() const;
    void setNameOne(const QString &newNameOne);

    qint64 getId() const;
    void setId(qint64 newId);

    int getStatus() const;
    void setStatus(int newStatus);

    qint64 getEndTime() const;
    void setEndTime(qint64 newEndTime);

    int getOtherId() const;
    void setOtherId(int newOtherId);

signals:


    void nameOneChanged();

    void idChanged();

    void statusChanged();

    void endTimeChanged();

    void otherIdChanged();

private:
    QString nameOne;
    int userStatus;
    int otherId;
    int type;
    int attendNum;
    int activeType;
    qint64 startTime;
    qint64 endTime;
    qint64 id;
    int status;
    Q_PROPERTY(QString nameOne READ getNameOne WRITE setNameOne NOTIFY nameOneChanged)
    Q_PROPERTY(qint64 id READ getId WRITE setId NOTIFY idChanged)
    Q_PROPERTY(int status READ getStatus WRITE setStatus NOTIFY statusChanged)
    Q_PROPERTY(qint64 endTime READ getEndTime WRITE setEndTime NOTIFY endTimeChanged)
    Q_PROPERTY(int otherId READ getOtherId WRITE setOtherId NOTIFY otherIdChanged)
};

#endif // ACTIVITY_H
