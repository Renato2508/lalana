@echo off
cls
xcopy "bin" "app/WEB-INF/classes" /s /e /y
cd app 
jar -cvf ../voyage.war .
cd ..
copy "voyage.war" "D:\app\apache-tomcat-10.0.22\webapps" 





