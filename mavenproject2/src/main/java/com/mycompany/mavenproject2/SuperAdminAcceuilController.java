/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Travaille
 */
public class SuperAdminAcceuilController implements Initializable {

   @FXML
    private StackPane rootpane;

    @FXML
    private AnchorPane qranchor;
    
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
   
    /*table authentification*/
    @FXML
    private TableView<TableAuthentification> authentification;

    @FXML
    private TableColumn<TableAuthentification, String> login;

    @FXML
    private TableColumn<TableAuthentification, String> password;

    @FXML
    private TableColumn<TableAuthentification, String> type;

    @FXML
    private TableColumn<TableAuthentification, String> acinp;

    @FXML
    private TableColumn<TableAuthentification, String> acina;
    
    
   
    
    /*table operation*/
      @FXML
    private TableView<TableOperation> operations;

    @FXML
    private TableColumn<TableOperation, Integer> oid;

    @FXML
    private TableColumn<TableOperation, String> ocina;

    @FXML
    private TableColumn<TableOperation, String> opp;

    @FXML
    private TableColumn<TableOperation, String> date;
        
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDrawer();
        ChargementA();
        ChargementO();
        
        
        
        
        
        
    }    
    
    private void initDrawer() {
        /*intégration de l'animation dans le leftDrawer avec la toolbar a partir du click sur le humbergeur*/  
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SuperAdminToolBar.fxml"));
            VBox toolbar = loader.load();
            drawer.setSidePane(toolbar);
            
        } catch (IOException ex) {
           
        }
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            drawer.toggle();
        });
        drawer.setOnDrawerOpening((event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            drawer.toFront();
        });
        drawer.setOnDrawerClosed((event) -> {
            drawer.toBack();
            task.setRate(task.getRate() * -1);
            task.play();
        });
}
 
   void ChargementA(){
       ObservableList<TableAuthentification> aobserv = FXCollections.observableArrayList();
       System.out.println("Connexion...");
            Connection con=null;
            PreparedStatement pst=null;
            String sql="select * from authentification";
            
            ResultSet rs=null;
        try {
            /*init table demande*/
             
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
            pst=con.prepareStatement(sql);
            rs = pst.executeQuery();
           while(rs.next()){
                //System.out.println("Authentification ok");
                aobserv.add(new TableAuthentification(rs.getString("login"), rs.getString("password"), rs.getString("type"), rs.getString("cin_a"), rs.getString("cin_p")));
                
                
            }
   
        } catch (SQLException ex) {
            Logger.getLogger(AdminSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
         login.setCellValueFactory(new PropertyValueFactory<>("login"));
         password.setCellValueFactory(new PropertyValueFactory<>("password"));
         type.setCellValueFactory(new PropertyValueFactory<>("type"));
         acina.setCellValueFactory(new PropertyValueFactory<>("cin_a"));
         acinp.setCellValueFactory(new PropertyValueFactory<>("cin_p"));
        authentification.setItems(aobserv);
   }
       
          void ChargementO(){
       ObservableList<TableOperation> aobserv = FXCollections.observableArrayList();
       System.out.println("Connexion...");
            Connection con=null;
            PreparedStatement pst=null;
            String sql="select * from operation";
            
            ResultSet rs=null;
        try {
            /*init table demande*/
             
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
            pst=con.prepareStatement(sql);
            rs = pst.executeQuery();
           while(rs.next()){
                //System.out.println("Authentification ok");
                aobserv.add(new TableOperation(rs.getInt("id"), rs.getString("cin_a"), rs.getString("description"), rs.getString("date_o")));
                
                
            }
   
        } catch (SQLException ex) {
            Logger.getLogger(AdminSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
         oid.setCellValueFactory(new PropertyValueFactory<>("id"));
         ocina.setCellValueFactory(new PropertyValueFactory<>("cin_a"));
         opp.setCellValueFactory(new PropertyValueFactory<>("description"));
         date.setCellValueFactory(new PropertyValueFactory<>("date_o"));
         
         operations.setItems(aobserv);
   }
    
}
