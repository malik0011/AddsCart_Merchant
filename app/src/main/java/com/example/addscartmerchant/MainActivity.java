package com.example.addscartmerchant;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.window.SplashScreen;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    TextView logoname;
    GifImageView gifSplash;
    Animation upAnim, downAnim, fadeoutAnim;

    SharedPreferences onboardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES);
        }
        //hooks
        gifSplash = findViewById(R.id.gifSplash);
        logoname = findViewById(R.id.logo_name);
        upAnim = AnimationUtils.loadAnimation(this, R.anim.up_animation);
        downAnim = AnimationUtils.loadAnimation(this, R.anim.down_animation);
        fadeoutAnim = AnimationUtils.loadAnimation(this,R.anim.fade_out_anim);
        gifSplash.setAnimation(downAnim);
        logoname.setAnimation(upAnim);



        Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.CALL_PHONE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                logoname.clearAnimation();
                                gifSplash.clearAnimation();
                                logoname.setAnimation(fadeoutAnim);
                                gifSplash.setAnimation(fadeoutAnim);
                                logoname.setVisibility(View.INVISIBLE);
                                startActivity(new Intent(getApplicationContext(),HomePage.class));
                                finish();
                            }
                        },3000);



//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                onboardingScreen = getSharedPreferences("OnBoardingScreen",MODE_PRIVATE);
//                                boolean isfirstTime = onboardingScreen.getBoolean("FirstTime",true);
//                                if (isfirstTime){
//                                    SharedPreferences.Editor editor = onboardingScreen.edit();
//                                    editor.putBoolean("FirstTime",false);
//                                    editor.commit();
//                                    startActivity(new Intent(MainActivity.this, HomePage.class));
//                                    finish();
//                                }
//                                else{
//                                    startActivity(new Intent(MainActivity.this, HomePage.class));
//                                    finish();
//                                }
//
//                            }
//                        }, 3000);
                    }
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(MainActivity.this, "Permission DenidedQ!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();


    }

//    public void goToToday(View view) {
//        startActivity(new Intent(this,TodaysPickup.class));
//    }
}

//package com.example.addscartmerchant;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.view.WindowManager;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.TextView;
//
//import pl.droidsonroids.gif.GifImageView;
//
//public class MainActivity extends AppCompatActivity {
//
//    TextView logoname;
//    GifImageView gifSplash;
//    Animation upAnim, downAnim, fadeoutAnim;
//
//    SharedPreferences onboardingScreen;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES);
//        }
//        //hooks
//        gifSplash = findViewById(R.id.gifSplash);
//        logoname = findViewById(R.id.logo_name);
//        upAnim = AnimationUtils.loadAnimation(this, R.anim.up_animation);
//        downAnim = AnimationUtils.loadAnimation(this, R.anim.down_animation);
//        fadeoutAnim = AnimationUtils.loadAnimation(this,R.anim.fade_out_anim);
//        gifSplash.setAnimation(downAnim);
//        logoname.setAnimation(upAnim);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                logoname.clearAnimation();
//                gifSplash.clearAnimation();
//                logoname.setAnimation(fadeoutAnim);
//                gifSplash.setAnimation(fadeoutAnim);
//                logoname.setVisibility(View.INVISIBLE);
//
//            }
//        },3000);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                onboardingScreen = getSharedPreferences("OnBoardingScreen",MODE_PRIVATE);
//                boolean isfirstTime = onboardingScreen.getBoolean("FirstTime",true);
//                if (isfirstTime){
//                    SharedPreferences.Editor editor = onboardingScreen.edit();
//                    editor.putBoolean("FirstTime",false);
//                    editor.commit();
//                    startActivity(new Intent(MainActivity.this, HomePage.class));
//                    finish();
//                }
//                else{
//                    startActivity(new Intent(MainActivity.this, HomePage.class));
//                    finish();
//                }
//
//            }
//        }, 3000);
//    }
//
//}