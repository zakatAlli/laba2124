package com.example.myapplication2124;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication2124.api.ApiClient;
import com.example.myapplication2124.api.ApiInterface;
import com.example.myapplication2124.data.Post;
import com.example.myapplication2124.data.PostAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Post> postList = new ArrayList<>();
    PostAdapter postAdapter;
    RecyclerView recyclerView;
    ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        getPosts();
        PostAdapter.OnUserClickListener onUserClickListener = (post, position) -> {

            Post selectedPost = postList.get(position);
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            intent.putExtra("userId", selectedPost.getUserId());
            startActivity(intent);
        };
        postAdapter = new PostAdapter(getApplicationContext(), postList,onUserClickListener);

    }

    public void getPosts(){
        Call<List<Post>> listPost = apiInterface.getPosts();
        listPost.enqueue(new Callback<List<Post>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    Log.e("CODE", response.code()+"");
                    return;
                }
                postList = response.body();
                postAdapter.changeData(postList);
                recyclerView.setAdapter(postAdapter);
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.w("Code", t.getLocalizedMessage()+ "");
            }
        });
    }
}