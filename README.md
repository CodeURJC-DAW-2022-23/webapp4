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
