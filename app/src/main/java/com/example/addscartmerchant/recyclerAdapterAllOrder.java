//package com.example.addscartmerchant;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//
//public class recyclerAdapter extends RecyclerView.Adapter<myviewholder> {
//
//    ArrayList<Order> data;
//    String id;
//    String number;
//    FirebaseDatabase fdata;
//    FirebaseAuth fAuth;
//
//    public recyclerAdapter(ArrayList<Order> data) {
//        this.data = data;
//    }
//
//    @NonNull
//    @Override
//    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.order_list,parent,false);
//        return new myviewholder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position) {
//        holder.name.setText(data.get(position).getUserName());
//        holder.OrderIdTv.setText(data.get(position).getOrderId());
//        id = (data.get(position).getOrderId().toString());
//        number = data.get(position).getUserPhoneNo();
//        holder.ItemsView.setText(data.get(position).getUserItems());
//        holder.TrackBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+data.get(position).getUserLat()+","+data.get(position).getUserLon()+"&mode=l"));
//                intent.setPackage("com.google.android.apps.maps");
////                if (intent.resolveActivity(view.getContext().getPackageManager())!= null){
////                    view.getContext().startActivity(intent);
////                }
//                view.getContext().startActivity(intent);
//                Toast.makeText(view.getContext(), "button Clicked: "+(position+1), Toast.LENGTH_SHORT).show();
//            }
//        });
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fAuth = FirebaseAuth.getInstance();
//                fdata = FirebaseDatabase.getInstance();
//                DatabaseReference getcomplete = fdata.getReference("OrderId").child("IsComplete").child(id);
//                getcomplete.setValue("True");
//                DatabaseReference phonenumber = fdata.getReference("FuturePickup").child(number);
//                phonenumber.setValue(null);
//                //deleted form the rec. view
//                data.remove(position);
//                notifyDataSetChanged();
//
//                //have to firebase data delete code here----
//
//                //code for del data form firebase
//            }
//        });
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//
//    public void notifyItemRangeChanged() {
//    }
//}
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

public class recyclerAdapterAllOrder extends RecyclerView.Adapter<myviewholderAllOrder> {

    ArrayList<Order> data;
    private static final int REQUEST_CALL =1;

    public recyclerAdapterAllOrder(ArrayList<Order> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myviewholderAllOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.all_order_list,parent,false);
        return new myviewholderAllOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholderAllOrder holder, int position) {
        holder.name.setText(data.get(position).getUserName());
        holder.OrderIdTv.setText(data.get(position).getOrderId());
        holder.ItemsView.setText(data.get(position).getUserItems());
        holder.address.setText((data.get(position).getAddress()));
        holder.mode.setText((data.get(position).getMode()));
    }
    @Override
    public int getItemCount() {
        return data.size();
    }


    public void notifyItemRangeChanged() {
    }
}
