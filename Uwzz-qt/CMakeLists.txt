cmake_minimum_required(VERSION 3.5)

project(Uwzz2 VERSION 0.1 LANGUAGES CXX)

set(CMAKE_INCLUDE_CURRENT_DIR ON)

set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

find_package(QT NAMES Qt6 Qt5 REQUIRED COMPONENTS Widgets Network QuickWidgets Qml Charts)
find_package(Qt${QT_VERSION_MAJOR} REQUIRED COMPONENTS Widgets Network QuickWidgets Qml Charts)

set(PROJECT_SOURCES
    main.cpp
    mainwindow.cpp
    mainwindow.h
    mainwindow.ui
    httptoxxt.h
    httptoxxt.cpp
    documentparse.h
    documentparse.cpp
    course.h
    course.cpp
    qml.qrc
    activity.h activity.cpp
    academicstatisticswidget.h academicstatisticswidget.cpp academicstatisticswidget.ui
    courseactionsdialog.h courseactionsdialog.cpp courseactionsdialog.ui
#    personmodel.h personmodel.cpp
    points.h points.cpp
    homework.h homework.cpp
    homeworkpoints.h homeworkpoints.cpp
    activedialog.h activedialog.cpp activedialog.ui
)

if(${QT_VERSION_MAJOR} GREATER_EQUAL 6)
    qt_add_executable(Uwzz2
        MANUAL_FINALIZATION
        ${PROJECT_SOURCES}
    )
# Define target properties for Android with Qt 6 as:
#    set_property(TARGET Uwzz2 APPEND PROPERTY QT_ANDROID_PACKAGE_SOURCE_DIR
#                 ${CMAKE_CURRENT_SOURCE_DIR}/android)
# For more information, see https://doc.qt.io/qt-6/qt-add-executable.html#target-creation
else()
    if(ANDROID)
        add_library(Uwzz2 SHARED
            ${PROJECT_SOURCES}
        )
    # Define properties for Android with Qt 5 after find_package() calls as:
    #    set(ANDROID_PACKAGE_SOURCE_DIR "${CMAKE_CURRENT_SOURCE_DIR}/android")
else()
    add_executable(Uwzz2
        ${PROJECT_SOURCES}
    )
endif()
endif()

target_link_libraries(Uwzz2 PRIVATE Qt${QT_VERSION_MAJOR}::Widgets Qt${QT_VERSION_MAJOR}::Network Qt${QT_VERSION_MAJOR}::QuickWidgets Qt${QT_VERSION_MAJOR}::Charts Qt6::Qml)

set_target_properties(Uwzz2 PROPERTIES
    MACOSX_BUNDLE_GUI_IDENTIFIER my.example.com
    MACOSX_BUNDLE_BUNDLE_VERSION ${PROJECT_VERSION}
    MACOSX_BUNDLE_SHORT_VERSION_STRING ${PROJECT_VERSION_MAJOR}.${PROJECT_VERSION_MINOR}
    MACOSX_BUNDLE TRUE
    WIN32_EXECUTABLE TRUE
)
set_property(TARGET Uwzz2 PROPERTY QT_ANDROID_EXTRA_LIBS
    G:/android_openssl-1.1.1l_1.0.2u/latest/arm64/libcrypto_1_1.so
    G:/android_openssl-1.1.1l_1.0.2u/latest/arm64/libssl_1_1.so)

#set_property(TARGET Uwzz2 PROPERTY
#    C:/Program Files/OpenSSL-Win64/bin/libcrypto-1_1-x64.dll
#    C:/Program Files/OpenSSL-Win64/bin/libssl-1_1-x64.dll
#    )


install(TARGETS Uwzz2
    BUNDLE DESTINATION .
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR})

if(QT_VERSION_MAJOR EQUAL 6)
    qt_finalize_executable(Uwzz2)
endif()
