/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXPasswordField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author Travaille
 */
public class AlertHendler {
   
   public static int c=0;
    
    public static void showMaterialDialog(StackPane root, Node nodeToBeBlurred, List<JFXButton> controls, String header, String body) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        if (controls.isEmpty()) {
            controls.add(new JFXButton("Okay"));
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);

        controls.forEach(controlButton -> {
            controlButton.getStyleClass().add("dialog-button");
            controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
        });

        dialogLayout.setHeading(new Label(header));
        dialogLayout.setBody(new Label(body));
        dialogLayout.setActions(controls);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
            nodeToBeBlurred.setEffect(null);
        });
        nodeToBeBlurred.setEffect(blur);
    }
     public static void showErrorMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(content);
       
        alert.showAndWait();
    }
        public static void showMaterialDialogConfirm(String title){
            
       
            
            Stage window = new Stage(StageStyle.UNDECORATED);
            WindowLoader.setStageIcon(window);
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMaxWidth(400);
            window.setOnCloseRequest((WindowEvent event) -> {
              c=2;
                /*Platform.exit();
              System.exit(0);*/
            });
            
            
            JFXButton confirmer = new JFXButton("confirmer");
            JFXPasswordField password = new JFXPasswordField();
            VBox layout = new VBox(10);
            password.setPromptText("Votre mot de passe");
            Label text = new Label("Saisissez votre mot de passe");
            text.setTextFill(Color.web("#da3d3d"));
            layout.getChildren().addAll(text, password);
            
            
            confirmer.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event) {
                     try { 
            System.out.println("Connexion...");
            Connection con=null;
            PreparedStatement pst=null;
            PreparedStatement pst2=null;
            String sql="select * from authentification where login=? and password=? ";
            String sql2="select * from superadmin where login=? and password=? ";
            ResultSet rs=null;
            ResultSet rs2=null;
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
            pst=con.prepareStatement(sql);
            pst.setString(1,FXMLController.getPrefCinA());
            pst.setString(2,password.getText());
        
           rs=pst.executeQuery();
          
          
           
           System.out.println(FXMLController.getPrefCinA());
           System.out.println(password.getText());
           
           if(rs.next()){
               c=1;
               window.close();
            
            } 
           else {
                  pst=con.prepareStatement(sql2);
                  pst.setString(1,FXMLController.getPrefLoginS());
                  pst.setString(2,password.getText());
                 
                  rs2=pst.executeQuery();
                  
                  if (rs2.next()){
                            c=1;
                     window.close(); 
                  }
      
           else{
               showErrorMessage("Erreur","Mot de passe incorrecte"); 
           }
                } }
        
        
        catch (SQLException ex) {
            Logger.getLogger(AlertHendler.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
                
            });
            
            layout.getChildren().addAll(confirmer);
            layout.setAlignment(Pos.CENTER);
            
            
            window.setY(300);
            window.setX(600);
            
            Scene scene = new Scene(layout);
            scene.getStylesheets().add("/styles/Styles.css");
            window.setScene(scene);
            window.showAndWait();
      }
        
 public static void showMaterialDialog(StackPane root, List<JFXButton> controls, String header, String body) {
        
        if (controls.isEmpty()) {
            controls.add(new JFXButton("Okay"));
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);

        controls.forEach(controlButton -> {
            controlButton.getStyleClass().add("dialog-button");
            controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
        });

        dialogLayout.setHeading(new Label(header));
        dialogLayout.setBody(new Label(body));
        dialogLayout.setActions(controls);
        dialog.show();
 }
        


}
