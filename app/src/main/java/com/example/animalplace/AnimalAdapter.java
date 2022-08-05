package com.example.animalplace;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    private List<AnimalModel> animalModel;

    public AnimalAdapter(List<AnimalModel> animalModel) {
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
        holder.animalName.setText(animalModel.get(position).getName());
        Glide.with(holder.itemView.getContext()).load(animalModel.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.animalImage);
    }

    @Override
    public int getItemCount() {
        return animalModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView animalName;
        ImageView animalImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            animalName = itemView.findViewById(R.id.animal_name);
            animalImage = itemView.findViewById(R.id.animal_image);
        }
    }
}
