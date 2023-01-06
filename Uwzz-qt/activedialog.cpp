#include "activedialog.h"
#include "ui_activedialog.h"

ActiveDialog::ActiveDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::ActiveDialog)
{
    ui->setupUi(this);
}

ActiveDialog::~ActiveDialog()
{
    delete ui;
}
