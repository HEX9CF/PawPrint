package com.example.pawprint.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.pawprint.R;
import com.example.pawprint.activity.EditActivity;
import com.example.pawprint.activity.LoginActivity;
import com.example.pawprint.activity.MainActivity;
import com.example.pawprint.api.AnimalApi;
import com.example.pawprint.api.UserApi;
import com.example.pawprint.model.Animal;
import com.example.pawprint.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登录点击监听器
 *
 * @date 2023/11/17
 */
public class LoginOnClickListener implements View.OnClickListener {
    private LoginActivity activity;
    private UserApi userApi;

    public LoginOnClickListener(LoginActivity activity) {
        this.activity = activity;
        this.userApi = activity.getRetrofit().create(UserApi.class);
    }

    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        if (view.getId() == R.id.login_btn_login) {
            // 读取表单信息
            String username = activity.getEtUsername().getText().toString();
            String password = activity.getEtPassword().getText().toString();

            // 检查表单信息
            if (username.isEmpty()) {
                Toast.makeText(context, "用户名不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.isEmpty()) {
                Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            // 设置用户信息
            activity.getUser().setUsername(username);
            activity.getUser().setPassword(password);

            // 创建请求
            Call<Result<Void>> call = userApi.login(activity.getUser());

            // 异步请求
            call.enqueue(new Callback<Result<Void>>() {
                @Override
                public void onResponse(Call<Result<Void>> call, Response<Result<Void>> response) {
                    Result<Void> result = response.body();
                    if (result == null) {
                        Toast.makeText(context, "未知错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (result.getCode() == 1) {
                        Toast.makeText(context, result.getMsg(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, result.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Result<Void>> call, Throwable t) {
                    Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
