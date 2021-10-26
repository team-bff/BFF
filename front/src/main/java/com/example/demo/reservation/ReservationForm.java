package com.example.demo.reservation;

public class ReservationForm {

    private int id;
    private int userId;
    private int vehiculeId;

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
