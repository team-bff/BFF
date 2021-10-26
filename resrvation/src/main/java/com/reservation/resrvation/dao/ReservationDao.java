package com.reservation.resrvation.dao;

import com.reservation.resrvation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Integer> {
    Reservation findById(int id);
    List<Reservation> findAllByUserId(int userId);
}
