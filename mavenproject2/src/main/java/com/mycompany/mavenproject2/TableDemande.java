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
public class TableDemande {
     String cin_p,nom,prenom,description; 
     int id ;

    
    
    
    
     public TableDemande(int id , String cin_p, String nom, String prenom, String description) {
        this.id = id;
        this.cin_p = cin_p;
        this.nom = nom;
        this.prenom = prenom;
        this.description=description;
        
    }
    
    
    
    
    
    public String getCin_p() {
        return cin_p;
    }

    public void setCin_p(String cin_p) {
        this.cin_p = cin_p;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description= description;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
}
