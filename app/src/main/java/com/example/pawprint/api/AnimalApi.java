package com.example.pawprint.api;

import com.example.pawprint.model.Animal;
import com.example.pawprint.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AnimalApi {
    @GET("api/animal")
    Call<Result<List<Animal>>> getAll();

    @GET("api/animal/{id}")
    Call<Result<Animal>> getById(@Path("id") int id);
}
