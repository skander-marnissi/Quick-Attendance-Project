/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

/**
 *
 * @author Travaille
 */
import javafx.beans.property.SimpleStringProperty;






public class Student {
    
    private String Prenom, Nom, Etat, Remarques;
    private String cin;

    public Student(String cin, String Nom, String Prenom, String Etat, String Remarque) {
        this.Prenom = Prenom;
        this.Nom = Nom;
        this.Etat = Etat;
        this.Remarques = Remarques;
        this.cin = cin;
    }

    public Student() {
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getNom() {
        return Nom;
    }

    public String getEtat() {
        return Etat;
    }

    public String getRemarques() {
        return Remarques;
    }

    public String getCin() {
        return cin;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public void setRemarques(String Remarques) {
        this.Remarques = Remarques;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
    
    
    
    
    
    
    
}

