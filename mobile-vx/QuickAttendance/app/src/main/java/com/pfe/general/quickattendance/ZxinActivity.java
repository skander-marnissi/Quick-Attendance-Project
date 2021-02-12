package com.pfe.general.quickattendance;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Date;
import java.util.Objects;
import java.util.StringTokenizer;


public class ZxinActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String name;
    DatabaseReference ref ;
    FirebaseDatabase database ;
    String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_zxin);


        database = FirebaseDatabase.getInstance();


        name = mAuth.getCurrentUser().getDisplayName();
        user_id = mAuth.getCurrentUser().getUid();

        ref = database.getReference("Users");


        final CardView capture = findViewById(R.id.cardViewCapture);
        final CardView sign_out = findViewById(R.id.sign_out);
        final CardView acceuil = findViewById(R.id.acceuil);


        acceuil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(ZxinActivity.this, LoginActivity.class);
                ZxinActivity.this.startActivity(main);
                finish();
            }
        });



        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
                finish();
            }
        });


        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(ZxinActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Capture");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            if(result.getContents()==null){
                Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_LONG).show();
            }
            else{

                final String decrypted = decryption(result.getContents(),result.getContents().length());
                Toast.makeText(this, decrypted , Toast.LENGTH_SHORT).show();
;



                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        StringTokenizer stk = new StringTokenizer(decrypted);
                        Date date = new Date();
                        String classe = stk.nextToken();
                        String mat = stk.nextToken();
                        String pass = stk.nextToken();
                        // a modifier ki yetbadel l Qr code

                        final String presence = "< " + user_id + " " + name + " " + classe + " " + date.toString() + " >";

                        if(Objects.equals(pass,"SKANDER")) {

                            ref.child("Informatique/LFI1/" + user_id).child("Presences").setValue(presence);
                            ref.child("Informatique/LFI1/" + user_id).child("Historique").push().setValue(presence);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(ZxinActivity.this,"Error",Toast.LENGTH_LONG).show();
                    }
                });





            }

            super.onActivityResult(requestCode, resultCode, data);
        }


    }

    public void signOut(){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        Intent out = new Intent(ZxinActivity.this, LoginActivity.class);
        ZxinActivity.this.startActivity(out);
        finish();
    }


    private String decryption(String en,int key){
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

    protected void onStart(){
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        name = mAuth.getCurrentUser().getDisplayName();

    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent out = new Intent(ZxinActivity.this, HomePage.class);
        ZxinActivity.this.startActivity(out);
        finish();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        signOut();
    }



}

