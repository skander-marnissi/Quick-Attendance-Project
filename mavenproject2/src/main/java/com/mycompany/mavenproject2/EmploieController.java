/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;
import javafx.event.EventHandler;
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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Travaille
 */
public class EmploieController implements Initializable {

     @FXML
    private ImageView emploie;
    @FXML
    private StackPane roote;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Connection con=null;
       /*try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
            String q="SELECT emploie FROM professeurs where cin_p=?";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,FXMLController.getPrefCinP());
            ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                System.out.println("chargement de l'image");
                InputStream is= rs.getBinaryStream("emploie");
                Image tof = new Image(is);
                emploie.setImage(tof);
                }
                else{System.out.println("erreur de chargement");}

        } catch (SQLException ex) {
            Logger.getLogger(InformationController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
      
 
               }    
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.UNDECORATED);
    }
    
    
    
    
            
    @FXML
    void onReturn(ActionEvent event) {
       ToolBarController tool = new ToolBarController();
       
       Stage curstage = (Stage) roote.getScene().getWindow();
      
       tool.setScene(0);
       
       
       curstage.hide();
    } 

}
