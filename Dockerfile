FROM openjdk:8
ADD target/blog-0.0.1-SNAPSHOT.jar blog-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","blog-0.0.1-SNAPSHOT.jar"]