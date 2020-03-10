package com.iit.findyourdog.util;

import com.iit.findyourdog.config.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Dog Breeds Class - Singleton
 */
public class DogBreeds {
    // creating singleton object
    private static DogBreeds dogBreeds = new DogBreeds();
    private List<String> dogBreedsList = new ArrayList<String>() {{
        add("pomeranian");
        add("bullmastiff");
        add("pug");
        add("boxer");
        add("germanshepherd");
        add("rottweiler");
        add("cockerspaniel");
        add("labradorretriever");
        add("goldenretriever");
        add("bostonbull");
        add("dandiedinmont");
        add("australianterrier");
        add("beaglel");
        add("blenheimspaniel");
        add("shihtzu");
    }};

    // to show user
    private List<String> showBreeds = new ArrayList<String>() {{
        add("Golden Retriever");
        add("Pomeranian");
        add("Beaglel");
        add("Labrador Retriever");
        add("Shih Tzu");
        add("Blenheim Spaniel");
        add("Australian Terrier");
        add("Dandie Dinmont");
        add("Boston Bull");
        add("Cocker Spaniel");
        add("Rottweiler");
        add("German Shepherd");
        add("Boxer");
        add("Pug");
        add("Bull Mastiff");
        add("SELECT A BREED..");
    }};

    // to show user get identifed with file name
    private Map<String, String> dogBreedMap = new HashMap<String,String>(){
        {
            put("goldenretriever", "Golden Retriever");
            put("pomeranian", "Pomeranian");
            put("shihtzu", "Shih Tzu");
            put("beaglel", "Beaglel");
            put("labradorretriever", "Labrador Retriever");
            put("blenheimspaniel", "Blenheim Spaniel");
            put("australianterrier", "Australian Terrier");
            put("dandiedinmont", "Dandie Dinmont");
            put("bostonbull", "Boston Bull");
            put("cockerspaniel", "Cocker Spaniel");
            put("rottweiler", "Rottweiler");
            put("germanshepherd", "German Shepherd");
            put("boxer", "Boxer");
            put("pug", "Pug");
            put("bullmastiff", "Bull Mastiff");
        }
    };


    public Map<String, String> getDogBreedMap() {
        return dogBreedMap;
    }

    public List<String> getShowBreeds() {
        return showBreeds;
    }

    private List<Integer> imageIndexList = new ArrayList<>();
    private List<Integer> breedIndexList = new ArrayList<>();
    private int indexImage = 0;
    private int indexBreed = 0;

    private DogBreeds() {
        getRandomImageIndex();
        getRandomBreedIndex();
    }

    public static DogBreeds getInstance() {
        return dogBreeds;
    }

    //get random Breed Index
    private void getRandomBreedIndex() {
        Random random = new Random();
        while (breedIndexList.size() < Config.MAX_DOG_BREEDS_COUNT) {
            int randomBreedIndex = random.nextInt
                    ((Config.MAX_DOG_BREEDS_COUNT - Config.MIN_DOG_BREEDS_COUNT) + 1)
                    + Config.MIN_DOG_BREEDS_COUNT;
            if (!breedIndexList.contains(randomBreedIndex)) {
                breedIndexList.add(randomBreedIndex);
            }
        }
    }

    // ger random breed
    public String getRandomBreed() {
        if (indexBreed == breedIndexList.size()) {
            breedIndexList.clear();
            getRandomBreedIndex();
            indexBreed = 0;
        }
        int breedIndex = breedIndexList.get(indexBreed++);
        return dogBreedsList.get(breedIndex);

    }

    //get Random image Index
    private void getRandomImageIndex() {
        Random random = new Random();
        while (imageIndexList.size() < Config.MAX_DOG_IMAGE_COUNT) {
            int randomIndex = random.nextInt
                    ((Config.MAX_DOG_IMAGE_COUNT - Config.MIN_DOG_IMAGE_COUNT) + 1)
                    + Config.MIN_DOG_IMAGE_COUNT;
            if (!imageIndexList.contains(randomIndex)) {
                imageIndexList.add(randomIndex);
            }

        }
    }

    // Get random Image
    public String getRandomImage(String breedName) {
        if (indexImage == imageIndexList.size()) {
            imageIndexList.clear();
            getRandomImageIndex();
            indexImage = 0;
        }
        String imageName = breedName + "_" + imageIndexList.get(indexImage++);
        System.out.println(imageName);
        return imageName;
    }

}
