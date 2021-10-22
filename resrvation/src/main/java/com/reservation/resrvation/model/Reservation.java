package com.reservation.resrvation.model;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private int user_id;
    @Column
    private int vehicule_id;

    public Reservation(int id, int user_id, int vehicule_id) {
        this.id = id;
        this.user_id = user_id;
        this.vehicule_id = vehicule_id;
    }

    public Reservation() {

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
}
