FROM openjdk:11-jre-slim
WORKDIR /home
COPY /target/catmotionAPI.jar catmotionAPI.jar
ENTRYPOINT ["java", "-jar", "catmotionAPI.jar"]
