FROM gradle:jdk11-alpine

COPY . /home/gradle/project/.
WORKDIR /home/gradle/project
RUN gradle clean distZip
RUN mv build/distributions/Example.zip .
RUN unzip Example.zip
EXPOSE 8080
CMD Example/bin/Example
