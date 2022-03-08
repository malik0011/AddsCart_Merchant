package com.example.addscartmerchant;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myviewholder extends RecyclerView.ViewHolder {

    TextView name,OrderIdTv,ItemsView;
    Button TrackBtn;
    public myviewholder(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.UserName);
        OrderIdTv = (TextView) itemView.findViewById(R.id.OrderId);
        ItemsView = (TextView) itemView.findViewById(R.id.Items);
       // TrackBtn = (Button) itemView.findViewById(R.id.trackOrder);
    }
}
