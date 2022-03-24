package com.example.addscartmerchant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdminPanel extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseDatabase fdata;
    EditText paper, plastic, metal, others, ewaste, iron;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        paper = findViewById(R.id.paper);
        plastic = findViewById(R.id.plastic);
        metal = findViewById(R.id.metals);
        others = findViewById(R.id.other);
        ewaste = findViewById(R.id.e_waste);
        iron= findViewById(R.id.iron);

        fdata = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();

        DatabaseReference getpaper = fdata.getReference("UpdatedPrice").child("Paper");
        getpaper.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                paper.setText((CharSequence) snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference getplastic = fdata.getReference("UpdatedPrice").child("Plastic");
        getplastic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                plastic.setText((CharSequence) snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference getmetal = fdata.getReference("UpdatedPrice").child("Metal");
        getmetal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                metal.setText((CharSequence) snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference getothers = fdata.getReference("UpdatedPrice").child("Others");
        getothers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                others.setText((CharSequence) snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference getewaste = fdata.getReference("UpdatedPrice").child("E-Waste");
        getewaste.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ewaste.setText((CharSequence) snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference getiron = fdata.getReference("UpdatedPrice").child("Iron");
        getiron.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                iron.setText((CharSequence) snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void Update(View view) {
        Toast.makeText(this, "The values have been updated..", Toast.LENGTH_SHORT).show();

        fAuth = FirebaseAuth.getInstance();
        fdata = FirebaseDatabase.getInstance();

        String getpaper = paper.getText().toString().trim();
        String getplastic = plastic.getText().toString().trim();
        String getmetal = metal.getText().toString().trim();
        String getothers = others.getText().toString().trim();
        String getewaste = ewaste.getText().toString().trim();
        String getiron = iron.getText().toString().trim();

        DatabaseReference updateprice = fdata.getReference("UpdatedPrice");
        HashMap<String,String> setprice = new HashMap<String,String>();
        setprice.put("Paper", getpaper);
        setprice.put("Plastic", getplastic);
        setprice.put("Metal", getmetal);
        setprice.put("Others", getothers);
        setprice.put("E-Waste", getewaste);
        setprice.put("Iron", getiron);
        updateprice.setValue(setprice);

    }
}