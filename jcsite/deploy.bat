@echo off

CALL mvn -DskipTests package

set TOMCATDIR=\apps\tomcat\apache-tomcat-8.0.35

del \apps\tomcat\apache-tomcat-8.0.35\webapps\website.war
REM rmdir /S/Q \apps\tomcat\apache-tomcat-8.0.35\webapps\website
REM PING 1.1.1.1 -n 2 -w 60000 >NUL
copy /Y target\website.war %TOMCATDIR%\webapps\website.war

dir %TOMCATDIR%\webapps\

REM tail -100f %TOMCATDIR%\logs\catalina.out 
