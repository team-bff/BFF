package com.example.demo;

import javax.persistence.*;

@Entity
public class Users {


    private int id;
    private String lastname;
    private long numberLicence;
    private int year;
    private int month;
    private int date;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Users() {
    }
@Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    @Column
    public long getNumberLicence() {
        return numberLicence;
    }

    public void setNumberLicence(long numberLicence) {
        this.numberLicence = numberLicence;
    }
    @Column
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    @Column
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    @Column
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Users(int id, String name, String lastname, long numberLicence, int year, int month, int date) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.numberLicence = numberLicence;
        this.year = year;
        this.month = month;
        this.date = date;
    }


}

