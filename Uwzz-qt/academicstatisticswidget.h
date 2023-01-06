#ifndef ACADEMICSTATISTICSWIDGET_H
#define ACADEMICSTATISTICSWIDGET_H

#include "points.h"
#include <QWidget>
#include <QtCharts>
#include "homework.h"
#include "homeworkpoints.h"

namespace Ui {
class AcademicStatisticsWidget;
}

class AcademicStatisticsWidget : public QWidget
{
    Q_OBJECT

public:
    explicit AcademicStatisticsWidget(QWidget *parent = nullptr);
    ~AcademicStatisticsWidget();
    void inittab1(QVariantList list);
    void inittab2(int uid, QString name,QList<Points*> pList);
    void inittab3(QString name,QList<HomeWork*> hwList,QList<HomeWorkPoints*> hwpList);

private slots:
    void on_tabWidget_tabBarClicked(int index);

private:
    Ui::AcademicStatisticsWidget *ui;

    // QWidget interface
};

#endif // ACADEMICSTATISTICSWIDGET_H
