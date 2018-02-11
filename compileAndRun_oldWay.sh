#!/bin/bash

CLASSPATH=".:libs/lib/classmate-1.3.4.jar:libs/lib/hibernate-validator-5.3.6.Final.jar:libs/lib/jackson-annotations-2.8.0.jar:libs/lib/jackson-core-2.8.10.jar:libs/lib/jackson-databind-2.8.10.jar:libs/lib/jboss-logging-3.3.1.Final.jar:libs/lib/jcl-over-slf4j-1.7.25.jar:libs/lib/jul-to-slf4j-1.7.25.jar:libs/lib/log4j-over-slf4j-1.7.25.jar:libs/lib/logback-classic-1.1.11.jar:libs/lib/logback-core-1.1.11.jar:libs/lib/postgresql-9.4.1208-jdbc42-atlassian-hosted.jar:libs/lib/slf4j-api-1.7.25.jar:libs/lib/snakeyaml-1.17.jar:libs/lib/spring-aop-4.3.14.RELEASE.jar:libs/lib/spring-beans-4.3.14.RELEASE.jar:libs/lib/spring-boot-1.5.10.RELEASE.jar:libs/lib/spring-boot-autoconfigure-1.5.10.RELEASE.jar:libs/lib/spring-boot-starter-1.5.10.RELEASE.jar:libs/lib/spring-boot-starter-logging-1.5.10.RELEASE.jar:libs/lib/spring-boot-starter-tomcat-1.5.10.RELEASE.jar:libs/lib/spring-boot-starter-web-1.5.10.RELEASE.jar:libs/lib/spring-context-4.3.14.RELEASE.jar:libs/lib/spring-core-4.3.14.RELEASE.jar:libs/lib/spring-expression-4.3.14.RELEASE.jar:libs/lib/spring-web-4.3.14.RELEASE.jar:libs/lib/spring-webmvc-4.3.14.RELEASE.jar:libs/lib/tomcat-annotations-api-8.5.27.jar:libs/lib/tomcat-embed-core-8.5.27.jar:libs/lib/tomcat-embed-el-8.5.27.jar:libs/lib/tomcat-embed-websocket-8.5.27.jar:libs/lib/validation-api-1.1.0.Final.jar"

javac -d bin -sourcepath src/main/java/ -cp $CLASSPATH src/main/java/bis332/Application.java
