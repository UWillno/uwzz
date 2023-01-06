#ifndef DOCUMENTPARSE_H
#define DOCUMENTPARSE_H

#include <QObject>
#include <QJsonDocument>
#include <QJsonObject>
#include "course.h"
#include <QJsonArray>
#include "activity.h"

class DocumentParse : public QObject
{
    Q_OBJECT
public:
    explicit DocumentParse(QObject *parent = nullptr);
    QVariantList signSL;

signals:
    //    void onActivityParseFinished();

public slots:
    QList<Course*> parseCourse(QString &document);
    bool parseActivities(QByteArray data,Course *course);
    bool parseSignStatistics(QByteArray data);
    bool parsePointsStatistics(QByteArray data,Course *course);
    bool parseHomeworkStatistics(QByteArray data1,QByteArray data2,Course *course);


};

#endif // DOCUMENTPARSE_H
