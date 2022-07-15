package com.example.animalplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnimalAdapter animalAdapter;
    private ArrayList<AnimalModel> animalModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();

        recyclerView = findViewById(R.id.animal_recycler_view);
        animalAdapter = new AnimalAdapter(animalModel);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(animalAdapter);
    }

    private void getData(){
        animalModel = new ArrayList<>();
        animalModel.add(new AnimalModel("bufalo", R.drawable.bufalo));
        animalModel.add(new AnimalModel("cerdo", R.drawable.cerdo));
        animalModel.add(new AnimalModel("cocodrilo", R.drawable.cocodrilo));
        animalModel.add(new AnimalModel("conejo", R.drawable.conejo));
        animalModel.add(new AnimalModel("elefante", R.drawable.elefante));
        animalModel.add(new AnimalModel("gato", R.drawable.gato));
        animalModel.add(new AnimalModel("morsa", R.drawable.morsa));
        animalModel.add(new AnimalModel("oveja", R.drawable.oveja));
        animalModel.add(new AnimalModel("panda", R.drawable.panda));
        animalModel.add(new AnimalModel("perro", R.drawable.perro));
        animalModel.add(new AnimalModel("pollo", R.drawable.pollo));
        animalModel.add(new AnimalModel("rana", R.drawable.rana));
        animalModel.add(new AnimalModel("tortuga", R.drawable.tortuga));
    }
}