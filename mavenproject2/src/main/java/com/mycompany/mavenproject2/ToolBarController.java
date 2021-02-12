/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.google.firebase.database.DatabaseReference;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ToolBarController implements Initializable {

    public ToolBarController() {
    }
    
    DatabaseReference firebaseRootRef;
    
    public static int i;
    
     @FXML
    private VBox root;
     
    @FXML
    private JFXButton information;

    @FXML
    private JFXButton emploie;

    @FXML
    private  JFXButton parametre;

    @FXML
    private  JFXButton deconexion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    
    @FXML
    void onDeconexion(ActionEvent event) {
         
        WindowLoader.loadWindowC(getClass().getResource("/fxml/LoginScene.fxml"), "Quick Attendance", (Stage) root.getScene().getWindow());
        
        firebaseRootRef.goOffline();
        FXMLController.removePerf();
        System.out.println( FXMLController.getPrefCinP()+"....\n"+ FXMLController.getPrefDepartementP());
    }

    @FXML
    void onEmploies(ActionEvent event) {
       i++;    
        if (i<2) {
            loadEmlpoie();
        }
    }

    @FXML
    public void onInformation(ActionEvent event) {
        i++;    
        if (i<2) {
              loadInfo();
             }
              
              
    }

    @FXML
    void onParametres(ActionEvent event) {

    }
    
  /* public void DisableButtons(){
      information.setDisable(true);    
}
   public void EnableButtons(){
      information.setDisable(false);
      
}*/

     static void setScene(int a) {
        i=a;
       
    }
   void loadEmlpoie() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Emploie.fxml"));
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            WindowLoader.setStageIcon(stage);
            stage.setTitle("Emploie");
            stage.setScene(new Scene(parent));
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.show();
            
            
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void loadInfo() {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Information.fxml"));
                Stage stage = new Stage(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                WindowLoader.setStageIcon(stage);
                stage.setTitle("Informations");
                stage.setScene(new Scene(parent));
                stage.setFullScreen(true);
                stage.setFullScreenExitHint("");
                stage.show();
                

            } catch (IOException ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
