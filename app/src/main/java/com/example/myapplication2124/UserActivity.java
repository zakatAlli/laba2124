package com.example.myapplication2124;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication2124.api.ApiClient;
import com.example.myapplication2124.api.ApiInterface;
import com.example.myapplication2124.data.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    int userID = 0;
    TextView tv;
    ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        tv = findViewById(R.id.TextViewUser);
        Intent intent = getIntent();
        userID = intent.getIntExtra("userId",0);
        getUser();
    }

    public void getUser(){
        Call<Users> listUsers = apiInterface.getUser(userID);
        listUsers.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    Users user = (Users) response.body();
                    tv.append("ID user: " + user.getId() + "\n");
                    tv.append("Name: " + user.getName()+ "\n");
                    tv.append("UserName: " + user.getUsername()+ "\n");
                    tv.append("Address: " + "\n");
                    tv.append("\tStreet: " + user.getAddress().getStreet() + "\n");
                    tv.append("\tSuite: " + user.getAddress().getSuite() + "\n");
                    tv.append("\tCity: " + user.getAddress().getCity() + "\n");
                    tv.append("\tZipCode: " + user.getAddress().getZipcode() + "\n");
                    tv.append("\tGeo: " + "\n");
                    tv.append("\t\tLat: " + user.getAddress().getGeo().getLat() + "\n");
                    tv.append("\t\tLng: " + user.getAddress().getGeo().getLng() + "\n");
                    tv.append("Phone: " + user.getPhone() + "\n");
                    tv.append("Website: " + user.getWebsite() + "\n");
                    tv.append("\tCompany:" + "\n");
                    tv.append("\t\tName company: " + user.getCompany().getName()+ "\n");
                    tv.append("\t\tCatchPhrase company: " + user.getCompany().getCatchPhrase() + "\n");
                    tv.append("\t\tBS company: " + user.getCompany().getBs());
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.e("CODE", t.getMessage() + "");
            }
        });

    }
}