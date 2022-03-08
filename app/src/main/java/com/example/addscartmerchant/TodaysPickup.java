package com.example.addscartmerchant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;

public class TodaysPickup extends AppCompatActivity {
    private ArrayList<Order> OrderList;
    private RecyclerView recyclerView;
    recyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_todays_pickup);
         this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

         recyclerView = (RecyclerView) findViewById(R.id.RecycleView);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         adapter = new recyclerAdapter(dataQueue());
         recyclerView.setAdapter(adapter);
    }
    public ArrayList<Order> dataQueue(){

        ArrayList<Order> holder = new ArrayList<>();

        Order obj1 = new Order("Ayan Malik","8334980173","12.23","Iron,Paper,Metals,E-Waste,Others","0001");
        holder.add(obj1);

        Order obj2 = new Order("Akshay","8334980173","12.23","Iron,Paper,Metals,E-Waste,Others","0002");
        holder.add(obj2);

        Order obj3 = new Order("Mayank","8334980173","12.23","Iron,Paper,Metals,E-Waste,Others","0003");
        holder.add(obj3);

        Order obj4 = new Order("Spandan","8334980173","12.23","Iron,Paper,Metals,E-Waste,Others","0004");
        holder.add(obj4);

        Order obj5 = new Order("Shubham","8334980173","12.23","Iron,Paper,Metals,E-Waste,Others","0005");
        holder.add(obj5);

        Order obj6 = new Order("Sudeep","8334980173","12.23","Iron,Paper,Metals,E-Waste,Others","0006");
        holder.add(obj6);

        Order obj7 = new Order("Sutej","8334980173","12.23","Iron,Paper,Metals,E-Waste,Others","0007");
        holder.add(obj7);

        Order obj8 = new Order("Abdul","8334980173","12.23","Iron,Paper,Metals,E-Waste,Others","0008");
        holder.add(obj8);

        return holder;
    }
}