package com.example.pawprint.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawprint.R;

public class ItemAnimalViewHolder extends RecyclerView.ViewHolder {
    TextView nameTv;
    TextView descriptionTv;

    public ItemAnimalViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTv = itemView.findViewById(R.id.textView);
        descriptionTv = itemView.findViewById(R.id.textView2);
    }

    public TextView getNameTv() {
        return nameTv;
    }

    public TextView getDescriptionTv() {
        return descriptionTv;
    }
}
