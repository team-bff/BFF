package com.example.demo.vehicules;


import javax.persistence.Column;

public class Vehicules {


        private int id;

        private String marque;

        private String modele;

        private String immatriculation;

        private int cvf;
    @Column(name = "disponible")
        private boolean available;

        private int categorie;

        public Vehicules(String marque, String modele, String immatriculation, int cvf, boolean available, int categorie) {
            this.marque = marque;
            this.modele = modele;
            this.immatriculation = immatriculation;
            this.cvf = cvf;
            this.available = available;
            this.categorie = categorie;
        }

        public Vehicules() {

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

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public int getCategorie() {
            return categorie;
        }

        public void setCategorie(int categorie) {
            this.categorie = categorie;
        }
    }


