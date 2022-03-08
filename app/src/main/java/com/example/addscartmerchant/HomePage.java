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

public class HomePage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        img = findViewById(R.id.Img);

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