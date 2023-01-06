#include "academicstatisticswidget.h"
#include "ui_academicstatisticswidget.h"



AcademicStatisticsWidget::AcademicStatisticsWidget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::AcademicStatisticsWidget)
{
    ui->setupUi(this);
    ui->tabWidget->setCurrentIndex(0);
}

AcademicStatisticsWidget::~AcademicStatisticsWidget()
{
    delete ui;
}

void AcademicStatisticsWidget::inittab1(QVariantList list)
{
    qInfo() << list;
    if(list[0].toDouble() == 0) return;
    ui->signCount->setText(ui->signCount->text()+"<br/>"+QString::number(list[1].toDouble())+"/"+QString::number(list[0].toDouble()));
    ui->signPer->setText(ui->signPer->text()+"<br/>"+list[2].toString()+"%");
    QChart *chart=new QChart();
    //    QChartView *view=new QChartView(chart);
    //    ui->signGraphicsView.setch
    QPieSeries *series = new QPieSeries();
    series->append("已签到："+QString::number(list[1].toDouble()),list[1].toDouble()/list[0].toDouble());
    series->append("缺勤："+QString::number((list[0].toDouble()-list[1].toDouble())),(list[0].toDouble()-list[1].toDouble())/list[0].toDouble());
    series->setLabelsVisible(true);
    chart->setTheme(QChart::ChartThemeBlueIcy);
    chart->setTitle("出勤统计");
    chart->setAnimationOptions(QChart::AllAnimations);
    chart->setAnimationDuration(400);
    chart->addSeries(series);
    ui->signGraphicsView->setChart(chart);

}

void AcademicStatisticsWidget::inittab2(int uid, QString name, QList<Points *> pList)
{   if(pList.size()==0) return;
    //    ui->pointsTable->clear();
    //    ui->pointsTable->setColumnCount();
    foreach (Points* p, pList) {
        if(p->uid==uid && p->name == name){
            ui->score->setText(ui->score->text()+QString::number(p->score));
            ui->quizScore->setText(ui->quizScore->text()+QString::number(p->quizScore));
            ui->answerScore->setText(ui->answerScore->text()+QString::number(p->answerScore));
            ui->discussScore->setText(ui->discussScore->text()+QString::number(p->discussScore));
            ui->pickScore->setText(ui->pickScore->text()+QString::number(p->pickScore));
            ui->voteScore->setText(ui->voteScore->text()+QString::number(p->voteScore));
            ui->otherScore->setText(ui->otherScore->text()+QString::number(p->pingfenScore+p->teacherScore+p->addScore+p->taskScore));
            //            ui->pointsTable->insertRow();
            //            ui->pointsTable->insertRow()
        }
        int row = ui->pointsTable->rowCount();
        ui->pointsTable->insertRow(row);

        for(int i=0;i < ui->pointsTable->columnCount();i++){
            ui->pointsTable->setItem(row,i,new QTableWidgetItem());
        }

        ui->pointsTable->item(row,0)->setText(p->name);
        //        ui->pointsTable->setItem(row,0,new QTableWidgetItem(p->name));
        //        ui->pointsTable->setItem(row,1,new QTableWidgetItem(QString::number(p->score)));
        ui->pointsTable->item(row,1)->setData(Qt::DisplayRole,p->score);
        ui->pointsTable->item(row,2)->setData(Qt::DisplayRole,p->pickScore);
        ui->pointsTable->item(row,3)->setData(Qt::DisplayRole,p->answerScore);
        ui->pointsTable->item(row,4)->setData(Qt::DisplayRole,p->discussScore);
        ui->pointsTable->item(row,5)->setData(Qt::DisplayRole,(p->pingfenScore+p->teacherScore+p->addScore+p->taskScore));
        ui->pointsTable->item(row,6)->setData(Qt::DisplayRole,p->score);
        //        ui->pointsTable->setItem(row,2,new QTableWidgetItem(QString::number(p->pickScore)));
        //        ui->pointsTable->setItem(row,3,new QTableWidgetItem(QString::number(p->answerScore)));
        //        ui->pointsTable->setItem(row,4,new QTableWidgetItem(QString::number(p->discussScore)));
        //        ui->pointsTable->setItem(row,5,new QTableWidgetItem(QString::number(p->voteScore)));
        //        ui->pointsTable->setItem(row,6,new QTableWidgetItem(QString::number(p->pingfenScore+p->teacherScore+p->addScore+p->taskScore)));
    }
    ui->pointsTable->sortByColumn(1,Qt::DescendingOrder);


}

void AcademicStatisticsWidget::inittab3(QString name, QList<HomeWork *> hwList, QList<HomeWorkPoints *> hwpList)
{
    QPointer<QTableWidget> p1= ui->hwTable1;
    QPointer<QTableWidget> p2= ui->hwTable2;
    foreach (HomeWork *hw, hwList) {
        int row = p1->rowCount();
        p1->insertRow(row);
        p1->setItem(row,0,new QTableWidgetItem(hw->getWorkName()));
        p1->setItem(row,1,new QTableWidgetItem(QString::number(hw->getTotalPublish())));
        p1->setItem(row,2,new QTableWidgetItem(QString::number(hw->getWorkSubmit())));
        p1->setItem(row,3,new QTableWidgetItem(QString::number(hw->getAvgScore())));
        p1->setItem(row,4,new QTableWidgetItem(QString::number(hw->getScore())));
        p1->setItem(row,5,new QTableWidgetItem(QString::number(hw->getQuestionNum())));
    }

    foreach (HomeWorkPoints *hwp, hwpList) {
        if(hwp->getUserName() == name){
            ui->workprogress->setText(ui->workprogress->text()+QString::number(hwp->getWorkSubmited())+"/"+QString::number(hwList.size()));
            ui->workavg->setText(ui->workavg->text()+QString::number(hwp->getAvg()));
        }
        int row = p2->rowCount();
        p2->insertRow(row);
        p2->setItem(row,0,new QTableWidgetItem(hwp->getUserName()));
        p2->setItem(row,1,new QTableWidgetItem(QString::number(hwp->getWorkMarked())));
        p2->setItem(row,2,new QTableWidgetItem(QString::number(hwp->getWorkSubmited())));
        p2->setItem(row,3,new QTableWidgetItem());
        p2->item(row,3)->setData(Qt::DisplayRole,hwp->getCompleteNum());
        p2->setItem(row,4,new QTableWidgetItem(QString::number(hwp->getMax())));
        p2->setItem(row,5,new QTableWidgetItem(QString::number(hwp->getMin())));
        p2->setItem(row,6,new QTableWidgetItem(QString::number(hwp->getAvg())));
    }
   p2->sortByColumn(3,Qt::DescendingOrder);
}

void AcademicStatisticsWidget::on_tabWidget_tabBarClicked(int index)
{
    if(index==3){
        this->close();
        deleteLater();
    }
}


