package com.pfe.general.quickattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.StringTokenizer;


public class Information extends AppCompatActivity {

        FirebaseAuth mAuth;
        String user_id;
        TextView nm;
        TextView prnm;

        TextView fire;

        TextView mail;
        String name;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        nm = findViewById(R.id.nom);
        prnm = findViewById(R.id.prenom);
        fire = findViewById(R.id.firecin);
        mail = findViewById(R.id.num_ins);
        final CardView remplir = findViewById(R.id.cdremplir);

        remplir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInformation();

            }
        });



    }

    public void getInformation(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");
        mAuth = FirebaseAuth.getInstance();


        user_id = mAuth.getCurrentUser().getUid();
        name = mAuth.getCurrentUser().getDisplayName();
        StringTokenizer stk = new StringTokenizer(name);
        String nom = stk.nextToken();
        String prenom = stk.nextToken();
        String dispmail =mAuth.getCurrentUser().getEmail();


        fire.setText(user_id);
        nm.setText(nom);
        prnm.setText(prenom);
        mail.setText(dispmail);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent out = new Intent(Information.this, HomePage.class);
        Information.this.startActivity(out);
        finish();
    }
    public void signOut(){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        Intent out = new Intent(Information.this, LoginActivity.class);
        Information.this.startActivity(out);
        finish();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        signOut();
    }


}
