package com.mycompany.mavenproject2;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLController implements Initializable{

    public static int t=0;
    
    
    @FXML
    private AnchorPane rootanchorpane;
  
    @FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField pass;

  

      @Override
    public void initialize(URL location, ResourceBundle resources) {
        
       
    }


    /* remove preferences*/
    public static void removePerf(){
        try {
            Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
            pref.removeNode();
        } catch (BackingStoreException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*Admin preferences*/
  public static void setPrefA(String a, String b,String c,String d){
    Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
    pref.put("cin" , a);
    pref.put("nom" , b);
    pref.put("prenom" , c);
    pref.put("email" , d);
    
    
    
    }  
    public static String getPrefCinA(){
    Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
    return(pref.get("cin" , "root"));
    }  
    public static String getPrefNomA(){
    Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
    return(pref.get("nom" , "root"));
    }  
    public static String getPrefPrenomA(){
    Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
    return(pref.get("prenom" , "root"));
    }  
    
    public static String getPrefEmailA(){
    Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
    return(pref.get("email" , "root"));
    }  
 
     /*Professeur preferences*/
    public static void setPrefP(String a, String b,String c,String d,String e){
    Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
    pref.put("cin" , a);
    pref.put("nom" , b);
    pref.put("prenom" , c);
    pref.put("departement" , d);
    pref.put("email" , e);
    
    
    }  
    public static String getPrefCinP(){
    Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
    
    return(pref.get("cin" , "root"));
    }  
    public static String getPrefNomP(){
    Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
    return(pref.get("nom" , "root"));
    }  
    public static String getPrefPrenomP(){
    Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
    return(pref.get("prenom" , "root"));
    }  
    public static String getPrefDepartementP(){
    Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
    return(pref.get("departement" , "root"));
    }  
     public static String getPrefEmailP(){
    Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
    return(pref.get("email" , "root"));
    }  
    
     /*super admin preferences*/
      public static void setPrefS(String a){
    Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
    pref.put("login" , a);
       }  
        public static String getPrefLoginS(){
    Preferences pref = Preferences.userNodeForPackage(FXMLController.class);
    return(pref.get("login" , "root"));
    }
        
        
        
        
    @FXML
    public void onPush(ActionEvent event) throws IOException {
        System.out.println("Connexion...");
        Connection con=null;
        PreparedStatement pst=null;
        PreparedStatement pst2=null;
        PreparedStatement pst3=null;
        
        
        ResultSet rs=null;
        ResultSet rs2=null;
        ResultSet rs3=null;
        ResultSet rs4=null;
        
        
        String sql="select * from authentification where login=? and password=? ";
        String sql2="select * from professeurs where cin_p=? ";
        String sql3="select * from admin where cin_a=? ";
        String sql4="select * from superadmin where login=? and password=? ";
        String sql5="select nomdep from departement where id_departement=? ";
        
        
        try{
            
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
            
        pst=con.prepareStatement(sql);
        pst.setString(1, user.getText());
        pst.setString(2, pass.getText());
        
        rs=pst.executeQuery();
        
        if(rs.next()){
          
           String type=rs.getString("type");
           
           if (type.equals("prof")) {
                    String cinp=rs.getString("cin_p");
                    pst=con.prepareStatement(sql2);
                    System.out.println(cinp);
                     pst.setString(1,cinp);
                     
                     rs2=pst.executeQuery();
                        if (rs2.next()){
                         System.out.println("je suis sur rs2");
                         String d="";
                         String a = rs2.getString("cin_p");
                         String b = rs2.getString("nom");
                         String c = rs2.getString("prenom");
                         pst = con.prepareStatement(sql5);
                         pst.setInt(1,rs2.getInt("id_departement"));
                         rs4=pst.executeQuery();
                         if (rs4.next()){
                         d = rs4.getString("nomdep");
                         }
                         
                         String e = rs2.getString("email");
                         
                         setPrefP(a,b,c,d,e);
                         System.out.println("j'ai remplis pref pour prof");                  
                        }else{ System.out.println("pas trouver");}
                       
                    t=1;
                    System.out.println(type);
                    System.out.println(t);
                    WindowLoader.loadWindowC(getClass().getResource("/fxml/ChargementScene.fxml"), "Quick Attendance", (Stage) rootanchorpane.getScene().getWindow());
                    
                }
                else {
                    String cina=rs.getString("cin_a");
                    pst=con.prepareStatement(sql3);
                    pst.setString(1,cina);
                    rs2=pst.executeQuery();
                        if (rs2.next()){
                         System.out.println("je suis sur rs2 pour admin");
                         String a = rs2.getString("cin_a");
                         String b = rs2.getString("nom");
                         String c = rs2.getString("prenom"); 
                         String d = rs2.getString("email");
                         setPrefA(a,b,c,d);
                         System.out.println("j'ai remplis pref pour admin");    
                        }
                        else{
                           System.out.println("pas trouver");}
                    
                    t=2;    
                    System.out.println(t);
                    WindowLoader.loadWindowC(getClass().getResource("/fxml/ChargementScene.fxml"), "Quick Attendance", (Stage) rootanchorpane.getScene().getWindow());
                 
                }
           
           
           
        }
        else {
            pst3=con.prepareStatement(sql4);
            pst3.setString(1, user.getText());
            pst3.setString(2, pass.getText());   
            rs3=pst3.executeQuery(); 
                if (rs3.next()){
                    t=3;    
                    setPrefS(user.getText());
                    System.out.println(t);
                    WindowLoader.loadWindowC(getClass().getResource("/fxml/ChargementScene.fxml"), "Quick Attendance", (Stage) rootanchorpane.getScene().getWindow());
                    
                }
                else{
                     AlertHendler.showErrorMessage(null, "Votre Identifiant Ou mot de passe est incorrecte");
                }
        }
        
        } catch (SQLException ex) {
           System.err.print(ex);
        }
       
    }
 @FXML
    void onMdpOublier(ActionEvent event) throws IOException {
        loadMdp();
        
    }
    void loadMdp(){
        
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/MdpOublier.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            WindowLoader.setStageIcon(stage);
            stage.setTitle("Demande de recupération");
            stage.setScene(new Scene(parent));
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
  
} 
 


    


