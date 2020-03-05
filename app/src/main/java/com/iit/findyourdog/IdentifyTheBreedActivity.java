package com.iit.findyourdog;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iit.findyourdog.alerts.WarningAlert;
import com.iit.findyourdog.alerts.successfulAlert;
import com.iit.findyourdog.util.AppUtils;
import com.iit.findyourdog.util.CustomAdapter;
import com.iit.findyourdog.util.DogBreeds;

public class IdentifyTheBreedActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnSubmit;
    private Spinner spinner;
    private TextView labelStatus;
    private TextView labelAnswer;

    private CustomAdapter dataAdapter = null;
    private boolean btnSubmitState = false;
    private String randomBreedName = null;
    private String imageName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_breed);

        // bind UI components
        createUIComponents();

    }

    private void createUIComponents() {
        imageView = findViewById(R.id.imageDog);
        btnSubmit = findViewById(R.id.buttonSubmit);
        spinner = findViewById(R.id.spinner);
        labelStatus = findViewById(R.id.labelStatus);
        labelAnswer = findViewById(R.id.labelAnswer);

        // initializing Listeners
        initializeListeners();

        setImageToView();
        createDropDownList();
    }

    private void initializeListeners() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btnSubmitState) {
                    if (spinner.getSelectedItem().equals("SELECT A BREED..")) {
                        Toast.makeText(getApplicationContext(),
                                "Please select a breed", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String selectedItem = (String) spinner.getSelectedItem();
                    System.out.println("Random Generated Breed: " + randomBreedName);
                    System.out.println("User selected Option: " + selectedItem);

                    selectedItem = selectedItem.replaceAll("\\s+","");
                    System.out.println(selectedItem);
                    System.out.println(selectedItem.toLowerCase().equals(randomBreedName));


                    if (selectedItem.toLowerCase().equals(randomBreedName)) {
                        System.out.println("Correct!!");
                        successfulAlert successfulAlert =
                                new successfulAlert(IdentifyTheBreedActivity.this);
                        successfulAlert.show();
                        labelStatus.setText("Tour Answer \" " + selectedItem + " \" is CORRECT!!" );
                        labelStatus.setTextColor(Color.GREEN);

                        labelAnswer.setText("CORRECT BREED: "
                                + DogBreeds.getInstance().getDogBreedMap().get(randomBreedName));
                        labelAnswer.setTextColor(Color.BLUE);

                    } else {
                        System.out.println("Wrong!!");
                        WarningAlert warningAlert =
                                new WarningAlert(IdentifyTheBreedActivity.this);
                        warningAlert.show();
                        labelStatus.setText("Your Answer \"" + selectedItem + " \" is WRONG!");
                        labelStatus.setTextColor(Color.RED);

                        labelAnswer.setText("CORRECT BREED: "
                                + DogBreeds.getInstance().getDogBreedMap().get(randomBreedName));
                        labelAnswer.setTextColor(Color.BLUE);
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
        dataAdapter = new CustomAdapter(this,
                android.R.layout.simple_spinner_item, DogBreeds.getInstance().getShowBreeds());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(dataAdapter.getCount());

    }

    private void setImageToView() {
        randomBreedName = DogBreeds.getInstance().getRandomBreed();
        imageName = DogBreeds.getInstance().getRandomImage(randomBreedName);
        imageView.setImageDrawable(AppUtils.getDrawable(this, imageName));
    }
}


