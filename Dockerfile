# Используем образ OpenJDK 17
FROM openjdk:17

# Установка переменных среды
ENV APP_HOME /app
ENV APP_JAR hospital-0.0.1-SNAPSHOT.jar

# Создание директории приложения
WORKDIR $APP_HOME

# Копирование JAR файла внутрь контейнера
COPY target/$APP_JAR $APP_HOME/

# Определение команды для запуска приложения
CMD ["java", "-jar", "hospital-0.0.1-SNAPSHOT.jar"]
