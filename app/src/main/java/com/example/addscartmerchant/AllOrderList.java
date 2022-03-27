package com.example.addscartmerchant;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
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

public class AllOrderList extends AppCompatActivity {

    private ArrayList<Order> OrderList;
    private RecyclerView recyclerView;
    recyclerAdapterAllOrder adapter;
    TextView allorders;

    FirebaseDatabase fdata;
    FirebaseAuth fAuth;

    ArrayList<String> ItemType = new ArrayList<String>();
    ArrayList<String> Date = new ArrayList<String>();
    ArrayList<String> PhoneNumber = new ArrayList<String>();
    ArrayList<String> Latitude = new ArrayList<String>();
    ArrayList<String> Longitude = new ArrayList<String>();
    ArrayList<String> mode = new ArrayList<String>();
    ArrayList<String> address = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order_list);
        recyclerView = (RecyclerView) findViewById(R.id.RecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new recyclerAdapterAllOrder(dataQueue());
        recyclerView.setAdapter(adapter);
        allorders = findViewById(R.id.allorders);

    }

    private ArrayList<Order> dataQueue() {
        ArrayList<Order> holder = new ArrayList<>();

        fAuth = FirebaseAuth.getInstance();
        fdata = FirebaseDatabase.getInstance();


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = new Date();
        String formatdate = formatter.format(date);
        String substr2 = formatdate.substring(3,5);
        int finaldate = Integer.valueOf(substr2);
        String finalformatdate = formatdate.substring(0,3)+finaldate+formatdate.substring(5);
        DatabaseReference refnumber = fdata.getReference("OrderId").child("Date");
        refnumber.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String data = dataSnapshot.getValue(String.class);
                    Date.add(data);
                }
                String key = String.valueOf(Date.size());
                allorders.setText(key);
                for(int i=0;i<Date.size();i++){
//                        Phone Number
                        DatabaseReference refnumber = fdata.getReference("OrderId").child("Phone Number");
                        int finalI = i;
                        refnumber.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    String data = dataSnapshot.getValue(String.class);
                                    PhoneNumber.add(data);
                                }

//                                Name
                                DatabaseReference getname = fdata.getReference("Users").child(PhoneNumber.get(finalI)).child("name");
                                getname.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String Name = (String) snapshot.getValue();

//                                        Latitude
                                        DatabaseReference reflat = fdata.getReference("OrderId").child("Latitude");
                                        reflat.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                                    String data = dataSnapshot.getValue(String.class);
                                                    Latitude.add(data);
                                                }

//                                                longitude
                                                DatabaseReference reflong = fdata.getReference("OrderId").child("Longitude");
                                                reflong.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                                            String data = dataSnapshot.getValue(String.class);
                                                            Longitude.add(data);
                                                        }

//                                                        Items
                                                        DatabaseReference refitems = fdata.getReference("OrderId").child("Items");
                                                        refitems.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                                                    String data = dataSnapshot.getValue(String.class);
                                                                    ItemType.add(data);
                                                                }
//                                                              mode of payment
                                                                DatabaseReference refmode = fdata.getReference("OrderId").child("Mode");
                                                                refmode.addValueEventListener(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                                                            String data = dataSnapshot.getValue(String.class);
                                                                            mode.add(data);
                                                                        }
//                                                                      address
                                                                        DatabaseReference refadd = fdata.getReference("OrderId").child("Address");
                                                                        refadd.addValueEventListener(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                                                                    String data = dataSnapshot.getValue(String.class);
                                                                                    address.add(data);
                                                                                }
//                                                                                DatabaseReference putdata = fdata.getReference("TodayPickup").child(PhoneNumber.get(finalI));
//                                                                                putdata.child("latitude").setValue(Latitude.get(finalI));
//                                                                                putdata.child("longitude").setValue(Longitude.get(finalI));
//                                                                                putdata.child("items").setValue(ItemType.get(finalI));
//                                                                                putdata.child("orderid").setValue(String.valueOf(finalI));
//                                                                                putdata.child("number").setValue(PhoneNumber.get(finalI));
//                                                                                putdata.child("mode").setValue(mode.get(finalI));
//                                                                                putdata.child("address").setValue(address.get(finalI));
                                                                                String orderid = (String) String.valueOf(finalI);
                                                                                String items = (String) ItemType.get(finalI);
                                                                                String lat = (String) Latitude.get(finalI);
                                                                                String lon = (String) Longitude.get(finalI);
                                                                                String name = (String) Name;
                                                                                String number = (String)PhoneNumber.get(finalI);
                                                                                String Mode = (String) mode.get(finalI);
                                                                                String Address = (String) address.get(finalI);
                                                                                String date = (String) Date.get(finalI);

                                                                                holder.add(new Order(name,number,"",items,orderid,lat,lon,Address,Mode,date));
                                                                                adapter.notifyDataSetChanged();
                                                                            }


                                                                            @Override
                                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                                            }
                                                                        });
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                                    }
                                                                });


                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return holder;
    }
}



//DatabaseReference getdata = fdata.getReference("FuturePickup");
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
//                    String mode = (String) datasnapshot.child("mode").getValue();
//                    String address = (String) datasnapshot.child("address").getValue();
//
//                    holder.add(new Order(name,number,"",items,orderid,lat,lon,address,mode));
//                }
//                adapter.notifyDataSetChanged();
//
//
//            }