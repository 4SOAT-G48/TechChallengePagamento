
#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements. See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License. You may obtain a copy of the License at
# 
#     http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Maven Wrapper
# ----------------------------------------------------------------------------

MVNW_REPO=https://repo.maven.apache.org/maven2
MVNW_VERSION=3.8.4
MVNW_DISTRO=apache-maven-${MVNW_VERSION}-bin.zip

# Verify if Maven Wrapper is already installed
if [ ! -f ".mvn/wrapper/maven-wrapper.jar" ]; then
  mkdir -p .mvn/wrapper
  curl -o .mvn/wrapper/maven-wrapper.jar ${MVNW_REPO}/org/apache/maven/wrapper/maven-wrapper/${MVNW_VERSION}/maven-wrapper-${MVNW_VERSION}.jar
fi

# Verify if Maven distribution is already installed
if [ ! -d ".mvn/maven" ]; then
  mkdir -p .mvn/maven
  curl -o .mvn/maven/${MVNW_DISTRO} ${MVNW_REPO}/org/apache/maven/apache-maven/${MVNW_VERSION}/${MVNW_DISTRO}
  unzip .mvn/maven/${MVNW_DISTRO} -d .mvn/maven
fi

# Run Maven
exec .mvn/maven/apache-maven-${MVNW_VERSION}/bin/mvn "$@"
