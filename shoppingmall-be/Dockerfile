FROM gradle:8.1.1-jdk17 AS build

USER root
WORKDIR /app

# 안전한 캐시 경로 설정
ENV GRADLE_USER_HOME=/home/gradle/.gradle-cache

# gradle wrapper 파일만 먼저 복사
COPY gradlew .
COPY gradle gradle/

# 실행 권한 부여
RUN apt update && apt install -y dos2unix
RUN chmod +x gradlew && dos2unix gradlew

# 나머지 파일 복사
COPY . .

# 의존성 다운로드만 먼저 수행
RUN ./gradlew dependencies --no-daemon

# 빌드
RUN ./gradlew build -x test --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8280
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]
