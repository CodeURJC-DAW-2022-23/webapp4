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
    - Transporte
    
## Relaciones entre entidades
<image src="/images/relations.png" alt="entities relations">

La relación de entidades podría ser: Un **usuario** va a un destino mediante un **transporte** y se hospeda en un **alojamiento**
    
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
    - Destinos más visitados (diagramad de barras)
    - Cuántos viajes se realizan cada mes a un destino (diagrama de barras)
    
- Tecnología complementaria:
    - Google Maps con puntos interesantes dependiendo del filtro
    - Envío correos electrónicos con novedades en destinos. (Newsletter) (método de suscripción para recibir correos)
    - Generador de PDF (Ticket de viajes/Factura)
    - Street View: La web mostrará las fotos del destino.

- Algoritmo avanzado:
    - Sistema de valoraciones: La web mostrará primero los destinos mejor valorados.
    - Algoritmo de ordenación basado en filtros
