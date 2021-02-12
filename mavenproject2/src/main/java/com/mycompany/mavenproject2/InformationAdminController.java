/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.jfoenix.controls.JFXButton;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Travaille
 */
public class InformationAdminController implements Initializable {

     @FXML
    private StackPane rooti;

    @FXML
    private ImageView photo;

    @FXML
    private Label cin;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
    private Label email;

    @FXML
    private JFXButton retour;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cin.setText(FXMLController.getPrefCinA());
       nom.setText(FXMLController.getPrefNomA());
       prenom.setText(FXMLController.getPrefPrenomA());
       email.setText(FXMLController.getPrefEmailA());
       System.out.println(FXMLController.getPrefCinA());
       System.out.println(FXMLController.getPrefNomA());
       System.out.println(FXMLController.getPrefPrenomA());
       System.out.println(FXMLController.getPrefEmailA());
       
       Connection con=null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
            String q="SELECT photo FROM admin where cin_a=?";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,FXMLController.getPrefCinA());
            System.out.println(FXMLController.getPrefCinA());
            ResultSet rs = pst.executeQuery();
               if (rs.next()) {
                System.out.println("chargement de l'image");
                InputStream is= rs.getBinaryStream("photo");
                Image tof = new Image(is);
                photo.setImage(tof);
                }
                else{System.out.println("erreur de chargement");}

        } catch (SQLException ex) {
            Logger.getLogger(InformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
   @FXML
    void onRetour(ActionEvent event) {
 
       AdminToolBarController tool = new AdminToolBarController();
       
       Stage curstage = (Stage) rooti.getScene().getWindow();
      
       tool.setScene(0);
       
       
       curstage.hide();
    }    
} 
    
    

