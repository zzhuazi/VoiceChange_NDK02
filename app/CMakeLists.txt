# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        native-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/cpp/native-lib.cpp)

set(my_lib_path ${CMAKE_SOURCE_DIR}/libs)

#添加三方的so库
add_library(
        libfmod
        SHARED
        IMPORTED)

#指定第三方库的绝对路径
set_target_properties(
        libfmod
        PROPERTIES IMPORTED_LOCATION
        ${my_lib_path}/${ANDROID_ABI}/libfmod.so)

#添加三方的so库
add_library(
        libfmodL
        SHARED
        IMPORTED)

#指定第三方库的绝对路径
set_target_properties(
        libfmodL
        PROPERTIES IMPORTED_LOCATION
        ${my_lib_path}/${ANDROID_ABI}/libfmodL.so)




# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

#导入路径，为了让编译时能够寻找到该文件夹
include_directories(src/main/cpp/inc)

target_link_libraries( # Specifies the target library.
        native-lib
        libfmod
        libfmodL
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})

