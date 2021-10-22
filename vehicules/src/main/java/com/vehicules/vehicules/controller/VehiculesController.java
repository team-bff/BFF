package com.vehicules.vehicules.controller;


import com.vehicules.vehicules.dao.VehiculesDao;
import com.vehicules.vehicules.model.Vehicules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehiculesController {

    @Autowired
    private VehiculesDao vehiculesDao;

    //Créer un vehicule "/vehicule"
    @PostMapping(value = "/vehicule")
    public void addVehicule(@RequestBody Vehicules vehicules){
        vehiculesDao.save(vehicules);
    }

    //Récupère tous les vehicules "/vehicules"
    @GetMapping(value = "/vehicules")
    public List<Vehicules> vehiculesList(){
        List<Vehicules> vehicules = vehiculesDao.findAll();
        return vehicules;
    }

    //Récupère un vehicule par son ID "/vehicules/{id}"
    @GetMapping(value = "/vehicules/{id}")
    public Vehicules showVehicules(@PathVariable int id){
        return vehiculesDao.getById(id);
    }

    //Supression d'un vehicules "/vehicule/{id}"
    @DeleteMapping(value = "/vehicule/{id}")
    public void deleteVehicul(@PathVariable int id){
        vehiculesDao.deleteById(id);
    }
}
