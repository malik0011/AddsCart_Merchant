package com.example.addscartmerchant;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myviewholder extends RecyclerView.ViewHolder {

    TextView name,OrderIdTv,ItemsView,address,mode,pickupdate;
    Button TrackBtn,delete,reshedule, complete;
    public myviewholder(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.UserName);
        OrderIdTv = (TextView) itemView.findViewById(R.id.OrderId);
        ItemsView = (TextView) itemView.findViewById(R.id.Items);
        address = (TextView) itemView.findViewById(R.id.address);
        mode = (TextView) itemView.findViewById(R.id.mode);
        pickupdate = (TextView) itemView.findViewById(R.id.pickupdate);
        TrackBtn = (Button) itemView.findViewById(R.id.trackOrder);
        delete = (Button) itemView.findViewById(R.id.Delete);
        reshedule = (Button) itemView.findViewById(R.id.Reschedule);
        complete = (Button) itemView.findViewById(R.id.complete);

    }
}
