# JDK 17 기반의 경량 OpenJDK 이미지 사용
FROM eclipse-temurin:17-jre-jammy

# 애플리케이션 실행 디렉토리 설정
WORKDIR /app

# JAR 파일을 컨테이너에 복사
COPY build/libs/*.jar app.jar

# 포트 노출 (Spring Boot의 기본 포트)
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
