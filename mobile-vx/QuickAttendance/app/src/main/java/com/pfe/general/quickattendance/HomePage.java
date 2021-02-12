package com.pfe.general.quickattendance;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.NavigationView;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HomePage extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    DatabaseReference ref ;
    private FirebaseAuth mAuth;

    FirebaseDatabase database ;
    DatabaseReference firebaseRootRef;
    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mDrawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");
        mAuth = FirebaseAuth.getInstance();



        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                user = findViewById(R.id.user);
                user.setText(mAuth.getCurrentUser().getDisplayName());
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        //getInformation();







        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                int a = R.id.presence;

                if(id == a){

                    Intent i = new Intent(HomePage.this, ZxinActivity.class);
                    startActivity(i);
                    finish();
                }
                 else if (id == R.id.deco){
                    signOut();
                    finish();

                }
                else if (id == R.id.infos){
                    Intent i = new Intent(HomePage.this, Information.class);
                    startActivity(i);

                 }

                return false;
            }
        });




    }




    public void getInformation(){
        Callbacks e = new Callbacks();
        String actif_user = mAuth.getCurrentUser().getUid();
        String mail = mAuth.getCurrentUser().getEmail();
        Toast.makeText(HomePage.this,actif_user, Toast.LENGTH_LONG).show();

        firebaseRootRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Informatique/LFI1/" + actif_user);
        e.readData(firebaseRootRef, new Callbacks.OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure() {

            }
        });


    }



    public void signOut(){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        Intent out = new Intent(HomePage.this, LoginActivity.class);
        HomePage.this.startActivity(out);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        signOut();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        signOut();

    }

}
