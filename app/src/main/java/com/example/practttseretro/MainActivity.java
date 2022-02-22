package com.example.practttseretro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));


        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Model>> call = jsonPlaceHolderApi.getModel( );
        call.enqueue(new Callback<List<Model>>( ) {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show( );
                    return;
                }
                List<Model> modelList = response.body();
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,modelList);
                recycler.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show( );

            }
        });


    }
}