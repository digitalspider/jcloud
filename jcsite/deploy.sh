#!/bin/bash

mvn package

TOMCATDIR=/opt/tomcat/tomcat-stage
#TOMCATDIR=/c/apps/tomcat/apache-tomcat-8.0.35

rm -rf $TOMCATDIR/webapps/website.war
sleep 2
cp target/website.war $TOMCATDIR/webapps/
tail -100f $TOMCATDIR/logs/catalina.out | sed -e 's/\(.*WARN.*\)/\o033[33m\1\o033[39m/' -e 's/\(.*ERROR.*\)/\o033[31m\1\o033[39m/'
