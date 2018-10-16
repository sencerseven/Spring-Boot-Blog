FROM openjdk:8
ADD target/spring-blog.jar spring-blog.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-blog.jar"]