package com.example.addscartmerchant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    public void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(data.get(position).getUserName());
        holder.OrderIdTv.setText(data.get(position).getOrderId());
        holder.ItemsView.setText(data.get(position).getUserItems());
        holder.TrackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=22.6554350,88.1164750&mode=l"));
                intent.setPackage("com.google.android.apps.maps");
                if (intent.resolveActivity(view.getContext().getPackageManager())!= null){
                    view.getContext().startActivity(intent);
                }
                Toast.makeText(view.getContext(), "button Clicked: "+(position+1), Toast.LENGTH_SHORT).show();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //deleted form the rec. view
                data.remove(position);
                notifyDataSetChanged();

                //have to firebase data delete code here----

                //code for del data form firebase
            }
        });
    }



    @Override
    public int getItemCount() {
        return data.size();
    }


}
