/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Travaille
 */
public class InformationController implements Initializable {

    @FXML
    private JFXButton retour;

    @FXML
    private StackPane rooti;
    
    @FXML
    private ToolBarController buttons;
   
    @FXML
    private Label cin;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
    private Label email;

    @FXML
    private Label departement;
    @FXML
    private ImageView photo;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cin.setText(FXMLController.getPrefCinP());
       nom.setText(FXMLController.getPrefNomP());
       prenom.setText(FXMLController.getPrefPrenomP());
       email.setText(FXMLController.getPrefEmailP());
       departement.setText(FXMLController.getPrefDepartementP());
       Connection con=null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
            String q="SELECT photo FROM professeurs where cin_p=?";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,FXMLController.getPrefCinP());
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
        
       ToolBarController tool = new ToolBarController();
       
       Stage curstage = (Stage) rooti.getScene().getWindow();
      
       tool.setScene(0);
       
       
       curstage.hide();
       
       
       
    }
  
}
