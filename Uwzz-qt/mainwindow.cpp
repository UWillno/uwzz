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
    QObject::connect(this,&MainWindow::logout,httptoxxt,&HttpToXxt::logout);

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
    emit selectedCourse(ui->courseLw->row(item));
}


void MainWindow::on_logoutBtn_clicked()
{
    //    qInfo()<< httptoxxt->courses().data;
    //       httptoxxt->login();
    ui->courseLw->clear();
    ui->tabWidget->setCurrentIndex(0);
    ui->tab1->setEnabled(true);
    emit logout();

    qInfo() << "登出";
}

