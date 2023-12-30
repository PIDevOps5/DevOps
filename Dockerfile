# Étape de construction (stage 1)
FROM adoptopenjdk/maven-openjdk8 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Étape d'exécution (stage 2)
FROM adoptopenjdk/openjdk8
COPY --from=build /app/target/SkiStationProject-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /app
EXPOSE 3306
CMD ["java", "-jar", "app.jar"]
