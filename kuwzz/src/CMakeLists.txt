# SPDX-License-Identifier: BSD-3-Clause
# SPDX-FileCopyrightText: 2023 UWillno <uwillno@outlook.com>

add_executable(kuwzz
    main.cpp
    about.cpp
    app.cpp
    debug.h debug.cpp
    resources.qrc)

target_link_libraries(kuwzz
    Qt5::Core
    Qt5::Gui
    Qt5::Qml
    Qt5::Quick
    Qt5::QuickControls2
    Qt5::Svg
    Qt5::WebEngine
    KF5::I18n
    KF5::CoreAddons
    KF5::ConfigCore
    KF5::ConfigGui)

if (ANDROID)
    kirigami_package_breeze_icons(ICONS
        list-add
        help-about
        application-exit
        applications-graphics
    )
endif()

kconfig_add_kcfg_files(kuwzz GENERATE_MOC kuwzzconfig.kcfgc)
install(TARGETS kuwzz ${KDE_INSTALL_TARGETS_DEFAULT_ARGS})
