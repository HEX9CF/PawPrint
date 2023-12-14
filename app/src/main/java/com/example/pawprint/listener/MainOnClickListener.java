package com.example.pawprint.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.example.pawprint.R;

import com.example.pawprint.activity.EditActivity;
import com.example.pawprint.activity.MainActivity;

public class MainOnClickListener implements View.OnClickListener{
    private MainActivity activity;

    public MainOnClickListener(MainActivity activity) {
        this.activity = activity;
    }
    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        if(view.getId() == R.id.main_btn_add) {
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("edit_type", false);
            context.startActivity(intent);
        }
    }
}
