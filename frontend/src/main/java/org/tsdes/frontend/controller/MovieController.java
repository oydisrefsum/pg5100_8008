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

    private String errorMsg;

    private boolean showError;

    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    public String getAverageStars(Long movieId) {

        return movieService.averageStars(movieId);
    }
    public String details(Long id){
        errorMsg = "";
        showError = false;
        stars = "";
        reviewText = "";

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
        for (Review review : movieService.getReviewSortedByDate(currentMovie.getId())){
            if(review.getUser().getUsername().equals(user.getUsername())){
                setErrorMsg("You have already reviewed this movie");
            }
        }
        if(!showError){
            if(parseInt(stars) < 1 || parseInt(stars) > 5){
                setErrorMsg("Stars can only be between 1 and 5");
                stars = "";
                showError = false;
                return;

            }
            movieService.rateAMovie(currentMovie, user, reviewText, parseInt(stars), LocalDate.now());
            stars = "";
            reviewText = "";
            showError = false;
        }
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

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = "";
        showError = true;
        this.errorMsg = errorMsg;
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

