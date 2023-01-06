#include "courseactionsdialog.h"
#include "ui_courseactionsdialog.h"
CourseActionsDialog::CourseActionsDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::CourseActionsDialog)
{
    ui->setupUi(this);
}

CourseActionsDialog::~CourseActionsDialog()
{
    delete ui;
}




void CourseActionsDialog::on_activeBtn_clicked()
{
    emit this->activeBtnclicked();
    this->close();
}


void CourseActionsDialog::on_statisticsBtn_clicked()
{
    emit this->statisticsBtnclicked();
    this->close();
}

