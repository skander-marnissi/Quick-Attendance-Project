/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;



import com.google.firebase.database.DatabaseReference;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Travaille
 */
public class AdminToolBarController implements Initializable {

   DatabaseReference firebaseRootRef;
   
    @FXML
    private VBox root;

    public static int j;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    

    @FXML
    void onAjouteE(ActionEvent event) {
       j++;    
        if (j<2) {
              loadAe();
             }
    }

    @FXML
    void onAjouteP(ActionEvent event) {
     j++;    
        if (j<2) {
              loadAp();
             }
    }

    @FXML
    void onDeconexion(ActionEvent event) {
         WindowLoader.loadWindowC(getClass().getResource("/fxml/LoginScene.fxml"), "Quick Attendance", (Stage) root.getScene().getWindow());
         firebaseRootRef.goOffline(); 
         FXMLController.removePerf();
          System.out.println( FXMLController.getPrefCinA()+"....\n");
    }

    @FXML
    void onInformation(ActionEvent event) {
             j++;    
        if (j<2) {
              loadInfo();
             }
    }

    @FXML
    void onParametre(ActionEvent event) {
             j++;    
        if (j<2) {
              loadParametre();
             }
    }
    
   
    static void setScene(int a) {
        j=a;
    }
    
    void loadInfo() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/InformationAdmin.fxml"));
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            WindowLoader.setStageIcon(stage);
            stage.setTitle("Information");
            stage.setScene(new Scene(parent));
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.show();
            
            
            stage.setOnCloseRequest((event) -> {
                    setScene(0);
                });
            
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void loadAp() {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/AjoutProfesseur.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                
                WindowLoader.setStageIcon(stage);
                
                stage.setTitle("Ajout professeur");
                stage.setScene(new Scene(parent));
                stage.setFullScreen(true);
                stage.setFullScreenExitHint("");
                stage.show();
                
                
                stage.setOnCloseRequest((event) -> {
                    setScene(0);
                });

            } catch (IOException ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    void loadAe() {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/AjoutEtudiant.fxml"));
                Stage stage = new Stage(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                
                WindowLoader.setStageIcon(stage);
                stage.setTitle("Ajout etudiant");
                stage.setScene(new Scene(parent));
                stage.setFullScreen(true);
                stage.setFullScreenExitHint("");
                stage.show();
                
                stage.setOnCloseRequest((event) -> {
                    setScene(0);
                });
                
            } catch (IOException ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        
    void loadParametre() {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/ParametresAdmin.fxml"));
                Stage stage = new Stage(StageStyle.UNDECORATED);
               stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Paramétres");
                WindowLoader.setStageIcon(stage);
                stage.setScene(new Scene(parent));
                stage.setMaximized(true);
                stage.show();
                
                
                stage.setOnCloseRequest((event) -> {
                    setScene(0);
                });

            } catch (IOException ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
