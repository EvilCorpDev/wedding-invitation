FROM eclipse-temurin:24-jre

WORKDIR /app

# optional but recommended
RUN mkdir -p /app/config

COPY wedding-invitation.jar /app/wedding-invitation.jar
COPY application-production.yaml /app/config/application-production.yaml
COPY run.sh /app/run.sh

RUN chmod +x /app/run.sh

EXPOSE 8085

ENTRYPOINT ["/app/run.sh"]