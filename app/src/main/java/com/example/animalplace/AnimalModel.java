package com.example.animalplace;

public class AnimalModel {
    private String animalName;
    private int animalPhoto;

    public AnimalModel(String animalName, int animalPhoto) {
        this.animalName = animalName;
        this.animalPhoto = animalPhoto;
    }

    public String getAnimalName() {
        return animalName;
    }

    public int getAnimalPhoto() {
        return animalPhoto;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setAnimalPhoto(int animalPhoto) {
        this.animalPhoto = animalPhoto;
    }
}
