@echo off
cls
xcopy "bin" "app/WEB-INF/classes" /s /e /y
cd app 
jar -cvf ../voyage.war .
cd ..
xcopy "voyage.war" "D:\ITU\S3\Projet Mme Baovola\apache-tomcat-10.0.22\apache-tomcat-10.0.22\webapps" /s /e /y





