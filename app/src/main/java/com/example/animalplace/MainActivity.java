package com.example.animalplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button supportButton;
    private RecyclerView recyclerView;
    private AnimalAdapter animalAdapter;
    private List<AnimalModel> animalModel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        supportButton = findViewById(R.id.support_button);
        recyclerView = findViewById(R.id.animal_recycler_view);
        animalAdapter = new AnimalAdapter(animalModel);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(animalAdapter);

        fetchAnimals();


        supportButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SupportActivity.class);
            startActivity(intent);
        });
    }

    private void fetchAnimals(){
        RetrofitClient.getRetrofitClient().getAnimals().enqueue(new Callback<List<AnimalModel>>() {
            @Override
            public void onResponse(Call<List<AnimalModel>> call, Response<List<AnimalModel>> response) {
                if(response.isSuccessful() && response.body() != null){
                    animalModel.addAll(response.body());
                    animalAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<AnimalModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error fetching animals", Toast.LENGTH_SHORT).show();
            }
        });
    }
}