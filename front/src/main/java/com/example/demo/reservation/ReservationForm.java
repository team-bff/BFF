package com.example.demo.reservation;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ReservationForm {

    private int id;
    private int userId;
    private int vehiculeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date firstDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastDate;
    private long km;

    public long getKm() {
        return km;
    }

    public void setKm(long km) {
        this.km = km;
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


    public ReservationForm(int id, int userId, int vehiculeId, long km, Date firstDate, Date lastDate) {
        this.id = id;
        this.userId = userId;
        this.vehiculeId = vehiculeId;
        this.km = km;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }

    public ReservationForm(int id, int userId, int vehiculeId) {
        this.id = id;
        this.userId = userId;
        this.vehiculeId = vehiculeId;
    }

    public ReservationForm() {

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
