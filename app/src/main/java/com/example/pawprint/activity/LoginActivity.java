package com.example.pawprint.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawprint.R;
import com.example.pawprint.listener.LoginOnClickListener;
import com.example.pawprint.model.User;
import com.example.pawprint.utils.RetrofitBuilder;

import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity{
    private User user;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 获取控件
        etUsername = findViewById(R.id.login_et_username);
        etPassword = findViewById(R.id.login_et_password);
        btnLogin = findViewById(R.id.login_btn_login);

        // 创建用户实例
        user = new User();

        // 创建 Retrofit 实例
        String baseUrl = getString(R.string.base_url);
        retrofit = RetrofitBuilder.build(baseUrl);

        // 设置监听器
        btnLogin.setOnClickListener(new LoginOnClickListener(this));
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public EditText getEtUsername() {
        return etUsername;
    }

    public EditText getEtPassword() {
        return etPassword;
    }

    public Button getBtnLogin() {
        return btnLogin;
    }

    public User getUser() {
        return user;
    }
}