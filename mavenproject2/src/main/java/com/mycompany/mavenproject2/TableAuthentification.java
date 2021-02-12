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
public class TableAuthentification {
     String login , password , type , cin_a , cin_p;

    public TableAuthentification(String login, String password, String type, String cin_a, String cin_p) {
        this.login = login;
        this.password = password;
        this.type = type;
        this.cin_a = cin_a;
        this.cin_p = cin_p;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCin_a() {
        return cin_a;
    }

    public void setCin_a(String cin_a) {
        this.cin_a = cin_a;
    }

    public String getCin_p() {
        return cin_p;
    }

    public void setCin_p(String cin_p) {
        this.cin_p = cin_p;
    }
    
    
}
