#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "httptoxxt.h"
#include <QQuickWidget>
#include <QQmlContext>
#include <QListWidgetItem>

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

private:
    Ui::MainWindow *ui;
    HttpToXxt *httptoxxt;
};
#endif // MAINWINDOW_H
