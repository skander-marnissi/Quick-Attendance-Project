/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.tasks.Task;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Travaille
 */
public class WriteData {
    


    private DatabaseReference ref;


    public static void FireInitialize() throws FileNotFoundException, IOException {

      FileInputStream serviceAccount = new FileInputStream("C:\\Users\\Travaille\\Desktop\\Etude LFI3\\mavenproject2\\src\\main\\java\\com\\mycompany\\mavenproject2\\quickattendancepfe-firebase-adminsdk-aqkmu-a73ce6628a.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://quickattendancepfe.firebaseio.com/")
        .build();

        FirebaseApp.initializeApp(options);

        System.out.println("Connexion");
        }


    public String createUser(String cin ,String numins, String nom , String prenom,String email, String departement,String classe){
        String mdp = GeneratePass(nom);
        CreateRequest request = new CreateRequest()
        .setEmail(email)
        .setUid(cin)
        .setEmailVerified(false)
        .setPassword(mdp)
        //.setPhoneNumber("+11234567890")
        .setDisplayName("John Doe")
        //.setPhotoUrl("http://www.example.com/12345678/photo.png")
        .setDisabled(false);

        Task<UserRecord> userRecord = FirebaseAuth.getInstance().createUser(request);
        System.out.println("Succes d'ajout utilisateur: ");
        ref = FirebaseDatabase.getInstance().getReference().child("Users/"+departement+"/"+classe).child(cin);
        Etudiant e = new Etudiant(nom, prenom, numins);
        Map newPost = new HashMap<>();
        newPost.put("FirstName", e.getPrenom());
        newPost.put("LastName", e.getNom());
        newPost.put("Num_ins", e.getN_Ins());
        ref.setValue(newPost);
        return(mdp);

    }
      public String GeneratePass(String nom) {
     
        Random ran = new Random();
       int reference = 1000+ran.nextInt(300);
        return(nom+reference);
     }
}
