package com.iit.findyourdog.util;

import com.iit.findyourdog.config.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DogBreeds {

    private static DogBreeds dogBreeds = new DogBreeds();
    private List<String> dogBreedsList = new ArrayList<String>() {{
        add("goldenretriever");
        add("beagle");
        add("redbone");
        add("cairn");
        add("cardigan");
        add("chow");
        add("pomeranian");
        add("greatpyrenees");
        add("entlebucher");
        add("appenzeller");
        add("collie");
        add("labradorretriever");
        add("lhasa");
        add("kuvasz");
        add("shihtzu");
    }};

    private List<String> showBreeds = new ArrayList<String>() {{
        add("Golden Retriever");
        add("Beagle");
        add("Redbone");
        add("Cairn");
        add("Cardigan");
        add("Chow");
        add("Pomeranian");
        add("Great Pyrenees");
        add("EntleBucher");
        add("Appenzeller");
        add("Collie");
        add("Labrador Retriever");
        add("Lhasa");
        add("Kuvasz");
        add("Shih Tzu");
        add("SELECT A BREED..");

    }};

    private Map<String, String> dogBreedMap = new HashMap<String,String>(){
        {
            put("goldenretriever", "Golden Retriever");
            put("beagle", "Beagle");
            put("redbone", "Redbone");
            put("cairn", "Cairn");
            put("cardigan", "Cardigan");
            put("chow", "Chow");
            put("pomeranian", "Pomeranian");
            put("greatpyrenees", "Great Pyrenees");
            put("entlebucher", "EntleBucher");
            put("appenzeller", "Appenzeller");
            put("collie", "Collie");
            put("labradorretriever", "Labrador Retriever");
            put("lhasa", "Lhasa");
            put("kuvasz", "Kuvasz");
            put("shihtzu", "Shih Tzu");

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

    public String getRandomBreed() {
        if (indexBreed == breedIndexList.size()) {
            breedIndexList.clear();
            getRandomBreedIndex();
            indexBreed = 0;
        }
        int breedIndex = breedIndexList.get(indexBreed++);
        return dogBreedsList.get(breedIndex);

    }

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
