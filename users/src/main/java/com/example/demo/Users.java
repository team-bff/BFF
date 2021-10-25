package com.example.demo;

import javax.persistence.*;

@Entity
public class Users {


    private int id;
    private String lastname;
    private long numberLicence;
//    private String year;
//    private int date;
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
    @Column(name = "firstname")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "licence_number")
    public long getNumberLicence() {
        return numberLicence;
    }
    public void setNumberLicence(long numberLicence) {
        this.numberLicence = numberLicence;
    }

//    @Column(name = "year_obtention")
//    public String getYear() {
//        return year;
//    }
//    public void setYear(String year) {
//        this.year = year;
//    }
//
//    @Column(name = "birthday")
//    public int getDate() {
//        return date;
//    }
//    public void setDate(int date) {
//        this.date = date;
//    }

    public Users(int id, String name, String lastname, long numberLicence) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.numberLicence = numberLicence;
//        this.year = year;
//        this.date = date;
    }


}

