FROM ubuntu:16.04

RUN apt-get update && \
	apt-get install -y openjdk-8-jdk && \
	apt-get install -y ant && \
	apt-get clean && \
	rm -rf /var/lib/apt/lists/*
	
RUN apt-get update && \
	apt-get install -y ca-certificates-java && \
	apt-get clean && \
	update-ca-certificates -f && \
	rm -rf /var/lib/apt/lists/*
	
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

RUN export PATH=$PATH:$JAVA_HOME/bin

RUN mkdir -p /opt/app

ADD target/weather-api-0.1.0.jar /opt/app/weather-api-0.1.0.jar

EXPOSE 8080 22

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /opt/app/weather-api-0.1.0.jar" ]