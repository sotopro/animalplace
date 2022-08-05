package com.example.animalplace;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("animals")
    Call<List<AnimalModel>> getAnimals();
}
