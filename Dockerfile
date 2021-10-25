FROM gradle:7.2.0-jdk11@sha256:fbe69451d195c2a5a2865ada5c41ffb774ea2242bd0ae8582b0ae15785950f6c as BUILD
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN gradle clean check build
CMD "pwd"

FROM adoptopenjdk/openjdk11:jre-11.0.12_7-alpine@sha256:8046268491eba319173b2673311f957173a3d3b4092cbe7275eb9392024e674a
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=BUILD project/build/libs/*.jar /app/application.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser
CMD "dumb-init" "java" "-jar" "java-application.jar"



