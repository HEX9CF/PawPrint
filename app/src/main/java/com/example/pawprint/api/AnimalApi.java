package com.example.pawprint.api;

import com.example.pawprint.model.Animal;
import com.example.pawprint.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * 动物api
 *
 * @author HEX9CF
 * @date 2023/11/17
 */
public interface AnimalApi {
    @GET("api/animal")
    Call<Result<List<Animal>>> getAll();

    @GET("api/animal/{id}")
    Call<Result<Animal>> getById(@Path("id") Integer id);

    @POST("api/animal")
    Call<Result<Void>> add(@Body Animal animal);

    @PUT("api/animal")
    Call<Result<Void>> modify(@Body Animal animal);

    @DELETE("api/animal/{id}")
    Call<Result<Void>> delete(@Path("id") Integer id);
}
