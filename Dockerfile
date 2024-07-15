# base-image
FROM adoptopenjdk/openjdk11
# COPY에서 사용될 경로 변수
# ARG JAR_FILE=build/libs/*.jar

WORKDIR /app

# jar 빌드 파일을 도커 컨테이너로 복사
COPY build/libs/WSIWT_BACK-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 5000


# jar 파일 실행
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","app.jar"]