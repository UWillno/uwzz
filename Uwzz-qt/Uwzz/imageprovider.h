#ifndef IMAGEPROVIDER_H
#define IMAGEPROVIDER_H

//#include <QObject>
#include <QQuickImageProvider>

class ImageProvider : public QQuickImageProvider
{
//    Q_OBJECT
public:
//    explicit ImageProvider(QObject *parent = nullptr);
    ImageProvider();
    QPixmap requestPixmap(const QString &id, QSize *size, const QSize &requestedSize);
};

#endif // IMAGEPROVIDER_H
