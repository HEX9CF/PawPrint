package com.example.pawprint.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawprint.R;

/**
 * 动物条目视图持有者
 *
 * @author HEX9CF
 * @date 2023/11/17
 */
public class ItemAnimalViewHolder extends RecyclerView.ViewHolder {
    TextView nameTv;
    TextView descriptionTv;
    ConstraintLayout rootView;

    public ItemAnimalViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTv = itemView.findViewById(R.id.textView);
        descriptionTv = itemView.findViewById(R.id.textView2);
        rootView = itemView.findViewById(R.id.rootView);
    }

    public void setNameTv(TextView nameTv) {
        this.nameTv = nameTv;
    }

    public void setDescriptionTv(TextView descriptionTv) {
        this.descriptionTv = descriptionTv;
    }

    public ConstraintLayout getRootView() {
        return rootView;
    }

    public void setRootView(ConstraintLayout rootView) {
        this.rootView = rootView;
    }

    public TextView getNameTv() {
        return nameTv;
    }

    public TextView getDescriptionTv() {
        return descriptionTv;
    }
}
