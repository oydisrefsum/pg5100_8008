package org.tsdes.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.tsdes.backend.entity.Purchase;
import org.tsdes.backend.entity.Trip;
import org.tsdes.backend.entity.User;
import org.tsdes.backend.service.TripService;
import org.tsdes.backend.service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class TripController implements Serializable {

    @Autowired
    private TripService tripService;

    @Autowired
    private UserInfoController infoController;

    @Autowired
    private UserService userService;


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
}
