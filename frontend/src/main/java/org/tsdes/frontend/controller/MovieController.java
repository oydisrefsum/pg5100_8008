package org.tsdes.frontend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.tsdes.backend.entity.Movie;
import org.tsdes.backend.entity.Review;
import org.tsdes.backend.entity.User;
import org.tsdes.backend.service.MovieService;
import org.tsdes.backend.service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static java.lang.Integer.parseInt;

@Named
@SessionScoped
public class MovieController implements Serializable {

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserInfoController infoController;

    @Autowired
    private UserService userService;

    private Movie currentMovie;

    private boolean seeByStars;

    private String reviewText;

    private String stars;

    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    public String getAverageStars(Long movieId) {

        return movieService.averageStars(movieId);
    }
    public String details(Long id){

        currentMovie = movieService.getMovieWithId(id);

        return "/ui/details.jsf?faces-redirect=true";
    }

    public List<Review> getReview(Long movieId){
        if(seeByStars){
            return movieService.getReviewsSortedByStars(movieId);
        }else{
            return movieService.getReviewSortedByDate(movieId);
        }
    }

    public Movie getCurrentMovie(){
        return currentMovie;
    }

    public void setSeeByStars(){
        seeByStars = true;
    }

    public void setSeeByDate(){
        seeByStars = false;
    }

    public String returnHome(){
        return "/index.jsf?faces-redirect=true";
    }

    public void addAReview(){

        User user = userService.getUserWithId(infoController.getUserName());
//check here for info om brukeren har reviewet filmen før

        for (Review review : movieService.getReviewSortedByDate(currentMovie.getId())){
            if(review.getUser().getUsername().equals(user.getUsername())){
                System.out.println("this user ha already reviewed this movie");
            }
        }

        movieService.rateAMovie(currentMovie, user, reviewText, parseInt(stars), LocalDate.now());
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = "";
        this.reviewText = reviewText;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = "";
        this.stars = stars;
    }




/*
    private List<Trip> trips;

    private Trip currentTrip;

    private List<Trip> searchedTrips;
    private String formText;



    public List<Trip> getTrips(int numberOfTrips){
        List<Trip> temp = new ArrayList<>();
        trips = tripService.getAllTrips();
        int n = 0;
        while (n < numberOfTrips){
            temp.add(trips.get(n));
            n++;
        }
        return temp;
    }

    public String details(Long id){

        currentTrip = tripService.getTripWithId(id);

        return "/ui/details.jsf?faces-redirect=true";
    }

    public Trip getCurrentTrip(){
        return currentTrip;
    }
    public String getFormText() {
        return formText;
    }

    public void setTripByLocation(String location){
        searchedTrips = new ArrayList<>();
        searchedTrips = tripService.getAllTripsBylocation(location);
    }

    public List<Trip> getSearchedTrips(){
        return searchedTrips;
    }

    public void setSearchedTrips(){
        setTripByLocation(formText);
    }
    public void setFormText(String formText) {
        this.formText = formText;
    }

    public String bookTrip(){
        User user = userService.getUserWithId(infoController.getUserName());
        tripService.bookATrip(user, currentTrip);
        return "/ui/userinfo.jsf?faces-redirect=true";
    }

    public List<Purchase> getBookedTrips(){
        return tripService.getAllPurchases(infoController.getUserName());
    }
    */
}

