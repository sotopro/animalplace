package com.example.animalplace;

public class AnimalModel {
    private String animalName;
    private String animalPhoto;

    public AnimalModel(String animalName, String animalPhoto) {
        this.animalName = animalName;
        this.animalPhoto = animalPhoto;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalPhoto() {
        return animalPhoto;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setAnimalPhoto(String animalPhoto) {
        this.animalPhoto = animalPhoto;
    }
}
