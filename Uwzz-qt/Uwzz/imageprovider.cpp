#include "imageprovider.h"

#include <QEventLoop>
#include <QNetworkReply>
#include <QNetworkRequest>

ImageProvider::ImageProvider() : QQuickImageProvider(QQuickImageProvider::Pixmap)
{

}

QPixmap ImageProvider::requestPixmap(const QString &id, QSize *size, const QSize &requestedSize)
{
    qInfo()<<id;
    QUrl url(id);
    QNetworkRequest request(url);
    QNetworkAccessManager manager;

    // Set custom headers if needed
    request.setRawHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");

    QNetworkReply *reply = manager.get(request);

    QEventLoop loop;
    QObject::connect(reply, &QNetworkReply::finished, &loop, &QEventLoop::quit);
    loop.exec();

    // Handle the reply and extract the pixmap

    QPixmap pixmap;
    // Your code to process the reply and extract the pixmap
    pixmap.loadFromData(reply->readAll());

    if (size)
        *size = pixmap.size();

    return pixmap;
}
