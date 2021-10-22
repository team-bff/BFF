package com.vehicules.vehicules.dao;

import com.vehicules.vehicules.model.Vehicules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculesDao extends JpaRepository<Vehicules, Integer> {
}
