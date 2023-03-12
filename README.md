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
<image src="/images/relations.png" alt="entities relations">

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
    - Inicio:...
              
- Instrucciones de ejecución:  
    - Para el uso de la aplicación nosotros hemos usado GitHub Desktop, MySQL Workbench 8.0 y Visual Studio Code. Para poder ejecutar la aplicación en Visual Studio Code es necesario instalarse las extensiones de "Extension Pack for Java" y "Spring Boot Extension Pack".
    - Una vez hecho eso, lo primero sería clonar el repositorio y situarte sobre la rama main, una vez en main, con Visual Studio (o el editor que uses) abres la carpeta \webapp4\Backend\idealTrip que es la carpeta donde se encuentra todo el código de la aplicación. 
    - Cuando ya estás dentro de la carpeta, buscas el archivo "IdealTripApplication.java" que es el archivo main del programa y el que arrancará la aplicación.
    - Antes de hacer esto, en MySQL Workbench debes crear una instancia local de MySQL, es importante que la constraseña no sea password, pues puede dar problemas. En nuestro caso pusimos password.12345 y no hemos tenido inconvenientes.
    - Cuando ya tenemos la instancia de la base de datos, ejecutamos el archivo "IdealTripApplication.java" y veremos como se conecta la base de datos con la aplicación y se empiezan a crear las tablas con sus respectivos datos. Cuando haya terminado, ya podremos usar la aplicación.
    - Para hacer esto, debemos ir en nuestro navegador a "https://localhost:8443" y cuando nos dé el aviso de que la conexión no es privada debemos clickar en "Advanced" y posteriormente en "Proceed to localhost (unsafe)"
    - Si hemos hecho todo correctamente, ya estaremos dentro de la aplicación, con todas las funcionalidades y los datos cargados.

