#include "debug.h"
//#include <QDebug>
Debug::Debug(QObject *parent)
    : QObject{parent}
{
    m_file.setFileName("/home/uwillno/Desktop/log.txt");
    m_in.setDevice(&m_file);
    if(m_file.open(QIODevice::WriteOnly | QIODevice::Append)){

        //        m_file.write(QDateTime::currentDateTime().toString("dd.MM.yyyy").toUtf8());
        m_in << QDateTime::currentDateTime().toString("dd.MM.yyyy hh-mm-ss") << "\n";
        m_file.close();
    }
}

Debug::~Debug()
{
    if(m_file.isOpen()){
        m_file.close();
    }

}



void Debug::log(const QString text, QString tag)
{
    //    qDebug() << tag << "-" << text;
    if(m_file.open(QIODevice::WriteOnly | QIODevice::Append)){

        m_in << tag << "-" << text << "\n";
        m_file.close();
    }



}
