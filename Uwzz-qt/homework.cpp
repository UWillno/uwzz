#include "homework.h"


HomeWork::HomeWork(double avgScore, int questionNum, double score, int totalPublish, QString workName, int workSubmit, QObject *parent)
    :avgScore(avgScore),questionNum(questionNum), score(score),totalPublish(totalPublish), workName(workName), workSubmit(workSubmit)
{

}

double HomeWork::getAvgScore() const
{
    return avgScore;
}

void HomeWork::setAvgScore(double newAvgScore)
{
    if (qFuzzyCompare(avgScore, newAvgScore))
        return;
    avgScore = newAvgScore;
    emit avgScoreChanged();
}

int HomeWork::getQuestionNum() const
{
    return questionNum;
}

void HomeWork::setQuestionNum(int newQuestionNum)
{
    if (questionNum == newQuestionNum)
        return;
    questionNum = newQuestionNum;
    emit questionNumChanged();
}

double HomeWork::getScore() const
{
    return score;
}

void HomeWork::setScore(double newScore)
{
    if (qFuzzyCompare(score, newScore))
        return;
    score = newScore;
    emit scoreChanged();
}

int HomeWork::getTotalPublish() const
{
    return totalPublish;
}

void HomeWork::setTotalPublish(int newTotalPublish)
{
    if (totalPublish == newTotalPublish)
        return;
    totalPublish = newTotalPublish;
    emit totalPublishChanged();
}

const QString &HomeWork::getWorkName() const
{
    return workName;
}

void HomeWork::setWorkName(const QString &newWorkName)
{
    if (workName == newWorkName)
        return;
    workName = newWorkName;
    emit workNameChanged();
}

int HomeWork::getWorkSubmit() const
{
    return workSubmit;
}

void HomeWork::setWorkSubmit(int newWorkSubmit)
{
    if (workSubmit == newWorkSubmit)
        return;
    workSubmit = newWorkSubmit;
    emit workSubmitChanged();
}
