package com.pfe.general.quickattendance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {



    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password1);

        final CardView clogin = findViewById(R.id.cardViewLogin);
        mAuth = FirebaseAuth.getInstance();


        // Takes you to the user page when Auth is successful

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                if (firebaseAuth.getCurrentUser() != null){

                    Intent scan_page = new Intent(LoginActivity.this, HomePage.class);
                    LoginActivity.this.startActivity(scan_page);
                    finish();


                }

            }



        };
        // Login button: Checks for the user in the Auth DataBase and changes the AuthState

        clogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = username.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this, "One of the text fields is Empty", Toast.LENGTH_LONG).show();
                }
                else {

                    mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task){
                            if (!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Your E-mail or password is incorrect", Toast.LENGTH_LONG).show();
                            }

                        }

                    });
                }


            }
        });




    }
    protected void onStart(){
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(authStateListener);



    }
    @Override
    public void onBackPressed(){
        moveTaskToBack(false);
    }




}