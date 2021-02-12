/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Travaille
 */
public class AjoutEtudiantController implements Initializable {
   
    @FXML
    private JFXTextField numins;
    @FXML
    private JFXComboBox<String> departement;
    
    @FXML
    private ImageView img;
    
    @FXML
    private JFXTextField cin;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXComboBox<String> classe;

    @FXML
    private JFXButton browse;

    @FXML
    private JFXTextField browsetext;

 
    @FXML
    private StackPane roote;

    @FXML
    private AnchorPane ajoutanchor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        classe.getItems().addAll("LFI1","LFI2","LFI3","LFM1","LFM2","PREPA");
        departement.getItems().addAll("Informatique","Physique","Biologie","Chimie","Mathematique","Electornique");
        //AlertHendler.showMaterialDialogConfirm("confirmation");
    }    
    
    
    @FXML
    void onAjouter(ActionEvent event) throws SQLException, FileNotFoundException {
        JFXButton ressayer= new JFXButton("revoir");
        System.out.println("Connexion...");
        System.out.println(verif());
         Connection con=null;
         PreparedStatement pst=null;
         String sql="INSERT INTO etudiants(cin_e,nom,prenom,photo,email,id_classe) VALUES(?,?,?,?,?,?)";
         String sql2="select id_classe from classe  where nom_c=?";
         ResultSet rs= null;
         AlertHendler.c=0;
         switch (verif())
                    {
                        case 1:
                            AlertHendler.showMaterialDialog(roote, ajoutanchor, Arrays.asList(ressayer),"Le champ de la carte d'indentité n'est pas correctemet remplit ! \n veuillez corriger merci", null);
                        
                        break;   
    
                        case 2:
                          AlertHendler.showMaterialDialog(roote, ajoutanchor, Arrays.asList(ressayer),"Veuillez remplir le champ du nom \n Merci !", null);
                         
                         break;
    
                        case 3:
                         AlertHendler.showMaterialDialog(roote, ajoutanchor, Arrays.asList(ressayer),"Veuillez remplir le champ du prenom \n Merci !", null) ;
                        
                         break;

                        case 4:
                         AlertHendler.showMaterialDialog(roote, ajoutanchor, Arrays.asList(ressayer),"Veuillez remplir le champ de l'email \n Merci !", null);
                        
                        break;

                        case 5:
                          AlertHendler.showMaterialDialog(roote, ajoutanchor, Arrays.asList(ressayer),"Veuillez Choisir une classe \n Merci !", null);;

                        break;
                        
                        case 6:
                           AlertHendler.showMaterialDialog(roote, ajoutanchor, Arrays.asList(ressayer),"Veuillez Selectionner une photo \n Merci !", null);;

                        break; 
                       
                        case 7:
                           AlertHendler.showMaterialDialog(roote, ajoutanchor, Arrays.asList(ressayer),"Veuillez verifiez le numéro d'incription \n Merci !", null);;

                        break; 
                        
                        case 0:
                          JFXButton confirmer= new JFXButton("ok");
                          confirmer.setOnAction((ActionEvent event1) -> {
                          AdminToolBarController tool = new AdminToolBarController();
                          SuperAdminToolBarController stool = new SuperAdminToolBarController();
                             Stage curstage = (Stage) roote.getScene().getWindow();
      
                                        tool.setScene(0);
                                        stool.setScene(0);
       
                                            curstage.hide();
                                                  });
                                   while(AlertHendler.c!=1)
                                   {  AlertHendler.showMaterialDialogConfirm("confirmation");}
                                     try
                                     {
                                        /*ajout dans la base de donnée administration*/
                                        File file = new File(browsetext.getText());
                                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
                                        
                                        
                                        pst=con.prepareStatement(sql2);
                                        pst.setString(1, classe.getSelectionModel().getSelectedItem());
                                        rs=pst.executeQuery();
                                        
                                        if (rs.next()){
                                        pst=con.prepareStatement(sql);
                                        
                                        pst.setString(1, cin.getText());
                                        pst.setString(2, nom.getText());
                                        pst.setString(3, prenom.getText());
                                        FileInputStream fis = new FileInputStream(file);
                                        pst.setBinaryStream(4, (InputStream)fis);
                                        
                                         pst.setString(5, email.getText());
                                         pst.setInt(6, rs.getInt("id_classe"));
                                       
                                        
                                        pst.executeUpdate();
                                        pst.close();}
                                        WriteData e = new WriteData();
                                        
                                        
                                      
                                        
                                        /*ajout dans Firebase*/
                                        String mdp = e.createUser(cin.getText(), numins.getText(), nom.getText(), prenom.getText(), email.getText(), departement.getSelectionModel().getSelectedItem(), classe.getSelectionModel().getSelectedItem());
                                       
                                        /*email de confirmation contenant login + mdp*/
                                        MdpOublierController.SendEmail(email.getText(), "[Quick ATTENDANCE] Création de compte", "Login: "+cin.getText()+"\n Mot de passe: "+mdp);
                                        
                                        
                                        AlertHendler.showMaterialDialog(roote,ajoutanchor,Arrays.asList(confirmer),"Etudiant ajouter avec succes! \n Merci.", null);
                                        break; 
                                     }
                                     
                                     catch(MySQLIntegrityConstraintViolationException ex){
                                         
                                     AlertHendler.showMaterialDialog(roote, ajoutanchor, Arrays.asList(ressayer),"Etudiant deja enregistrer! \n Veuillez revoir vos paramétres.", null);
                                     }
                                   catch(FileNotFoundException e){
                                       
                                     AlertHendler.showMaterialDialog(roote, ajoutanchor, Arrays.asList(ressayer),"Selectionner une photo \n Veuillez revoir vos paramétres.", null);
                               
                                    }    
        
                                  }}                     
    

                            
    
                   
   
   
    @FXML
    void onParcourir(ActionEvent event) {
         FileChooser fc= new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\Travaille\\Desktop"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("images Files","*.png","*.jpg","*.gif","*.jpeg"));
        Stage stage = (Stage) roote.getScene().getWindow();
        File file= fc.showOpenDialog(stage);
        if(file!=null){
           browsetext.setEditable(true);
           browsetext.setText(file.getAbsolutePath());
           browse.getStyleClass().set(1, "parcourir-button-remplie");
           browsetext.setEditable(false);
           Image tof =new Image(file.toURI().toString(),95,81,true,true);
           
           img.setImage(tof);
           
        }else{
        browse.getStyleClass().set(1, "parcourir-button");
        }
    }

    @FXML
    void onRetour(ActionEvent event) {
      AdminToolBarController tool = new AdminToolBarController();
       
       Stage curstage = (Stage) roote.getScene().getWindow();
       SuperAdminToolBarController stool = new SuperAdminToolBarController();
       stool.setScene(0);
      
       tool.setScene(0);
       
       
       curstage.hide();
    }
        public int verif(){
      try{
        int a = Integer.parseInt(cin.getText());
        int b = Integer.parseInt(numins.getText());
        if ((cin.getText().isEmpty())||(cin.getText().length()!=8)){
         return (1);   
        }
        else if (nom.getText().isEmpty()){
           return (2);   
        }
         else if (prenom.getText().isEmpty()){
           return (3);
        }
         else if (!(email.getText().contains("@"))||!(email.getText().contains("."))){
             return (4);   
        }
         
        else if (classe.getSelectionModel().getSelectedItem()==null){
            return (5); 
         }
        else if (browsetext.getText().isEmpty()){
           return (6);   
        }
        else if ((numins.getText().isEmpty())||(numins.getText().length()!=7)){
           return (7); 
    }
      }
      catch (NumberFormatException e) {
          System.out.println("isNan");
          return(1);
}
     
    return(0);
        }
}
