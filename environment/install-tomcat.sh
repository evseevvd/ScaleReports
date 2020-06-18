#!/bin/bash
TEXT_COLOR_RED='\033[0;31m'
TEXT_COLOR_GREEN='\033[0;32m'
SERVER_DIRECTORY=/opt/server
TEMP_DIRECTORY=/opt/tmp
BUILD_DIRECTORY=/opt/build
JAVA_HOME=/usr/lib/jvm/java-8-oracle/jre
APP_NAME=ScaleReports
APP_DIRECTORY="$BUILD_DIRECTORY"/"$APP_NAME"
APP_BUILD="$APP_DIRECTORY"/build
APP_WEB="$APP_DIRECTORY"/web
TOMCAT_USER=tomcat

if [ ! -d "$JAVA_HOME" ]; then
    echo -e "${TEXT_COLOR_GREEN}Install Java"
    apt-get install -y python-software-properties &&
    add-apt-repository ppa:webupd8team/java &&
    apt-get update &&
    apt-get install -y oracle-java8-installer
    echo "JAVA_HOME=$JAVA_HOME" >> /etc/environment &&
    source /etc/environment
else
        echo -e "${TEXT_COLOR_GREEN}Java already installed"
fi

if [ ! -d "$SERVER_DIRECTORY" ]; then
    echo -e "${TEXT_COLOR_GREEN}Install tomcat 8 into /opt/server"
    mkdir "$TEMP_DIRECTORY"
    wget -P "$TEMP_DIRECTORY" http://apache-mirror.rbc.ru/pub/apache/tomcat/tomcat-8/v8.5.15/bin/apache-tomcat-8.5.15.tar.gz
    tar -xvzf "$TEMP_DIRECTORY"/apache-tomcat-8.5.15.tar.gz -C "$TEMP_DIRECTORY"
    rm "$TEMP_DIRECTORY"/apache-tomcat-8.5.15.tar.gz
    mkdir "$SERVER_DIRECTORY"
    cp -R "$TEMP_DIRECTORY"/apache-tomcat-8.5.15/* "$SERVER_DIRECTORY"
    rm -rf "$TEMP_DIRECTORY"
else
        echo -e "${TEXT_COLOR_GREEN}tomcat 8 already installed"
fi

if ! id $TOMCAT_USER >/dev/null 2>&1; then
        echo -e "${TEXT_COLOR_GREEN}Create user '$TOMCAT_USER' for web server"
        useradd $TOMCAT_USER
        mkhomedir_helper $TOMCAT_USER
fi
echo -e "${TEXT_COLOR_GREEN}Build frontend application $APP_NAME"
cd "$APP_WEB"
su - $TOMCAT_USER npm install && bower install && grunt build release

if [ ! -d "$BUILD_DIRECTORY" ]; then
        echo -e "${TEXT_COLOR_GREEN}Cloning application repository $APP_NAME"
        mkdir "$BUILD_DIRECTORY"
        cd "$BUILD_DIRECTORY"
        git clone https://github.com/evseevvd/ScaleReports.git
fi

if [ -d "$APP_DIRECTORY" ]; then
    echo -e "${TEXT_COLOR_GREEN}Build application $APP_NAME"
    cd "$APP_NAME"
    chmod +x gradlew
    ./gradlew build
else
    echo -e "${TEXT_COLOR_RED}Error cloning app repository"
    exit 1;
fi

if [ ! -d "$APP_BUILD" ]; then
    echo -e "${TEXT_COLOR_RED}Error build application"
    exit 1;
else
    echo -e "${TEXT_COLOR_GREEN}Deploy application $APP_NAME"
fi




