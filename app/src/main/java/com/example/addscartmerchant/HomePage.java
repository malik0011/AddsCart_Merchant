package com.example.addscartmerchant;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.JarOutputStream;

public class HomePage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton img;
    FirebaseDatabase fdata;
    FirebaseAuth fAuth;
    Button Today,Future;

    ArrayList<String> ItemType = new ArrayList<String>();
    ArrayList<String> Date = new ArrayList<String>();
    ArrayList<String> PhoneNumber = new ArrayList<String>();
    ArrayList<String> Latitude = new ArrayList<String>();
    ArrayList<String> Longitude = new ArrayList<String>();
    ArrayList<String> mode = new ArrayList<String>();
    ArrayList<String> address = new ArrayList<String>();

    int d1,d2,m1,m2,y1,y2;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        img = findViewById(R.id.Img);
        Today = findViewById(R.id.button);
        Future = findViewById(R.id.button2);

        fAuth = FirebaseAuth.getInstance();
        fdata = FirebaseDatabase.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String formatdate = formatter.format(date);
        d1=Integer.parseInt(formatdate.substring(0,2));
        m1=Integer.parseInt(formatdate.substring(3,5));
        y1=Integer.parseInt(formatdate.substring(6));


        DatabaseReference deltodaypickup = fdata.getReference("TodayPickup");
        deltodaypickup.setValue(null);
        DatabaseReference delfuturepickup = fdata.getReference("FuturePickup");
        delfuturepickup.setValue(null);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Today.setEnabled(true);
                Future.setEnabled(true);
            }
        }, 5000);




//        Date
        DatabaseReference refnumber = fdata.getReference("OrderId").child("Date");
        refnumber.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String data = dataSnapshot.getValue(String.class);
                    Date.add(data);
                }
                for(int i=0;i<Date.size();i++){
                    d2=Integer.parseInt(Date.get(i).substring(0,2));
                    m2=Integer.parseInt(Date.get(i).substring(3,5));
                    y2=Integer.parseInt(Date.get(i).substring(6));
                    if(Date.get(i).equals(formatdate)){
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

                                        DatabaseReference putname = fdata.getReference("TodayPickup").child(PhoneNumber.get(finalI));
                                        putname.child("name").setValue((String)snapshot.getValue());

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
                                                                                DatabaseReference putdata = fdata.getReference("TodayPickup").child(PhoneNumber.get(finalI));
                                                                                putdata.child("latitude").setValue(Latitude.get(finalI));
                                                                                putdata.child("longitude").setValue(Longitude.get(finalI));
                                                                                putdata.child("items").setValue(ItemType.get(finalI));
                                                                                putdata.child("orderid").setValue(String.valueOf(finalI));
                                                                                putdata.child("number").setValue(PhoneNumber.get(finalI));
                                                                                putdata.child("mode").setValue(mode.get(finalI));
                                                                                putdata.child("address").setValue(address.get(finalI));

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
                    else{
                        if(y2==y1){
                            if(m2==m1){
                                if(d2>d1){
//                                    this is definite condition
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

                                                    DatabaseReference putname = fdata.getReference("FuturePickup").child(PhoneNumber.get(finalI));
                                                    putname.child("name").setValue((String)snapshot.getValue());

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


                                                                            DatabaseReference refmode = fdata.getReference("OrderId").child("Mode");
                                                                            refmode.addValueEventListener(new ValueEventListener() {
                                                                                @Override
                                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                                                                        String data = dataSnapshot.getValue(String.class);
                                                                                        mode.add(data);
                                                                                    }

                                                                                    DatabaseReference refadd = fdata.getReference("OrderId").child("Address");
                                                                                    refadd.addValueEventListener(new ValueEventListener() {
                                                                                        @Override
                                                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                                                                                String data = dataSnapshot.getValue(String.class);
                                                                                                address.add(data);
                                                                                            }
                                                                                            DatabaseReference putdata = fdata.getReference("FuturePickup").child(PhoneNumber.get(finalI));
                                                                                            putdata.child("latitude").setValue(Latitude.get(finalI));
                                                                                            putdata.child("longitude").setValue(Longitude.get(finalI));
                                                                                            putdata.child("items").setValue(ItemType.get(finalI));
                                                                                            putdata.child("orderid").setValue(String.valueOf(finalI));
                                                                                            putdata.child("number").setValue(PhoneNumber.get(finalI));
                                                                                            putdata.child("mode").setValue(mode.get(finalI));
                                                                                            putdata.child("address").setValue(address.get(finalI));

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
                            else if(m2>m1){
//                                this is definite condition
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

                                                DatabaseReference putname = fdata.getReference("FuturePickup").child(PhoneNumber.get(finalI));
                                                putname.child("name").setValue((String)snapshot.getValue());

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

                                                                        DatabaseReference refmode = fdata.getReference("OrderId").child("Mode");
                                                                        refmode.addValueEventListener(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                                                                    String data = dataSnapshot.getValue(String.class);
                                                                                    mode.add(data);
                                                                                }

                                                                                DatabaseReference refadd = fdata.getReference("OrderId").child("Address");
                                                                                refadd.addValueEventListener(new ValueEventListener() {
                                                                                    @Override
                                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                                                                            String data = dataSnapshot.getValue(String.class);
                                                                                            address.add(data);
                                                                                        }
                                                                                        DatabaseReference putdata = fdata.getReference("FuturePickup").child(PhoneNumber.get(finalI));
                                                                                        putdata.child("latitude").setValue(Latitude.get(finalI));
                                                                                        putdata.child("longitude").setValue(Longitude.get(finalI));
                                                                                        putdata.child("items").setValue(ItemType.get(finalI));
                                                                                        putdata.child("orderid").setValue(String.valueOf(finalI));
                                                                                        putdata.child("number").setValue(PhoneNumber.get(finalI));
                                                                                        putdata.child("mode").setValue(mode.get(finalI));
                                                                                        putdata.child("address").setValue(address.get(finalI));

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
                        else if(y2>y1){
//                            this is the definite condition
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

                                            DatabaseReference putname = fdata.getReference("FuturePickup").child(PhoneNumber.get(finalI));
                                            putname.child("name").setValue((String)snapshot.getValue());

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

                                                                    DatabaseReference refmode = fdata.getReference("OrderId").child("Mode");
                                                                    refmode.addValueEventListener(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                                                                String data = dataSnapshot.getValue(String.class);
                                                                                mode.add(data);
                                                                            }

                                                                            DatabaseReference refadd = fdata.getReference("OrderId").child("Address");
                                                                            refadd.addValueEventListener(new ValueEventListener() {
                                                                                @Override
                                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                                                                        String data = dataSnapshot.getValue(String.class);
                                                                                        address.add(data);
                                                                                    }
                                                                                    DatabaseReference putdata = fdata.getReference("FuturePickup").child(PhoneNumber.get(finalI));
                                                                                    putdata.child("latitude").setValue(Latitude.get(finalI));
                                                                                    putdata.child("longitude").setValue(Longitude.get(finalI));
                                                                                    putdata.child("items").setValue(ItemType.get(finalI));
                                                                                    putdata.child("orderid").setValue(String.valueOf(finalI));
                                                                                    putdata.child("number").setValue(PhoneNumber.get(finalI));
                                                                                    putdata.child("mode").setValue(mode.get(finalI));
                                                                                    putdata.child("address").setValue(address.get(finalI));

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

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationView.bringToFront();
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(HomePage.this,drawerLayout,R.string.dummy_content,R.string.dummy_content);
                drawerLayout.addDrawerListener(toggle);
                toggle.syncState();
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.history:
                        Intent intent = new Intent(HomePage.this,OrderHistory.class);
                        startActivity(intent);
                        break;
                    case R.id.adminPanel:
                        Intent intent1 = new Intent(HomePage.this,AdminPanel.class);
                        startActivity(intent1);
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void goToToday(View view) {
        Toast.makeText(this, "Opening Today's pickup..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,TodaysPickup.class));
    }
    public void goToFuture(View view) {
        Toast.makeText(this, "Opening Future's pickup..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,FuturesPickup.class));
    }

    public void AlOrder(View view) {
        startActivity(new Intent(this,AllOrderList.class));
    }
}