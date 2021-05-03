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
 * Created by arcuri82 on 15-Dec-17.
 */
@Service
public class DefaultDataInitializerService {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @PostConstruct
    public void initialize(){

        attempt(() -> userService.createUser("foo", "foo", "bar", "foo@bar.com", "123"));
        attempt(() -> movieService.createMovie("Interstellar", "Christopher Nolan", LocalDate.of(2014, Month.NOVEMBER, 7)));
        attempt(() -> movieService.createMovie("Pulp Fiction", "Quentin Tarantino", LocalDate.of(1994, Month.JANUARY, 20)));
        attempt(() -> movieService.createMovie("The Guard", "John Micheal McDonagh", LocalDate.of(2011, Month.JULY, 7)));
        attempt(() -> movieService.createMovie("E.T", "Steven Spielberg", LocalDate.of(1982, Month.JUNE, 11)));
        attempt(() -> movieService.createMovie("Moonrise Kingdom", "Wes Anderson", LocalDate.of(2012, Month.JUNE, 8)));
        attempt(() -> movieService.createMovie("Gravity", "Alfonso Cuaron", LocalDate.of(2013, Month.NOVEMBER, 8)));
        attempt(() -> movieService.createMovie("Shaun of the Dead", "Edgar Wright", LocalDate.of(2004, Month.SEPTEMBER, 3)));
        attempt(() -> movieService.createMovie("Snowpiercer", "Joon-ho Bong", LocalDate.of(2013, Month.JULY, 29)));
        attempt(() -> movieService.createMovie("Lock, Stock, and Two Smoking Barrels", "Guy Richie", LocalDate.of(1999, Month.FEBRUARY, 26)));
        attempt(() -> movieService.createMovie("North by Northwest", "Alfred Hitchcock", LocalDate.of(1959, Month.JULY, 1)));
        attempt(() -> movieService.createMovie("Blue Velvet", "David Lynch", LocalDate.of(2007, Month.SEPTEMBER, 21)));


        Long id =  movieService.createMovie("The Godfather", "Francis Ford Coppola", LocalDate.of(2007, Month.OCTOBER, 14));
        Long id2 = movieService.createMovie("Lost in Translation", "Sofia Coppola", LocalDate.of(2003, Month.NOVEMBER, 3));

        User user = userService.getUserWithId("foo");

        Movie movie1 = movieService.getMovieWithId(id);
        Movie movie2 = movieService.getMovieWithId(id2);

        attempt(() -> movieService.rateAMovie(movie1, user, "Very Good movie", 4, LocalDate.of(2017, Month.OCTOBER, 13)));
        attempt(() -> movieService.rateAMovie(movie2, user, "Very Bad movie", 1, LocalDate.of(2013, Month.APRIL, 13)));




    }

    private  <T> T attempt(Supplier<T> lambda){
        try{
            return lambda.get();
        }catch (Exception e){
            return null;
        }
    }
}
