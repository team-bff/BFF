package com.example.demo.users;

import java.util.Date;

public class Users {

    private String lastname;
    private long numberLicence;
    private Date yearObtention;
    private Date birthday;
    private String name;
    private int id;


    public Users( String name, String lastname, long numberLicence,  Date yearObtention, Date birthday) {

        this.lastname = lastname;
        this.numberLicence = numberLicence;
        this.yearObtention = yearObtention;
        this.birthday = birthday;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public long getNumberLicence() {
        return numberLicence;
    }

    public void setNumberLicence(long numberLicence) {
        this.numberLicence = numberLicence;
    }

    public Date getYearObtention() {
        return yearObtention;
    }

    public void setYearObtention(Date yearObtention) {
        this.yearObtention = yearObtention;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


}


