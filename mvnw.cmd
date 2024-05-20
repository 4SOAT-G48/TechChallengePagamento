
@echo off
setlocal
set MVNW_REPO=https://repo.maven.apache.org/maven2
set MVNW_VERSION=3.8.4
set MVNW_DISTRO=apache-maven-%MVNW_VERSION%-bin.zip

:: Verify if Maven Wrapper is already installed
if not exist ".mvn\wrapper\maven-wrapper.jar" (
  mkdir ".mvn\wrapper"
  curl -o .mvn\wrapper\maven-wrapper.jar %MVNW_REPO%/org/apache/maven/wrapper/maven-wrapper/%MVNW_VERSION%/maven-wrapper-%MVNW_VERSION%.jar
)

:: Verify if Maven distribution is already installed
if not exist ".mvn\maven" (
  mkdir ".mvn\maven"
  curl -o .mvn\maven\%MVNW_DISTRO% %MVNW_REPO%/org/apache/maven/apache-maven/%MVNW_VERSION%/%MVNW_DISTRO%
  unzip .mvn\maven\%MVNW_DISTRO% -d .mvn\maven
)

:: Run Maven
.mvn\mavenpache-maven-%MVNW_VERSION%in\mvn %*
endlocal
