FROM adoptopenjdk/openjdk14-openj9
WORKDIR /app
ADD ./target/store-prices.jar /app/store-prices.jar
CMD ["java", "-Xmx512m", "-jar", "/app/store-prices.jar"]
