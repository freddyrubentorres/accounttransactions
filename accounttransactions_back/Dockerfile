# Usamos una imagen base de OpenJDK 17
FROM openjdk:17-slim AS build
# Instalar Maven
RUN apt-get update && apt-get install -y maven
# Establecer el directorio de trabajo en el contenedor
WORKDIR /app
# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml /app
RUN mvn dependency:go-offline
# Copiar el código fuente de la aplicación
COPY src /app/src
# Construir la aplicación y generar el archivo JAR
RUN mvn clean package -DskipTests
# Imagen para ejecutar la aplicación
FROM openjdk:17-slim
# Establecer el directorio de trabajo en el contenedor
WORKDIR /app
# Copiar el archivo JAR desde el contenedor de construcción
COPY --from=build /app/target/accounttransactions_back-0.0.1-SNAPSHOT.jar /app/accounttransactions_back.jar
# Exponer el puerto que usará la aplicación (por defecto en Spring Boot es 8080)
EXPOSE 8080
# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/accounttransactions_back.jar"]