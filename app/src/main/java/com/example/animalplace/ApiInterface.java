package com.example.animalplace;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("animals")
    Call<List<AnimalModel>> getAnimals();

    @POST("support")
    Call<SupportModel> postAnimal(@Body SupportModel supportModel);
}
