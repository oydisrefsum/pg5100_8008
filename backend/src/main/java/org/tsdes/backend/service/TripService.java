package org.tsdes.backend.service;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsdes.backend.entity.Purchase;
import org.tsdes.backend.entity.Trip;
import org.tsdes.backend.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;*/
/*

@Service
@Transactional
public class TripService {

    @Autowired
    private EntityManager em;

    public Long createTrip(String title, String description, Integer cost, String location, LocalDate dateOfDeparture) {


        Trip trip = new Trip();
        trip.setTitle(title);
        trip.setDescription(description);
        trip.setCost(cost);
        trip.setDateOfDeparture(dateOfDeparture);
        trip.setLocation(location);

        em.persist(trip);

        return trip.getId();
    }

    public Long createTripWithTrip(Trip trip){
        em.persist(trip);
        return trip.getId();
    }

    public void deleteTrip(Long id){

        Trip trip = em.find(Trip.class, id);
        if(trip == null){
            throw new IllegalArgumentException("Trip with id "+id+" does not exist");
        }
        Query query = em.createQuery("DELETE FROM Trip t WHERE t.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public Trip getTripWithId(Long id){
        Trip trip = em.find(Trip.class, id);
        if(trip == null){
            throw new IllegalArgumentException("Trip with id "+id+" does not exist");
        }
        return trip;
    }

    public List<Trip> getAllTrips() {
        TypedQuery<Trip> query = em.createQuery(
                "SELECT t FROM Trip t", Trip.class);
        return query.getResultList();
    }
    public List<Trip> getAllTripsByCost() {
        TypedQuery<Trip> query = em.createQuery(
                "SELECT t FROM Trip t ORDER BY t.cost ASC", Trip.class);
        return query.getResultList();
    }
    public List<Trip> getAllTripsBylocation(String location) {
        TypedQuery<Trip> query = em.createQuery(
                "SELECT t FROM Trip t WHERE t.location=?1 ORDER BY t.cost ASC", Trip.class);
        query.setParameter(1, location);
        return query.getResultList();
    }

    public Long bookATrip(User user, Trip trip){
        Purchase purchase = new Purchase();
        purchase.setTrip(trip);
        purchase.setUser(user);
        purchase.setDateBooked(LocalDate.of(2022, Month.MAY, 15));

        em.persist(purchase);

        return purchase.getId();
    }

    public List<Purchase> getAllPurchases(String userId) {
        TypedQuery<Purchase> query = em.createQuery(
                "SELECT p FROM Purchase p WHERE p.user.username=?1 ", Purchase.class);
        query.setParameter(1, userId);
        return query.getResultList();
    }


}
*/
