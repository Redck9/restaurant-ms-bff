FROM openjdk:17
COPY build/libs/*.jar redck-restaurant-ms-bff.jar
COPY src/main/resources/restaurant-ms-bff/compose.yaml .
ENTRYPOINT ["java", "-jar", "redck-restaurant-ms-bff.jar"]
EXPOSE 8282
