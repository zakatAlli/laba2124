package com.example.myapplication2124.api;

import com.example.myapplication2124.data.Post;
import com.example.myapplication2124.data.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/posts")
    Call<List<Post>> getPosts();

    @GET("/users/{id}")
    Call<Users> getUser(@Path("id") int userId);
}
