# Usa una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim as build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo `pom.xml` y las dependencias para construir la aplicación
COPY pom.xml .

# Descarga las dependencias de Maven (esto solo ocurre cuando el `pom.xml` cambia)
RUN mvn dependency:go-offline

# Copia el código fuente de la aplicación al contenedor
COPY src /app/src

# Construye la aplicación usando Maven
RUN mvn clean package -DskipTests

# Usa una imagen más ligera de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado en el contenedor desde la etapa anterior
COPY --from=build /app/target/accounttransactions_back-0.0.1-SNAPSHOT.jar /app/app.jar

# Expone el puerto 8080 para que la aplicación sea accesible fuera del contenedor
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
