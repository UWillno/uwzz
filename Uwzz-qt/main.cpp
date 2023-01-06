#include "mainwindow.h"

#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    w.setWindowOpacity(0.9);
    w.setStyle(QStyleFactory::create("Fusion"));
//    qInfo()<< QStyleFactory::keys();
    w.show();
    return a.exec();
}
