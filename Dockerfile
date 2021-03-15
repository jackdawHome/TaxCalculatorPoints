FROM fabric8/java-alpine-openjdk11-jre
RUN mkdir /opt/app
COPY ./target/TaxCalculatorPoints-1.0-SNAPSHOT-jar-with-dependencies.jar /opt/app/TaxCalculatorPoints.jar