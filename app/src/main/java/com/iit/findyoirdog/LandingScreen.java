package com.iit.findyoirdog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.iit.findyourdog.MainActivity;
import com.iit.findyourdog.R;

public class LandingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);


        setupview();

    }

    private void setupview(){
        int secondsDelayed = 5;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity( new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}
