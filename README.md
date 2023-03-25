# JWT-Authentication

refered from:
1) https://www.youtube.com/watch?v=KxqlJblhzfI
2) https://www.youtube.com/watch?v=P_29bHsVjjg

Flow of code:
(https://user-images.githubusercontent.com/111079517/210486085-6dfbdbb9-7c41-4f01-b190-8ccc07c252e2.png)





#application.yml config read

I also faced the same issue. Tried lot of things, but it nothing worked.
In the end, when I added below dependency in pom, my application is able to read application.yml file.
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-configuration-processor</artifactId>
</dependency>
