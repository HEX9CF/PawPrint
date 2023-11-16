package com.example.pawprint.listener;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.pawprint.activity.EditActivity;

public class ItemAnimalOnClickListener implements View.OnClickListener{
    private int position;

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "点击 " + position, Toast.LENGTH_LONG).show();
        view.getContext().startActivity(new Intent(view.getContext(), EditActivity.class));
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
