package com.idealtrip.idealTrip.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.IOException;
import java.sql.Blob;

import javax.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.idealtrip.idealTrip.model.Catering;
import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.House;
import com.idealtrip.idealTrip.model.User;

@Service
public class InitDatabase {
    @Autowired
    private DestinationService destinations;

    @Autowired
    private CateringService caterings;

    @Autowired
    private HouseService houses;

    // @Autowired
    // private TourismService tourisms;

    // @Autowired
    // private NewsletterService newsletters;

    @Autowired
    private PurchaseService purchases;

    @Autowired
    private ReviewService reviews;

    @Autowired
    private UserService users;

    // @Autowired(required = false)
    // private PasswordEncoder passwordEncoder;

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
        List<Catering> initedCatering = generateCatering();

        for (Catering catering : initedCatering) {
            caterings.save(catering);
        }

    }

    private List<Catering> generateCatering() {
        List<Catering> catering = new ArrayList<>();
        List<String> nameFood = new ArrayList<>();
        nameFood.add("Ratatouille");
        nameFood.add("Crepe");
        nameFood.add("Cassoulet");
        nameFood.add("Boeuf Bourguignon");
        nameFood.add("Quiche Lorraine");
        nameFood.add("Escargots");
        return catering;
    }

    List<String> cities = new ArrayList<>();

    private List<Destination> generateDestination() {
        List<Destination> destinations = new ArrayList<>();
        cities.add("");
        cities.add("Paris");
        cities.add("Bangkok");
        cities.add("Maldivas");
        cities.add("Atenas");
        cities.add("Londres");
        cities.add("Alpes Julianos");
        cities.add("Santa Marta");
        cities.add("Singapur");

        for (int i = 1; i < cities.size(); i++) {
            Destination destination = new Destination();
            String city = cities.get(i);
            destination.setNameDestination(city);
            destination.setContentDestination(
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was");
            destination.setPrice(100.99f);
            String file = destination.getNameDestination().replace(' ', '-');
            destination.setTitleImage("/static/assets/images/Cities/" + file + ".jpg");
            try {
                setCityImage(destination, destination.getTitleImage());
            } catch (IOException e) {
                // TODO Auto-generated catch block
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

    private List<House> generateHouse() {
        List<House> houses = new ArrayList<>();
        List<String> houseNames = new ArrayList<>();
        houseNames.add(" ");
        houseNames.add("Ibis Paris Tour Eiffel Cambronne 15ème");
        houseNames.add("Lamphuhouse Bangkok - SHA Extra Plus Certified");
        houseNames.add("Rasdu View Inn");
        houseNames.add("The Lop Athens Holidays Luxury Suites");
        houseNames.add("Royal Lancaster London");
        houseNames.add("Apartmaji Telemark Kranjska Gora");
        houseNames.add("Hilton Santa Marta");
        houseNames.add("Studio M Hotel");

        for (int i = 1; i < houseNames.size(); i++) {
            House house = new House();
            String apartment = houseNames.get(i);
            house.setNameHouse(apartment);
            house.setContentHouse("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
            house.setPrice(100.99f);
            house.setHostName(randomNameGenerator());
            String nameDestination = cities.get(i);
            house.setDestinationName(nameDestination);
            String file = nameDestination.replace(' ', '-');
            house.setHostImage("/static/assets/images/alojamiento/host" + file + "1.jpg");
            try {
                setHostImageB(house, house.getHostImage());
            } catch (IOException e) {
                // TODO Auto-generated catch block
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

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
            // user.setEncodedPassword(passwordEncoder.encode("1234"));
            user.setEncodedPassword("12345");
            user.setRoles("USER");
            user.setProfileAvatar("/static/images/c1.jpg");
            try {
                setProfileAvatarContent(user, user.getProfileAvatar());
            } catch (IOException e) {
                // TODO Auto-generated catch block
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
            // user.setEncodedPassword(passwordEncoder.encode("adminPass"));
            user.setEncodedPassword("123456");
            user.setRoles("USER", "ADMIN");
            user.setProfileAvatar("/static/images/c1.jpg");
            try {
                setProfileAvatarContent(user, user.getProfileAvatar());
            } catch (IOException e) {
                // TODO Auto-generated catch block
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

    // private List<Review> initReviews(List<Destination> initDestinations,
    // List<User> initUsers) {
    // List<Review> reviews = new ArrayList<>();
    // boolean added = true;
    // for (Destination destination : initDestinations) {
    // for (User user : initUsers) {
    // if (added) {
    // Review review = new Review();
    // review.setContentReview("Comment for destination " +
    // destination.getNameDestination());
    // review.setUser(user);
    // review.setRatingReview((int) (Math.random() * 5) + 1);
    // reviews.add(review);
    // added = !added;
    // }
    // }
    // }
    // return reviews;
    // }
}