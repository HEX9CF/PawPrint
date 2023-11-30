package com.example.pawprint.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pawprint.R;
import com.example.pawprint.adapter.AnimalRecyclerAdapter;
import com.example.pawprint.api.AnimalApi;
import com.example.pawprint.listener.EditOnClickListener;
import com.example.pawprint.model.Animal;
import com.example.pawprint.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 主要活动
 *
 * @author HEX9CF
 * @date 2023/11/17
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AnimalRecyclerAdapter animalRecyclerAdapter;
    private List<Animal> animalList = new ArrayList<>();
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        startActivity(new Intent(MainActivity.this, EditActivity.class));

        recyclerView = findViewById(R.id.recyclerView);

        // 构造数据
/*
        for(int i = 0; i < 50; i++) {
            Animal animal = new Animal();
            animal.setName("名字" + i);
            animal.setDescription("简介" + i);
            animalList.add(animal);
        }
*/

        // 创建 Retrofit 实例
        String baseUrl = getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 AnimalApi 实例
        AnimalApi animalApi = retrofit.create(AnimalApi.class);

        // 创建请求
        Call<Result<List<Animal>>> call = animalApi.getAll();

        // 异步请求
        call.enqueue(new Callback<Result<List<Animal>>>() {
            @Override
            public void onResponse(Call<Result<List<Animal>>> call, Response<Result<List<Animal>>> response) {
                Result<List<Animal>> body = response.body();
                if (body == null) {
                    return;
                }
                Toast.makeText(MainActivity.this, "获取档案列表成功", Toast.LENGTH_LONG).show();
                animalList = body.getData();
                // 设置适配器
                animalRecyclerAdapter = new AnimalRecyclerAdapter();
                animalRecyclerAdapter.setAnimalList(animalList);
                recyclerView.setAdapter(animalRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<Result<List<Animal>>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_LONG).show();
            }
        });

        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);
    }
}