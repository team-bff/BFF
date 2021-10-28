package com.example.demo.vehicules;

public class VehiculesForm {


    private int id;

    private String marque;

    private String modele;

    private String immatriculation;

    private int cvf;



    private int categorie;

    public VehiculesForm( String marque, String modele, String immatriculation, int cvf, int categorie) {
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.cvf = cvf;

        this.categorie = categorie;
    }

    public VehiculesForm() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getCvf() {
        return cvf;
    }

    public void setCvf(int cvf) {
        this.cvf = cvf;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }
}

