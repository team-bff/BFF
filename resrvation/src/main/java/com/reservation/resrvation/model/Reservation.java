package com.reservation.resrvation.model;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue
    private int id;

    private int userId;

    private int vehiculeId;

    public Reservation(int id, int userId, int vehiculeId) {
        this.id = id;
        this.userId = userId;
        this.vehiculeId = vehiculeId;
    }

    public Reservation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    @Column
    public int getVehiculeId() {
        return vehiculeId;
    }

    public void setVehiculeId(int vehiculeId) {
        this.vehiculeId = vehiculeId;
    }
}
