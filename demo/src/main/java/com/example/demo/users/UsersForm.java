package com.example.demo.users;


public class UsersForm {

    private String lastname;
    private long numberLicence;
    private String yearObtention;
    private String birthday;
    private String name;
    private int id;


    public UsersForm(int id,  String name, String lastname, long numberLicence,  String yearObtention, String birthday) {
        this.id = id;
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

    public UsersForm() {
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

    public String getYearObtention() {
        return yearObtention;
    }

    public void setYearObtention(String yearObtention) {
        this.yearObtention = yearObtention;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


}


