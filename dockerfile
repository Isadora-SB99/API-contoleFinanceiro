FROM openjdk:11
COPY ./target/controleFinanceiro.jar /app/controleFinanceiro.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "controleFinanceiro.jar"]
EXPOSE 8080