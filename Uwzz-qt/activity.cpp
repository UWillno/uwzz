#include "activity.h"

Activity::Activity(QString nameOne,int userStatus, int otherId, int type, int attendNum, int activeType, qint64 startTime, qint64 endTime, qint64 id, int status, QObject *parent)
    :nameOne(nameOne),userStatus(userStatus),otherId(otherId),type(type),attendNum(attendNum),activeType(activeType),startTime(startTime),endTime(endTime),id(id),status(status)
{

}

const QString &Activity::getNameOne() const
{
    return nameOne;
}

void Activity::setNameOne(const QString &newNameOne)
{
    if (nameOne == newNameOne)
        return;
    nameOne = newNameOne;
    emit nameOneChanged();
}

qint64 Activity::getId() const
{
    return id;
}

void Activity::setId(qint64 newId)
{
    if (id == newId)
        return;
    id = newId;
    emit idChanged();
}

int Activity::getStatus() const
{
    return status;
}

void Activity::setStatus(int newStatus)
{
    if (status == newStatus)
        return;
    status = newStatus;
    emit statusChanged();
}

qint64 Activity::getEndTime() const
{
    return endTime;
}

void Activity::setEndTime(qint64 newEndTime)
{
    if (endTime == newEndTime)
        return;
    endTime = newEndTime;
    emit endTimeChanged();
}

int Activity::getOtherId() const
{
    return otherId;
}

void Activity::setOtherId(int newOtherId)
{
    if (otherId == newOtherId)
        return;
    otherId = newOtherId;
    emit otherIdChanged();
}
