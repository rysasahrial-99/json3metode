package com.example.myapplication.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiProv {
    String BASE_URL = "https://api.kawalcorona.com/indonesia/";
    @GET("provinsi")
    Call<String> getIndonesia();
}