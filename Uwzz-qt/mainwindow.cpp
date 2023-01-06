#include "mainwindow.h"
#include "./ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    httptoxxt=new HttpToXxt(this);
    ui->phoneLe->setText(httptoxxt->username());
    ui->passwordLe->setText(httptoxxt->password());
    //    qmlRegisterType<HttpToXxt>("Uwzz.xxt", 1, 0, "Xxt");
    //    qmlRegisterType<Course>("Uwzz.xxt.course", 1, 0, "Course");
    //    ui->cousesdisplay->setSource(QUrl("qrc:/Courses.qml"));
    QObject::connect(httptoxxt,&HttpToXxt::loginF,this,&MainWindow::loginF);
    QObject::connect(httptoxxt,&HttpToXxt::loginS,this,&MainWindow::loginS);
    QObject::connect(this,&MainWindow::selectedCourse,httptoxxt,&HttpToXxt::courseDetail);
    //    QObject::connect(this,&MainWindow::logout,httptoxxt,&HttpToXxt::logout);
    listMn =new QMenu(this);
    QAction *action1=new QAction("活动列表",listMn);
    QAction *action2=new QAction("学情统计",listMn);
    listMn->addAction(action1);
    listMn->addAction(action2);
    QObject::connect(ui->courseLw,&QListWidget::customContextMenuRequested,this,&QMainWindow::customContextMenuRequested);
    QObject::connect(listMn,SIGNAL(triggered(QAction*)),this,SLOT(getactivitylist(QAction*)));
    QObject::connect(listMn,SIGNAL(triggered(QAction*)),this,SLOT(preAcademicStatistics(QAction*)));

}

MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow::on_loginBtn_clicked()
{
    httptoxxt->setUsername(ui->phoneLe->text());
    httptoxxt->setPassword(ui->passwordLe->text());
    httptoxxt->login();

}

void MainWindow::loginF()
{
    ui->loginLabel->setText("<font color='red'>账号或密码错误！！</font>");
    //    17671056113
}

void MainWindow::loginS()
{
    ui->loginLabel->setText("<font color='green'>登录成功！!</font>");
    QObject::connect(httptoxxt,&HttpToXxt::coursesRefreshed,this,&MainWindow::updateCourseList);
    ui->tab1->setEnabled(false);
    ui->tabWidget->setCurrentIndex(1);
    httptoxxt->save();
}







void MainWindow::updateCourseList(QList<Course*> data)
{
    ui->courseLw->clear();
    foreach (Course * course, data) {
        ui->courseLw->addItem(course->getName());
    }

    //    Model internalModel;
    //    Model data;
    //    foreach (Course* course, data) {
    //        internalModel.insert(course);
    //    }

    //    ui->cousesdisplay->rootContext()->setContextProperty("internalModel", &internalModel);

    //    qmlEngine->rootContext()->setContextProperty("internalModel", &internalModel);
}


void MainWindow::on_courseLw_itemDoubleClicked(QListWidgetItem *item)
{
    //    qInfo() << ui->courseLw->row(item);
    //    emit selectedCourse(ui->courseLw->row(item));
}


void MainWindow::on_logoutBtn_clicked()
{
    //    qInfo()<< httptoxxt->courses().data;
    //       httptoxxt->login();
    ui->courseLw->clear();
    ui->tabWidget->setCurrentIndex(0);
    ui->tab1->setEnabled(true);
    ui->loginLabel->setText("");
    //    emit logout();

    qInfo() << "登出";
}


void MainWindow::on_courseLw_itemClicked(QListWidgetItem *item)
{
    //    listMn->popup(QCursor::pos());

//    dialog =new CourseActionsDialog(this);
    QScopedPointer<CourseActionsDialog> dialog(new CourseActionsDialog(this));

    dialog.data()->setParent(this);
    connect(dialog.data(),SIGNAL(activeBtnclicked()),this->listMn->actions()[0],SLOT(trigger()));
    connect(dialog.data(),SIGNAL(statisticsBtnclicked()),this->listMn->actions()[1],SLOT(trigger()));

    dialog->exec();
}

void MainWindow::on_MainWindow_customContextMenuRequested(const QPoint &pos)
{
    listMn->exec(QCursor::pos());
}

void MainWindow::getactivitylist(QAction *action)
{
    //    qInfo() << "ttttttt";
    qInfo() << action;
    if(action->text()!="活动列表") return;
    if(ui->courseLw->currentRow()!=-1){
        this->httptoxxt->getActivityList(ui->courseLw->currentRow());
    }
    QObject::connect(httptoxxt,SIGNAL(onActivityParseFinished()),this,SLOT(showActivityWidget()));
}

void MainWindow::showActivityWidget()
{
    ui->activeLw->clear();
    qInfo() << "获取成功！";
    foreach (Activity *activity, httptoxxt->getCourseList()[httptoxxt->currentIndex]->activitylist) {
        if(activity->getStatus()==1)
            ui->activeLw->addItem(activity->getNameOne()+"\t到期时间："+QDateTime::fromMSecsSinceEpoch(activity->getEndTime()).toString("yyyy-MM-dd hh:mm:ss"));
        else
            ui->activeLw->addItem(activity->getNameOne()+"\t已过期");
    }
    ui->tabWidget->setCurrentIndex(2);
}

void MainWindow::preAcademicStatistics(QAction *action)
{
    if(action->text()!="学情统计") return;
    if(ui->courseLw->currentRow()!=-1){
        //        this->httptoxxt->getActivityList(ui->courseLw->currentRow());
        QObject::connect(httptoxxt,&HttpToXxt::onStatisticsParsed,this,&MainWindow::execAcademicStatistics);
        this->httptoxxt->getAcademic(ui->courseLw->currentRow());
    }

}

void MainWindow::execAcademicStatistics()
{
    QObject::disconnect(httptoxxt,&HttpToXxt::onStatisticsParsed,this,&MainWindow::execAcademicStatistics);
    //    AcademicStatisticsWidget *asw=new AcademicStatisticsWidget(this);
    QPointer<AcademicStatisticsWidget> asw=new AcademicStatisticsWidget(this);
//    QScopedPointer<AcademicStatisticsWidget> asw(new AcademicStatisticsWidget(this));
    asw->setParent(this);
    asw->resize(this->size());
    asw->inittab1(httptoxxt->dp()->signSL);
    asw->inittab2(httptoxxt->getUid(),httptoxxt->getName(),httptoxxt->getCourseList().at(httptoxxt->currentIndex)->getPointsList());
    asw->inittab3(httptoxxt->getName(),httptoxxt->getCourseList().at(httptoxxt->currentIndex)->HomeWorkList,httptoxxt->getCourseList().at(httptoxxt->currentIndex)->HomeWorkPointsList);
    asw->show();
}



//void MainWindow::on_courseLw_itemPressed(QListWidgetItem *item)
//{

//}

