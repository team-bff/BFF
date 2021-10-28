package com.example.demo.reservation;

import javax.persistence.*;
import java.util.Date;

@Table(name = "reservations")
public class Reservation {

    private int id;

    private int userId;

    private int vehiculeId;

    private long km;
    private Date firstDate;
    private Date lastDate;

    public long getKm() {
        return km;
    }

    public void setKm(long km) {
        this.km = km;
    }

    public Reservation(int id, int userId, int vehiculeId, long km, Date firstDate, Date lastDate) {
        this.id = id;
        this.userId = userId;
        this.vehiculeId = vehiculeId;
        this.km = km;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }


    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }


    public Reservation(int id, int userId, int vehiculeId, Date firstDate, Date lastDate) {
        this.id = id;
        this.userId = userId;
        this.vehiculeId = vehiculeId;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVehiculeId() {
        return vehiculeId;
    }

    public void setVehiculeId(int vehiculeId) {
        this.vehiculeId = vehiculeId;
    }
}
