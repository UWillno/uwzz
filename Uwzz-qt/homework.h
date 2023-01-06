#ifndef HOMEWORK_H
#define HOMEWORK_H

#include <QObject>

class HomeWork : public QObject
{
    Q_OBJECT
public:
    explicit HomeWork(double avgScore,int questionNum,double score,int totalPublish,QString workName,int workSubmit,QObject *parent = nullptr);

    double getAvgScore() const;
    void setAvgScore(double newAvgScore);

    int getQuestionNum() const;
    void setQuestionNum(int newQuestionNum);
    double getScore() const;
    void setScore(double newScore);
    int getTotalPublish() const;
    void setTotalPublish(int newTotalPublish);
    const QString &getWorkName() const;
    void setWorkName(const QString &newWorkName);
    int getWorkSubmit() const;
    void setWorkSubmit(int newWorkSubmit);

signals:

    void avgScoreChanged();

    void questionNumChanged();
    void scoreChanged();
    void totalPublishChanged();
    void workNameChanged();
    void workSubmitChanged();

private:
    double avgScore;
//    double orgAvgScore;
    int questionNum;
    double score;
    int totalPublish;
    QString workName;
    int workSubmit;
    Q_PROPERTY(double avgScore READ getAvgScore WRITE setAvgScore NOTIFY avgScoreChanged)
    Q_PROPERTY(int questionNum READ getQuestionNum WRITE setQuestionNum NOTIFY questionNumChanged)
    Q_PROPERTY(double score READ getScore WRITE setScore NOTIFY scoreChanged)
    Q_PROPERTY(int totalPublish READ getTotalPublish WRITE setTotalPublish NOTIFY totalPublishChanged)
    Q_PROPERTY(QString workName READ getWorkName WRITE setWorkName NOTIFY workNameChanged)
    Q_PROPERTY(int workSubmit READ getWorkSubmit WRITE setWorkSubmit NOTIFY workSubmitChanged)
};

#endif // HOMEWORK_H
