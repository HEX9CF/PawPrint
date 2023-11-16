package com.example.pawprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.pawprint.adapter.AnimalRecyclerAdapter;
import com.example.pawprint.pojo.Animal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AnimalRecyclerAdapter animalRecyclerAdapter;
    List<Animal> animalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        // 构造数据
        for(int i = 0; i < 50; i++) {
            Animal animal = new Animal();
            animal.setName("名字" + i);
            animal.setDescription("简介" + i);
            animalList.add(animal);
        }

        // 设置适配器
        animalRecyclerAdapter = new AnimalRecyclerAdapter();
        animalRecyclerAdapter.setAnimalList(animalList);
        recyclerView.setAdapter(animalRecyclerAdapter);

        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);
    }


}