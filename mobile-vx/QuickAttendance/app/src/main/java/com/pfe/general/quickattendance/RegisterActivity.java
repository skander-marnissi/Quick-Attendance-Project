package com.pfe.general.quickattendance;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference ref;
    private String user_id;
    String depSelected;
    String classSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();



        //reglage taa l spinner department
        Spinner department = findViewById(R.id.department);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Department, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(adapter);

        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final TextView selected = (TextView) view;



                if(selected.getText().equals("Informatique")){
                    Spinner Classe = findViewById(R.id.Class);
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(RegisterActivity.this,R.array.info, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    Classe.setAdapter(adapter);
                    Classe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            TextView selected1 = (TextView) view;
                            classSelected = selected1.getText().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                else if (selected.getText().equals("Mathematique")){

                    Spinner Classe = findViewById(R.id.Class);
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(RegisterActivity.this,R.array.math, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    Classe.setAdapter(adapter);
                    Classe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            TextView selected2 = (TextView) view;
                            classSelected = selected2.getText().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }
                else {

                    Spinner Classe = findViewById(R.id.Class);
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(RegisterActivity.this,R.array.phy, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    Classe.setAdapter(adapter);
                    Classe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            TextView selected3 = (TextView) view;
                            classSelected = selected3.getText().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                depSelected = selected.getText().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






        final CardView cregister = findViewById(R.id.cardViewRegister);
        final CardView cback = findViewById(R.id.cardViewBack);



        cback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(register);
            }
        });

        cregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeData();
               // Intent relog = new Intent(RegisterActivity.this, LoginActivity.class);
                //RegisterActivity.this.startActivity(relog);


            }
        });



    }


    public void storeData(){

        final EditText password = findViewById(R.id.password2);
        final EditText mail = findViewById(R.id.email);


       final CardView cregister = findViewById(R.id.cardViewRegister);
       final CardView cback = findViewById(R.id.cardViewBack);



        String s_mail = mail.getText().toString();


        String s_pass = password.getText().toString();


        if ((TextUtils.isEmpty(s_mail)||TextUtils.isEmpty(s_pass))) {
            Toast.makeText(RegisterActivity.this, "Operation Failed: One of the Fields is Empty", Toast.LENGTH_LONG).show();
        }
        else {
            final EditText FirstName = findViewById(R.id.FirstName);
            final EditText LastName = findViewById(R.id.LastName);
            final String s_fname = FirstName.getText().toString();
            final String s_lname = LastName.getText().toString();
            final EditText cin = findViewById(R.id.cin);
            final EditText ins = findViewById(R.id.ins);
            final String s_cin = cin.getText().toString();
            final String s_ins = ins.getText().toString();

            if (TextUtils.isEmpty(s_fname) || TextUtils.isEmpty(s_cin) || TextUtils.isEmpty(s_lname) || TextUtils.isEmpty(s_ins)) {
                Toast.makeText(RegisterActivity.this, "Operation Failed: One of the Fields is Empty", Toast.LENGTH_SHORT).show();
            } else if (s_cin.length() != 8) {
                Toast.makeText(RegisterActivity.this, "Please Enter a Valid CIN number", Toast.LENGTH_LONG).show();

            } else if (s_ins.length() != 7) {
                Toast.makeText(RegisterActivity.this, "Please Enter a Valid INS number", Toast.LENGTH_LONG).show();

            } else {
                mAuth.createUserWithEmailAndPassword(s_mail, s_pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Operation Failed.. Try again", Toast.LENGTH_LONG).show();
                        }

                        else {
                            user_id = mAuth.getCurrentUser().getUid();
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest update = new UserProfileChangeRequest.Builder().setDisplayName(s_fname+ " " + s_lname).build();
                            user.updateProfile(update);


                            ref = FirebaseDatabase.getInstance().getReference().child("Users" + "/" + depSelected + "/" + classSelected ).child(user_id);

                            Map newPost = new HashMap<>();
                            newPost.put("FirstName", s_fname);
                            newPost.put("LastName", s_lname);
                            newPost.put("Cin", s_cin);
                            newPost.put("Num_ins", s_ins);
                            ref.setValue(newPost);
                            Toast.makeText(RegisterActivity.this, "Registering.. Thank you for waiting", Toast.LENGTH_SHORT).show();



                        }


                    }
                });

            }
        }



    }



}
