package com.example.addscartmerchant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton img;
    FirebaseDatabase fdata;
    FirebaseAuth mAuth;

    ArrayList<String> ItemType = new ArrayList<String>();
    ArrayList<String> FullAddress = new ArrayList<String>();
    ArrayList<String> Date = new ArrayList<String>();
    ArrayList<String> PhoneNumber = new ArrayList<String>();
    ArrayList<String> Latitude = new ArrayList<String>();
    ArrayList<String> Longitude = new ArrayList<String>();
    ArrayList<Order> TodayPickup = new ArrayList<Order>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        img = findViewById(R.id.Img);

        fdata = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        DatabaseReference refitems = fdata.getReference("OrderId").child("Items");
        refitems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String data = dataSnapshot.getValue(String.class);
                    ItemType.add(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference refdate = fdata.getReference("OrderId").child("Date");
        refdate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String data = dataSnapshot.getValue(String.class);
                    Date.add(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference refnumber = fdata.getReference("OrderId").child("Phone Number");
        refnumber.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String data = dataSnapshot.getValue(String.class);
                    PhoneNumber.add(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference reflat = fdata.getReference("OrderId").child("Latitude");
        reflat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String data = dataSnapshot.getValue(String.class);
                    Latitude.add(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference reflong = fdata.getReference("OrderId").child("Longitude");
        reflong.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String data = dataSnapshot.getValue(String.class);
                    Longitude.add(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference refadd = fdata.getReference("OrderId").child("Address");
        refadd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String data = dataSnapshot.getValue(String.class);
                    FullAddress.add(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String formatdate = formatter.format(date);
        String substr2 = formatdate.substring(3,5);
        int finaldate = Integer.valueOf(substr2);
        formatdate = formatdate.substring(0,3)+finaldate+formatdate.substring(5);

        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference getdata = fdata.getReference("Users").child(user.getPhoneNumber());
        getdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String data = dataSnapshot.getValue(String.class);
                    System.out.println(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        for(int i=0;i<Date.size();i++){
            if (Date.get(i).equals(formatdate)){

                TodayPickup.add(new Order("","","","","","",""));
            }
        }






        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationView.bringToFront();
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(HomePage.this,drawerLayout,R.string.dummy_content,R.string.dummy_content);
                drawerLayout.addDrawerListener(toggle);
                toggle.syncState();
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.history:
                        Intent intent = new Intent(HomePage.this,OrderHistory.class);
                        startActivity(intent);
                        break;
                    case R.id.adminPanel:
                        Intent intent1 = new Intent(HomePage.this,AdminPanel.class);
                        startActivity(intent1);
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void goToToday(View view) {
        Toast.makeText(this, "Opening Today's pickup..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,TodaysPickup.class));
    }
}