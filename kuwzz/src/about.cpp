// SPDX-License-Identifier: GPL-2.0-or-later
// PDX-FileCopyrightText: 2023 UWillno <uwillno@outlook.com>

#include "about.h"

KAboutData AboutType::aboutData() const
{
    return KAboutData::applicationData();
}
