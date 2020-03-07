package com.iit.findyourdog;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.iit.findyourdog.alerts.SuccessfulAlert;
import com.iit.findyourdog.alerts.WarningAlert;
import com.iit.findyourdog.util.AppUtils;
import com.iit.findyourdog.util.DogBreeds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdentifyTheDogActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageOne;
    private ImageView imageTwo;
    private ImageView imageThree;
    private Button btnSubmit;
    private TextView txtBreedName;
    private ConstraintLayout imageOneBorder;
    private ConstraintLayout imageTwoBorder;
    private ConstraintLayout imageThreeBorder;

    private List<String> randomGeneratedBreedList = new ArrayList<>();
    private List<ImageView> imageViewList = new ArrayList<>();
    private List<ConstraintLayout> imageBorderedList = new ArrayList<>();
    private int randomPickedHeadingIndex = 0;
    private boolean btnSubmitState = false;
    private int selectedImageIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_dog);

        initializeTheUIComponents();
    }

    private void initializeTheUIComponents() {
        imageOne = findViewById(R.id.imageViewOne);
        imageTwo = findViewById(R.id.imageViewTwo);
        imageThree = findViewById(R.id.imageViewThree);
        btnSubmit = findViewById(R.id.btnSubmitDog);
        txtBreedName = findViewById(R.id.txtBreedName);
        imageOneBorder = findViewById(R.id.conImageOne);
        imageTwoBorder = findViewById(R.id.conImageTwo);
        imageThreeBorder = findViewById(R.id.conImageThree);


        //clear image view and random images list
        imageViewList.clear();
        randomGeneratedBreedList.clear();

        imageViewList.add(imageOne);
        imageViewList.add(imageTwo);
        imageViewList.add(imageThree);

        imageBorderedList.add(imageOneBorder);
        imageBorderedList.add(imageTwoBorder);
        imageBorderedList.add(imageThreeBorder);


        btnSubmit.setOnClickListener(this);
        setImagesToView();
        setHeading();

    }

    private void initializeListeners() {
        if (!btnSubmitState) {
            //validate user is select a image or not
            if (selectedImageIndex == -1) {
                Toast.makeText(getApplicationContext(),
                        "Please Select an Image!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (selectedImageIndex == randomPickedHeadingIndex) {
                SuccessfulAlert identifyBreedCorrectMessage = new SuccessfulAlert(IdentifyTheDogActivity.this);
                identifyBreedCorrectMessage.show();

                imageBorderedList.get(selectedImageIndex).setBackgroundColor(Color.GREEN);
            } else {
                WarningAlert identifyBreedWrongMessage = new WarningAlert(IdentifyTheDogActivity.this);
                identifyBreedWrongMessage.show();

                imageBorderedList.get(selectedImageIndex).setBackgroundColor(Color.RED);
                imageBorderedList.get(randomPickedHeadingIndex).setBackgroundColor(Color.GREEN);
            }
            btnSubmit.setText("Next");
            btnSubmitState = true;
        } else if (btnSubmitState) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            btnSubmitState = false;
        }
    }

    private void setImageToView(ImageView imageView, int position) {
        String randomBreedName = DogBreeds.getInstance().getRandomBreed();
        if (!randomGeneratedBreedList.contains(randomBreedName)) {
            randomGeneratedBreedList.add(randomBreedName);
        }
        String imageName = DogBreeds.getInstance().getRandomImage(randomBreedName);
        imageView.setImageDrawable(AppUtils.getDrawable(this, imageName));
        imageView.setTag(position);
    }

    private void setImagesToView() {
        for (int index = 0; index < 3; index++) {
            imageViewList.get(index).setOnClickListener(IdentifyTheDogActivity.this);
            setImageToView(imageViewList.get(index), index);
        }
    }

    private void setHeading() {
        Random random = new Random();
        randomPickedHeadingIndex = random.nextInt(3);
        String breedName = randomGeneratedBreedList.get(randomPickedHeadingIndex);
        txtBreedName.setText(DogBreeds.getInstance().getDogBreedMap().get(breedName));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewOne:

                imageOneBorder = findViewById(R.id.conImageOne);
                imageTwoBorder = findViewById(R.id.conImageTwo);
                imageThreeBorder = findViewById(R.id.conImageThree);

                imageThreeBorder.setBackgroundColor(Color.TRANSPARENT);
                imageTwoBorder.setBackgroundColor(Color.TRANSPARENT);
                imageOneBorder.setBackgroundColor(Color.YELLOW);
                selectedImageIndex = (int) imageOne.getTag();

                break;
            case R.id.imageViewTwo:

                imageOneBorder.setBackgroundColor(Color.TRANSPARENT);
                imageThreeBorder.setBackgroundColor(Color.TRANSPARENT);
                imageTwoBorder.setBackgroundColor(Color.YELLOW);
                selectedImageIndex = (int) imageTwo.getTag();

                break;
            case R.id.imageViewThree:

                imageOneBorder.setBackgroundColor(Color.TRANSPARENT);
                imageTwoBorder.setBackgroundColor(Color.TRANSPARENT);
                imageThreeBorder.setBackgroundColor(Color.YELLOW);
                selectedImageIndex = (int) imageThree.getTag();

                break;

            case R.id.btnSubmitDog:

                initializeListeners();

                break;


        }
    }
}