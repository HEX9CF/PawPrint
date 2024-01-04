package com.example.pawprint.api;

import com.example.pawprint.model.Result;
import com.example.pawprint.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 用户api
 *
 * @date 2023/11/17
 */
public interface UserApi {
    @POST("api/user/login")
    Call<Result<Void>> login(@Body User user);
}
