package com.example.animalplace;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    private ArrayList<AnimalModel> animalModel;

    public AnimalAdapter(ArrayList<AnimalModel> animalModel) {
        this.animalModel = animalModel;
    }

    @NonNull
    @Override
    public AnimalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.ViewHolder holder, int position) {
        holder.animalName.setText(animalModel.get(position).getAnimalName());
        holder.animalPhoto.setImageResource(animalModel.get(position).getAnimalPhoto());
    }

    @Override
    public int getItemCount() {
        return animalModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView animalName;
        ImageView animalPhoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            animalName = itemView.findViewById(R.id.animal_name);
            animalPhoto = itemView.findViewById(R.id.animal_photo);
        }
    }
}
