#ifndef HOMEWORKPOINTS_H
#define HOMEWORKPOINTS_H

#include <QObject>

class HomeWorkPoints : public QObject
{
    Q_OBJECT
public:
    explicit HomeWorkPoints(QString userName,
    int workMarked,
    int workSubmited,
    double avg,
    int completeNum,
    double max,
    double min,QObject *parent = nullptr);

    const QString &getUserName() const;
    void setUserName(const QString &newUserName);
    int getWorkMarked() const;
    void setWorkMarked(int newWorkMarked);
    int getWorkSubmited() const;
    void setWorkSubmited(int newWorkSubmited);
    double getAvg() const;
    void setAvg(double newAvg);
    int getCompleteNum() const;
    void setCompleteNum(int newCompleteNum);
    double getMax() const;
    void setMax(double newMax);
    double getMin() const;
    void setMin(double newMin);

signals:

    void userNameChanged();
    void workMarkedChanged();
    void workSubmitedChanged();
    void avgChanged();
    void completeNumChanged();
    void maxChanged();
    void minChanged();

private:
    QString userName;
    int workMarked;
    int workSubmited;
    double avg;
    int completeNum;
    double max;
    double min;
    Q_PROPERTY(QString userName READ getUserName WRITE setUserName NOTIFY userNameChanged)
    Q_PROPERTY(int workMarked READ getWorkMarked WRITE setWorkMarked NOTIFY workMarkedChanged)
    Q_PROPERTY(int workSubmited READ getWorkSubmited WRITE setWorkSubmited NOTIFY workSubmitedChanged)
    Q_PROPERTY(double avg READ getAvg WRITE setAvg NOTIFY avgChanged)
    Q_PROPERTY(int completeNum READ getCompleteNum WRITE setCompleteNum NOTIFY completeNumChanged)
    Q_PROPERTY(double max READ getMax WRITE setMax NOTIFY maxChanged)
    Q_PROPERTY(double min READ getMin WRITE setMin NOTIFY minChanged)
};

#endif // HOMEWORKPOINTS_H
