package com.example.pawprint.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawprint.R;

/**
 * 动物条目视图持有者
 *
 * @date 2023/11/17
 */
public class ItemAnimalViewHolder extends RecyclerView.ViewHolder {
    TextView nameTv;
    TextView descriptionTv;
    ImageView avatarIv;
    ConstraintLayout rootView;

    public ItemAnimalViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTv = itemView.findViewById(R.id.item_tv_title);
        descriptionTv = itemView.findViewById(R.id.item_tv_desc);
        avatarIv = itemView.findViewById(R.id.item_iv_avatar);
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

    public ImageView getAvatarIv() {
        return avatarIv;
    }

    public void setAvatarIv(ImageView avatarIv) {
        this.avatarIv = avatarIv;
    }
}
