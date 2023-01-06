#ifndef POINTS_H
#define POINTS_H

#include <QObject>

class Points : public QObject
{
    Q_OBJECT
public:
    explicit Points( QString name,
                     int uid,
                     double score,
                     double quizScore,
                     double answerScore,
                     double pingfenScore,
                     double pickScore,
                     double discussScore,
                     double voteScore,
                     double taskScore,
                     double teacherScore,
                     double addScore,
                     QObject *parent = nullptr);

    double score; //总分？？？
    double quizScore; //测验
    double answerScore; // 回答

    double pingfenScore; //评分？？？
    double pickScore;// 选人
    double discussScore; //讨论
    double voteScore; // 投票
    double taskScore; // 任务??问卷？？
    double teacherScore; // ???
    double addScore;// 加分????
    int uid;
    QString name;

signals:



};

#endif // POINTS_H
