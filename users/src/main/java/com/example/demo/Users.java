package com.example.demo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Users {


    private int id;
    private String lastname;
    private long numberLicence;
    private Date birthday;
    private Date yearObtention;
    private String name;

    public Users(int id, String lastname, long numberLicence, String name, Date birthday, Date yearObtention) {
        this.id = id;
        this.lastname = lastname;
        this.numberLicence = numberLicence;
        this.name = name;
        this.birthday = birthday;
        this.yearObtention = yearObtention;
    }

    public Users() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "year_obtention")
    public Date getYearObtention() {
        return yearObtention;
    }
    public void setYearObtention(Date yearObtention) {
        this.yearObtention = yearObtention;
    }



}

