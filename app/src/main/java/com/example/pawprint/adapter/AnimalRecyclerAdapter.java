package com.example.pawprint.adapter;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

        // 设置文本
        if(animal.getName() == null || animal.getName().isEmpty()) {
            holder.getNameTv().setText("");
        } else {
            holder.getNameTv().setText(animal.getName());
        }
        if(animal.getDescription() == null || animal.getDescription().isEmpty()) {
            holder.getDescriptionTv().setText("");
        } else {
            holder.getDescriptionTv().setText(animal.getDescription());
        }

        // 设置头像
        if(animal.getAvatar() == null || animal.getAvatar().isEmpty()) {
            holder.getAvatarIv().setImageResource(R.drawable.baseline_photo_240);
        } else {
            Uri uri = Uri.parse(animal.getAvatar());
            Glide.with(holder.getAvatarIv().getContext())
                    .load(uri)
                    .placeholder(R.drawable.baseline_photo_240)
                    .error(R.drawable.baseline_photo_240)
                    .into(holder.getAvatarIv());
        }

        // 设置监听器
        ItemAnimalOnClickListener onClickListener = new ItemAnimalOnClickListener();
        onClickListener.setAnimal(animal);
        holder.getRootView().setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
        notifyDataSetChanged();
    }
}
