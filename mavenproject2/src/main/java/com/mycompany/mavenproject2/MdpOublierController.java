/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * FXML Controller class
 *
 * @author Travaille
 */
public class MdpOublierController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private StackPane rootm;

    @FXML
    private AnchorPane anchorm;

    
    
    @FXML
    private JFXTextField cin;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
       
     
    public boolean verif(){
     try{
        int a = Integer.parseInt(cin.getText());
        if ((cin.getText().isEmpty())||(cin.getText().length()!=8)){
         return (false); 
        }
     
     }
        catch (NumberFormatException e) {
          System.out.println("isNan");
          return(false);
                            } 
     return (true);
    }
    
    
    @FXML
    void onEnvoyer(ActionEvent event) {
          JFXButton ok= new JFXButton("ok"); 
          Connection con=null;
         PreparedStatement pst=null;
         ResultSet rs=null;
         ResultSet rs2=null;
          String sql="select * from professeurs where cin_p="+cin.getText()+"";
          String sql1="select * from authentification where login="+cin.getText()+"";
          if (verif()){
          try{
            System.out.println("verif ok");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
            
        pst=con.prepareStatement(sql);
    
        rs=pst.executeQuery();
            
        if(rs.next()){
           String email=rs.getString("email");
           pst=con.prepareStatement(sql1);
           rs2=pst.executeQuery();
           System.out.println("rs 1 ok");
          if(rs2.next()){
             System.out.println("rs 2 ok");
              String pass=rs2.getString("password");
              System.out.println(pass);
              SendEmail(email,"[QUICK ATTENDANCE]recuperation de mot de passe","Votre mot de passe est : "+pass);
               AlertHendler.showMaterialDialog(rootm, anchorm, Arrays.asList(ok),"Vous receverez un e-mail contenant votre mot de passe \n Merci !", null);
          }
        }
          else{
            AlertHendler.showMaterialDialog(rootm, anchorm, Arrays.asList(ok),"Ce compte n'existe pas  \n Merci !", null);   
          }
    }
    
    
         catch (SQLException ex) {
              Logger.getLogger(MdpOublierController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
          else{
               AlertHendler.showMaterialDialog(rootm, anchorm, Arrays.asList(ok),"Numéro de catre d'identité incorrecte \n Merci !", null);
          }
          
              }
    
    
    
   public static void SendEmail(String addresse , String subject ,String message){
          try {
              String from = "skander.hamdi.firebase@gmail.com";
              String pass = "skander1.hamdi1";
              String[] to = {addresse};
              String host = "smtp.gmail.com";
              
              Properties prop = System.getProperties();
              prop.put("mail.smtp.starttls.enable", "true");
              prop.put("mail.smtp.host", host);
              prop.put("mail.smtp.user", from);
              prop.put("mail.smtp.password", pass);
              prop.put("mail.smtp.port", "587");
              prop.put("mail.smtp.auth", "true");
              
              Session session = Session.getDefaultInstance(prop);
              MimeMessage msg = new MimeMessage(session);
              msg.setFrom(new InternetAddress(from));
              InternetAddress[] toaddress = new InternetAddress[to.length];
              for(int i =0 ; i< to.length;i++){
                  toaddress[i]= new InternetAddress(to[i]);
                  
              }
               for(int i =0 ; i< toaddress.length;i++){
                 msg.setRecipient(Message.RecipientType.TO, toaddress[i]);
                
              }
               msg.setSubject(subject);
               msg.setContent(message , "text/html ; charset=utf-8");
               Transport transport = session.getTransport("smtp");
               transport.connect(host,from,pass);
               transport.sendMessage(msg, msg.getAllRecipients());
               transport.close();
               
              
          } catch (MessagingException ex) {
              Logger.getLogger(MdpOublierController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
        
     
    }
  public static boolean isReachableByPing(String host) {
     try{
                String cmd = "";
                if(System.getProperty("os.name").startsWith("Windows")) {   
                        // pour windows
                        cmd = "ping -n 1 " + host;
                } else {
                        // Pour linux et os 
                        cmd = "ping -c 1 " + host;
                }

                Process myProcess = Runtime.getRuntime().exec(cmd);
                myProcess.waitFor();

                if(myProcess.exitValue() == 0) {

                        return true;
                } else {

                        return false;
                }

        } catch( Exception e ) {

                e.printStackTrace();
                return false;
        }
  }
}
