package com.example.animalplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button supportButton;
    private RecyclerView recyclerView;
    private AnimalAdapter animalAdapter;
    private ArrayList<AnimalModel> animalModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();

        supportButton = findViewById(R.id.support_button);
        recyclerView = findViewById(R.id.animal_recycler_view);
        animalAdapter = new AnimalAdapter(animalModel);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(animalAdapter);

        supportButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SupportActivity.class);
            startActivity(intent);
        });
    }

    private void getData(){
        animalModel = new ArrayList<>();
        animalModel.add(new AnimalModel("bufalo", "https://cdn-icons-png.flaticon.com/512/3969/3969798.png"));
        animalModel.add(new AnimalModel("cerdo", "https://cdn-icons-png.flaticon.com/512/3969/3969799.png"));
        animalModel.add(new AnimalModel("cocodrilo", "https://cdn-icons-png.flaticon.com/512/3969/3969794.png"));
        animalModel.add(new AnimalModel("conejo", "https://cdn-icons-png.flaticon.com/512/3969/3969777.png"));
        animalModel.add(new AnimalModel("elefante", "https://cdn-icons-png.flaticon.com/512/3969/3969722.png"));
        animalModel.add(new AnimalModel("gato", "https://cdn-icons-png.flaticon.com/512/3969/3969800.png"));
        animalModel.add(new AnimalModel("morsa", "https://cdn-icons-png.flaticon.com/512/3969/3969761.png"));
        animalModel.add(new AnimalModel("oveja", "https://cdn-icons-png.flaticon.com/512/3969/3969801.png"));
        animalModel.add(new AnimalModel("panda", "https://cdn-icons-png.flaticon.com/512/3969/3969735.png"));
        animalModel.add(new AnimalModel("perro", "https://cdn-icons-png.flaticon.com/512/3969/3969775.png"));
        animalModel.add(new AnimalModel("pollo", "https://cdn-icons-png.flaticon.com/512/3969/3969783.png"));
        animalModel.add(new AnimalModel("rana", "https://cdn-icons-png.flaticon.com/512/3969/3969785.png"));
        animalModel.add(new AnimalModel("tortuga", "https://cdn-icons-png.flaticon.com/512/3969/3969730.png"));
    }
}