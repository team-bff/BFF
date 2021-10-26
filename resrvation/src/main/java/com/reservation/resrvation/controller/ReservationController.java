package com.reservation.resrvation.controller;

import com.reservation.resrvation.dao.ReservationDao;
import com.reservation.resrvation.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationDao reservationDao;

    //Créer une réservtion "/reservation"
    @PostMapping(value = "/reservation")
    public void addReservation(@RequestBody Reservation reservation){
        reservationDao.save(reservation);
    }

    //Affiche toutes les réservations d'un utilisateur "/reservation/all/{id}"
    @GetMapping(value = "/reservation/all/{userId}")
    public List<Reservation> allReservationList(@PathVariable int userId){
        return reservationDao.findAllByUserId(userId);

    }
    //Affiche la réservation d'un utilisateur "/reservation/{id}"
    @GetMapping(value = "/reservation/{id}")
    public Reservation reservation(@PathVariable int id){
        return reservationDao.findById(id);
    }

    //Modification d'une réservation "/reservation/edit/{id}"
    @PutMapping(value = "/reservation/edit/{id}")
    public Reservation editReservation(@PathVariable int id, @RequestBody Reservation reservation){
        reservation.setId(id);
        return reservationDao.save(reservation);
    }

    //Supression d'une reservation "/reservation/delete/{id}"
    @DeleteMapping(value = "/reservation/delete/{id}")
    public void deleteReservaton(@PathVariable int id){
        reservationDao.deleteById(id);
    }

}
