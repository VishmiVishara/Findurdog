package com.iit.findyourdog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

//Landing Screen
public class LandingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);

        initializeView();
    }

    private void initializeView(){
        int secondsDelayed = 3;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity( new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}
