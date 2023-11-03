package com.example.pawprint.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawprint.MainActivity;
import com.example.pawprint.R;
import com.example.pawprint.pojo.Animal;
import com.example.pawprint.viewHolder.ItemAnimalViewHolder;

import java.util.List;

public class AnimalRecyclerAdapter extends RecyclerView.Adapter<ItemAnimalViewHolder> {
    List<Animal> animalList;

    @NonNull
    @Override
    public ItemAnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_animal, null);
        ItemAnimalViewHolder itemAnimalViewHolder = new ItemAnimalViewHolder(view);
        return itemAnimalViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAnimalViewHolder holder, int position) {
        Animal animal = animalList.get(position);
        holder.getNameTv().setText(animal.getName());
        holder.getDescriptionTv().setText(animal.getDescription());
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }
}
