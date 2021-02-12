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
public class TableEtudiant {
    String cin , numinsc,nom,prenom,email,departement,classe ;

    public TableEtudiant(String cin, String numinsc, String nom, String prenom, String email, String departement, String classe) {
        this.cin = cin;
        this.numinsc = numinsc;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.departement = departement;
        this.classe = classe;
    }

    public String getCin() {
        return cin;
    }

    public String getNuminsc() {
        return numinsc;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartement() {
        return departement;
    }

    public String getClasse() {
        return classe;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNuminsc(String numinsc) {
        this.numinsc = numinsc;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
    
    
}
