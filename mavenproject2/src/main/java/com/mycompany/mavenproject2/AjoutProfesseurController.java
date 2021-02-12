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
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Travaille
 */
public class AjoutProfesseurController implements Initializable {

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
    private JFXComboBox<String> departement;

    @FXML
    private JFXButton browse;

    @FXML
    private JFXTextField browsetext;

 
    @FXML
    private StackPane rootp;

    @FXML
    private AnchorPane ajoutanchor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
         PreparedStatement pst2=null;
         PreparedStatement pst3=null;
         String sql="INSERT INTO professeurs(cin_p,nom,prenom,email,photo,id_departement) VALUES(?,?,?,?,?,?)";
         String sql2="INSERT INTO authentification (login,password,type,cin_p) VALUES(?,?,?,?)";
         String sql3="INSERT INTO operation (cin_a,description) VALUES(?,?)";
         String sql4="select id_departement from departement where nomdep=?";
         AlertHendler.c=0;
         ResultSet rs = null;
         switch (verif())
                    {
                        case 1:
                            AlertHendler.showMaterialDialog(rootp, ajoutanchor, Arrays.asList(ressayer),"Le champ de la carte d'indentité n'est pas correctemet remplit ! \n veuillez corriger merci", null);
                        
                        break;   
    
                        case 2:
                          AlertHendler.showMaterialDialog(rootp, ajoutanchor, Arrays.asList(ressayer),"Veuillez remplir le champ du nom \n Merci !", null);
                         
                         break;
    
                        case 3:
                         AlertHendler.showMaterialDialog(rootp, ajoutanchor, Arrays.asList(ressayer),"Veuillez remplir le champ du prenom \n Merci !", null) ;
                        
                         break;

                        case 4:
                         AlertHendler.showMaterialDialog(rootp, ajoutanchor, Arrays.asList(ressayer),"Veuillez remplir le champ de l'email \n Merci !", null);
                        
                        break;

                        case 5:
                          AlertHendler.showMaterialDialog(rootp, ajoutanchor, Arrays.asList(ressayer),"Veuillez Choisir un departement \n Merci !", null);;

                        break;
                        
                        case 6:
                           AlertHendler.showMaterialDialog(rootp, ajoutanchor, Arrays.asList(ressayer),"Veuillez Selectionner une photo \n Merci !", null);;

                        break; 
                        
                        case 0:
                          JFXButton confirmer= new JFXButton("ok");
                          confirmer.setOnAction((ActionEvent event1) -> {
                          AdminToolBarController tool = new AdminToolBarController();
                          SuperAdminToolBarController stool = new SuperAdminToolBarController();
                             Stage curstage = (Stage) rootp.getScene().getWindow();
      
                                        tool.setScene(0);
                                        stool.setScene(0);
       
       
                                            curstage.hide();
                                                  });
                                   while(AlertHendler.c!=1)
                                   {  AlertHendler.showMaterialDialogConfirm("confirmation");}
                                     try
                                     {
                                        
                                        File file = new File(browsetext.getText());
                                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
                                        pst=con.prepareStatement(sql);
                                        pst2=con.prepareStatement(sql2);
                                        pst3=con.prepareStatement(sql3);
                                        
                                        pst3.setString(1,cin.getText());
                                        pst3.setString(2,"Ajout du professeur "+nom.getText()+prenom.getText());
                                        pst3.executeUpdate();
                                        pst3.close();
                                        
                                        String mdp=   GeneratePass();     
                                       
                                        pst2.setString(1, cin.getText());
                                        pst2.setString(2, mdp);
                                        pst2.setString(3,"prof");
                                        pst2.setString(4, cin.getText());
                                        pst2.executeUpdate();
                                        pst2.close();
                                        
                                        pst.setString(1, cin.getText());
                                        pst.setString(2, nom.getText());
                                        pst.setString(3, prenom.getText());
                                       
                                        pst.setString(4, email.getText());
                                          
                                       
                                        FileInputStream fis = new FileInputStream(file);
                                        pst.setBinaryStream(5, (InputStream)fis);
                                         
                                        pst2 = con.prepareStatement(sql4);
                                        pst2.setString(1,departement.getSelectionModel().getSelectedItem());
                                        rs=pst2.executeQuery();
                                            if (rs.next()){
                                            pst.setInt(6,rs.getInt("id_departement"));
                                                    }
                                        
                                        pst.executeUpdate();
                                    
                                        pst.close();
                                        /*email pour professeur*/
                                        MdpOublierController.SendEmail(email.getText(), "[Quick ATTENDANCE] Création de compte", "Login: "+cin.getText()+"\n Mot de passe: "+mdp);
                                       
                                        AlertHendler.showMaterialDialog(rootp,ajoutanchor,Arrays.asList(confirmer),"Professeur ajouter avec succes! \n Merci.", null);
                                        break; 
                                       }
                                     catch(MySQLIntegrityConstraintViolationException ex){
                                     AlertHendler.showMaterialDialog(rootp, ajoutanchor, Arrays.asList(ressayer),"Professeur deja enregistrer! \n Veuillez revoir vos paramétres.", null);
                                     }
                                   catch(FileNotFoundException e){
                                     AlertHendler.showMaterialDialog(rootp, ajoutanchor, Arrays.asList(ressayer),"selectionner une photo \n Veuillez revoir vos paramétres.", null);
                               
                                    }    
        
                                  }}                     
    

                            
    
                   
   
   
    @FXML
    void onParcourir(ActionEvent event) {
         FileChooser fc= new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\Travaille\\Desktop"));
        fc.getExtensionFilters().addAll(new ExtensionFilter("images Files","*.png","*.jpg","*.gif","*.jpeg"));
        Stage stage = (Stage) rootp.getScene().getWindow();
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
       
       Stage curstage = (Stage) rootp.getScene().getWindow();
      
       SuperAdminToolBarController stool = new SuperAdminToolBarController();
       stool.setScene(0);
       
       tool.setScene(0);
       
       
       curstage.hide();
    }
        public int verif(){
      try{
        int a = Integer.parseInt(cin.getText());
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
         
        else if (departement.getSelectionModel().getSelectedItem()==null){
            return (5); 
         }
        else if (browsetext.getText().isEmpty()){
           return (6);   
        }
        
    }
      catch (NumberFormatException e) {
          System.out.println("isNan");
          return(1);
}
     
    return(0);
        }

    private String GeneratePass() {
        Random ran = new Random();
       int reference = 1000+ran.nextInt(300);
        return(nom.getText()+reference);
    }
}
