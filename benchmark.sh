#!/usr/bin/env bash
echo "Benchmark:LOG4J" > jmh.log
./mvnw clean package -P log4j
java -jar target/logging-benchmark-log4j.jar | tee -a jmh.log
mv ./jmh-result.csv ./jmh-log4j.csv

echo "Benchmark:JUL" >> jmh.log
./mvnw clean package -P jul
java -jar target/logging-benchmark-jul.jar | tee -a jmh.log
mv ./jmh-result.csv ./jmh-jul.csv

echo "Benchmark:LOGBACK" >> jmh.log
./mvnw clean package -P logback
java -jar target/logging-benchmark-logback.jar | tee -a jmh.log
mv ./jmh-result.csv ./jmh-logback.csv
