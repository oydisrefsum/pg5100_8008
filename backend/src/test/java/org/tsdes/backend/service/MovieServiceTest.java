package org.tsdes.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tsdes.backend.StubApplication;
import org.tsdes.backend.entity.Movie;
import org.tsdes.backend.entity.Review;
import org.tsdes.backend.entity.User;

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


    @Test
    public void testDeleteMovie(){
        Long movieId = movieService.createMovie("IT", "Steven Spielberg", LocalDate.of(2012, Month.MAY, 15));
        assertTrue(movieService.getAllMovies().size() > 0);

        movieService.deleteMovie(movieId);

        assertFalse(movieService.getAllMovies().size() > 0);
    }
    @Test
    public void testDeleteMovieWithWrongId(){
        Long movieId = movieService.createMovie("IT", "Steven Spielberg", LocalDate.of(2012, Month.MAY, 15));
        assertTrue(movieService.getAllMovies().size() > 0);

        assertThrows(IllegalArgumentException.class, () -> movieService.deleteMovie(4L) );
    }
    @Test
    public void testGetAllMovies(){
        Long movieId1 = movieService.createMovie("IT", "Steven Spielberg", LocalDate.of(2012, Month.MAY, 15));
        Long movieId2 = movieService.createMovie("Ready Player One", "Steven Spielberg", LocalDate.of(2018, Month.JULY, 10));
        Long movieId3 = movieService.createMovie("SVK", "Steven Spielberg", LocalDate.of(2016, Month.JUNE, 25));

        assertEquals(3, movieService.getAllMovies().size());
    }

    @Test
    public void testCreateMovieWithMovie(){
        Long movieId = movieService.createMovie("Ready Player One","Steven Spielberg", LocalDate.of(2018, Month.JULY, 10));

        assertNotNull(movieId);
    }

    @Test
    public void testAverageStars(){
        Long movieId = movieService.createMovie("Ready Player One","Steven Spielberg", LocalDate.of(2018, Month.JULY, 10));

        Movie movie = movieService.getMovieWithId(movieId);
        User user = getUser();
        userService.creatUserWithUser(user);

        movieService.rateAMovie(movie, user, "not good", 1, LocalDate.of(2016, Month.JUNE, 25));

        movieService.rateAMovie(movie, user, "very good", 5, LocalDate.of(2016, Month.JUNE, 25));

        assertEquals("3",movieService.averageStars(movieId));
    }

    @Test
    public void testAverageStarsForMovieWithoutReview(){
        Long movieId = movieService.createMovie("Ready Player One","Steven Spielberg", LocalDate.of(2018, Month.JULY, 10));

        assertEquals("This movie has no reviews yet",movieService.averageStars(movieId));
    }

    private User getUser() {
        User user = new User();
        user.setEnabled(true);
        user.setUsername("username");
        user.setFirstname("user");
        user.setLastname("name");
        user.setPassword("1234");
        user.setEmail("user@name.com");
        return user;
    }

    @Test
    public void testGetMoviesByStars(){
        Long movieId = movieService.createMovie("Ready Player One","Steven Spielberg", LocalDate.of(2018, Month.JULY, 10));

        Movie movie = movieService.getMovieWithId(movieId);

        Long movieId1 = movieService.createMovie("Ready Player Five","Steven Spielberg", LocalDate.of(2018, Month.JULY, 10));

        Movie movie1 = movieService.getMovieWithId(movieId1);

        Long movieId2 = movieService.createMovie("Ready Player Four","Steven Spielberg", LocalDate.of(2018, Month.JULY, 10));

        Movie movie2 = movieService.getMovieWithId(movieId2);

        Long movieId3 = movieService.createMovie("Ready Player Two","Steven Spielberg", LocalDate.of(2018, Month.JULY, 10));

        Movie movie3 = movieService.getMovieWithId(movieId3);

        User user = getUser();
        userService.creatUserWithUser(user);

        movieService.rateAMovie(movie1, user, "excellent", 5, LocalDate.of(2016, Month.JUNE, 25));
        movieService.rateAMovie(movie, user, "very bad", 1, LocalDate.of(2016, Month.JUNE, 25));
        movieService.rateAMovie(movie2, user, "very good", 4, LocalDate.of(2016, Month.JUNE, 25));
        movieService.rateAMovie(movie3, user, "not very good", 2, LocalDate.of(2016, Month.JUNE, 25));


        List<Movie> movies = movieService.getMoviesByStars();

        System.out.println(movies.size());

        assertEquals("5", movieService.averageStars(movies.get(0).getId()));
        assertEquals("1", movieService.averageStars(movies.get(3).getId()));
    }

    @Test
    public void getReviewsSortedByStars(){

        Long movieId = movieService.createMovie("Ready Player One","Steven Spielberg", LocalDate.of(2018, Month.JULY, 10));

        Movie movie = movieService.getMovieWithId(movieId);


        User user = getUser();
        userService.creatUserWithUser(user);

        movieService.rateAMovie(movie, user, "excellent", 5, LocalDate.of(2016, Month.JUNE, 25));
        movieService.rateAMovie(movie, user, "very bad", 1, LocalDate.of(2016, Month.JUNE, 25));
        movieService.rateAMovie(movie, user, "very good", 4, LocalDate.of(2016, Month.JUNE, 25));
        movieService.rateAMovie(movie, user, "not very good", 2, LocalDate.of(2016, Month.JUNE, 25));

        List<Review> reviews = movieService.getReviewsSortedByStars(movieId);

        assertEquals(5, reviews.get(0).getStars());
        assertEquals(1, reviews.get(3).getStars());

    }
    @Test
    public void getReviewsSortedByDate(){

        Long movieId = movieService.createMovie("Ready Player One","Steven Spielberg", LocalDate.of(2018, Month.JULY, 10));

        Movie movie = movieService.getMovieWithId(movieId);


        User user = getUser();
        userService.creatUserWithUser(user);

        movieService.rateAMovie(movie, user, "excellent", 5, LocalDate.of(2015, Month.JUNE, 25));
        movieService.rateAMovie(movie, user, "very bad", 1, LocalDate.of(2016, Month.JUNE, 25));
        movieService.rateAMovie(movie, user, "very good", 4, LocalDate.of(2012, Month.JUNE, 25));
        movieService.rateAMovie(movie, user, "not very good", 2, LocalDate.of(2020, Month.JUNE, 25));

        List<Review> reviews = movieService.getReviewSortedByDate(movieId);

        assertEquals(LocalDate.of(2020, Month.JUNE, 25), reviews.get(0).getDateWritten());
        assertEquals(LocalDate.of(2012, Month.JUNE, 25), reviews.get(3).getDateWritten());


    }

}
