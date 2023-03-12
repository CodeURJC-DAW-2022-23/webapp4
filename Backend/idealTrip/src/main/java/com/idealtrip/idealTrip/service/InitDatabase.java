package com.idealtrip.idealTrip.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.IOException;
import java.sql.Blob;

import javax.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.idealtrip.idealTrip.model.Catering;
import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.House;
import com.idealtrip.idealTrip.model.Newsletter;
import com.idealtrip.idealTrip.model.Purchase;
import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.Tourism;
import com.idealtrip.idealTrip.model.User;
//import com.idealtrip.idealTrip.repository.UserRepository;

@Service
public class InitDatabase {
        @Autowired
        private DestinationService destinations;

        @Autowired
        private CateringService caterings;

        @Autowired
        private HouseService houses;

        @Autowired
        private TourismService tourisms;

        @Autowired
        private NewsletterService newsletters;

        @Autowired
        private PurchaseService purchases;

        @Autowired
        private ReviewService reviews;

        @Autowired
        private UserService users;

        //@Autowired
        //private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @PostConstruct
        public void init() throws IOException {

                List<User> initedUsers = generateUser();
                for (User user : initedUsers) {
                        users.save(user);
                }

                List<Destination> initedDestinations = generateDestination();
                for (Destination destination : initedDestinations) {
                        destinations.save(destination);
                }

                List<House> initedHouses = generateHouse();
                for (House house : initedHouses) {
                        houses.save(house);
                }

                List<Catering> initedCatering = generateCatering(initedDestinations);
                for (Catering catering : initedCatering) {
                        caterings.save(catering);
                }

                List<Tourism> initedTourisms = generateTourism(initedDestinations);
                for (Tourism tourism : initedTourisms) {
                        tourisms.save(tourism);
                }

                List<Newsletter> initedNewsletters = generateNewsleter();
                for (Newsletter newsletter : initedNewsletters) {
                        newsletters.save(newsletter);
                }

                List<Review> initedReview = generateReviews(initedDestinations, initedUsers);
                for (Review review : initedReview) {
                        reviews.save(review);
                }
                List<Purchase> initedPurchase = generatePurchase(initedHouses, initedUsers);
                for (Purchase purchase : initedPurchase) {
                        purchases.save(purchase);
                }

        }

        private List<Purchase> generatePurchase(List<House> initedHouses, List<User> initedUsers) {
                List<Purchase> purchases = new ArrayList<>();
                for (User user : initedUsers) {
                        Purchase purchase = new Purchase();
                        purchase.setUser(user);
                        purchase.setHouse(initedHouses.get((int) (Math.random() * 5) + 1));
                        purchases.add(purchase);
                }
                return purchases;
        }

        private List<Newsletter> generateNewsleter() {
                List<Newsletter> newsletter = new ArrayList<>();
                for (int i = 1; i <= 4; i++) {
                        Newsletter news = new Newsletter();
                        news.setEmail(randomLastNameGenerator() + "1@gmail.com");
                        newsletter.add(news);
                }
                return newsletter;
        }

        private List<Tourism> generateTourism(List<Destination> initedDestinations) {
                List<Tourism> ListTourism = new ArrayList<>();
                List<String> namePlace = new ArrayList<>();
                List<String> contentPlace = new ArrayList<>();
                // Paris
                // namePlace.add(" ");
                namePlace.add("Torre Eiffel");
                namePlace.add("Arco del Triunfo");
                namePlace.add("Museo del Louvre");
                namePlace.add("Catedral de Notre Dame");
                namePlace.add("Basílica Sagrado Corazón");
                namePlace.add("Disneyland París");
                // contentPlace.add(" ");
                contentPlace.add(
                                "El edificio más alto del mundo por 41 años. Desde su construcción, hasta 1930, la Torre Eiffel fue el edificio más alto del mundo, gracias a sus 312 metros de altura");
                contentPlace.add(
                                "Uno de los monumentos más famosos, probablemente se trate del arco de triunfo más célebre del mundo. Construido entre 1806 y 1836 por orden de Napoleón Bonaparte para conmemorar la victoria en la batalla de Austerlitz");
                contentPlace.add(
                                "El Louvre es uno de los museos más grandes del mundo. En concreto, se ubica en el distrito 1, es decir, en pleno centro de París y recoge casi 35.000 objetos que abarcan desde la Prehistoria hasta el siglo XXI");
                contentPlace.add(
                                "Es una catedral de culto católico, sede de la archidiócesis de París, la capital de Francia. Dedicada a la Virgen María, se sitúa en la pequeña isla de la Cité");
                contentPlace.add(
                                "Su construcción fue decidida por la Asamblea Nacional en 1873, como un edificio religioso a perpetuidad en homenaje a la memoria de los ciudadanos que habían perdido la vida durante la Guerra franco-prusiana");
                contentPlace.add(
                                "Disneyland París es un complejo de entretenimiento en Francia. Abarca dos parques temáticos, hoteles resort, Disney Nature Resorts, un complejo comercial, gastronómico y de entretenimiento, y un campo de golf");
                // Bangkok
                namePlace.add("Templo del Buda Esmeralda");
                namePlace.add("Wat Pho");
                namePlace.add("Los canales de Bangkok");
                namePlace.add("Wat Arun");
                namePlace.add("Chinatown");
                namePlace.add("Wat Suthat");
                contentPlace.add(
                                "Construido a finales del S.XVIII, fue la residencia real y la sede del gobierno durante 150 años, hasta mediados del S. XX. Está compuesto por un conjunto arquitectónico de más de 100 construcciones en el que se puede apreciar la belleza de la arquitectura tradicional tailandesa");
                contentPlace.add(
                                "Wat Pho es uno de los templos más grandes y antiguos de Bangkok. Es muy conocido por albergar el gigantesco Buda Reclinado que mide 15 metros de alto y 46 de largo");
                contentPlace.add(
                                "A Bangkok también se la conoce como la Venecia de Asia ya que muchas zonas de la ciudad están atravesadas por canales navegables. El transporte fluvial es muy importante en esta ciudad y hay líneas de ferrys que funcionan como si fueran autobuses");
                contentPlace.add(
                                "Conocido como el Templo del Amanecer, es un templo budista de arquitectura Khmer que le da una estética muy diferente a la gran mayoría de templos de Bangkok");
                contentPlace.add(
                                "El Chinatown de Bangkok es uno de los más grandes de toda Asia, en consonancia con la importancia de su influyente comunidad china");
                contentPlace.add(
                                "Destaca por la cantidad de estatuas de Buda que flanquean sus galerías laterales (más de 150), por el enorme Buda que alberga su sala principal, y sobre todo por los murales que decoran los muros de esta sala");
                // Maldivas
                namePlace.add("Malé");
                namePlace.add("Bikini-beach de Malé");
                namePlace.add("Resorts de Maldivas");
                namePlace.add("Bioluminiscencia");
                namePlace.add("Buceo y snorkel");
                namePlace.add("DhAngethi");
                contentPlace.add(
                                "Es una ciudad pintoresca, algo caótica, al más puro estilo Asia, pero si te gusta callejear estamos seguros de que le encontrarás un cierto encanto. Date una vuelta por el mercado del pescado, es un lugar súper colorido y sí, también lleno de olores");
                contentPlace.add(
                                "Date un chapuzón en su playa pública. ¿Sabías que es artificial? Sí, parece mentira pero la capital de Maldivas no tenía playas donde poder bañarse");
                contentPlace.add(
                                "Si no tienes problemas de presupuesto date un capricho alojándote en algún resort de lujo. No suelen ser baratos (al revés), pero son el lugar perfecto para vivir la vida loca");
                contentPlace.add(
                                "¿Sabías que si estás en el sitio idóneo y en la temporada correcta, puede que tengas la suerte de experimentar uno de los fenómenos naturales más asombrosos que hay: la bioluminiscencia?");
                contentPlace.add(
                                "Si quieres bucear… estás de suerte: Maldivas es uno de los mejores sitios al mundo para hacerlo. Podrás ver tortugas, tiburones, peces tropicales y ¡hasta tiburones ballena!");
                contentPlace.add(
                                "Es la mejor forma de ver como es realmente este país, tendrás la oportunidad de conocer a locales, y sus costumbres. Lejos de los focos y de los resorts");
                // Atenas
                namePlace.add("La Acrópolis");
                namePlace.add("Barrio de Plaka");
                namePlace.add("Barrio de Anafiotika");
                namePlace.add("Templo de Zeus Olímpico");
                namePlace.add("Barrio de Monastiraki");
                namePlace.add("Ágora Antigua");
                contentPlace.add(
                                "Es el lugar más importante de Atenas. A pesar de encontrarse en una posición fácil de defender, esta ha sido saqueada y destruida numerosas veces a lo largo de su historia aunque gracias a las diferentes restauraciones te puedes hacer una idea bastante exacta de la importancia que tuvo durante la época clásica");
                contentPlace.add(
                                "Situado a los pies de la Acrópolis, el pintoresco barrio de Plaka es otro de los lugares que visitar en Atenas. Considerado como el barrio más antiguo de la ciudad, todavía conserva el encanto de la Grecia tradicional con calles estrechas, bonitas fachadas de edificios del siglo XIX");
                contentPlace.add(
                                "Enclavado dentro de Plaka, en la ladera de la Ácropolis, se encuentra el pintoresco barrio de Anafiótica, que enamora por su estrechas callejuelas rodeadas de pequeñas casas blancas y azules, que te trasladan por unos instantes a los bonitos pueblos de las islas griegas");
                contentPlace.add(
                                "El emperador romano Adriano fue el principal artífice de la finalización de la obra y en honor a él puedes ver la enorme puerta de Adriano de 20 metros de altura al lado del templo");
                contentPlace.add(
                                "Este barrio con influencias turcas, tiene en sus mercados en forma de zocos su gran atractivo, junto a varias iglesias ortodoxas y mezquitas, que te recomendamos no perderte en la visita");
                contentPlace.add(
                                "Fue el lugar de encuentro de los antiguos habitantes de la ciudad, en la que se mezclaba la actividad social y política con la comercial. Con cierto parecido al foro romano, en este recinto se encontraban desde edificios administrativos, mercados, entre otros");
                // Londres
                namePlace.add("Big Ben");
                namePlace.add("Puente de la Torre");
                namePlace.add("Hyde Park");
                namePlace.add("Abadía de Westminster");
                namePlace.add("Piccadilly Circus");
                namePlace.add("Museo Británico");
                contentPlace.add(
                                "En el Palacio de Westminster encontrarás el Parlamento Británico y el Big Ben, su famosa Torre del Reloj. Esta torre de más de 100 metros de altura fue construida en 1858 en estilo gótico y destaca por los cuatro relojes situados en cada una de las caras");
                contentPlace.add(
                                "Construido en el 1894 sobre el río Támesis, es el puente más famoso que ver en Londres y su nombre se debe a la proximidad con la Torre de Londres, que está justo al lado");
                contentPlace.add(
                                "Es el parque más grande y famoso de la ciudad en el que encontrarás desde un enorme lago, varios monumentos, un jardín de rosas, los jardines y el Palacio de Kensington, hasta un sinfín de senderos para pasear o correr");
                contentPlace.add(
                                "Patrimonio de la Humanidad, es el templo religioso más antiguo y famoso que visitar en Londres. En ella se han celebrado desde el año 1066 casi todas las ceremonias de coronación de reyes, monarcas ingleses, bodas como la de los actuales príncipes y funerales como el de la princesa Diana de Gales");
                contentPlace.add(
                                "Es una pequeña plaza que hace de intersección de varias calles y el punto de encuentro más famoso entre locales y turistas que visitan la ciudad");
                contentPlace.add(
                                "Inaugurado en el año 1759, es uno de los museos más importantes del mundo y otro de los lugares que ver en Londres imprescindibles");
                // Alpes Julianos
                namePlace.add("Lago Bled");
                namePlace.add("Cascada Pericnik");
                namePlace.add("Cascada Kozjak");
                namePlace.add("Sobre el río Soca");
                namePlace.add("Parque nacional del Triglav");
                namePlace.add("Mangart");
                contentPlace.add(
                                "Bled es la imagen del paraíso. Una perla turística de dimensiones mundiales en el margen del Parque Nacional Triglav que estuvo entre los nominados para las siete nuevas maravillas del mundo");
                contentPlace.add(
                                "Está situada en el espectacular valle de Vrata y cerca de la aldea de Mojstrana. La cascada consta de dos partes");
                contentPlace.add(
                                "Una de las cascadas con más encanto de Eslovenia La senda para llegar es fácil y corta pero la sorpresa final es mayúscula. Una grieta entre altas paredes deja colar la luz y el agua en un rincón de cuento");
                contentPlace.add(
                                "El valle del río Soča es un paraíso para unas experiencias activas al aire libre. Además, puedes disfrutar de excelente gastronomía y festivales de música, descubrir la historia del valle, o simplemente llenarte de energía en la naturaleza pintoresca");
                contentPlace.add(
                                "Es el único parque nacional de Eslovenia. Recibe su nombre por el monte Triglav, un símbolo nacional de Eslovenia. El Triglav, la montaña más alta del país con 2.864m");
                contentPlace.add(
                                "Situada en la frontera entre Italia y Eslovenia. Con una elevación de 2.679 metros, es el tercer pico más alto de Eslovenia, después de Triglav y Škrlatica");
                // Santa Marta
                namePlace.add("Parque bolívar");
                namePlace.add("Parque de los novios");
                namePlace.add("Parque Camellón Rodrigo de Bastidas");
                namePlace.add("Parque Tayrona");
                namePlace.add("Taganga");
                namePlace.add("Minca");
                contentPlace.add(
                                "En el Parque Bolívar de Santa Marta podrás encontrar la estatua del Libertador Simón Bolívar. Esta plaza no siempre se llamó así. En sus inicios era conocida como la Plaza de Armas. Al inicio de la república, adoptó el nombre de Plaza de la Constitución");
                contentPlace.add(
                                "El Parque de los Novios es uno de los mejores lugares para visitar en Santa Marta debido a su gran ambiente. Es uno de los centros gastronómicos y turísticos de la ciudad");
                contentPlace.add(
                                "Pasear al atardecer por el Parque Camellón Rodrigo de Bastidas es una de las cosas más bonitas que hacer en Santa Marta, así que no te lo pierdas. Aquí también se encuentra una pequeña playa en la que refrescarse");
                contentPlace.add(
                                "Es un pequeño paraíso situado a aproximadamente 15 kilómetros del centro de Santa Marta. Si visitas este parque, estarás rodeado de selva, playas y lugares tan bonitos como el Cabo de San Juan");
                contentPlace.add(
                                "Taganga es un pequeño pueblo pesquero situado a unos 5 kilómetros del centro de Santa Marta que se ha convertido en un gran punto turístico de la ciudad y un paraíso para mochileros");
                contentPlace.add(
                                "Es una zona con frondosa vegetación de Sierra Nevada a la que los turistas llegan en busca de naturaleza, paz y desconexión");
                // Singapur
                namePlace.add("Gardens by the Bay");
                namePlace.add("Little India");
                namePlace.add("Marina Bay Sands");
                namePlace.add("Parque Merlion");
                namePlace.add("Hawkers");
                namePlace.add("Clarke Quay");
                contentPlace.add(
                                "Estos enormes y modernos jardines, situados entre el mar y la parte trasera del hotel Marina Bay Sands, se diferencian del resto por las zonas donde se concentran los llamados superárboles");
                contentPlace.add(
                                "Uno de los barrios con más encanto. En esta zona uedes pasear entre bonitos edificios de colores, visitar templos hindús y budistas, perderte en centros comerciales llenos de tiendas además de probar la gastronomía india más deliciosa");
                contentPlace.add(
                                "Es el complejo de edificios más famoso de Singapur, en el que se incluye un hotel, el museo de Arte y Ciencia, dos teatros, varios restaurantes, un centro comercial y un enorme casino");
                contentPlace.add(
                                "Frente al Marina Bay Sands se encuentra una bahía, se encuentra una estatua de 9 metros de altura, mitad pez y mitad león, que representa el pasado pesquero de la ciudad y los orígenes de su fundador");
                contentPlace.add(
                                "Una de las mejores cosas que hacer en Singapur es probar la comida callejera de los hawkers. Estos mercados concentran numerosos puestos de comida a precios económicos donde los comensales comparten mesa y servicios");
                contentPlace.add(
                                "Clarke Quay fue un antiguo muelle en el que hace un tiempo se rehabilitaron sus almacenes hasta convertir la zona en otro de los lugares imprescindibles que ver en Singapur");
                // END
                int cont = 0;
                int contCity = 0;
                for (String place : namePlace) {
                        Tourism tourism = new Tourism();
                        tourism.setNameTourism(place);
                        tourism.setContentTourism(contentPlace.get(cont));
                        // ADD IMAGE
                        cont++;
                        tourism.setNameDestination(cities.get(contCity));
                        tourism.setDestination(initedDestinations.get(contCity));
                        tourism.setImageTourismURL("/static/assets/images/informacion/"+place+".jpg");
                        
                        try {
                                setTourismImageB(tourism, tourism.getImageTourismURL());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        ListTourism.add(tourism);
                        if ((cont) % 6 == 0) {
                                contCity++;
                        }
                }
                return ListTourism;
        }

        private void setTourismImageB(Tourism tourism, String titleImage) throws IOException {
                Resource image = new ClassPathResource(titleImage);
                tourism.setImageTourismFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
        }

        private List<Catering> generateCatering(List<Destination> initedDestinations) {
                List<Catering> ListCatering = new ArrayList<>();
                List<String> nameFood = new ArrayList<>();
                List<String> contentFood = new ArrayList<>();
                // Paris
                // nameFood.add(" ");
                nameFood.add("Ratatouille");
                nameFood.add("Crepe");
                nameFood.add("Cassoulet");
                nameFood.add("Boeuf Bourguignon");
                nameFood.add("Quiche Lorraine");
                nameFood.add("Escargots");
                // contentFood.add(" ");
                contentFood.add(
                                "El plato tiene su origen en la cocina provenzal de Niza; dichos orígenes se reflejan en el nombre: «Rata» significa «comida» y «touille», «remover». De modo que «ratatouille» significa «comida revuelta» que se cocina a fuego lento durante horas");
                contentFood.add(
                                "Las creps son originarias de la región de Bretaña, al oeste de Francia, en donde se llaman krampouezh; actualmente es un plato consumido a diario en todo el país, especialmente en el Chandeleur​ o Fiesta de la Candelaria, como parte de la tradición local");
                contentFood.add(
                                "Este plato, originario de Laguedoc-Roussillon, está compuesto de alubias blancas, muslos de pato y carne de cerdo (bajo diferentes formas). Las familias de campesinos lo han consumido durante siglos. Hoy, los franceses lo cocinan para las reuniones familiare");
                contentFood.add(
                                "Es un plato cuyo origen se sitúa en la región francesa de la Borgoña, a 100 km al sur de París. Es un estofado de buey, en esta zona es muy reconocido por su sabor");
                contentFood.add(
                                "Originaria de la región de Lorena, en Francia. En un principio, el relleno se componía sólo de huevos y crema de leche o nata fresca. En el siglo XIX, se le añadieron las pequeñas tiras o tacos de panceta magra, fresca o ahumada");
                contentFood.add(
                                "Son un plato tradicional de la gastronomía francesa. Consiste en unos caracoles cocidos y servidos en su concha rellena de mantequilla de ajo y perejil. Se sirve como entrante en Francia y en los restaurantes franceses");
                // Bangkok
                nameFood.add("PAD THAI");
                nameFood.add("Som tam");
                nameFood.add("Tod man pla");
                nameFood.add("Pinchos morunos");
                nameFood.add("Sticky rice mango");
                nameFood.add("Yam Ma Khwa Yao");
                contentFood.add(
                                "Los noodles van acompañados de soja, vegetales o quizá algo de carne y frutos secos picados. Los ingredientes pueden variar dependiendo del lugar donde lo pidas pero seguro que te encanta en todas sus versiones");
                contentFood.add(
                                "Tras este nombre se esconde una ensalada de papaya verde que, además de una comida típica tailandesa, es una muy buena opción si te apetece comer algo ligero y fresco");
                contentFood.add(
                                "Son unos pastelitos de pescado con un poco de sabor a curry, cilantro y limón. Pueden servirlos solos o acompañados de un poco de cebolla");
                contentFood.add(
                                "Si también te apetece probar la comida rápida que se puede adquirir en los puestos callejeros de Bangkok, verás que los pinchos morunos son una comida típica estrella en las calles tailandesas");
                contentFood.add(
                                "El mango acompaña a un arroz compacto gracias a que se le ha añadido leche de coco. Puedes probar esta comida típica tanto en restaurantes como en los puestos callejeros para poder disfrutar de él mientras visitas la ciudad de Bangkok");
                contentFood.add(
                                "ensalada de sabor salado y dulce con un toque de picante leve. Se encuentra preparada con gamas secas, salsa de pescado, limón, huevos y berenjena, una preparación muy recomendable");
                // Maldivas
                nameFood.add("Bajiya");
                nameFood.add("Curries");
                nameFood.add("Huni Hakuru Folhi");
                nameFood.add("Garudiya");
                nameFood.add("Gulha");
                nameFood.add("Kaashi Kiru");
                contentFood.add(
                                "Tentempié triangular que se fríe u hornea cuya masa es una mezcla de patata, guisantes, cilantro, lentejas que se rellena de pescado seco, hojas de curry, curry en polvo y cebolla");
                contentFood.add(
                                "Son los platos más básicos y populares del país. Varía según la región y también según sea elaborado por un cocinero u otro. El curry es un condimento básico en la cocina de las Maldivas");
                contentFood.add("Torta de azúcar, harina y coco");
                contentFood.add(
                                "Es un caldo o sopa clara de pescado cocido a base de atún listado o de aleta amarilla. A veces se agrega cebolla, hojas de curry, chiles y lima para darle más sabor. Se sirve caliente con arroz cocido al vapor y limón");
                contentFood.add(
                                "Aperitivo. Especie de croquetas redondas cuya masa de harina de trigo o arroz está rellena de atún ahumado, enlatado o peces Maldive, coco, hojas de curry, cebolla, jengibre, jugó de limón y cúrcuma. Se reboza y se fríe");
                contentFood.add("Leche de coco");
                // Atenas
                nameFood.add("Tzatziki");
                nameFood.add("Spanakopita");
                nameFood.add("Tyropita");
                nameFood.add("Spanakotiropites");
                nameFood.add("Saganaki");
                nameFood.add("Dolmades");
                contentFood.add(
                                "Es una salsa griega de yogur hecha de pepino y ajo. Es un complemento ácido para las carnes asadas, el kebab (brochetas) y los faláfeles (croquetas).");
                contentFood.add(
                                "Es un pastel salado griego relleno de espinaca troceada, queso feta, cebolla o cebolleta, huevo y condimentos varios");
                contentFood.add(
                                "Es un pastel salado típico de la cocina griega elaborado con capas de masa filo y relleno con una mezcla de queso y huevo");
                contentFood.add(
                                "es una empanadilla típica de Grecia. Su principal ingrediente de relleno suele ser el queso feta. La empanada argentina es notablemente similar, aunque las dimensiones de la spanakotiropita son bastante menores (en este caso más semejantes a las gyoza japonesas).");
                contentFood.add(
                                "Es un sencillo y tradicional plato de la cocina griega, basado en queso feta frito en aceite de oliva");
                contentFood.add(
                                "Los dolmades pueden definirse como enrollados de hojas de parra rellenos una mezcla que suele contener carne, arroz, cebolla, piñones y uvas pasas.");
                // Londres
                nameFood.add("Desayuno inglés completo");
                nameFood.add("Pastel y puré");
                nameFood.add("Pescado y patatas fritas");
                nameFood.add("Espaguetis a la boloñesa");
                nameFood.add("Pollo Tikka Masala");
                nameFood.add("Té de la tarde");
                contentFood.add(
                                "Reliquia culinaria de nuestras raíces anglosajonas, este plato se hizo popular entre todas las clases socioeconómicas durante la Revolución Industrial. Es un plato de mimos, perfecto para la mañana después de una gran noche de fiesta o para prepararse para un largo día de trabajo");
                contentFood.add(
                                "Originario del East End de Londres, el pie and mash es la piedra angular de la cocina obrera de la Revolución Industrial. Pasteles de carne, puré de patata esponjoso, licor");
                contentFood.add(
                                "La historia del pescado y las patatas fritas revela una sorprendente historia de origen. El pescado frito procede de los judíos exiliados de la Península Ibérica durante el siglo XIV, y las patatas fritas de los belgas francófonos. Lo único que se hizo fue emparejarlos por primera vez");
                contentFood.add(
                                "Después de que muchos cocineros y turistas italianos se hayan retorcido las manos, el 'spag bol' sigue siendo el epítome de la comida reconfortante inglesa");
                contentFood.add(
                                "El pollo tikka masala es el símbolo de la cocina anglo-india que se impuso en el país tras la época del Imperio Británico. Se rumorea que fue creado por una casa de curry de Glasgow");
                contentFood.add(
                                "Esta tradición tan británica tiene sus orígenes en el siglo XIX, pero sigue siendo un placer para los visitantes");
                // Alpes Julianos
                nameFood.add("Struklji");
                nameFood.add("Zganci");
                nameFood.add("Golaz");
                nameFood.add("Ricet");
                nameFood.add("Obara");
                nameFood.add("Idrijski zlikrofi");
                contentFood.add(
                                "Pastel de harina relleno de requesón o crema de nueces. Se sirve caliente y existen más de 70 variedades, tanto dulces como saladas. Es uno de los platos típicos de Liubliana más recurrentes para picar entre horas.");
                contentFood.add(
                                "plato realizado a base de harina de maíz o de trigo sarraceno que normalmente acompaña los platos de carne estofada. Se trata de un plato difícil de encontrar en otro lugar del mundo, por lo que es motivo de orgullo entre los eslovenos");
                contentFood.add(
                                "Carne estofada con patatas y guisantes que se sirve en una gran hogaza de pan. Se trata de un plato bastante común en otros países de la zona, como Eslovaquia o Hungría.");
                contentFood.add(
                                "Sopa densa a base de judías perfecta para entrar en calor los fríos días de invierno. Aunque es una receta bastante simple, se trata de uno de los platos más sabrosos de la gastronomía de Liubliana");
                contentFood.add("Sopa elaborada a base de carne, especialmente intestinos");
                contentFood.add(
                                "Pasta rellena al más puro estilo italiano, pero con toques autóctonos. Este plato parecido a los raviolis sirve como plato principal o guarnición y suele ir acompañado de chicharrones o de salsa de cordero");
                // Santa Marta
                nameFood.add("Arepa de huevo");
                nameFood.add("Pescado frito con patacón");
                nameFood.add("Ceviche de camarón");
                nameFood.add("Arroz con coco");
                nameFood.add("Cocada");
                nameFood.add("Carimañola");
                contentFood.add(
                                "No pierdas de vista los mostradores que están a lo largo de toda la ciudad ofreciendo estas delicias fritas a base de maíz (dulces o saladas, según tu preferencia) con huevo entero");
                contentFood.add(
                                "Este plato, crujiente y sabroso, se prepara con cualquier tipo de pescado de mar o río, siendo los más usuales la mojarra, el pargo rojo y el sábalo");
                contentFood.add(
                                "Esta receta a base de pescado, cebolla morada, picante, sal, limón y salsa rosada (una mezcla de salsa de tómate con mayonesa), es otra preparación típica de la región");
                contentFood.add(
                                "Este manjar preparado a base de arroz blanco en leche de coco es el acompañante perfecto para un buen pescado con patacón y ensalada");
                contentFood.add(
                                "Las cocadas, preparadas a partir de la ralladura de coco y endulzadas con panela o azúcar, son dulces que venden en una gran variedad de sabores. Las puedes encontrar con sabor a limón, guayaba, piña y tamarindo");
                contentFood.add(
                                "Este manjar del caribe está hecho con masa de yuca, también llamada mandioca, la cual se tritura y amasa para luego ser rellenada con queso y carne blanca o roja");
                // Singapur
                nameFood.add("Crabs chilli or pepper");
                nameFood.add("Raya a la barbacoa");
                nameFood.add("Satay");
                nameFood.add("Laksa");
                nameFood.add("Durian");
                nameFood.add("Biryani");
                contentFood.add(
                                "Los cangrejos con chile son los más comunes en los restaurantes y generalmente se comen junto con los mantous (bollos) fritos, que se usan para mojar en la exquisita salsa de chile");
                contentFood.add(
                                "Originaria de los puestos de venta ambulante, la raya a la barbacoa se ha convertido en un popular plato que se sirve en centros de comida (hawker centers)");
                contentFood.add(
                                "Los Satay son pinchos de carne asada servidos con arroz y salsa de cacahuetes. Hay satays de pollo, de cerdo, de ternera e incluso de cordero");
                contentFood.add(
                                "El mejor ejemplo de la buena combinación de sabores e ingredientes chinos y malayos en un solo tazón. Los fideos, a menudo de arroz, constituyen la base del laksa, seguido de una salsa o curry, algunos trozos de carne, y a menudo algunas verduras y hierbas");
                contentFood.add(
                                "En el sudeste asiático, algunos lo consideran el 'rey de las frutas' y otros la 'fruta más apestosa del mundo'. Los locales adoran tanto la carne que la convierten en postres, pasteles, tartas e incluso batidos");
                contentFood.add(
                                "Es un plato de influencia musulmana/india y usa un característico arroz de grano largo tipo basmati");
                // END
                int cont = 0;
                int contCity = 0;
                for (String food : nameFood) {
                        // Destination destination = initedDestinations;
                        Catering catering = new Catering();
                        catering.setNameFood(food);
                        catering.setContentFood(contentFood.get(cont));
                        // catering.setDestination(initedDestinations.get(cont));
                        cont++;
                        catering.setImageFoodUrl("/static/assets/images/informacion/" + food + ".jpg");
                        try {
                                setFoodImage(catering, catering.getImageFoodUrl());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        catering.setNameDestination(cities.get(contCity));
                        catering.setDestination(initedDestinations.get(contCity));
                        ListCatering.add(catering);
                        if ((cont) % 6 == 0) {
                                contCity++;
                        }
                }
                return ListCatering;
        }

        List<String> cities = new ArrayList<>();
        List<Float> prices = Arrays.asList(120f, 95f, 210f, 150f, 180f, 130f, 230f, 165f);

        private List<Destination> generateDestination() {
                List<Destination> destinations = new ArrayList<>();
                List<String> titleDestination = new ArrayList<>();
                // cities.add("");
                // titleDestination.add("");
                cities.add("Paris");
                titleDestination.add("La ciudad del amor");
                cities.add("Bangkok");
                titleDestination.add("El corazón vibrante de Tailandia");
                cities.add("Maldivas");
                titleDestination.add("Un oasis de belleza natural");
                cities.add("Atenas");
                titleDestination.add("Civilización antigua");
                cities.add("Londres");
                titleDestination.add("Vibrante, Histórica y Diversa");
                cities.add("Alpes-Julianos");
                titleDestination.add("Montañas salvajes y majestuosas");
                cities.add("Santa-Marta");
                titleDestination.add("Vibrante, Costera y Diversa");
                cities.add("Singapur");
                titleDestination.add("Moderna, Cosmopolita y Contraste");

                for (int i = 0; i < cities.size(); i++) {
                        Destination destination = new Destination();
                        String city = cities.get(i);
                        String title = titleDestination.get(i);
                        destination.setNameDestination(city);
                        destination.setContentDestination(title);
                        float price = prices.get(i);
                        destination.setPrice(price);
                        String file = destination.getNameDestination().replace(' ', '-');
                        destination.setTitleImage("/static/assets/images/Cities/" + file + ".jpg");
                        try {
                                setCityImage(destination, destination.getTitleImage());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        destinations.add(destination);
                }
                return destinations;
        }

        private void setCityImage(Destination destination, String titleImage) throws IOException {
                Resource image = new ClassPathResource(titleImage);
                destination.setTitleImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
        }

        private void setFoodImage(Catering catering, String titleImage) throws IOException {
                Resource image = new ClassPathResource(titleImage);
                catering.setImageFoodFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
        }

        private List<House> generateHouse() {
                List<House> houses = new ArrayList<>();
                List<String> houseNames = new ArrayList<>();


                // houseNames.add(" ");
                houseNames.add("Ibis Paris Tour Eiffel Cambronne 15ème");
                houseNames.add("Lamphuhouse Bangkok - SHA Extra Plus Certified");
                houseNames.add("Rasdu View Inn");
                houseNames.add("The Lop Athens Holidays Luxury Suites");
                houseNames.add("Royal Lancaster London");
                houseNames.add("Apartmaji Telemark Kranjska Gora");
                houseNames.add("Hilton Santa Marta");
                houseNames.add("Studio M Hotel");

                List<String> houseContents = Arrays.asList(
                                "Este hermoso apartamento cuenta con una amplia sala de estar con impresionantes vistas a la ciudad",
                                "Disfruta de la tranquilidad de este acogedor apartamento ubicado en el corazón del campo.",
                                "Experimenta la vida de lujo en este elegante apartamento con comodidades de alta gama.",
                                "Relájate en la comodidad moderna de este apartamento elegante y con estilo.",
                                "Escápate a esta encantadora cabaña con impresionantes vistas al océano.",
                                "Experimenta el encanto rústico de esta casa de campo.",
                                "Indulge en la escapada de vacaciones definitiva en esta lujosa villa frente al mar.",
                                "Descubre la magia de esta ciudad histórica en un apartamento tradicional con comodidades modernas.");

                List<String> streetViewLinkArray = new ArrayList<>();

                streetViewLinkArray.add("https://www.google.com/maps/embed?pb=!4v1676028794230!6m8!1m7!1skHrLzS2dgYYAAAQvO4D0RA!2m2!1d48.84704800513715!2d2.301612553443306!3f247.7688792620428!4f16.333893010043653!5f1.5076336197232112");
                streetViewLinkArray.add("https://www.google.com/maps/embed?pb=!4v1676112121790!6m8!1m7!1sgI2Nv9nYUk30vGgKvo1r2A!2m2!1d46.48270672884353!2d13.78001426307877!3f9.776072580970688!4f0.2519408423733296!5f0.7820865974627469"); 
                streetViewLinkArray.add("https://www.google.com/maps/embed?pb=!4v1676048404186!6m8!1m7!1sFlCzoNYDZnr8RoYsztOziw!2m2!1d37.93826123710596!2d23.69895010457095!3f304.0260391520811!4f19.74034068554542!5f0.7820865974627469");
                streetViewLinkArray.add("https://www.google.com/maps/embed?pb=!4v1676046226246!6m8!1m7!1sCAoSLEFGMVFpcFBFaVRoTnhNLVlzeG13MjRaUXgxRklPOEFvNmEteXl4cEloVlJq!2m2!1d13.76172!2d100.4959854!3f100!4f0!5f0.7820865974627469");
                streetViewLinkArray.add("https://www.google.com/maps/embed?pb=!4v1676111472590!6m8!1m7!1sCAoSLEFGMVFpcFBQcGxoRk5oamFOYl9WNDFqSTlQcm90YThidU95TkFyTnpYWWJa!2m2!1d51.5125237!2d-0.1750489!3f239.12570828959397!4f27.75889621227269!5f0.4000814197993535");
                streetViewLinkArray.add("https://www.google.com/maps/embed?pb=!4v1676047440085!6m8!1m7!1sCAoSLEFGMVFpcFBwVTBlSE5uMGtTZTgwOTNRUnFwbVlQUjhOSVhnM0JVancyb2Np!2m2!1d4.2634683!2d72.9927701!3f170.68325763793726!4f11.885678211503674!5f0.7820865974627469");
                streetViewLinkArray.add("https://www.google.com/maps/embed?pb=!4v1676112679447!6m8!1m7!1sCAoSLEFGMVFpcE5QNHQ2YjhZVWY1MVpNVk44VWpXbzVRS1p0TG83eXJvSEdZX1FZ!2m2!1d11.2392006!2d-74.2161595!3f173.57106640224222!4f-3.6988222650587232!5f0.7820865974627469");
                streetViewLinkArray.add("https://www.google.com/maps/embed?pb=!4v1676113241150!6m8!1m7!1sCAoSLEFGMVFpcE1ZRElvUlY1RVl1NEUwYVhSendQT0s4RGh3SVFvLTV0WDF6UC02!2m2!1d1.2908669!2d103.8394557!3f16.8968797741457!4f31.91353930181144!5f0.7820865974627469");

                List<String> mapsLinkArray = new ArrayList<>();

                 mapsLinkArray.add(
                                "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2625.586302423611!2d2.299161515479695!3d48.847029279286474!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47e67022d77fe41f%3A0x465bb7c0930ff4e6!2sibis%20Paris%20Tour%20Eiffel%20Cambronne%2015%C3%A8me!5e0!3m2!1ses!2ses!4v1676025822254!5m2!1ses!2ses");
                 mapsLinkArray.add(
                                 "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3875.2878159269135!2d100.49370791744384!3d13.761509100000008!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x30e2996d7dccc813%3A0xc375022480278c3f!2sLamphu%20House%20Bangkok!5e0!3m2!1ses!2ses!4v1676046099002!5m2!1ses!2ses");
                 mapsLinkArray.add(
                                 "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d994.6943610796532!2d72.9927701!3d4.2634683!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3b4059353498bb27%3A0x725acaffdc7e1bf3!2sRasdu%20View%20Inn!5e0!3m2!1ses!2ses!4v1676047461285!5m2!1ses!2ses");
                 mapsLinkArray.add(
                                 "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3146.661991674933!2d23.696965315089535!3d37.93832617973003!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14a1bd0d2fbfea0f%3A0x3aa2b716da352fba!2sThe%20Lop%20Athens%20Holiday%20Luxury%20Suites!5e0!3m2!1ses!2ses!4v1676048346449!5m2!1ses!2ses");
                 mapsLinkArray.add(
                                 "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2483.0500927497337!2d-0.17738568440794428!3d51.51229697963592!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4876054d83ad0061%3A0x6ed2e258bff80d13!2sRoyal%20Lancaster%20London!5e0!3m2!1ses!2ses!4v1676111354746!5m2!1ses!2ses");
                 mapsLinkArray.add(
                                 "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2747.269065211928!2d13.777958715394814!3d46.482984379126236!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x477a7dc52a55f57b%3A0x49f06bf1a0b0c2c2!2sApartmaji%20Telemark!5e0!3m2!1ses!2ses!4v1676112041823!5m2!1ses!2ses");
                 mapsLinkArray.add(
                                 "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3913.303245549507!2d-74.21854368547578!3d11.23908839200858!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8ef4f57dc34eef6b%3A0x852ce023b1f15da!2sHotel%20Hilton%20Garden%20Inn%20Santa%20Marta!5e0!3m2!1ses!2ses!4v1676112603067!5m2!1ses!2ses");
                 mapsLinkArray.add(
                                 "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3988.8058374389993!2d103.83698601446783!3d1.290815299058522!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31da199e743b9537%3A0x8345fd72774c8e84!2sStudio%20M%20Hotel!5e0!3m2!1ses!2ses!4v1676113111403!5m2!1ses!2ses");
                
                for (int i = 0; i < houseNames.size(); i++) {
                        House house = new House();
                        String apartment = houseNames.get(i);
                        house.setNameHouse(apartment);
                        house.setContentHouse(houseContents.get(i));
                        float price = prices.get(i);
                        house.setPrice(price);
                        house.setHostName(randomNameGenerator());
                        String nameDestination = cities.get(i);
                        house.setDestinationName(nameDestination);
                        String file = nameDestination.replace(' ', '-');
                        house.setHostImage("/static/assets/images/alojamiento/host" + file + "1.jpg");
                        try {
                                setHostImageB(house, house.getHostImage());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        List<String> auxList = new ArrayList<>();
                        for (int j = 1; j <= 5; j++) {
                                auxList.add("/static/assets/images/alojamiento/" + file + "1." + j + ".jpg");
                        }
                        house.setImagesHouse(auxList);

                        try {
                                setImagesHouseB(house, house.getImagesHouse());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }

                        String streetViewLink = streetViewLinkArray.get(i);
                        house.setStreetViewLink(streetViewLink);

                        String mapLink = mapsLinkArray.get(i);
                        house.setMapsLink(mapLink);

                        houses.add(house);
                }
                return houses;
        }

        private void setImagesHouseB(House house, List<String> imagesHouse) throws IOException {
                List<Blob> auxBlob = new ArrayList<>();
                for (String str : imagesHouse) {
                        Resource image = new ClassPathResource(str);
                        auxBlob.add(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
                        house.setImagesHouseFile(auxBlob);
                }
        }

        private void setHostImageB(House house, String hostImage) throws IOException {
                Resource image = new ClassPathResource(hostImage);
                house.setHostImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
        }

        private List<User> generateUser() {
                List<User> users = new ArrayList<>();

                for (int i = 1; i <= 5; i++) {
                        User user = new User();
                        String name = randomNameGenerator();
                        String lastName = randomLastNameGenerator();
                        user.setName(name);
                        user.setLastName(lastName);
                        user.setEmail(lastName + i + "@gmail.com");
                        user.setEncodedPassword(passwordEncoder.encode("12345"));
                        // user.setEncodedPassword("12345");
                        user.setRoles("USER");
                        user.setProfileAvatar("/static/assets/images/c1.jpg");
                        try {
                                setProfileAvatarContent(user, user.getProfileAvatar());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        users.add(user);
                }
                for (int i = 1; i <= 2; i++) {
                        User user = new User();
                        String name = "admin" + i;
                        String lastName = "";
                        user.setName(name);
                        user.setLastName(lastName);
                        user.setEmail(name + "@gmail.com");
                        user.setEncodedPassword(passwordEncoder.encode("123456"));
                        // user.setEncodedPassword("123456");
                        user.setRoles("USER", "ADMIN");
                        user.setProfileAvatar("/static/assets/images/c1.jpg");
                        try {
                                setProfileAvatarContent(user, user.getProfileAvatar());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        users.add(user);
                }
                return users;

        }

        private void setProfileAvatarContent(User user, String profileAvatar) throws IOException {
                Resource image = new ClassPathResource(profileAvatar);
                user.setProfileAvatarFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
        }

        private String randomNameGenerator() {
                List<String> names = new ArrayList<>();
                names.add("Sergio");
                names.add("Carlos");
                names.add("Adrian");
                names.add("Jorge");
                names.add("Shu");
                names.add("David");
                names.add("Elena");
                names.add("Paula");
                names.add("Pedro");
                names.add("Diego");
                names.add("Alejandro");
                names.add("Maria");

                Random random = new Random();
                int index = random.nextInt(names.size());
                String randomName = names.get(index);

                return randomName;
        }

        private String randomLastNameGenerator() {
                List<String> LastNames = new ArrayList<>();
                LastNames.add("Cuadros");
                LastNames.add("Perez");
                LastNames.add("Pedroche");
                LastNames.add("Francisco");
                LastNames.add("Ye");
                LastNames.add("Moreno");
                LastNames.add("Diez");
                LastNames.add("Corda");
                LastNames.add("Rodriguez");
                LastNames.add("Torres");
                LastNames.add("Torequin");
                LastNames.add("Flores");

                Random random = new Random();
                int index = random.nextInt(LastNames.size());
                String randomName = LastNames.get(index);

                return randomName;
        }

        private List<Review> generateReviews(List<Destination> initedDestinations, List<User> initedUsers) {
                List<Review> reviews = new ArrayList<>();
                for (Destination destination : initedDestinations) {
                        for (User user : initedUsers) {
                                for (int i = 0; i < 3; i++) { // generate 3 reviews for each destination and user
                                        Review review = new Review();
                                        review.setContentReview("Review del destino: " +
                                                        destination.getNameDestination());
                                        review.setUser(user);
                                        review.setDestination(destination);
                                        review.setRatingReview((int) (Math.random() * 5) + 1);
                                        review.setTitleReview("Buena oferta");
                                        reviews.add(review);
                                }
                        }
                }
                return reviews;
        }

}