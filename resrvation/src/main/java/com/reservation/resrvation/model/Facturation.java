package com.reservation.resrvation.model;

import javax.persistence.*;

@Entity
@Table(name = "facturation")
public class Facturation {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private int user_id;
    @Column
    private int vehicule_id;
    @Column
    private int prix_base;
    @Column
    private int prix_km;
    @Column
    private int prix_total;

    public Facturation(int id, int user_id, int vehicule_id, int prix_base, int prix_km, int prix_total) {
        this.id = id;
        this.user_id = user_id;
        this.vehicule_id = vehicule_id;
        this.prix_base = prix_base;
        this.prix_km = prix_km;
        this.prix_total = prix_total;
    }

    public Facturation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVehicule_id() {
        return vehicule_id;
    }

    public void setVehicule_id(int vehicule_id) {
        this.vehicule_id = vehicule_id;
    }

    public int getPrix_base() {
        return prix_base;
    }

    public void setPrix_base(int prix_base) {
        this.prix_base = prix_base;
    }

    public int getPrix_km() {
        return prix_km;
    }

    public void setPrix_km(int prix_km) {
        this.prix_km = prix_km;
    }

    public int getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(int prix_total) {
        this.prix_total = prix_total;
    }
}
