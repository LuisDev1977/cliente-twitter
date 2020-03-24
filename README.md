"# cliente-twitter" 

1) Es necesario disponer de una cuenta de desarrollador Twitter
2) Modificar todos los valores del fichero: \src\main\resources\twitter4j.properties
3) Breve explicación del microservicio:

Arrancar la aplicacion SpringBoot ClienteTwitterApplication.
La aplicación dispone de 2 controller para facilitar las pruebas:
1) twitter-buscador-controller: para consumir tweets
2) tweet-controller: API-REST para la gestión de BBDD en memoria 

Se puede acceder a los servicios a través del siguiente endpoint: http://localhost:8080/swagger-ui.html

Y a la consola de la BBDD H2 desde: http://localhost:8080/h2-console

Instrucciones para las pruebas:

1) Consumir tweets a través del siguiente endpoint: http://localhost:8080/twitter/search/{key}, donde {key} sería la palabra a buscar.

invocar el servicio 1 o varias veces cambiando la {key} para cargar tweets en nuestra BBDD.

2) Posteriormente, ya se podrían invocar el resto de Rest services.