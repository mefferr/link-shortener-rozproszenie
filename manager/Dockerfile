FROM public.ecr.aws/amazoncorretto/amazoncorretto:17.0.8-al2-native-headless as builder
WORKDIR /app
COPY src ./src
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw clean package -DskipTests

FROM public.ecr.aws/amazoncorretto/amazoncorretto:17.0.8-al2-native-headless
ENV JAVA_HOME=/usr/lib/jvm/java-17-amazon-corretto
WORKDIR /app
COPY --from=builder /app/target/magazyn-server-0.0.1-SNAPSHOT.jar ./
ENV SPRING_PROFILES_ACTIVE=cloud
EXPOSE 8080
CMD ["java", "-jar", "manager-0.0.1-SNAPSHOT.jar"]
