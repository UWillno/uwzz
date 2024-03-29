// SPDX-License-Identifier: GPL-2.0-or-later
// SPDX-FileCopyrightText: 2023 UWillno <uwillno@outlook.com>

#pragma once

#include <QObject>
#include <KAboutData>

class AboutType : public QObject
{
    Q_OBJECT
    Q_PROPERTY(KAboutData aboutData READ aboutData CONSTANT)
public:
    [[nodiscard]] KAboutData aboutData() const;
};
