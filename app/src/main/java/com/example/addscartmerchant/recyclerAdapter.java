package com.example.addscartmerchant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<myviewholder> {

    ArrayList<Order> data;

    public recyclerAdapter(ArrayList<Order> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.order_list,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.name.setText(data.get(position).getUserName());
        holder.OrderIdTv.setText(data.get(position).getOrderId());
        holder.ItemsView.setText(data.get(position).getUserItems());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
