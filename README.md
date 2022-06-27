# BackEndG1CP2
BACKEND DESARRROLLADO G1 CP2:

- INTEGRANTES: 

1. CHRISTIAN PATRICIO SALINAS VILLA
2. PATRICIO ROLANDO CUEVA GONZAGA 
3. FRANKLIN SANTIAGO DOMINGUEZ YANQUE
4. GISELLE XIMENA ORTIZ VILLALTA
5. KEVIN ALEXANDER HERNANDEZ VASQUEZ
6. KEVIN VINICIO AGUILAR LITUMA
7. LISSETH THALIA SIZALIMA GUAYLLASACA
8. MARIELA LISSETH NIEVES LUPERCIO
9. ROLANDO PATRICIO REMACHE ORTIZ
10. VERONICA LORENA SARI MOLINA


*************************************************************************************************************************************************

- HERRAMIENTAS DE USO:

 1. HEROKU: PARA DESPLIEGUE DE APLICACION EN LA NUBE
 2. SPRINGBOOT: PARA DESARROLLO DE SERRVICIOS CONSUMIDOS EN FRONT-END.
 3. SWAGGER: LIBRERIA PARA DOCUMENTACION DE SERVICIOS.
 4. JJWT: SEGURIDAD PARA DATOS QUE CONTENDRA EL PROYECTO

*************************************************************************************************************************************************

- VERSIONES DE PLUGINS: 

  1. JAVA-VERSION:8
  2. SNAPSHOT: 0.0.1-SNAPSHOT
  3. SPRING BOOT: 2.7.0
  4. HEROKU PLUGIN: 3.0.3
  5. JSON WEB TOKEN: 0.9.1
  6. VALIDATION API: 2.0.1.Final
  7. SPRING FOX: 3.0.0


*************************************************************************************************************************************************
- DESCRIPCION DE LAS ESTRUCTURAS: 

1. Para la creacion de anexos y poder descargarlos, en la aprte del front, todos estos anexos se encuentran precargados en la ruta "/uploads".
2. La estructura del proyecto esta compuesta por "controllers - models - repositories - security - services", cada carpeta cuenta con diferentes archivos para la ejecucion del proyecto, estas carpetas de las puede apreciar en la ruta "/src/main/Grupo1/BackEndG1CP2"
3. Dentro de las carpetas "models - repositories" se clasifican en clases modelos para la creacion de tablas, y otra carpeta extra "Views - Repositories/ReposiotiresViews" se encuentran clases de vistas que se utilizan para poder listar y obtener resultados en la parte visual. 

*************************************************************************************************************************************************
- REQUISITOS PARA LA EJECUCION DEL BACKEND:

1. El usuario que desee probar todo el proyecto de back, debera contar con la version JDK No. 8, ya que el proyecto fue desarrollado bajo esas caracteristicas.
2. En cuanto a los datos base que contendra el proyecto, estos se encuentra precargados dentro del archivo "import.sql" dentro de la ruta "/src/main/resources"
3. Debido a que el proyecto esta desplegado por la herramienta HEROKU, debe ingresar datos de una base local, ya que si ejecutan con las credenciales precargadas en el archivo "application.properties" en la ruta "/src/main/resources" este borrara todos los avences presentes hasta la fecha dentro del aplicativo. 

Ejmplo: 

spring.mvc.pathmatch.matching-strategy=ant-path-matcher


spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.datasource.url=jdbc:postgresql://localhost:5432/bdg1c2
spring.datasource.username=postgres
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.hibernate.show-sql=true


#Security
#Variable Firma de Seguridad
jwt.secret= secret
jwt.expiration= 36000   


*************************************************************************************************************************************************
- ENLACE DE APLICACION DESPLEGADO POR HEROLU: 

1. https://backendg1c2.herokuapp.com/swagger-ui/#/

*************************************************************************************************************************************************
- SEGURIDAD DE PROYECTO:

Para poder resguardar datos y todo informacion "sensible que tendra el proyecto" de utilzo JWT, ya que es una herramienta la cual nos ayuda a bloquear rutas, las cuales pueden ser accedidas por entidades externas, la clasificion de seguridad que tiene el proyecto, es la siguiente:

1. En la carpeta "security" que esta en la ruta "/src/java/Grupo1/BackEndG1CP2" se encuentran controladores, modelos, enums, repositories, services, utils. Cada archivo que se encuentra nos ayuda para la seguirdad del proyecto, ya que por medio de esta se crean usuarios para podeer acceder son estos a las rutas con la cual cuenta el proyecto.

# BackEndG1CP2
