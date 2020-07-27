package com.example.myapplication.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiGlobal {
    String BASE_URL = "https://api.kawalcorona.com/";
    @GET("positif")
    Call<String> getIndonesia();
}