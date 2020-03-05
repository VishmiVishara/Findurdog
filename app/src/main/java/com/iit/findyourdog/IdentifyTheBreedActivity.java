package com.iit.findyourdog;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.iit.findyourdog.util.DogBreeds;
import com.iit.findyourdog.util.ImageUtils;

public class IdentifyTheBreedActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnSubmit;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_breed);

        imageView = findViewById(R.id.imageDog);

        String breedName = DogBreeds.getInstance().getRandomBreed();
        final String imageName = DogBreeds.getInstance().getRandomImage(breedName);
        imageView.setImageDrawable(ImageUtils.getDrawable(this, imageName));
        createDropDownList();

        btnSubmit = findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String breedName = DogBreeds.getInstance().getRandomBreed();
                String imageName = DogBreeds.getInstance().getRandomImage(breedName);
                imageView.setImageDrawable(ImageUtils.getDrawable(IdentifyTheBreedActivity.this, imageName));
                createDropDownList();
            }
        });

    }

    protected void createDropDownList() {
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, DogBreeds.getInstance().dogBreedsList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();
        String text = spinner.getSelectedItem().toString();
        System.out.println(text);
    }

}


