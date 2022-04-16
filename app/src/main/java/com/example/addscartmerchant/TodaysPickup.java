//package com.example.addscartmerchant;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.view.WindowManager;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//public class TodaysPickup extends AppCompatActivity {
//    private ArrayList<Order> OrderList;
//    private RecyclerView recyclerView;
//    recyclerAdapter adapter;
//
//    FirebaseDatabase fdata;
//    FirebaseAuth fAuth;
//
//    ArrayList<String> ItemType = new ArrayList<String>();
//    ArrayList<String> FullAddress = new ArrayList<String>();
//    ArrayList<String> Date = new ArrayList<String>();
//    ArrayList<String> PhoneNumber = new ArrayList<String>();
//    ArrayList<String> Latitude = new ArrayList<String>();
//    ArrayList<String> Longitude = new ArrayList<String>();
////    Order obj = new Order();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_todays_pickup);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        recyclerView = (RecyclerView) findViewById(R.id.RecycleView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new recyclerAdapter(dataQueue());
//        recyclerView.setAdapter(adapter);
//    }
//    public ArrayList<Order> dataQueue(){
//
//        ArrayList<Order> holder = new ArrayList<>();
//
//        fAuth = FirebaseAuth.getInstance();
//        fdata = FirebaseDatabase.getInstance();
//
//
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//        java.util.Date date = new Date();
//        String formatdate = formatter.format(date);
//        String substr2 = formatdate.substring(3,5);
//        int finaldate = Integer.valueOf(substr2);
//        String finalformatdate = formatdate.substring(0,3)+finaldate+formatdate.substring(5);
//
//        DatabaseReference getdata = fdata.getReference("TodayPickup");
//        getdata.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot datasnapshot: snapshot.getChildren()) {
//                    String name = (String) datasnapshot.child("name").getValue();
//                    String items = (String) datasnapshot.child("items").getValue();
//                    String lat = (String) datasnapshot.child("latitude").getValue();
//                    String lon = (String) datasnapshot.child("longitude").getValue();
//                    String orderid = (String) datasnapshot.child("orderid").getValue();
//                    String number = (String) datasnapshot.child("number").getValue();
//
//                    holder.add(new Order(name,number,"",items,orderid,lat,lon));
//                }
//                adapter.notifyDataSetChanged();
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return holder;
//    }
//}
package com.example.addscartmerchant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TodaysPickup extends AppCompatActivity {
    private ArrayList<Order> OrderList;
    private RecyclerView recyclerView;
    recyclerAdapter adapter;

    FirebaseDatabase fdata;
    FirebaseAuth fAuth;
    TextView totalorders;

    ArrayList<String> ItemType = new ArrayList<String>();
    ArrayList<String> FullAddress = new ArrayList<String>();
    ArrayList<String> Date = new ArrayList<String>();
    ArrayList<String> PhoneNumber = new ArrayList<String>();
    ArrayList<String> Latitude = new ArrayList<String>();
    ArrayList<String> Longitude = new ArrayList<String>();
//    Order obj = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_pickup);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView = (RecyclerView) findViewById(R.id.RecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new recyclerAdapter(dataQueue());
        recyclerView.setAdapter(adapter);
        totalorders = findViewById(R.id.alltodayorders);
    }
    public ArrayList<Order> dataQueue(){

        ArrayList<Order> holder = new ArrayList<>();

        fAuth = FirebaseAuth.getInstance();
        fdata = FirebaseDatabase.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = new Date();
        String formatdate = formatter.format(date);
        String substr2 = formatdate.substring(3,5);
        int finaldate = Integer.valueOf(substr2);
        String finalformatdate = formatdate.substring(0,3)+finaldate+formatdate.substring(5);

        DatabaseReference getdata = fdata.getReference("TodayPickup");
        getdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datasnapshot: snapshot.getChildren()) {
                    String name = (String) datasnapshot.child("name").getValue();
                    String items = (String) datasnapshot.child("items").getValue();
                    String lat = (String) datasnapshot.child("latitude").getValue();
                    String lon = (String) datasnapshot.child("longitude").getValue();
                    String orderid = (String) datasnapshot.child("orderid").getValue();
                    String number = (String) datasnapshot.child("number").getValue();
                    String mode = (String) datasnapshot.child("mode").getValue();
                    String address = (String) datasnapshot.child("address").getValue();
                    String date = (String) datasnapshot.child("date").getValue();

                    holder.add(new Order(name,number,"",items,orderid,lat,lon,address,mode,date));
                }
                adapter.notifyDataSetChanged();
                if(holder.isEmpty())
                    totalorders.setText("0");
                else
                    totalorders.setText(String.valueOf(holder.size()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return holder;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("TodayPickup");
        ref.setValue(null);
    }
}