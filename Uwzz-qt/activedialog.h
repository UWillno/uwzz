#ifndef ACTIVEDIALOG_H
#define ACTIVEDIALOG_H

#include <QDialog>

namespace Ui {
class ActiveDialog;
}

class ActiveDialog : public QDialog
{
    Q_OBJECT

public:
    explicit ActiveDialog(QWidget *parent = nullptr);
    ~ActiveDialog();

private:
    Ui::ActiveDialog *ui;
};

#endif // ACTIVEDIALOG_H
