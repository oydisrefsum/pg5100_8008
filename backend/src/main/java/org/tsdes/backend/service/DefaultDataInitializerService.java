package org.tsdes.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tsdes.backend.entity.Movie;
import org.tsdes.backend.entity.User;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.function.Supplier;

/**
 * File adapted from file repo https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/service/DefaultDataInitializerService.java
 */
@Service
public class DefaultDataInitializerService {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @PostConstruct
    public void initialize(){

        attempt(() -> userService.createUser("John", "John", "Doe", "foo@bar.com", "123"));
        attempt(() -> userService.createUser("Brad", "Brad", "Doe", "foo@bar.com", "123"));
        attempt(() -> userService.createUser("Jennifer", "Jennifer", "Doe", "foo@bar.com", "123"));
        attempt(() -> userService.createUser("Allison", "Allison", "Doe", "foo@bar.com", "123"));
        attempt(() -> userService.createUser("Bethany", "Bethany", "Doe", "foo@bar.com", "123"));
        attempt(() -> userService.createUser("Bob", "Bob", "Doe", "foo@bar.com", "123"));
        attempt(() -> userService.createUser("Joe", "Joe", "Doe", "foo@bar.com", "123"));

        Long id = movieService.createMovie("Interstellar", "Christopher Nolan", LocalDate.of(2014, Month.NOVEMBER, 7));
        Long id2 = movieService.createMovie("Pulp Fiction", "Quentin Tarantino", LocalDate.of(1994, Month.JANUARY, 20));
        Long id3 = movieService.createMovie("The Guard", "John Micheal McDonagh", LocalDate.of(2011, Month.JULY, 7));
        Long id4 = movieService.createMovie("E.T", "Steven Spielberg", LocalDate.of(1982, Month.JUNE, 11));
        Long id5 = movieService.createMovie("Moonrise Kingdom", "Wes Anderson", LocalDate.of(2012, Month.JUNE, 8));
        Long id6 = movieService.createMovie("Gravity", "Alfonso Cuaron", LocalDate.of(2013, Month.NOVEMBER, 8));
        Long id7 = movieService.createMovie("Shaun of the Dead", "Edgar Wright", LocalDate.of(2004, Month.SEPTEMBER, 3));
        Long id8 = movieService.createMovie("Snowpiercer", "Joon-ho Bong", LocalDate.of(2013, Month.JULY, 29));
        Long id9 = movieService.createMovie("North by Northwest", "Alfred Hitchcock", LocalDate.of(1959, Month.JULY, 1));
        Long id10 = movieService.createMovie("Blue Velvet", "David Lynch", LocalDate.of(2007, Month.SEPTEMBER, 21));
        Long id11 =  movieService.createMovie("The Godfather", "Francis Ford Coppola", LocalDate.of(2007, Month.OCTOBER, 14));
        Long id12 = movieService.createMovie("Lost in Translation", "Sofia Coppola", LocalDate.of(2003, Month.NOVEMBER, 3));

        User user1 = userService.getUserWithId("John");
        User user2 = userService.getUserWithId("Brad");
        User user3 = userService.getUserWithId("Allison");
        User user4 = userService.getUserWithId("Bethany");


        Movie movie1 = getMovieWithId(id);
        Movie movie2 = getMovieWithId(id2);
        Movie movie3 = getMovieWithId(id3);
        Movie movie4 = getMovieWithId(id4);
        Movie movie5 = getMovieWithId(id5);
        Movie movie6 = getMovieWithId(id6);
        Movie movie7 = getMovieWithId(id7);
        Movie movie8 = getMovieWithId(id8);
        Movie movie9 = getMovieWithId(id9);
        Movie movie10 = getMovieWithId(id10);
        Movie movie11 = getMovieWithId(id11);
        Movie movie12 = getMovieWithId(id12);


        attempt(() -> movieService.rateAMovie(movie1, user1, "Very Good movie", 4, LocalDate.of(2017, Month.OCTOBER, 1)));
        attempt(() -> movieService.rateAMovie(movie2, user2, "Ok movie", 3, LocalDate.of(2014, Month.AUGUST, 3)));
        attempt(() -> movieService.rateAMovie(movie1, user3, "Did not like it", 2, LocalDate.of(2012, Month.MAY, 6)));
        attempt(() -> movieService.rateAMovie(movie4, user3, "Excellent", 5, LocalDate.of(1990, Month.DECEMBER, 12)));
        attempt(() -> movieService.rateAMovie(movie4, user4, "Not very good", 2, LocalDate.of(2013, Month.NOVEMBER, 13)));
        attempt(() -> movieService.rateAMovie(movie7, user3, "Awful", 1, LocalDate.of(2014, Month.OCTOBER, 20)));
        attempt(() -> movieService.rateAMovie(movie7, user1, "Did not like it at all", 1, LocalDate.of(2007, Month.JULY, 16)));
        attempt(() -> movieService.rateAMovie(movie8, user2, "Not very good, liked the storyline", 2, LocalDate.of(2015, Month.JUNE, 4)));
        attempt(() -> movieService.rateAMovie(movie8, user2, "Pretty good!", 4, LocalDate.of(2000, Month.JUNE, 21)));
        attempt(() -> movieService.rateAMovie(movie10, user1, "Amazing!!!", 5, LocalDate.of(2020, Month.MAY, 29)));
        attempt(() -> movieService.rateAMovie(movie11, user2, "Very bad acting", 2, LocalDate.of(2013, Month.APRIL, 7)));
        attempt(() -> movieService.rateAMovie(movie12, user4, "Not bad, not good", 3, LocalDate.of(2015, Month.MARCH, 13)));





    }

    private Movie getMovieWithId(Long id12) {
        return movieService.getMovieWithId(id12);
    }

    private  <T> T attempt(Supplier<T> lambda){
        try{
            return lambda.get();
        }catch (Exception e){
            return null;
        }
    }
}
