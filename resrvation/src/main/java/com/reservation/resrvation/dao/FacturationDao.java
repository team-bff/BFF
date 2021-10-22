package com.reservation.resrvation.dao;

import com.reservation.resrvation.model.Facturation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturationDao extends JpaRepository<Facturation, Integer> {
    Facturation findById(int id);

}
