/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Travaille
 */
public class ConsulterProfesseurController implements Initializable {

     @FXML
    private AnchorPane anchorc;
    
    @FXML
    private StackPane root;
    
   @FXML
    private JFXTextField cin;

    @FXML
    private JFXTextField departement;


    @FXML
    private TableView<TableProfesseur> professeur;

    @FXML
    private TableColumn<TableProfesseur, String> cinp;

    @FXML
    private TableColumn<TableProfesseur, String>nomp;

    @FXML
    private TableColumn<TableProfesseur, String> prenomp;

    @FXML
    private TableColumn<TableProfesseur, String> email;

    @FXML
    private TableColumn<TableProfesseur, String> departementp;
    

    public static String id;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
   
    }
       
    
       void ChargementEc(){
           
         
           ObservableList<TableProfesseur> aobserv = FXCollections.observableArrayList();
           System.out.println("Connexion...");
           aobserv.clear();
           
           Connection con=null;
                 PreparedStatement pst=null;
                 ResultSet rs=null;
                 ResultSet rs1=null;
                 ResultSet rs2=null;
                 String sql1="select cin_p,nom,prenom,email,nomdep from departement d ,professeurs p where (p.cin_p=?) and (p.id_departement=d.id_departement)";
                 String sql2="select cin_p,nom,prenom,email,nomdep from departement d ,professeurs p where (d.nomdep=?) and(d.id_departement=p.id_departement)";
                
             try {
                 

                 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
                 pst=con.prepareStatement(sql1);
                 pst.setString(1, cin.getText());
                 rs = pst.executeQuery();
               
                
                      while(rs.next()){
                     
                     aobserv.add(new TableProfesseur(rs.getString("cin_p"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("nomdep")));
                                   }
                

             } catch (SQLException ex) {
                 Logger.getLogger(AdminSceneController.class.getName()).log(Level.SEVERE, null, ex);
                 
             }
             


              cinp.setCellValueFactory(new PropertyValueFactory<>("cin"));
              nomp.setCellValueFactory(new PropertyValueFactory<>("nom"));
              prenomp.setCellValueFactory(new PropertyValueFactory<>("prenom"));
              email.setCellValueFactory(new PropertyValueFactory<>("email"));
              departementp.setCellValueFactory(new PropertyValueFactory<>("departement"));
              
              
              
            professeur.setItems(aobserv);
   }
     
        void ChargementEd(){
           
          
           ObservableList<TableProfesseur> aobserv = FXCollections.observableArrayList();
           System.out.println("Connexion...");
           aobserv.clear();
           
           Connection con=null;
                 PreparedStatement pst=null;
                 ResultSet rs=null;
                 ResultSet rs1=null;
                 ResultSet rs2=null;
                 String sql1="select cin_p,nom,prenom,email,nomdep from departement d ,professeurs p where (p.cin_p=?) and (p.id_departement=d.id_departement)";
                 String sql2="select cin_p,nom,prenom,email,nomdep from departement d ,professeurs p where (d.nomdep=?) and(d.id_departement=p.id_departement)";
                 try {
                 

                 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
                 pst=con.prepareStatement(sql2);
                 pst.setString(1, departement.getText());
                 rs = pst.executeQuery();
               
                
                      while(rs.next()){
                     
                     aobserv.add(new TableProfesseur(rs.getString("cin_p"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("nomdep")));
                                   }
                

             } catch (SQLException ex) {
                 Logger.getLogger(AdminSceneController.class.getName()).log(Level.SEVERE, null, ex);
                 
             }
             


              cinp.setCellValueFactory(new PropertyValueFactory<>("cin"));
              
              nomp.setCellValueFactory(new PropertyValueFactory<>("nom"));
              prenomp.setCellValueFactory(new PropertyValueFactory<>("prenom"));
              email.setCellValueFactory(new PropertyValueFactory<>("email"));
              departementp.setCellValueFactory(new PropertyValueFactory<>("departement"));
              
              
              
             professeur.setItems(aobserv);
   
    }
           
        
        
       
          
       
       @FXML
        void Onretour(ActionEvent event) {
                Stage curstage = (Stage) root.getScene().getWindow();
                 curstage.hide();
        }

        @FXML
        void onRecherche(ActionEvent event) {
            JFXButton confirmer= new JFXButton("ok");
            
            switch (verif()){
                    
                case 1:
                    ChargementEc();
               
                    break;
                case 2:
                
                    ChargementEd();
                
                    break;
               
                case 0:
                 AlertHendler.showMaterialDialog(root,anchorc,Arrays.asList(confirmer),"veuillez choisir un seul champ \n Merci.", null);
                break;
            
        }

     }
 
        
        private int verif(){

            if((!cin.getText().isEmpty())&&(!departement.getText().isEmpty()))
            {
                return(0);
                 }
            else if ((!cin.getText().isEmpty())&&(!departement.getText().isEmpty())){
                        return(0);
                    }
           
            
            else if (!cin.getText().isEmpty()){
                        return(1);
                    }
            else if (!cin.getText().isEmpty()){
                        return(1);
                    }
                    else if(!departement.getText().isEmpty()){
                       return(2);
                    }
              
            return(0);
 }
        
            @FXML
    void onSupprimer(ActionEvent event) {
        
        JFXButton confirmer= new JFXButton("ok");
        TableProfesseur selected=professeur.getSelectionModel().getSelectedItem();
        
        if (selected == null){
        AlertHendler.showMaterialDialog(root,anchorc,Arrays.asList(confirmer),"veuillez selectionner un professeur \n Merci.", null);
        return;
        }
       
        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
        alerte.setTitle("supprission professeur");
        alerte.setContentText("étes-vous sûr de vouloir supprimer"+  selected.getNom()+"  "+selected.getPrenom()+" ?");
        Optional<ButtonType> reponse = alerte.showAndWait();
       
        if (reponse.get()==ButtonType.OK){
        
            while(AlertHendler.c!=1)
      {  AlertHendler.showMaterialDialogConfirm("confirmation");}
        
        Connection con=null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
            String sql="delete from professeurs where cin_p="+selected.getCin()+"";
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();
            professeur.refresh();
            AlertHendler.showMaterialDialog(root,anchorc,Arrays.asList(confirmer),"Suppression effectuer \n Merci.", null);
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterEtudiantsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }   else
        {AlertHendler.showMaterialDialog(root,anchorc,Arrays.asList(confirmer),"Suppression annuler \n Merci.", null);}
        
    }
      
    
    
    @FXML
    void onModifier(ActionEvent event) {
        JFXButton confirmer= new JFXButton("ok");
        TableProfesseur selected=professeur.getSelectionModel().getSelectedItem();
        id = selected.getCin();
        System.out.println(".."+id+"...");
        
        if (selected == null){
        AlertHendler.showMaterialDialog(root,anchorc,Arrays.asList(confirmer),"veuillez selectionner un professeur \n Merci.", null);
        return;
        }
          
         try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/ModifierProfesseur.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                
                WindowLoader.setStageIcon(stage);
                
                stage.setTitle("Modifier professeur");
                stage.setScene(new Scene(parent));
                stage.setFullScreen(true);
                stage.setFullScreenExitHint("");
                stage.show();
                
                

            } catch (IOException ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    static void setId(String a){
        a="";
    }
}
