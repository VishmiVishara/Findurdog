package com.iit.findyourdog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iit.findyourdog.alerts.WarningAlert;
import com.iit.findyourdog.alerts.successfulAlert;
import com.iit.findyourdog.util.AppUtils;
import com.iit.findyourdog.util.CustomizedOnItemSelectedListener;
import com.iit.findyourdog.util.DogBreeds;

public class IdentifyTheBreedActivity extends AppCompatActivity {

    ArrayAdapter<String> dataAdapter;
    boolean btnSubmitState = false;
    private ImageView imageView;
    private Button btnSubmit;
    private Spinner spinner;
    private String randomBreedName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_breed);

        imageView = findViewById(R.id.imageDog);
        btnSubmit = findViewById(R.id.buttonSubmit);
        spinner = findViewById(R.id.spinner);

        setImageToView();
        createDropDownList();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btnSubmitState) {
                    addListenerOnSpinnerItemSelection();
                    if (spinner.getSelectedItem() == null) {
                        Toast.makeText(getApplicationContext(),
                                "Please select a breed", Toast.LENGTH_SHORT).show();
                    }

                    String selectedItem = (String) spinner.getSelectedItem();
                    System.out.println("User selected Option" + selectedItem);
                    System.out.println("############################" + randomBreedName);
                    if (selectedItem.toLowerCase().equals(randomBreedName)) {
                        successfulAlert successfulAlert =
                                new successfulAlert(IdentifyTheBreedActivity.this);
                        successfulAlert.show();
                    }
                    else{
                        WarningAlert warningAlert =
                                new WarningAlert(IdentifyTheBreedActivity.this);
                        warningAlert.show();
                    }

                    spinner.setEnabled(false);
                    spinner.setClickable(false);
                    btnSubmit.setText("Next");
                    btnSubmitState = true;

                } else if (btnSubmitState) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    btnSubmitState = false;
                }

            }

        });

    }

    protected void createDropDownList() {
        dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, DogBreeds.getInstance().getDogBreedsList());
        spinner.setSelection(-1);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void setImageToView() {
        randomBreedName = DogBreeds.getInstance().getRandomBreed();
        final String imageName = DogBreeds.getInstance().getRandomImage(randomBreedName);
        imageView.setImageDrawable(AppUtils.getDrawable(this, imageName));
    }


    public void addListenerOnSpinnerItemSelection() {
        spinner.setOnItemSelectedListener(new CustomizedOnItemSelectedListener());

    }
}


