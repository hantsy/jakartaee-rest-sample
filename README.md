#  Jakarta EE JAXRS Sample

A Jakarta Restful WebService Sample application based on the [jakartaee8-starter](https://github.com/hantsy/jakartaee8-starter) boilerplate.

## Build

1. Clone a copy of the source codes.

   ```bash
   git clone https://github.com/hantsy/jakartaee-jaxrs-sample
   ```

2. Run on Glassfish, Wildfly or Open Liberty.

   ```bash
   mvn clean package cargo:run -pglassfish-local
   mvn clean wildfly:run -Pwildfly
   mvn clean liberty:create dependency:copy liberty:run -Popenliberty
   ```
   
## Reference

* 