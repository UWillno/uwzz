#include "points.h"



Points::Points(QString name, int uid, double score, double quizScore, double answerScore, double pingfenScore, double pickScore, double discussScore, double voteScore, double taskScore, double teacherScore, double addScore, QObject *parent)
    :name(name),uid(uid),score(score),quizScore(quizScore),answerScore(answerScore),
      pingfenScore(pingfenScore),pickScore(pickScore),discussScore(discussScore),voteScore(voteScore),
      taskScore(taskScore), teacherScore(teacherScore),addScore(addScore)
{

}
