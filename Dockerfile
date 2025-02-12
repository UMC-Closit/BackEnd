# OpenJDK 기반 이미지 사용
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일 복사
COPY build/libs/*.jar app.jar

# 캐시없이 빌드
RUN ./gradlew clean build --no-build-cache

# 컨테이너 실행 시 실행할 명령어
CMD ["java", "-jar", "app.jar"]
