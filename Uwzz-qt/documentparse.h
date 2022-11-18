#ifndef DOCUMENTPARSE_H
#define DOCUMENTPARSE_H

#include <QObject>
#include <QJsonDocument>
#include "course.h"

class DocumentParse : public QObject
{
    Q_OBJECT
public:
    explicit DocumentParse(QObject *parent = nullptr);

signals:



public slots:
    QList<Course*> parseCourse(QString &document);


};

#endif // DOCUMENTPARSE_H
