package com.reservation.resrvation.model;

import javax.persistence.*;

@Entity
@Table(name = "facturation")
public class Facturation {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private int userId;
    @Column
    private int vehiculeId;
    @Column
    private int prix_base;
    @Column
    private int prix_km;
    @Column
    private int prix_total;

    public Facturation(int id, int userId, int vehiculeId, int prix_base, int prix_km, int prix_total) {
        this.id = id;
        this.userId = userId;
        this.vehiculeId = vehiculeId;
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
        return userId;
    }

    public void setUser_id(int userId) {
        this.userId = userId;
    }

    public int getVehicule_id() {
        return vehiculeId;
    }

    public void setVehicule_id(int vehiculeId) {
        this.vehiculeId = vehiculeId;
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
