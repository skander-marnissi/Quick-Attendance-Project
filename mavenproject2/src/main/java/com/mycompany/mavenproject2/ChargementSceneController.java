/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import static com.mycompany.mavenproject2.FXMLController.t;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Travaille
 */
public class ChargementSceneController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    
    @FXML
    private Circle c1;

    @FXML
    private Circle c2;

    @FXML
    private Circle c3;
    

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(t);
        if (FXMLController.t==1){
        setRotateNextP(c1,true,360,5);
        setRotate(c2,true,180,3);
        setRotate(c3,true,145,4);
    }else if(FXMLController.t==2){
           setRotateNextA(c1,true,360,5);
        setRotate(c2,true,180,3);
        setRotate(c3,true,145,4); 
        }
        else if(FXMLController.t==3){
           setRotateNextS(c1,true,360,5);
        setRotate(c2,true,180,3);
        setRotate(c3,true,145,4); 
        }
    }    

    private void setRotate(Circle circle, boolean rotation, int angle, long dure) {
        RotateTransition rt = new RotateTransition(javafx.util.Duration.seconds(dure), circle);
        rt.setAutoReverse(rotation);
        rt.setByAngle(angle);
        rt.setDelay(javafx.util.Duration.ZERO);
        rt.setRate(3);
        rt.setCycleCount(18);
        rt.play();
    }
     private void setRotateNextP(Circle circle, boolean rotation, int angle, long dure) {
        RotateTransition rt = new RotateTransition(javafx.util.Duration.seconds(dure), circle);
        rt.setAutoReverse(rotation);
        rt.setByAngle(angle);
        rt.setDelay(javafx.util.Duration.ZERO);
        rt.setRate(3);
        rt.setCycleCount(2);
        rt.setOnFinished((ActionEvent event) -> {
            
               WindowLoader.loadWindowB(getClass().getResource("/fxml/AcceuilScene.fxml"), "Quick Attendance", (Stage) rootpane.getScene().getWindow());
            
        
            
        });
        rt.play();
    }
    private void setRotateNextA(Circle circle, boolean rotation, int angle, long dure) {
        RotateTransition rt = new RotateTransition(javafx.util.Duration.seconds(dure), circle);
        rt.setAutoReverse(rotation);
        rt.setByAngle(angle);
        rt.setDelay(javafx.util.Duration.ZERO);
        rt.setRate(3);
        rt.setCycleCount(2);
        rt.setOnFinished((ActionEvent event) -> {
            
               WindowLoader.loadWindowB(getClass().getResource("/fxml/AdminScene.fxml"), "Quick Attendance", (Stage) rootpane.getScene().getWindow());
            
        
            
        });
        rt.play();
    }

    private void setRotateNextS(Circle circle, boolean rotation, int angle, int dure) {
    RotateTransition rt = new RotateTransition(javafx.util.Duration.seconds(dure), circle);
        rt.setAutoReverse(rotation);
        rt.setByAngle(angle);
        rt.setDelay(javafx.util.Duration.ZERO);
        rt.setRate(3);
        rt.setCycleCount(2);
        rt.setOnFinished((ActionEvent event) -> {
            
               WindowLoader.loadWindowB(getClass().getResource("/fxml/SuperAdminAcceuil.fxml"), "Quick Attendance", (Stage) rootpane.getScene().getWindow());
            
        
            
        });
        rt.play();
    }
    
}
