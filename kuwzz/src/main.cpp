/*
    SPDX-License-Identifier: GPL-2.0-or-later
    SPDX-FileCopyrightText: 2023 UWillno <uwillno@outlook.com>
*/

#include <QApplication>
#include <QQmlApplicationEngine>
#include <QUrl>
#include <QtQml>

#include "about.h"
#include "app.h"
#include "version-kuwzz.h"
#include <KAboutData>
#include <KLocalizedContext>
#include <KLocalizedString>
#include <QtWebEngine>
#include "kuwzzconfig.h"
#include "debug.h"
#include <QQmlContext>

Q_DECL_EXPORT int main(int argc, char *argv[])
{
    QGuiApplication::setAttribute(Qt::AA_EnableHighDpiScaling);
    QApplication app(argc, argv);
    QCoreApplication::setOrganizationName(QStringLiteral("KDE"));
    QCoreApplication::setApplicationName(QStringLiteral("KUwzz"));

    KAboutData aboutData(
                         // The program name used internally.
                         QStringLiteral("KUwzz"),
                         // A displayable program name string.
                         i18nc("@title", "KUwzz"),
                         // The program version string.
                         QStringLiteral(KUWZZ_VERSION_STRING),
                         // Short description of what the app does.
                         i18n("Application Description"),
                         // The license this code is released under.
                         KAboutLicense::GPL,
                         // Copyright Statement.
                         i18n("(c) 2023"));
    aboutData.addAuthor(i18nc("@info:credit", "UWillno"),
                        i18nc("@info:credit", "Author Role"),
                        QStringLiteral("uwillno@outlook.com"),
                        QStringLiteral("https://yourwebsite.com"));
    KAboutData::setApplicationData(aboutData);

    QQmlApplicationEngine engine;
    QtWebEngine::initialize();
    auto config = KUwzzConfig::self();

    qmlRegisterSingletonInstance("org.kde.KUwzz", 1, 0, "Config", config);

    AboutType about;
    qmlRegisterSingletonInstance("org.kde.KUwzz", 1, 0, "AboutType", &about);

    App application;
    qmlRegisterSingletonInstance("org.kde.KUwzz", 1, 0, "App", &application);

    engine.rootContext()->setContextObject(new KLocalizedContext(&engine));
    engine.load(QUrl(QStringLiteral("qrc:///main.qml")));

    Debug debug;

    engine.rootContext()->setContextProperty("db",&debug);



    if (engine.rootObjects().isEmpty()) {
        return -1;
    }

    return app.exec();
}
