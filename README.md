#  Jakarta EE JAXRS Sample

![compile and build](https://github.com/hantsy/jakartaee-jaxrs-sample/workflows/build/badge.svg)
![Integration Test with Arquillian Payara Managed Container](https://github.com/hantsy/jakartaee-jaxrs-sample/workflows/it-with-arq-payara-managed/badge.svg)


A Jakarta Restful Web Service Sample application based on the [Jakarta EE 8 Starter](https://github.com/hantsy/jakartaee8-starter-boilerplate) boilerplate.

This project is the successor of [Java EE 8 Jaxrs Sample](https://github.com/hantsy/javaee8-jaxrs-sample) and [Java EE 7 Jaxrs Sample](https://github.com/hantsy/ee7-jaxrs-sample), and updated to the new Jakarta EE 8 API, including:

* Jakarta Restful Web Service
* Jakarta Enterprise Beans/Jakarta Persistence/Jakarta Bean Validation
* Jakarta Context and Dependency Injection
* Jakarta Security Enterprise API
* Microprofile Config API

> Note: In this sample, I upgraded the codebase to Java 11  and used the latest Payara 5 to run the application by default. 


## Build

1. Clone a copy of the source codes.

   ```bash
   git clone https://github.com/hantsy/jakartaee-jaxrs-sample
   ```

2. Run on Payara, WildFly or Open Liberty.

   ```bash
   mvn clean package cargo:run -Ppayara-local
   //or deploy to a running payara server
   mvn clean package cargo:deploy -Ppayara-remote
   
   //run on Wildfly server
   mvn clean wildfly:run -Pwildfly
   
   //run on Open Liberty
   mvn clean liberty:create dependency:copy liberty:run -Popenliberty
   ```
   
## Reference

* [Securing JAX-RS Endpoints with JWT](https://antoniogoncalves.org/2016/10/03/securing-jax-rs-endpoints-with-jwt/)
* [Java EE Security Essentials](https://dzone.com/refcardz/getting-started-java-ee?chapter=1)
* [JAX-RS, JWT & a pinch of JSR 375](https://abhirockzz.wordpress.com/2016/03/21/jax-rs-jwt-a-pinch-of-jsr-375/)
* [JSON Web Token in action with JAX-RS](https://abhirockzz.wordpress.com/2016/03/18/json-web-token-in-action-with-jax-rs/)
* [Java Magazine Hub](https://java-magazine-hub.zeef.com/)
* [Efficient JAX-RS: Conditional GETs & PUTs](https://abhirockzz.wordpress.com/2016/03/27/efficient-jax-rs-conditional-gets-puts/)
* [Java EE Security API - Soteria](https://www.n-k.de/2018/07/java-ee-security-api-jsr-375-soteria.html)

  
