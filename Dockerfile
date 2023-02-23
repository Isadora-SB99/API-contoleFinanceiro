FROM openjdk:11
EXPOSE 8000
WORKDIR /app
//COPY target/controleFinanceiro-0.0.3-SNAPSHOT.jar controleFinanceiro-0.0.3-SNAPSHOT.jar
COPY jar-artifact controleFinanceiro-0.0.3-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/controleFinanceiro-0.0.3-SNAPSHOT.jar"]
