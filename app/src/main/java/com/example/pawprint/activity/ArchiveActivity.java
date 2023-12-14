package com.example.pawprint.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pawprint.R;
import com.example.pawprint.listener.ArchiveOnClickListener;
import com.example.pawprint.model.Animal;
import com.example.pawprint.api.AnimalApi;
import com.example.pawprint.model.Result;
import com.example.pawprint.utils.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 编辑活动
 *
 * @date 2023/11/16
 */
public class ArchiveActivity extends AppCompatActivity {
    Animal animal;
    private Retrofit retrofit;
    // 控件成员变量
    private ImageView ivAvatar;
    private TextView tvId;
    private TextView tvName;
    private TextView tvSpecies;
    private TextView tvAge;
    private TextView tvGender;
    private TextView tvLocation;
    private TextView tvHealth;
    private TextView tvAppearance;
    private TextView tvDiet;
    private TextView tvDescription;
    private Button btnEdit;

    /**
     * 创建时
     *
     * @param savedInstanceState 保存实例状态
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        // 初始化控件
        ivAvatar = findViewById(R.id.iv_avatar);
        tvId = findViewById(R.id.tv_id);
        tvName = findViewById(R.id.tv_name);
        tvSpecies = findViewById(R.id.tv_species);
        tvAge = findViewById(R.id.tv_age);
        tvGender = findViewById(R.id.tv_gender);
        tvLocation = findViewById(R.id.tv_location);
        tvHealth = findViewById(R.id.tv_health);
        tvAppearance = findViewById(R.id.tv_appearance);
        tvDiet = findViewById(R.id.tv_diet);
        tvDescription = findViewById(R.id.tv_description);
        btnEdit = findViewById(R.id.btn_edit);

        // 获取传递的参数
        Intent intent = this.getIntent();
        Integer id = intent.getIntExtra("id", 0);

        // 创建 Retrofit 实例
        String baseUrl = getString(R.string.base_url);
        retrofit = RetrofitBuilder.build(baseUrl);

        // 绑定监听器
        this.btnEdit.setOnClickListener(new ArchiveOnClickListener(this));

        // 创建 AnimalApi 实例
        AnimalApi animalApi = retrofit.create(AnimalApi.class);

        // 创建请求
        Call<Result<Animal>> call = animalApi.getById(id);

        // 异步请求
        call.enqueue(new Callback<Result<Animal>>() {
            @Override
            public void onResponse(Call<Result<Animal>> call, Response<Result<Animal>> response) {
                Result<Animal> body = response.body();
                if(body == null) {
                    return;
                }
                animal = response.body().getData();
                if(animal == null) {
                    return;
                }
                // 设置控件值
                if(animal.getId() != null) {
                    int id = animal.getId();
                    tvId.setText(String.valueOf(id));
                }
                if(animal.getName() != null) {
                    tvName.setText(animal.getName());
                }
                if(animal.getSpecies() != null) {
                    tvSpecies.setText(animal.getSpecies());
                }
                if(animal.getSpecies() != null) {
                    int age = animal.getAge();
                    tvAge.setText(String.valueOf(age));
                }
                if(animal.getGender() != null) {
                    switch(animal.getGender()) {
                        case 1:
                            // 雄性
                            tvGender.setText("雄性");
                            break;
                        case 2:
                            // 雌性
                            tvGender.setText("雌性");
                            break;
                        case 3:
                            // 其他
                            tvGender.setText("其他");
                            break;
                        default:
                            // 未知
                            tvGender.setText("未知");
                            break;
                    }
                }
                if(animal.getLocation() != null) {
                    tvLocation.setText(animal.getLocation());
                }
                if(animal.getHealth() != null) {
                    tvHealth.setText(animal.getHealth());
                }
                if(animal.getAppearance() != null) {
                    tvAppearance.setText(animal.getAppearance());
                }
                if(animal.getDiet() != null) {
                    tvDiet.setText(animal.getDiet());
                }
                if(animal.getDescription() != null) {
                    tvDescription.setText(animal.getDescription());
                }

                // 设置头像
                Uri uri = Uri.parse(animal.getAvatar());
                Glide.with(ArchiveActivity.this)
                        .load(uri)
                        .placeholder(R.drawable.baseline_photo_240)
                        .error(R.drawable.baseline_photo_240)
                        .into(ivAvatar);
            }

            @Override
            public void onFailure(Call<Result<Animal>> call, Throwable t) {
                Toast.makeText(ArchiveActivity.this, "请求失败", Toast.LENGTH_LONG).show();
            }
        });
    }

    public Animal getAnimal() {
        return animal;
    }



}
