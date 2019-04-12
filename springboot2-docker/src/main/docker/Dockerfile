FROM java:8

VOLUME /ROOT

ADD springboot2-docker.jar springboot2-docker.jar

RUN bash -c 'touch /springboot2-docker.jar'

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone

EXPOSE 8282

ENTRYPOINT ["java", "-jar", "springboot2-docker.jar"]
