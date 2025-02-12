# OpenJDK 기반 이미지 사용
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# Gradle 캐시 강제 새로고침
RUN gradle clean build --refresh-dependencies --no-daemon

# JAR 파일 복사
COPY build/libs/*.jar app.jar

# 컨테이너 실행 시 실행할 명령어
CMD ["java", "-jar", "app.jar"]
