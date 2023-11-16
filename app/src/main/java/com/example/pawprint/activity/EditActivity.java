package com.example.pawprint.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.pawprint.R;
import com.example.pawprint.listener.EditOnClickListener;
import com.example.pawprint.model.Animal;
import com.example.pawprint.api.AnimalApi;
import com.example.pawprint.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 编辑活动
 *
 * @author HEX9CF
 * @date 2023/11/16
 */
public class EditActivity extends AppCompatActivity {
    private Animal animal;
    private Retrofit retrofit;
    // 控件成员变量
    private EditText etName;
    private EditText etSpecies;
    private EditText etAge;
    private RadioGroup rgGender;
    private EditText etLocation;
    private EditText etHealth;
    private EditText etAppearance;
    private EditText etDiet;
    private EditText etDescription;
    private Button btnCancel;
    private Button btnSubmit;

    /**
     * 创建时
     *
     * @param savedInstanceState 保存实例状态
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // 初始化控件
        etName = findViewById(R.id.et_name);
        etSpecies = findViewById(R.id.et_species);
        etAge = findViewById(R.id.et_age);
        rgGender = findViewById(R.id.rg_gender);
        etLocation = findViewById(R.id.et_location);
        etHealth = findViewById(R.id.et_health);
        etAppearance = findViewById(R.id.et_appearance);
        etDiet = findViewById(R.id.et_diet);
        etDescription = findViewById(R.id.et_description);
        btnCancel = findViewById(R.id.btn_cancel);
        btnSubmit = findViewById(R.id.btn_submit);

        // 绑定监听器
        this.btnSubmit.setOnClickListener(new EditOnClickListener());
        this.btnCancel.setOnClickListener(new EditOnClickListener());

        // 创建 Retrofit 实例
        String baseUrl = getString(R.string.base_url);
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AnimalApi animalApi = retrofit.create(AnimalApi.class);
        Call<Result<Animal>> call = animalApi.getById(2);

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
                if(animal.getName() != null) {
                    etName.setText(animal.getName());
                }
                if(animal.getSpecies() != null) {
                    etSpecies.setText(animal.getSpecies());
                }
                if(animal.getSpecies() != null) {
                    int age = animal.getAge();
                    etAge.setText(String.valueOf(age));
                }
                if(animal.getGender() != null) {
                    switch(animal.getGender()) {
                        case 1:
                            // 雄性
                            rgGender.check(R.id.rb_male);
                            break;
                        case 2:
                            // 雌性
                            rgGender.check(R.id.rb_female);
                            break;
                        case 3:
                            // 其他
                            rgGender.check(R.id.rb_other);
                            break;
                        default:
                            // 未知
                            rgGender.check(R.id.rb_unknown);
                            break;
                    }
                }
                if(animal.getLocation() != null) {
                    etLocation.setText(animal.getLocation());
                }
                if(animal.getHealth() != null) {
                    etHealth.setText(animal.getHealth());
                }
                if(animal.getAppearance() != null) {
                    etAppearance.setText(animal.getAppearance());
                }
                if(animal.getDiet() != null) {
                    etDiet.setText(animal.getDiet());
                }
                if(animal.getDescription() != null) {
                    etDescription.setText(animal.getDescription());
                }
            }

            @Override
            public void onFailure(Call<Result<Animal>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}