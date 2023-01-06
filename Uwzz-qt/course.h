#ifndef COURSE_H
#define COURSE_H

#include "activity.h"
#include "homework.h"
#include "homeworkpoints.h"
#include "points.h"
#include <QObject>

class Course : public QObject
{
    Q_OBJECT

    Q_PROPERTY(QString name READ getName WRITE setName NOTIFY nameChanged)
    Q_PROPERTY(QString classId READ getClassId WRITE setClassId NOTIFY classIdChanged)
    Q_PROPERTY(QString courseId READ getCourseId WRITE setCourseId NOTIFY courseIdChanged)
    Q_PROPERTY(QString image READ getImage WRITE setImage NOTIFY imageChanged)

public:
    explicit Course(QString name,QString classId,QString courseId,QString image, QObject *parent = nullptr);

    QList<Activity*> activitylist;

    const QString &getName() const;
    void setName(const QString &newName);

    const QString &getClassId() const;
    void setClassId(const QString &newClassId);

    const QString &getCourseId() const;
    void setCourseId(const QString &newCourseId);

    const QString &getImage() const;
    void setImage(const QString &newImage);

    const QList<Points *> &getPointsList() const;
    void setPointsList(const QList<Points *> &newPointsList);
    QList<Points*> PointsList;
    QList<HomeWork*> HomeWorkList;
    QList<HomeWorkPoints*> HomeWorkPointsList;

signals:


    void nameChanged();

    void classIdChanged();

    void courseIdChanged();

    void imageChanged();

    void PointsListChanged();

private:
    QString name;
    QString classId;
    QString courseId;
    QString image;



    Q_PROPERTY(QList<Points *> PointsList READ getPointsList WRITE setPointsList NOTIFY PointsListChanged)
};

#endif // COURSE_H
