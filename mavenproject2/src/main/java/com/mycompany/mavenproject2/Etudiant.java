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
public class Etudiant {
      String Nom, Prenom, N_Ins;
    
    
    public Etudiant(String Nom, String Prenom, String N_Ins){

        this.Nom = Nom;
        this.Prenom = Prenom;
        this.N_Ins = N_Ins;
    }


    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getN_Ins() {
        return N_Ins;
    }

    public void setN_Ins(String N_Ins) {
        this.N_Ins = N_Ins;
    }
    
}
