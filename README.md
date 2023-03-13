# webapp4
## Nombre aplicación web:
IdealTrip

## Temática:
Agencia de planificación de viajes, recomendaciones y opiniones por usuarios.

## Integrantes:

| Nombre| Apellidos| Correo| Github|
| ----- | ---- | ----|----|
| David| Moreno Martín | d.moreno1.2019@alumnos.urjc.es| DavidMorenoo
| Sergio| Cuadros Flores | s.cuadros.2020@alumnos.urjc.es| scuadrosf
| Adrián | Pedroche Rata | a.pedroche.2020@alumnos.urjc.es| AdriPdr
| Jorge Ezequiel | de Francisco Bernal | je.defrancisco.2020@alumnos.urjc.es | JrgDfrn
| ShuHeng| Ye| s.ye.2020@aulmnos.urjc.es| shy10op

## Trello:
https://trello.com/b/ggomNP2X/idealtrip

# Fase 0: Formación del equipo y definición de las funcionalidades de la web

## Aspectos principales de la web:
- Entidades: 
    - Usuario 
    - Destino 
    - Alojamiento 
    - Reserva
    - Opinión

    
## Relaciones entre entidades
<image src="/images/relation.png" alt="entities relations">

La relación de entidades podría ser: Un **usuario** selecciona un **destino** y **compra** un alquiler para hospedarse en un **alojamiento**
    
- Permisos de los usuarios:  
    - Anónimos: Podrá únicamente realizar una búsqueda simple, sin determinados filtros y sin poder reservar. 
    - Registrados: Disponen de todas las funcionalidades permitidas, como reservar, opinar y demás. Además, también disponen de los filtros, según el destino puedes filtrar por gastronomía, cultura, entretenimiento, etc. Sin embargo, no tendrán el control total de la aplicación web. Por último, los usuarios registrados podrán editar su perfil, añadiendo un avatar entre otras cosas.
    - Administrador: Tendrá el control total sobre la información de la web. Por ejemplo, revisar y controlar las opiniones, o subir la base de datos con los diferentes alojamientos, transportes u opiniones sobre los lugares.

- Imágenes: 
    - Usuario registrado: Tendrá la posibilidad de subir un avatar a su perfil.
    - Alojamientos: Cada alojamiento tendrá sus correspondientes fotos para que los usuarios puedan ver el sitio antes de hacer la reserva.
    - Destinos: Imagen de los diferentes destinos que se ofrecen.
    - Logotipo de la agencia
    - Imágenes creativas que apliquen una buena armonía.
  
- Gráficos:  
    - Destinos más visitados (diagrama de barras)
    - Cuántos viajes se realizan cada mes a un destino (diagrama de barras)
    
- Tecnología complementaria:
    - Google Maps con puntos interesantes dependiendo del filtro
    - Envío correos electrónicos con novedades en destinos. (Newsletter) (método de suscripción para recibir correos)
    - Generador de PDF (Ticket de viajes/Factura)
    - Street View: La web mostrará las fotos del destino.

- Algoritmo avanzado:
    - Sistema de valoraciones: La web mostrará primero los destinos mejor valorados.
    - Algoritmo de ordenación basado en filtros
    
    
# Fase 1: Maquetación de páginas con HTML y CSS
    
En esta fase hemos, diseñado y maquetado la aplicación mediante HTML, CSS y Javascrip. Además hemos definido las relaciones entre las diferentes pantallas, y que tipos de usuarios tendrán acceso a estas.
    
- Descripción de pantallas:
    - Inicio: Esta pantalla contiene un buscador con un desplegable para escoger entre varios destinos del catálogo, además de tener un widget para escoger la fecha en la que se desea viajar. También dispone de un menú horizontal en la parte superior donde puedes navegar a otras ventanas como es a la de destinos o a la de contacto. También nos facilita un botón donde podremos iniciar sesión o registrarnos. Por último, dispondremos de un catálogo de todos los destinos de los que disponemos con un breve resumen.
    <image src="/images/Principal.jpg" alt="entities relations">
    - Destinos: Esta panatalla también tiene en la parte superior el menú para facilitar la navegación. El objetivo de esta es poder escoger entre todos los destinos de los que disponemos para que el usuario haga “click” encima del que quiera descubrir más información.
    <image src="/images/Destinations.jpg" alt="entities relations">
    - Inicio de sesión/Registro: En esta pantalla se le permitirá al usuario no registrado poder registrarse, o bien si ya está registrado poder iniciar sesión.
    <image src="/images/Login.jpg" alt="entities relations">
    <image src="/images/Login2.jpg" alt="entities relations">
    - Menú del destino: Esta pantalla nos facilita un menú con 4 opciones: restauración, turismo, opiniones y alojamientos. También nos aparecerá debajo una gráfica que muestra la cantidad de gente que ha escogido ese destino en los diferentes meses del año. 
    <image src="/images/EachDestination.jpg" alt="entities relations">
    - Turismo: Esta pantalla contiene varias imágenes de monumentos y sitios de interés del destino previamente escogido, acompañados de una breve descripción si ponemos el cursor encima de las fotos.
    <image src="/images/Place.jpg" alt="entities relations">
    - Alojamientos: Esta pantalla nos muestra una descripción del alojamiento del destino escogido, además de varias imágenes del mismo. Además, se nos brinda la oportunidad de cerrar ya nuestra reserva si pulsamos el botón de compra que nos destinara a la ventana de pago.
    <image src="/images/House.jpg" alt="entities relations">
    - Pago: Esta pantalla, nos proporcionará un breve resumen sobre la reserva que hemos realizado, así como el destino, el precio y los días que hemos elegido. Se nos dará la opción de escoger el método de pago. Una vez escogido, tendremos que introducir los datos y darle a confirmar para que se efectúe el pago.
    <image src="/images/Payment.jpg" alt="entities relations">
    - Contáctanos: Esta pantalla muestra la información de la compañía, así como dónde se ubica, el número de teléfono, y la ubicación en Google Maps. También se facilita un formulario para que el usuario se pueda comunicar con nosotros.
    <image src="/images/Contact.jpg" alt="entities relations">
    - Administrador: Esta pantalla permite al usuario con el rol de administrador gestionar a todos los usuarios registrados.
    <image src="/images/Admin.jpg" alt="entities relations">
    - Opinión: Esta pantalla nos permite añadir una valoración del destino además de una reseña. También se nos permitirá ver las opniniones de los demás usuarios
    <image src="/images/AddReview.jpg" alt="entities relations">
    -Restauración: Esta pantalla al igual que la de turismo, nos mostrará imágenes de la gastronomía típica del destino preseleccionado junto a una pequeña descricpción cuando coloquemos el cursor encima de la imagen
        <image src="/images/Catering.jpg" alt="entities relations">
            
 - Relación entre las distintas pantallas de la aplicación web:
          <image src="/images/IdealTrip.jpg" alt="entities relations">  
              
              
# Fase 2: Web con HTML generado en servidor y AJAX

    
En esta fase hemos implementado la aplicación completa, lo hemos hecho mediante SpringBoot y MySQL, además, también hemos añadido datos de ejemplo a la base de datos que se cargan al arrancar la aplicación, estos datos han sido especificados en el archivo "InitDataBase.java" y son mostrados dinamicamente en la aplicación. Las imágenes también han sido incluidas en la base de datos para facilitar el despliegue en entornos restringidos
              
También hemos incluido una página de error que salta cuando ocurre algún error o se intenta acceder a alguna URL inexistente, esta página de error va acorde a la temática del resto de la aplicación.
              
La paginación mediante JS y AJAX también ha sido incluida en la parte de reviews, de este modo, no se incluyen todas las reviews a primera vista, sino que es necesario pulstar un botón de "Mostrar más", cuando este botón es pulsado, no se recarga la página entera, sino que solamente se carga la parte de las 5 reviews siguientes.
              
Además, disponemos 3 tipos de usuarios implementados mediante Spring Security, usuario NO registrado, usuario Registrado y usuario Administrador, cada uno con sus funciones delimitadas por su rol. Por poner algunos ejemplos, un usuario NO registrado no puede alquilar un alojamiento, debe registrarse en la aplicación para poder hacerlo. Del mismo modo, un usuario Registrado puede visitar su perfil, mientras que un No registrado no puede hacerlo.
              
Estas son solo algunas de las funciones implementadas en esta segunda fase, ahora continuaremos con la navegación de la aplicación, que a pesar de tener similitudes, también incluye nuevas pantallas.
              
    
- Navegación actualizada:
    - Inicio: Esta pantalla contiene un buscador con un desplegable para escoger entre varios destinos del catálogo, además de tener un widget para escoger la fecha en la que se desea viajar. También dispone de un menú horizontal en la parte superior donde puedes navegar a otras ventanas como es a la de destinos o a la de contacto. También nos facilita un botón donde podremos iniciar sesión o registrarnos.
    <image src="/images/Inicio.jpg">
    -Destinos: Esta panatalla también tiene en la parte superior el menú para facilitar la navegación. El objetivo de esta es poder escoger entre todos los destinos de los que disponemos para que el usuario haga “click” encima del que quiera descubrir más información.
    <image src="/images/DestinationsV2.jpg" >
    - Inicio de sesión/Registro: En esta pantalla se le permitirá al usuario no registrado poder registrarse, o bien si ya está registrado poder iniciar sesión.
    <image src="/images/LoginV2.jpg">
    <image src="/images/RegisterV2.jpg">
    - Menú del destino: Esta pantalla nos facilita un menú con 4 opciones: restauración, turismo, opiniones y alojamientos.
    <image src="/images/EachDestinationV2.jpg">
    - Turismo: Esta pantalla contiene varias imágenes de monumentos y sitios de interés del destino previamente escogido, acompañados de una breve descripción si ponemos el cursor encima de las fotos.
    <image src="/images/PlaceV2.jpg">
    - Alojamientos: Esta pantalla nos muestra una descripción del alojamiento del destino escogido, además de varias imágenes del mismo. Además, se nos brinda la oportunidad de cerrar ya nuestra reserva si pulsamos el botón de compra que nos destinara a la ventana de pago.
    <image src="/images/HouseV2.jpg">
    - Pago: Esta pantalla, nos proporcionará un breve resumen sobre la reserva que hemos realizado, así como el destino, el precio y los días que hemos elegido. Se nos dará la opción de escoger el método de pago. Una vez escogido, tendremos que introducir los datos y darle a confirmar para que se efectúe el pago.
    <image src="/images/PaymentV2.jpg" alt="entities relations">
    <image src="/images/EmailPaymenV2.jpg" alt="entities relations">
    - Contáctanos: Esta pantalla muestra la información de la compañía, así como dónde se ubica, el número de teléfono, y la ubicación en Google Maps. También se facilita un formulario para que el usuario se pueda comunicar con nosotros.
    <image src="/images/ContactV2.jpg" alt="entities relations">
    - Administrador: Esta pantalla permite al usuario con el rol de administrador gestionar a todos los usuarios registrados.
    <image src="/images/AdminV2.jpg" alt="entities relations">
    <image src="/images/AdminV22.jpg" alt="entities relations">
    - Opinión: Esta pantalla nos permite añadir una valoración del destino además de una reseña si estamos registrados (Primera imagen) y en caso contrario únicamente dejará visualizar valoraciones (Segunda imagen). También se nos permitirá ver las opniniones de los demás usuarios
    <image src="/images/AddReviewV2.jpg" alt="entities relations">
    <image src="/images/ReviewV2.jpg" alt="entities relations">
    -Restauración: Esta pantalla al igual que la de turismo, nos mostrará imágenes de la gastronomía típica del destino preseleccionado junto a una pequeña descricpción cuando coloquemos el cursor encima de la imagen
        <image src="/images/CateringV2.jpg" alt="entities relations">
 - Relación entre las distintas pantallas de la aplicación web (Diagrama de navegación):
          <image src="/images/IdealTripV2.jpg" alt="Diagrama de navegacion">
              
- Instrucciones de ejecución:  
    - Para el uso de la aplicación nosotros hemos usado GitHub Desktop, MySQL Workbench 8.0 y Visual Studio Code. Para poder ejecutar la aplicación en Visual Studio Code es necesario instalarse las extensiones de "Extension Pack for Java" y "Spring Boot Extension Pack".
    - Una vez hecho eso, lo primero sería clonar el repositorio y situarte sobre la rama main, una vez en main, con Visual Studio (o el editor que uses) abres la carpeta \webapp4\Backend\idealTrip que es la carpeta donde se encuentra todo el código de la aplicación. 
    - Cuando ya estás dentro de la carpeta, buscas el archivo "IdealTripApplication.java" que es el archivo main del programa y el que arrancará la aplicación.
    - Antes de hacer esto, en MySQL Workbench debes crear una instancia local de MySQL, es importante que la constraseña no sea password, pues puede dar problemas. En nuestro caso pusimos password.12345 y no hemos tenido inconvenientes.
    - Cuando ya tenemos la instancia de la base de datos, ejecutamos el archivo "IdealTripApplication.java" y veremos como se conecta la base de datos con la aplicación y se empiezan a crear las tablas con sus respectivos datos. Cuando haya terminado, ya podremos usar la aplicación.
    - Para hacer esto, debemos ir en nuestro navegador a "https://localhost:8443" y cuando nos dé el aviso de que la conexión no es privada debemos clickar en "Advanced" y posteriormente en "Proceed to localhost (unsafe)"
    - Si hemos hecho todo correctamente, ya estaremos dentro de la aplicación, con todas las funcionalidades y los datos cargados.

- Diagrama con las entidades de la base de datos  

     <image src="/images/EntityDiagram.png" alt="entities relations">  
           
- Diagrama de clases y templates:  
              <image src="/images/DiagramaDeClasesIdealTrip.png" >  

           
- Participación de miembros:  
### Sergio Cuadros Flores
#### Descripción textual: 
En esta fase mi participación principal ha sido en la inicialización del proyecto, configurando todos los archivos iniciales, con la creación de la base de datos, introducción de los datos precargados, así como gestionar la pantalla en la que se muestran los diferentes destinos y el control de pantallas mediante el id del destino y las posteriores pantallas de catering (Restauración) y tourism (Turismo). Además, he gestionado la parte de seguridad relacionada con el https, cifrado SSL, control del puerto 8443. De igual manera, me he encargado del control del login y registrarse, añadiendo los usuarios registrados a la base de datos. Asimismo, he estado pendiente de la realización de las tareas de los demás compañeros, apoyando y ayudando en todo lo que necesitasen en todo momento. He gestionado el control del header y footer, así como la colaboración en la realización de la base de datos de newsletter junto con mi compañero Jorge. Por último he podido gestionar junto con mi compañero Adrián el control de permisos de los usuarios dependiendo de la ruta a la que podían tener acceso o no debido a su rol de registrados, no registrados y administradores.

Mis commits no son grandes, por tanto no hay commits que engloben varias o una funcionalidad completa dado que he ido haciendo commits progresivos y continuados.
#### 5 commits más significativos

| Commit  | Descripción                                      | Link                                                                                            |
| ------------- | ------------------------------------------------ | ----------------------------------------------------------------------------------------------- |
| #1            | Creación de los ficheros para la base de datos   | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/bdc8de0bf1caf2ee3fa4d3a24473bb3ab44c1c16 | 
| #2            | Inicialización de la base de datos | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/e42f966f5174151cba04efe9794156ecffbe5d00 | 
| #3            | Mostar los destinos dependiendo de su id |https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/3ccb292c92f487b25ee26849889bd540ffeba827| 
| #4            | Realización de login y registro	   | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/3c7a0ffa90b49c54ce3a7184edf4c9f710d16e0c#diff-21a6e6c8cd127959bb4596c8985e0b4d7f56a3c2f8f290f76bb233876ebfbc5a | 
| #5            | Control pantallas catering y tourism			   | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/86872c79e208e112467ca4235a08bc94476d2135 | 
|               |                                                   | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/b7d766286e0e82c8bd45246eb8eaaa39140cb41f|
#### 5 ficheros más participación

| Número fichero  | Fichero                                      | 
| ------------- | ------------------------------------------------ |
| #1            | <a href="https://github.com/CodeURJC-DAW-2022-23/webapp4/blob/main/Backend/idealTrip/src/main/java/com/idealtrip/idealTrip/service/InitDatabase.java">InitDatabase.java</a>		   | 
| #2            | <a href="https://github.com/CodeURJC-DAW-2022-23/webapp4/blob/main/Backend/idealTrip/src/main/java/com/idealtrip/idealTrip/controller/LoginController.java">LoginController.java</a> | 
| #3            | <a href="https://github.com/CodeURJC-DAW-2022-23/webapp4/blob/main/Backend/idealTrip/src/main/java/com/idealtrip/idealTrip/controller/ServiceController.java">ServiceController.java</a> |
| #4            | <a href="https://github.com/CodeURJC-DAW-2022-23/webapp4/blob/main/Backend/idealTrip/src/main/java/com/idealtrip/idealTrip/controller/ProfileController.java">ProfileController.java</a>	   | 
| #5            | <a href="https://github.com/CodeURJC-DAW-2022-23/webapp4/tree/main/Backend/idealTrip/src/main/java/com/idealtrip/idealTrip/model>Directorio Model</a>			   | 
         
### David Moreno Martín
#### Descripción textual: 
Empecé encargándome de realizar el mapping de todas las ciudades, así como de todos los servicios de estas (Turismo, Catering, Opiniones y Alojamiento), después trabajé en mostrar las reviews hechas por los usuarios en su perfil, mostrando únicamente aquellas realizadas por ese usuario. También trabaje con la base de datos para hacer que, en lugar de mostrar 1 única review para todos los usuarios, cada usuario "escribiera" 3 reviews sobre cada destino con una puntuación aleatoria. Posteriormente incluí en cada alojamiento una descripción personalizada y la posibilidad de verlos mediante google maps, ambos cargándose desde la base de datos. Asimismo, implementé la paginación mediante JavaScript y AJAX, de este modo, las reviews son cargadas en segundo plano y no es necesario recargar toda la página.  
Además de esto, he corregido errores, traducido código, eliminado warnings y actualizado la página de error 404 para ser coherente en base al estilo del resto de la aplicación. Por último, también he participado activamente en el ReadMe de esta segunda fase.

#### 5 commits más significativos

| Commit  | Descripción                                      | Link                                                                                            |
| ------------- | ------------------------------------------------ | ----------------------------------------------------------------------------------------------- |
| #1            | Mapping general de todas las ciudades y servicios		   | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/aa48a5969832f7f1a9d6ff2ad3321225cf1cf713#diff-80b956c241b8bcfa657bad6d158f25fc2d34e46fa8fe46c4f15ac3f8643113dd | 
| #2            | Mostrar reviews en perfil usuarios | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/ec26fef71bfa556d23f9171b1a3350d3d93492c8 | 
| #3            | Página error | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/6ac548755f840408a550881a30247e1f69aeaf23 | 
| #4            | Google maps alojamientos	   | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/dbf3a9e16b2bf70f014a3aef0b8575a76224744c | 
| #5            | Añadida paginación con AJAX y JS | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/ce305a774cb7d9474e80e2ab92947bb05f4a1d8a | 
         
#### 5 ficheros más participación

| Número fichero  | Fichero                                      | 
| ------------- | ------------------------------------------------ |
| #1            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx		   | 
| #2            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx | 
| #3            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx |
| #4            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx	   | 
| #5            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx			   |
  
### Adrián Pedroche Rata
#### Descripción textual: 
En primer lugar, comencé añadiendo toda la información correspondiente a cada destino, es decir, los sitios turísticos y la comida típica. Posteriormente, me encargué de realizar la gestión de los ususarios, distinguiendo entre usuario registrado, no registrado o administrador, dándoles permisos según sea necesario (esto último junto a Sergio). 
Más adelante, me encargué de permitir que el usuario puediera cerrar su sesión (logout para usuarios registrados). Entre medias introduje la acción de mostrar una página html de error cuando se intentase acceder a una URL inexistente. Posteriormente me centré en el buscador de países que tenemos implementado en el "/index". Para terminar la fase intentando construir un diagrama de barras a partir de la media de las opiniones registradas por los ususarios en un determinado destino. 

#### 5 commits más significativos

| Commit  | Descripción                                      | Link                                                                                            |
| ------------- | ------------------------------------------------ | ----------------------------------------------------------------------------------------------- |
| #1            | Error 404 al acceder a una URL inexistente	   | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/486a1d3593f69568b6eba0fee03abfb8ca68c23f | 
| #2            | Buscador de países de la página de inicio | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/9d6ca4142df3d78eacdb9d0f1213ca5358298fce | 
| #3            | Cerrar sesión del usuario | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/ab245851208c4ba051a139dc18bc3f43dd6075f5 | 
| #4            | Mustach para diferenciar ususarios, error al loguearse y springSecuiry| https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/c41054b53be30636f1e3adab6ae7b1d699e6ba19 | 
| #5            | Finalización de introducir toda la información de los destinos	   | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/23775f412952f9a1d9a8beaee1199ad61e922e56 | 

#### 5 ficheros más participación
         
| Número fichero  | Fichero                                      | 
| ------------- | ------------------------------------------------ |
| #1            | LogoutController.java		   | 
| #2            | LoginController.java | 
| #3            | IndexController.java |
| #4            | header.html	   | 
| #5            | index.html			   |
         
         
### Jorge Ezequiel	de Francisco Bernal
#### Descripción textual: 
xxxxxxxxxxxxxxxxxxxx

#### 5 commits más significativos

| Commit  | Descripción                                      | Link                                                                                            |
| ------------- | ------------------------------------------------ | ----------------------------------------------------------------------------------------------- |
| #1            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx		   | https://github.com/CodeURJC-DAW-2022-23/webapp4/tree/Desarrollo | 
| #2            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx | https://github.com/CodeURJC-DAW-2022-23/webapp4/tree/Desarrollo | 
| #3            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx | https://github.com/CodeURJC-DAW-2022-23/webapp4/tree/Desarrollo | 
| #4            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx	   | https://github.com/CodeURJC-DAW-2022-23/webapp4/tree/Desarrollo | 
| #5            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx			   | https://github.com/CodeURJC-DAW-2022-23/webapp4/tree/Desarrollo | 

#### 5 ficheros más participación

| Número fichero  | Fichero                                      | 
| ------------- | ------------------------------------------------ |
| #1            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx		   | 
| #2            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx | 
| #3            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx |
| #4            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx	   | 
| #5            | xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx			   |         
         
### ShuHeng	Ye
#### Descripción textual:

En cuanto a mi trabajo, aunque debido a un problema familiar mi tiempo para esta practica fue muy limitado, he logrado implementar una función que permite mostrar reseñas según el ID del destino. Además, he añadido algunas características para los usuarios, como la posibilidad de comentar en la página de opiniones, editar su nombre y apellido, y cambiar su foto de perfil para mostrar los avatar del usuarios en la pagina de reviews. También he creado una página de alojamiento y he integrado enlaces del StreetView para mostrarlo en la página de alojamiento.

#### 5 commits más significativos

| Commit  | Descripción                                      | Link                                                                                            |
| ------------- | ------------------------------------------------ | ----------------------------------------------------------------------------------------------- |
| #1            | Editar los datos del usuario en la pagina de perfil | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/7c798d7fff2ae97f358fee6e019ea04da63363be| 
| #2            |  Pagina de alojamiento | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/5d25941dc855e02edb4018643da30b446f50ee2f | 
| #3            | Guardar comentarios del usuario en la base de datos | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/dcfae4e807e54997dfe04dc1192e2d6596adcdb3| 
| #4            | Cambiar avatar y mostrar los avatar de todos los usuarios comentado | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/bad5e05eabd0125e59576c94f56aaa5d06f77d2c | 
| #5            | Mostrar los comentarios	               | https://github.com/CodeURJC-DAW-2022-23/webapp4/commit/5dbad07d183c0cbac12a65b5a1f9bf7a27a7800c| 

#### 5 ficheros más participación

| Número fichero  | Fichero                                      | 
| ------------- | ------------------------------------------------ |
| #1            | citiesController.java		   | 
| #2            | profileController.java | 
| #3            | reviewService.java |
| #4            | review.html	   | 
| #5            | house.html			   |         

  
