#include "course.h"

Course::Course(QString name, QString classId, QString courseId, QString image,QObject *parent):name(name),classId(classId),courseId(courseId),image(image)
{

}

const QString &Course::getName() const
{
    return name;
}

void Course::setName(const QString &newName)
{
    if (name == newName)
        return;
    name = newName;
    emit nameChanged();
}

const QString &Course::getClassId() const
{
    return classId;
}

void Course::setClassId(const QString &newClassId)
{
    if (classId == newClassId)
        return;
    classId = newClassId;
    emit classIdChanged();
}

const QString &Course::getCourseId() const
{
    return courseId;
}

void Course::setCourseId(const QString &newCourseId)
{
    if (courseId == newCourseId)
        return;
    courseId = newCourseId;
    emit courseIdChanged();
}

const QString &Course::getImage() const
{
    return image;
}

void Course::setImage(const QString &newImage)
{
    if (image == newImage)
        return;
    image = newImage;
    emit imageChanged();
}

const QList<Points *> &Course::getPointsList() const
{
    return PointsList;
}

void Course::setPointsList(const QList<Points *> &newPointsList)
{
    if (PointsList == newPointsList)
        return;
    PointsList = newPointsList;
    emit PointsListChanged();
}



