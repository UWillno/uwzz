#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "httptoxxt.h"
#include <QQuickWidget>
#include <QQmlContext>
#include <QListWidgetItem>
#include <QButtonGroup>
#include "academicstatisticswidget.h"
#include "courseactionsdialog.h"
#include "documentparse.h"
QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();
signals:
    void selectedCourse(int index);
    void logout();

private slots:
    void on_loginBtn_clicked();
    void loginF();
    void loginS();

    void updateCourseList(QList<Course*> data);
    void on_courseLw_itemDoubleClicked(QListWidgetItem *item);

    void on_logoutBtn_clicked();

    void on_courseLw_itemClicked(QListWidgetItem *item);

    void on_MainWindow_customContextMenuRequested(const QPoint &pos);
    void getactivitylist(QAction *action);
    void showActivityWidget();
    void preAcademicStatistics(QAction *action);
    void execAcademicStatistics();
//    void on_courseLw_itemPressed(QListWidgetItem *item);

private:
//    CourseActionsDialog *dialog;
    Ui::MainWindow *ui;
    HttpToXxt *httptoxxt;
    QMenu *listMn;

};
#endif // MAINWINDOW_H
