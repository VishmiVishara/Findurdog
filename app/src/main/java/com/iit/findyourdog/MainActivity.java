package com.iit.findyourdog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnBreed;
    private Button btnDog;
    private Button btnSearchBreeds;

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
    }

}
