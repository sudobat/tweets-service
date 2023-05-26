# Tweet-Service

## Descripcio

Micro  Servei  que  permet  operar  amb  tweets

## Taula  de  continguts

If your README is long, add a table of contents to make it easy for users to find what they need.


## Instalacio

Generar imatge

> mvn spring-boot:build-image -Dspring-boot.build-image.imageName=caixabanktech/tweets-service
 
Alternativa : 

> docker build -t caixabanktech/tweets-service .

Arrancar imatge micro

> docker run -p 8080:8080 -t docker.io/caixabanktech/tweets-service:latest

Arrancar imatge apuntant a mysql

> docker run  -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t docker.io/caixabankteh/tweets-service:latest 

Arrancar mysql port 3306

> docker-compose up

Arrancar mysql + micro 

> docker-compose -f docker-compose-all.yml up

Parar 

> docker-compose down

Testing del micro

> mvn verify -DskipIT=false -Dmaven.test.ski

Para validar los tweets

> curl http://localhost:8080/tweets

## Us
 
Per compilar 

> mvn clean install

Per compilar sense test

> mvn clean install -Dmaven.test.skip

 Per arrancar el servidor

  > mvn spring-boot:run

### Profiles

* default - BBDD en memoria H2
* dev - BBDD mySql (necesari la imatge docker adicional)

Per arrancar indicant profie (en aquest cas mysql).

>   mvn spring-boot:run -Dspring-boot.run.profiles=dev

## Credits  

Col·laboradors

* Marc  Alvarez

* Carles  Bescós

* Roger  Gras

## Tests

Per als test d'integració
  
> mvn verify -DskipIT=false -Dmaven.test.skip



