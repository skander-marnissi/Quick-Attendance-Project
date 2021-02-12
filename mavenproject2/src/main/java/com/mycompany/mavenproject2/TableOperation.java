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
public class TableOperation {
    int id;
    String cin_a , description , date_o;

    public TableOperation(int id, String cin_a, String description, String date_o) {
        this.id = id;
        this.cin_a = cin_a;
        this.description = description;
        this.date_o = date_o;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin_a() {
        return cin_a;
    }

    public void setCin_a(String cin_a) {
        this.cin_a = cin_a;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_o() {
        return date_o;
    }

    public void setDate_o(String date_o) {
        this.date_o = date_o;
    }
    
}
