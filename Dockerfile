# Use latest JDK version
FROM openjdk:latest
# Copy the jar package into tmp 
COPY ./target/coursework-group2-0.1.0.1-jar-with-dependencies.jar /tmp
# Set tmp as working directory of container
WORKDIR /tmp
# Execute the jar package
ENTRYPOINT ["java", "-jar", "coursework-group2-0.1.0.1-jar-with-dependencies.jar"]
