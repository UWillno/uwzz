#ifndef COURSEACTIONSDIALOG_H
#define COURSEACTIONSDIALOG_H

#include <QDialog>

namespace Ui {
class CourseActionsDialog;
}

class CourseActionsDialog : public QDialog
{
    Q_OBJECT

public:
    explicit CourseActionsDialog(QWidget *parent = nullptr);
    ~CourseActionsDialog();
signals:
    void activeBtnclicked();
    void statisticsBtnclicked();

private slots:

    void on_activeBtn_clicked();

    void on_statisticsBtn_clicked();

private:
    Ui::CourseActionsDialog *ui;
};

#endif // COURSEACTIONSDIALOG_H
