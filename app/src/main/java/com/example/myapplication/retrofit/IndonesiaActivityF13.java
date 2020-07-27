package com.example.myapplication.retrofit;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.Indonesia;
import com.example.myapplication.R;
import com.example.myapplication.Ui.IndonesiaActivityF10;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class IndonesiaActivityF13 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        if (checkConnectivity()){
            getResponse();
        }
        new CountDownTimer(5000, 1000) {
            @Override
            public void onFinish() {
                Intent intent = new Intent(getBaseContext(), IndonesiaActivityF10.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onTick(long millisUntilFinished) {
            }
        }.start();
    }
    private void getResponse(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<String> call = api.getIndonesia();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String m = response.body();
                    if (response.body() != null) {
                        try {
                            JSONArray jsonArray = new JSONArray(m);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonData = jsonArray.getJSONObject(i);
                                Indonesia.name = jsonData.getString("name");
                                Indonesia.positif = jsonData.getString("positif");
                                Indonesia.sembuh = jsonData.getString("sembuh");
                                Indonesia.meninggal = jsonData.getString("meninggal");
                                Indonesia.dirawat = jsonData.getString("dirawat");
                            }

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            return false;
        } else {
            return true;
        }
    }
}