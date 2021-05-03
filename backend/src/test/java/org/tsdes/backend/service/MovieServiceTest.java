package org.tsdes.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tsdes.backend.StubApplication;
import org.tsdes.backend.entity.Movie;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MovieServiceTest extends ServiceTestBase {

    @Autowired
    private MovieService movieService;

    @Autowired
    UserService userService;

    @Test
    public void testNoExistingTrips() {

        List<Movie> list = movieService.getAllMovies();
        assertEquals(0, list.size());
    }

    @Test
    public void testCreateMovie(){
        Long movieId = movieService.createMovie("IT", "Steven Spielberg", LocalDate.of(2012, Month.MAY, 15));
        assertNotNull(movieId);
    }

/*
    @Test
    public void testDeleteTrip(){
        Long tripId = tripService.createTrip("Paris", "Fly", 3999, "Paris", LocalDate.of(2022, Month.MAY, 15));
        assertTrue(tripService.getAllTrips().size() > 0);

        tripService.deleteTrip(tripId);

        assertFalse(tripService.getAllTrips().size() > 0);
    }
    @Test
    public void testDeleteTripWithWrongId(){
        Long tripId = tripService.createTrip("Paris", "Fly", 3999, "Paris", LocalDate.of(2022, Month.MAY, 15));
        assertTrue(tripService.getAllTrips().size() > 0);

        assertThrows(IllegalArgumentException.class, () -> tripService.deleteTrip(4L) );
    }

    @Test
    public void testGetAllTripsByCost(){
        Long tripId = tripService.createTrip("Paris", "Fly", 3999, "Paris", LocalDate.of(2022, Month.MAY, 15));
        Long tripId1 = tripService.createTrip("Paris1", "Fly", 2999, "Paris", LocalDate.of(2022, Month.MAY, 15));
        Long tripId2 = tripService.createTrip("Paris2", "Fly", 6999, "Paris", LocalDate.of(2022, Month.MAY, 15));
        Long tripId3 = tripService.createTrip("Paris3", "Fly", 1999, "Paris", LocalDate.of(2022, Month.MAY, 15));

        List<Trip> trips = tripService.getAllTripsByCost();

        assertEquals(trips.get(0).getCost(),1999);
        assertEquals(trips.get(3).getCost(), 6999);
    }
    @Test
    public void testGetAllTripsByCostWithLocation(){
        Long tripId = tripService.createTrip("test", "Fly", 33999, "Paris", LocalDate.of(2022, Month.MAY, 15));
        Long tripId1 = tripService.createTrip("test1", "Fly", 42999, "Greece", LocalDate.of(2022, Month.MAY, 15));
        Long tripId2 = tripService.createTrip("test2", "Fly", 51999, "USA", LocalDate.of(2022, Month.MAY, 15));
        Long tripId3 = tripService.createTrip("test3", "Fly", 13999, "Paris", LocalDate.of(2022, Month.MAY, 15));

        List<Trip> trips = tripService.getAllTripsBylocation("Paris");

        assertEquals(13999,trips.get(0).getCost());
        assertEquals(33999, trips.get(1).getCost());
        assertEquals(2, trips.size());
        assertEquals("Paris", trips.get(0).getLocation());
    }

    @Test
    public void testBookATrip(){
        User user = new User();
        user.setEmail("e@mail.com");
        user.setLastname("last");
        user.setFirstname("first");
        user.setUsername("ownrenwiboqnfonw");
        user.setPassword("password");
        user.setEnabled(true);

        Trip trip = new Trip();
        trip.setDateOfDeparture(LocalDate.of(2022, Month.MAY, 15));
        trip.setCost(200);
        trip.setDescription("knff3");
        trip.setTitle("trip");
        trip.setLocation("Malaga");

//brukeren og trippen måtte være i systemet før man kunne booke en tur
        tripService.createTripWithTrip(trip);
        userService.creatUserWithUser(user);
        Long purchaseId = tripService.bookATrip(user,trip);

        assertNotNull(purchaseId);
    }

 */
}
