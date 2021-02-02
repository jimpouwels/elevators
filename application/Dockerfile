ARG REPO_LOCATION=docker.na1.hsdp.io/
ARG BASE_VERSION=latest
FROM ${REPO_LOCATION}phe_images/cce-base-image-jre11:${BASE_VERSION}
ENV JAR_FILE="amwell-adapter.jar"
ADD build/libs/*.jar /app/${JAR_FILE}
ENTRYPOINT ["/app/run-app.sh"]