#!/usr/bin/env bash

REPOSITORY=/opt/webapp/
# shellcheck disable=SC2164
cd $REPOSITORY

APP_NAME=arlabel
# shellcheck disable=SC2010
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep 'SNAPSHOT.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

# 서비스를 중지하여 현재 운영 중인 애플리케이션의 인스턴스를 안전하게 종료
sudo systemctl stop $APP_NAME.service

# 빌드된 JAR 파일을 애플리케이션 실행 경로로 복사하여 새 버전으로 업데이트
sudo cp $JAR_PATH /opt/webapp/$APP_NAME.jar

# 새로운 애플리케이션 버전을 시작하여 업데이트된 서비스를 실행
sudo systemctl start $APP_NAME.service

# 새로 시작된 서비스의 상태를 확인하여 정상적으로 실행되고 있는지 검증
sudo systemctl status $APP_NAME.service
