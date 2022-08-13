FROM maven:3-openjdk-17

ENV MOUNT_POINT="/opt/mount-point"
ENV SOLUTION_CODE_PATH="/opt/client/solution"
ENV COMPILE_LOG_LOCATION="/opt/client/compile.json"

RUN mkdir /app
WORKDIR /app
COPY ./target/*.jar /app/it-cup-first-stage.jar
EXPOSE 9081
ENTRYPOINT ["java", "-jar", "/app/it-cup-first-stage.jar"]


