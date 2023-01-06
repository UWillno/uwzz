#include "homeworkpoints.h"


HomeWorkPoints::HomeWorkPoints(QString userName, int workMarked, int workSubmited, double avg, int completeNum, double max, double min, QObject *parent)
    :userName(userName),workMarked(workMarked),workSubmited(workSubmited),avg(avg),completeNum(completeNum),max(max),min(min)
{

}

const QString &HomeWorkPoints::getUserName() const
{
    return userName;
}

void HomeWorkPoints::setUserName(const QString &newUserName)
{
    if (userName == newUserName)
        return;
    userName = newUserName;
    emit userNameChanged();
}

int HomeWorkPoints::getWorkMarked() const
{
    return workMarked;
}

void HomeWorkPoints::setWorkMarked(int newWorkMarked)
{
    if (workMarked == newWorkMarked)
        return;
    workMarked = newWorkMarked;
    emit workMarkedChanged();
}

int HomeWorkPoints::getWorkSubmited() const
{
    return workSubmited;
}

void HomeWorkPoints::setWorkSubmited(int newWorkSubmited)
{
    if (workSubmited == newWorkSubmited)
        return;
    workSubmited = newWorkSubmited;
    emit workSubmitedChanged();
}

double HomeWorkPoints::getAvg() const
{
    return avg;
}

void HomeWorkPoints::setAvg(double newAvg)
{
    if (qFuzzyCompare(avg, newAvg))
        return;
    avg = newAvg;
    emit avgChanged();
}

int HomeWorkPoints::getCompleteNum() const
{
    return completeNum;
}

void HomeWorkPoints::setCompleteNum(int newCompleteNum)
{
    if (completeNum == newCompleteNum)
        return;
    completeNum = newCompleteNum;
    emit completeNumChanged();
}

double HomeWorkPoints::getMax() const
{
    return max;
}

void HomeWorkPoints::setMax(double newMax)
{
    if (qFuzzyCompare(max, newMax))
        return;
    max = newMax;
    emit maxChanged();
}

double HomeWorkPoints::getMin() const
{
    return min;
}

void HomeWorkPoints::setMin(double newMin)
{
    if (qFuzzyCompare(min, newMin))
        return;
    min = newMin;
    emit minChanged();
}
