package com.elanco.elancoapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.elanco.elancoapps.FirstButton.MyBroadCastReciver;

public class Spalsh extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spalsh);


           Handler handler=new Handler();
           handler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   Intent intent=new Intent(Spalsh.this,MainActivity.class);
                   startActivity(intent);
                   finish();
               }
           },1000);

       }
    private void SaveMedicine() {

    }
}
