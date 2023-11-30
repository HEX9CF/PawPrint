package com.example.pawprint.listener;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.example.pawprint.model.Animal;

import com.example.pawprint.activity.EditActivity;

/**
 * 动物条目点击监听器
 *
 * @author HEX9CF
 * @date 2023/11/17
 */
public class ItemAnimalOnClickListener implements View.OnClickListener{
    Animal animal;

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), EditActivity.class);
        intent.putExtra("id", animal.getId().toString());
        view.getContext().startActivity(intent);
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
