package com.iit.findyourdog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.iit.findyourdog.config.Config;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button btnBreed;
    private Button btnDog;
    private Button btnSearchBreeds;
    private Switch timerModeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUIComponents();
        initializeListeners();

    }


    private void initializeUIComponents() {
        btnBreed        =   findViewById(R.id.btnBreed);
        btnDog          =   findViewById(R.id.btnDog);
        btnSearchBreeds =   findViewById(R.id.btnSearchBreeds);
        timerModeSwitch =   findViewById(R.id.switchTimerMode);

        timerModeSwitch.setTypeface(ResourcesCompat.getFont(this, R.font.font_2));

    }

    private void initializeListeners() {

        // Identify the Breed
        btnBreed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), IdentifyTheBreedActivity.class);
                startActivity(intent);
            }
        });


        // Identify the Dog
        btnDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), IdentifyTheDogActivity.class);
                startActivity(intent);
            }
        });


        // Search Dog Breed
        btnSearchBreeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SearchDogBreedsActivity.class);
                startActivity(intent);
            }
        });

        timerModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Config.TIMER_GAME_MODE = 1;
                }else{
                    Config.TIMER_GAME_MODE = 0;
                }
            }
        });
    }

}
