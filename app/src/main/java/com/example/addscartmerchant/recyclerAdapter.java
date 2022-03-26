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

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.type.DateTime;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class recyclerAdapter extends RecyclerView.Adapter<myviewholder> {

    ArrayList<Order> data;
    String change;
    private static final int REQUEST_CALL =1;

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
        holder.address.setText((data.get(position).getAddress()));
        holder.mode.setText((data.get(position).getMode()));
        holder.TrackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+data.get(position).getUserLat()+","+data.get(position).getUserLon()+"&mode=l"));
                intent.setPackage("com.google.android.apps.maps");
//                if (intent.resolveActivity(view.getContext().getPackageManager())!= null){
//                    view.getContext().startActivity(intent);
//                }
                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(), "button Clicked: "+(position+1), Toast.LENGTH_SHORT).show();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
//                    ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
//                }
//                else{
//                    String phoneNo = "tel:"+"8867825522";
//                    Intent intent = new Intent(Intent.ACTION_CALL);
//                    intent.setData(Uri.parse(phoneNo));
//                    view.getContext().startActivity(intent);
//                }
                String phoneNo = "tel:"+data.get(position).getUserPhoneNo();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(phoneNo));
                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(), "Calling..."+data.get(position).getUserPhoneNo(), Toast.LENGTH_SHORT).show();
//                notifyDataSetChanged();

                //have to firebase data delete code here----

                //code for del data form firebase
            }
        });
        holder.reshedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String sDate1=;
//                Date date1= null;
//                try {
//                    date1 = new SimpleDateFormat("dd-MM-yyyy").parse(sDate1);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                Date dt = date1;
//                Date tomorrow = new Date(dt.getTime() + (1000 * 60 * 60 * 24));
//                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//                String strDate= formatter.format(tomorrow);

                DatabaseReference refdate = FirebaseDatabase.getInstance().getReference("OrderId").child("Date").child(data.get(position).getOrderId());
                refdate.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String sDate1= (String) snapshot.getValue();
                        Date date1= null;
                        try {
                            date1 = new SimpleDateFormat("dd-MM-yyyy").parse(sDate1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date dt = date1;
                        Date tomorrow = new Date(dt.getTime() + (1000 * 60 * 60 * 24));
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        String strDate= formatter.format(tomorrow);
                        refdate.setValue(strDate);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//                refdate.addValueEventListener(new ValueEventListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.O)
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        change = (String) snapshot.getValue();
//                        int orderid = Integer.parseInt(data.get(position).getOrderId());
//
////                        DatabaseReference upload = FirebaseDatabase.getInstance().getReference("OrderId").child("Date").child(String.valueOf(orderid));
////                        upload.setValue(strDate);
////                        LocalDateTime today = LocalDateTime.now();     //Today
////                        LocalDateTime tomorrow = today.plusDays(1);     //Plus 1 day
////                        LocalDateTime yesterday = today.minusDays(1);   //Minus 1 day
////                        System.out.println("Today:     "+today);
////                        System.out.println("Tomorrow:  "+tomorrow);
////                        System.out.println("Yesterday: "+yesterday);
////                        DateTime dtOrg = new DateTime(date1);
////                        DateTime dtPlusOne = dtOrg.plusDays(1);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
            }
        });
    }




    @Override
    public int getItemCount() {
        return data.size();
    }


    public void notifyItemRangeChanged() {
    }
}
