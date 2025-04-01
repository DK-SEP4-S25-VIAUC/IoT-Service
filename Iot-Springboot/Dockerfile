FROM mcr.microsoft.com/devcontainers/java:21

WORKDIR /src

# Install dependencies and configure Git using the build args
RUN apt-get update && apt-get install -y curl unzip git && rm -rf /var/lib/apt/lists/*

# Install Maven globally :
RUN curl -fsSL https://apache.osuosl.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz -o /tmp/maven.tar.gz \
    && tar -xzf /tmp/maven.tar.gz -C /opt \
    && ln -s /opt/apache-maven-3.9.6 /opt/maven \
    && rm /tmp/maven.tar.gz

# Set Maven environment variables
ENV MAVEN_HOME=/opt/maven
ENV PATH=${MAVEN_HOME}/bin:${PATH}

# Copy all project files into to the image
COPY . .

#HTTP port
EXPOSE 8080

CMD ["/bin/bash"]
