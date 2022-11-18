#ifndef REGEXP_H
#define REGEXP_H

#include <QObject>
#include <QRegExp>

class RegExp : public QObject
{
    Q_OBJECT
public:
    explicit RegExp(QObject *parent = nullptr);

signals:

};

#endif // REGEXP_H
