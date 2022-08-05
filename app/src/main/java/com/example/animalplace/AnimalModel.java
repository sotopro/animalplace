package com.example.animalplace;

public class AnimalModel {
    private String name;
    private String image;
    private String createAt;
    private String animalType;
    private String id;
    private String location;

    public AnimalModel(String name, String image, String createAt, String animalType, String id, String location) {
        this.name = name;
        this.image = image;
        this.createAt = createAt;
        this.animalType = animalType;
        this.id = id;
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getCreateAt() {
        return createAt;
    }
    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
    public String getAnimalType() {
        return animalType;
    }
    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
