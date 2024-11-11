# Usar una imagen base de Java
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR de tu aplicación al contenedor
COPY target/exchange-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación escucha
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
