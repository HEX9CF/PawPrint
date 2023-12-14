package com.example.pawprint.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.example.pawprint.R;
import com.example.pawprint.activity.EditActivity;
import com.example.pawprint.activity.MainActivity;
import com.example.pawprint.api.AnimalApi;
import com.example.pawprint.model.Animal;
import com.example.pawprint.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 编辑点击监听器
 *
 * @date 2023/11/17
 */
public class EditOnClickListener implements View.OnClickListener {
    private EditActivity activity;
    private AnimalApi animalApi;
    public EditOnClickListener(EditActivity activity) {
        this.activity = activity;
        this.animalApi = activity.getRetrofit().create(AnimalApi.class);
    }

    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        if(view.getId() == R.id.edit_btn_submit) {
            if(activity.getEditType()) {
                Animal animal = new Animal();

                // 读取表单信息
                animal.setId(Integer.parseInt(activity.getEtId().getText().toString()));
                String name = activity.getEtName().getText().toString();
                if(name.isEmpty()) {
                    Toast.makeText(context, "名字不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                animal.setName(name);
                animal.setSpecies(activity.getEtSpecies().getText().toString().isEmpty() ? "" : activity.getEtSpecies().getText().toString());
                animal.setAge(activity.getEtAge().getText().toString().isEmpty() ? 0 : Integer.parseInt(activity.getEtAge().getText().toString()));
                int rbId = activity.getRgGender().getCheckedRadioButtonId();
                int genderId;
                if(rbId == R.id.rb_male) {
                    genderId = 1;
                } else if(rbId == R.id.rb_female) {
                    genderId = 2;
                } else if(rbId == R.id.rb_other) {
                    genderId = 3;
                } else {
                    genderId = 0;
                }
                animal.setGender(genderId);
                animal.setLocation(activity.getEtLocation().getText().toString().isEmpty() ? "" : activity.getEtLocation().getText().toString());
                animal.setHealth(activity.getEtHealth().getText().toString().isEmpty() ? "" : activity.getEtHealth().getText().toString());
                animal.setAppearance(activity.getEtAppearance().getText().toString().isEmpty() ? "" : activity.getEtAppearance().getText().toString());
                animal.setDiet(activity.getEtDiet().getText().toString().isEmpty() ? "" : activity.getEtDiet().getText().toString());
                animal.setDescription(activity.getEtDescription().getText().toString().isEmpty() ? "" : activity.getEtDescription().getText().toString());

                // 创建请求
                Call<Result<Void>> call = animalApi.modify(animal);

                // 异步请求
                call.enqueue(new Callback<Result<Void>>() {
                    @Override
                    public void onResponse(Call<Result<Void>> call, Response<Result<Void>> response) {
                        if(response.body() == null) {
                            return;
                        }
                        String msg = response.body().getMsg();
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Result<Void>> call, Throwable t) {
                        Toast.makeText(context, "提交失败", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Animal animal = new Animal();

                // 读取表单信息
                String name = activity.getEtName().getText().toString();
                if(name.isEmpty()) {
                    Toast.makeText(context, "名字不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                animal.setName(name);
                animal.setSpecies(activity.getEtSpecies().getText().toString().isEmpty() ? "" : activity.getEtSpecies().getText().toString());
                animal.setAge(activity.getEtAge().getText().toString().isEmpty() ? 0 : Integer.parseInt(activity.getEtAge().getText().toString()));
                int rbId = activity.getRgGender().getCheckedRadioButtonId();
                int genderId;
                if(rbId == R.id.rb_male) {
                    genderId = 1;
                } else if(rbId == R.id.rb_female) {
                    genderId = 2;
                } else if(rbId == R.id.rb_other) {
                    genderId = 3;
                } else {
                    genderId = 0;
                }
                animal.setGender(genderId);
                animal.setLocation(activity.getEtLocation().getText().toString().isEmpty() ? "" : activity.getEtLocation().getText().toString());
                animal.setHealth(activity.getEtHealth().getText().toString().isEmpty() ? "" : activity.getEtHealth().getText().toString());
                animal.setAppearance(activity.getEtAppearance().getText().toString().isEmpty() ? "" : activity.getEtAppearance().getText().toString());
                animal.setDiet(activity.getEtDiet().getText().toString().isEmpty() ? "" : activity.getEtDiet().getText().toString());
                animal.setDescription(activity.getEtDescription().getText().toString().isEmpty() ? "" : activity.getEtDescription().getText().toString());

                // 创建请求
                Call<Result<Void>> call = animalApi.add(animal);

                // 异步请求
                call.enqueue(new Callback<Result<Void>>() {
                    @Override
                    public void onResponse(Call<Result<Void>> call, Response<Result<Void>> response) {
                        if(response.body() == null) {
                            return;
                        }
                        String msg = response.body().getMsg();
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Result<Void>> call, Throwable t) {
                        Toast.makeText(context, "提交失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        if(view.getId() == R.id.edit_btn_cancel) {
            // Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();
            activity.finish();
        }
    }
}
