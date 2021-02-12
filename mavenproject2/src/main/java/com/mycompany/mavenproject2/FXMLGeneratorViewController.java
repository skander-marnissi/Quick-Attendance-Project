/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;
import com.jfoenix.controls.JFXComboBox;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.fxml.FXML;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author Travaille
 */
public class FXMLGeneratorViewController implements Initializable {

    @FXML
    private JFXComboBox<String> com1;

    @FXML
    private JFXComboBox<String> com2;

        @Override
    public void initialize(URL url, ResourceBundle rb) {
        com2.getItems().addAll("Francais","anglais","JEE","SOA","Math");
        com1.getItems().addAll("LFI1","LFI2","LFM2","PREPA","LFI3");
    }    
    
    
   

    @FXML
    void onCreate(ActionEvent event) {

     String convert = com1.getSelectionModel().getSelectedItem()+" "+com2.getSelectionModel().getSelectedItem();
     String timeStamp = new SimpleDateFormat("_HH_mm_ss").format(Calendar.getInstance().getTime());
     System.out.println(timeStamp);
    convert +=" "+"skander";
    convert=convert.toUpperCase();
    int key=convert.length();
    String cry= encryption(convert,key);
    System.out.println(cry);
    System.out.println(dencryption(cry,key));

    ByteArrayOutputStream out = QRCode.from(cry).to(ImageType.JPG).stream();
    
    File f = new File("C:\\Users\\Travaille\\Desktop\\Etude LFI3\\mavenproject2\\src\\main\\resources\\Qrimages\\"+com1.getSelectionModel().getSelectedItem()+timeStamp+".jpg");
         
             try {
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(out.toByteArray());
                fos.flush();
                
            
            
// TODO add your handling code here:
        } catch (FileNotFoundException ex) {
            System.out.println("Error File");
        } catch (IOException ex) {
            System.out.println("Error IO");
        }
    }                                   
        
   
     
   private String encryption(String en,int key) {
    String result="";
         char ch;
          int l = en.length();
            for(int i=0;i<l;i++) {
                        ch = en.charAt(i);
                        ch+=key;
                        result+=ch;
           
       }
         return(result);
    
  }
   
 private String dencryption(String en,int key){
        String result="";
        int l = en.length();
         char ch;
            for(int i=0;i<l;i++) {
                        ch = en.charAt(i);
                        ch-=key;
                        result+=ch;
           
       }
         return(result);
     
 }
    @FXML
    void onReturn(ActionEvent event) throws IOException {
        Parent acceuilViewParent = FXMLLoader.load(getClass().getResource("/fxml/LoginScene.fxml"));
        Scene acceuilViewScene = new Scene(acceuilViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(acceuilViewScene);
        window.show();

    }
}
