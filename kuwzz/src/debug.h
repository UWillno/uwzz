#ifndef DEBUG_H
#define DEBUG_H

#include <QObject>
#include <QFile>
#include <QTextStream>
#include <QDateTime>
class Debug : public QObject
{
    Q_OBJECT
public:
    explicit Debug(QObject *parent = nullptr);
    ~Debug();

private:
    QFile m_file;
    QTextStream m_in;

signals:


public slots:
    void log(const QString text, QString tag="");
};

#endif // DEBUG_H
