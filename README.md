#  Jakarta REST Example

![compile and build](https://github.com/hantsy/jakartaee-jaxrs-sample/workflows/build/badge.svg)

The repository has already been upgraded to Jakarta EE 11.

* For the Jakarta EE 10 version, please check the [EE10 archive](https://github.com/hantsy/jakartaee-rest-sample/releases/tag/ee10) or [EE10 tag](https://github.com/hantsy/jakartaee-rest-sample/tree/ee10).
* For the Jakarta EE 8 version, please check the [EE8 archive](https://github.com/hantsy/jakartaee-rest-sample/releases/tag/1.0.0) and [EE8 tag](https://github.com/hantsy/jakartaee-rest-sample/tree/1.0.0).

For the example projects before Jakarta EE 8, check the following repositories instead.

* [Java EE 8 Jaxrs Sample](https://github.com/hantsy/javaee8-jaxrs-sample)
* [Java EE 7 Jaxrs Sample](https://github.com/hantsy/ee7-jaxrs-sample)

Compared to the previous Jakarta EE 10 version, the following changes are applied:

* Update Jakarta EE API to 11.0.0 and Glassfish to 8.0.0
* Replace all EJB codes with pure CDI codes
* Use record type as possible

## Prerequisites

* Java 21
* The latest Apache Maven 3.x or 4.x

## Build

1. Clone a copy of the source codes.

   ```bash
   git clone https://github.com/hantsy/jakartaee-jaxrs-sample
   ```

2. Run on Glassfish.

   ```bash
   mvn clean package cargo:run -Pglassfish
   ```
   
3. Run Integration Tests against Arquillian Glassfish Managed Adapter

   ```bash 
   mvn clean verify -Parq-glassfish-managed
   ```
   
## Reference

* [Securing JAX-RS Endpoints with JWT](https://antoniogoncalves.org/2016/10/03/securing-jax-rs-endpoints-with-jwt/)
* [Java EE Security Essentials](https://dzone.com/refcardz/getting-started-java-ee?chapter=1)
* [JAX-RS, JWT & a pinch of JSR 375](https://abhirockzz.wordpress.com/2016/03/21/jax-rs-jwt-a-pinch-of-jsr-375/)
* [JSON Web Token in action with JAX-RS](https://abhirockzz.wordpress.com/2016/03/18/json-web-token-in-action-with-jax-rs/)
* [Java Magazine Hub](https://java-magazine-hub.zeef.com/)
* [Efficient JAX-RS: Conditional GETs & PUTs](https://abhirockzz.wordpress.com/2016/03/27/efficient-jax-rs-conditional-gets-puts/)
* [Java EE Security API - Soteria](https://www.n-k.de/2018/07/java-ee-security-api-jsr-375-soteria.html)

  
