@echo off

mvn clean deploy -DskipTests=true -DrepositoryId=rdc-releases -f ../pom.xml
