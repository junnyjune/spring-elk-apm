

./gradlew clean bootJar --parallel  
docker build --platform linux/amd64,linux/arm64 -t spring-test:1.0.1 .  
