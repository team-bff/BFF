package com.reservation.resrvation.controller;

import com.reservation.resrvation.dao.FacturationDao;
import com.reservation.resrvation.model.Facturation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class FacturationController {

    @Autowired
    FacturationDao facturationDao;

    //Créer une facturation "/facture"
    @PostMapping(value = "/facture")
    public void addFacture(@RequestBody Facturation facturation) {
        facturationDao.save(facturation);
    }

    //Affiche toutes les factures d'un utilisateur "/factures/all/{id}"
    @GetMapping(value = "/factures/all/{id}")
    public List<Facturation> AllfactureList(@PathVariable Iterable user_id){
        List<Facturation> facturations = facturationDao.findAllById(user_id);
        return facturations;
    }

    //Affiche une facture de l'utilisateur "/facture/{id}"
    @GetMapping(value = "/facture/{id}")
    public Facturation showFacture(@PathVariable int id){
        return facturationDao.findById(id);
    }
}
