package com.example.pawprint.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawprint.R;
import com.example.pawprint.listener.ItemAnimalOnClickListener;
import com.example.pawprint.model.Animal;
import com.example.pawprint.viewHolder.ItemAnimalViewHolder;

import java.util.List;

/**
 * 动物回收器适配器
 *
 * @date 2023/11/17
 */
public class AnimalRecyclerAdapter extends RecyclerView.Adapter<ItemAnimalViewHolder> {
    List<Animal> animalList;

    @NonNull
    @Override
    public ItemAnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = View.inflate(parent.getContext(), R.layout.item_animal, null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, null, false);
        return new ItemAnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAnimalViewHolder holder, int position) {
        Animal animal = animalList.get(position);

        holder.getNameTv().setText(animal.getName());
        holder.getDescriptionTv().setText(animal.getDescription());

        // 设置监听器
        ItemAnimalOnClickListener onClickListener = new ItemAnimalOnClickListener();
        onClickListener.setAnimal(animal);
        holder.getRootView().setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }
}
