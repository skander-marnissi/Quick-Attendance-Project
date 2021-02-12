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
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
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
public class AjoutAdminController implements Initializable {
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
    private JFXButton browse;

    @FXML
    private JFXTextField browsetext;

 
    @FXML
    private StackPane roota;

    @FXML
    private AnchorPane ajoutanchor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
         
         String sql2="INSERT INTO authentification (login,password,type,cin_a) VALUES(?,?,?,?)";
         String sql3="INSERT INTO operation (cin_a,description) VALUES(?,?)";
         String sql="INSERT INTO admin(cin_a,nom,prenom,email,photo) VALUES(?,?,?,?,?)";
         AlertHendler.c=0;
         switch (verif())
                    {
                        case 1:
                            AlertHendler.showMaterialDialog(roota, ajoutanchor, Arrays.asList(ressayer),"Le champ de la carte d'indentiter n'est pas correctemet remplit (assurer vous qu'il ne s'agit que de nombre et/ou verifier le nombre de caractere  ! \n veuillez corriger merci", null);
                        
                        break;   
    
                        case 2:
                          AlertHendler.showMaterialDialog(roota, ajoutanchor, Arrays.asList(ressayer),"Veuillez remplir le champ du nom \n Merci !", null);
                         
                         break;
    
                        case 3:
                         AlertHendler.showMaterialDialog(roota, ajoutanchor, Arrays.asList(ressayer),"Veuillez remplir le champ du prenom \n Merci !", null) ;
                        
                         break;

                        case 4:
                         AlertHendler.showMaterialDialog(roota, ajoutanchor, Arrays.asList(ressayer),"Veuillez remplir le champ de l'email \n Merci !", null);
                        
                        break;

                        case 5:
                           AlertHendler.showMaterialDialog(roota, ajoutanchor, Arrays.asList(ressayer),"Veuillez Selectionner une photo \n Merci !", null);;

                        break; 
                        
                        case 0:
                          JFXButton confirmer= new JFXButton("ok");
                          confirmer.setOnAction((ActionEvent event1) -> {
                         
                          SuperAdminToolBarController stool = new SuperAdminToolBarController();
       
                             Stage curstage = (Stage) roota.getScene().getWindow();
                             
                             
                                      
                                        stool.setScene(0);
       
                                            curstage.hide();
                                                  });
                                   while(AlertHendler.c!=1)
                                   {  AlertHendler.showMaterialDialogConfirm("confirmation");}
                                     try
                                     {
                                        String a = cin.getText().toString()+ "_a";
                                        File file = new File(browsetext.getText());
                                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
                                        pst=con.prepareStatement(sql);
                                        
                                        pst2=con.prepareStatement(sql2);
                                        pst3=con.prepareStatement(sql3);
                                        
                                        pst3.setString(1,a);
                                        pst3.setString(2,"Ajout de l'administrateur "+nom.getText()+" "+prenom.getText());
                                        pst3.executeUpdate();
                                        pst3.close();
                                        
                                        String mdp=GeneratePass();     
                                       
                                        pst2.setString(1, a);
                                        pst2.setString(2, mdp);
                                        pst2.setString(3,"admin");
                                        pst2.setString(4, cin.getText());
                                        pst2.executeUpdate();
                                        pst2.close();
                                        
                                        
                                        
                                        System.out.println(a);
                                        pst.setString(1, a);
                                        pst.setString(2, nom.getText());
                                        pst.setString(3, prenom.getText());
                                        pst.setString(4, email.getText());
                                        
                                        FileInputStream fis = new FileInputStream(file);
                                        pst.setBinaryStream(5, (InputStream)fis);
                                        
                                        pst.executeUpdate();
                                        pst.close();
                                        
                                        MdpOublierController.SendEmail(email.getText(), "[Quick ATTENDANCE] Création de compte", "Login: "+cin.getText()+"\n Mot de passe: "+mdp);
                                       
                                        AlertHendler.showMaterialDialog(roota,ajoutanchor,Arrays.asList(confirmer),"Admin ajouter avec succes! \n Merci.", null);
                                        break; 
                                       }
                                     catch(MySQLIntegrityConstraintViolationException ex){
                                     AlertHendler.showMaterialDialog(roota, ajoutanchor, Arrays.asList(ressayer),"Admin deja enregistrer! \n Veuillez revoir vos paramétres.", null);
                                     }
                                   catch(FileNotFoundException e){
                                     AlertHendler.showMaterialDialog(roota, ajoutanchor, Arrays.asList(ressayer),"Selectionner une photo \n Veuillez revoir vos paramétres.", null);
                               
                                    }    
        
                                  }}                     
    

                            
    
                   
   
   
    @FXML
    void onParcourir(ActionEvent event) {
         FileChooser fc= new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\Travaille\\Desktop"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("images Files","*.png","*.jpg","*.gif","*.jpeg"));
        Stage stage = (Stage) roota.getScene().getWindow();
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
       SuperAdminToolBarController stool = new SuperAdminToolBarController();
       stool.setScene(0);
       
       Stage curstage = (Stage) roota.getScene().getWindow();
      
       
       
       
       curstage.hide();
    }
        public int verif(){
      try{
        int a = Integer.parseInt(cin.getText());
        if ((cin.getText().isEmpty())||(cin.getText().length()!=6)){
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
         
        else if (browsetext.getText().isEmpty()){
           return (5);   
        }
        
    }
      catch (NumberFormatException e) {
          System.out.println("isNan");
          return(1);
}
     
    return(0);
        }

  public String GeneratePass() {
     
        Random ran = new Random();
       int reference = 1000+ran.nextInt(300);
        return(nom.getText()+reference);
     }
}
