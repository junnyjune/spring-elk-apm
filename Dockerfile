FROM eclipse-temurin:18-jdk AS builder
ENV JAR_FILE=build/libs/*.jar
COPY $JAR_FILE application.jar
COPY ./damo/dev /damo
RUN java -Djarmode=layertools -jar application.jar extract


FROM eclipse-temurin:18-jre

RUN mkdir -p /apm-data && chmod 755 /apm-data
COPY --from=builder dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder application/ ./
COPY --from=builder damo/ ./damo
#agent download 방식
COPY --from=docker.elastic.co/observability/apm-agent-java:latest /usr/agent/elastic-apm-agent.jar /apm-agent.jar

ENV ELASTIC_APM_ENVIRONMENT=test
ENV ELASTIC_APM_SERVICE_NAME=spring-apm-test
ENV ELASTIC_APM_SERVER_URL=http://vc-stg-apm.kakaohealthcare.com
ENV ELASTIC_APM_APPLICATION_PACKAGES=com.example.springapm
ENV ELASTIC_APM_CAPTURE_BODY=all
ENV ELASTIC_APM_PROFILING_INFERRED_SPANS_LIB_DIRECTORY=/apm-data
ENV ELASTIC_APM_PROFILING_INFERRED_SPANS_ENABLED=true
ENV ELASTIC_APM_TRACE_CONTINUATION_STRATEGY=restart
ENV ELASTIC_APM_TRANSACTION_SAMPLE_RATE=1
ENV ELASTIC_APM_CAPTURE_HEADERS=true
ENV SCP_INST_HOME=/damo
ENV LD_LIBRARY_PATH=/damo

ENTRYPOINT ["java", \
"-javaagent:/apm-agent.jar", \
"org.springframework.boot.loader.JarLauncher"]
