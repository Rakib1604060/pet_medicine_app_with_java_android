package com.elanco.elancoapps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.elanco.elancoapps.FifthButton.Fragment_fifth;
import com.elanco.elancoapps.FourthButton.Fragment_medicinelist;
import com.elanco.elancoapps.SecondButton.Fragment_secondbutton;
import com.elanco.elancoapps.ThirdButton.Fragment_thirdbutton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.theartofdev.edmodo.cropper.CropImage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

 Uri uri;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.Fragment_contatiner,new Fragment_Home())
                            .commit();
                    return true;
                case R.id.navigation_option2:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.Fragment_contatiner,new Fragment_secondbutton())
                            .commit();

                    return true;
                case R.id.navigation_option3:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.Fragment_contatiner,new Fragment_thirdbutton())
                            .addToBackStack("sample")
                            .commit();

                    return true;
                case R.id.navigation_option4:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.Fragment_contatiner,new Fragment_medicinelist())
                            .addToBackStack("sample")
                            .commit();

                    return  true;
                case  R.id.navigation_option5:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.Fragment_contatiner,new Fragment_fifth())
                            .addToBackStack("sample")
                            .commit();
                    return  true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Fragment_contatiner,new Fragment_Home())
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

    }
}
