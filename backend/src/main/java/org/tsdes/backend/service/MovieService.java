package org.tsdes.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsdes.backend.entity.Movie;
import org.tsdes.backend.entity.Review;
import org.tsdes.backend.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class MovieService {

    @Autowired
    private EntityManager em;

    public Long createMovie(String title, String director, LocalDate dateOfRelease) {


        Movie movie = new Movie();
        movie.setDirector(director);
        movie.setTitle(title);
        movie.setDateOfRelease(dateOfRelease);

        em.persist(movie);

        return movie.getId();
    }

    public void deleteMovie(Long id){

        Movie movie = em.find(Movie.class, id);
        if(movie == null){
            throw new IllegalArgumentException("Movie with id "+id+" does not exist");
        }
        Query query = em.createQuery("DELETE FROM Movie m WHERE m.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public Movie getMovieWithId(Long id){
        Movie movie = em.find(Movie.class, id);
        if(movie == null){
            throw new IllegalArgumentException("Movie with id "+id+" does not exist");
        }
        return movie;
    }

    public List<Movie> getAllMovies() {
        TypedQuery<Movie> query = em.createQuery(
                "SELECT m FROM Movie m", Movie.class);
        return query.getResultList();
    }

    public Long rateAMovie(Movie movie, User user, String text, Integer stars, LocalDate dateWritten){
        Review review = new Review();
        review.setMovie(movie);
        review.setUser(user);
        review.setDateWritten(dateWritten);
        review.setReview(text);
        review.setStars(stars);

        em.persist(review);

        user.setReviews(review);

        movie.setReviews(review);
        return review.getId();
    }

    public Integer averageStars(Long id){
        TypedQuery<Review> query = em.createQuery(
                "SELECT r FROM Review r WHERE r.movie.id=?1", Review.class);
        query.setParameter(1, id);
        Integer stars = 0;
        for(Review review : query.getResultList()){
            stars += review.getStars();
        }
        if(query.getResultList().size() == 0){
            return 0;
        }
        return (stars/query.getResultList().size());

    }

    public List<Movie> getMoviesByStars(){
        TypedQuery<Review> query = em.createQuery(
                "SELECT r FROM Review r ORDER BY r.stars desc ", Review.class);

        List<Movie> movies = new ArrayList<>();
        for(Review review : query.getResultList()){
            movies.add(review.getMovie());
            System.out.println(review.getMovie().getTitle());
        }
        return movies;
    }

    public List<Review> getReviewsSortedByStars(Long movieId){
        TypedQuery<Review> query = em.createQuery(
                "SELECT r FROM Review r where r.movie.id=?1 ORDER BY r.stars DESC", Review.class);
        query.setParameter(1, movieId);

        return query.getResultList();
    }

}

