# Jakarta EE REST Example

[![build](https://github.com/hantsy/jakartaee-rest-sample/actions/workflows/build.yml/badge.svg)](https://github.com/hantsy/jakartaee-rest-sample/actions/workflows/build.yml)

This repository has been updated to **Jakarta EE 11**. Compared to the Jakarta EE 10 version, this branch includes:

* Jakarta EE API updated to 11.0.0 and GlassFish upgraded to 8.0.0
* All EJB code replaced with CDI equivalents
* Use of Java `record` types where appropriate

### Previous releases

* **Jakarta EE 10:** see the [EE10 archive](https://github.com/hantsy/jakartaee-rest-sample/releases/tag/ee10) or the [EE10 tag](https://github.com/hantsy/jakartaee-rest-sample/tree/ee10).
* **Jakarta EE 8:** see the [EE8 archive](https://github.com/hantsy/jakartaee-rest-sample/releases/tag/1.0.0) or the [EE8 tag](https://github.com/hantsy/jakartaee-rest-sample/tree/1.0.0).

For even earlier examples (prior to Jakarta EE 8), check these repositories:

* [Java EE 8 JAX‑RS Sample](https://github.com/hantsy/javaee8-jaxrs-sample)
* [Java EE 7 JAX‑RS Sample](https://github.com/hantsy/ee7-jaxrs-sample)


## Prerequisites

* Java 21
* Apache Maven 3.x or 4.x (latest)

## Building and running

1. Clone the repository:

   ```bash
   git clone https://github.com/hantsy/jakartaee-jaxrs-sample
   ```

2. Start the application on GlassFish:

   ```bash
   mvn clean package cargo:run -Pglassfish
   ```

3. Execute the integration tests using the Arquillian GlassFish managed adapter:

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
