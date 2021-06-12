# Version: 1.0
FROM postgres:latest
MAINTAINER Joao Silva "jvgsilva180@gmail.com"
ENV REFRESHED_AT 2021-03-05
RUN apt-get update && apt-get install -y locales
RUN touch /usr/share/locale/locale.alias
ENV LANG pt_BR.UTF-8
ENV LANGUAGE pt_BR:pt
ENV LC_ALL pt_BR.UTF-8
RUN sed -i '/pt_BR.UTF-8/s/^# //g' /etc/locale.gen && locale-gen && update-locale LANG=pt_BR.UTF-8
ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
