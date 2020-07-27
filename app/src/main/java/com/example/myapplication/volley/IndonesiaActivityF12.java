package com.example.myapplication.volley;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.model.Indonesia;
import com.example.myapplication.Ui.IndonesiaActivityF10;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IndonesiaActivityF12 extends AppCompatActivity {

    private static String url = "https://api.kawalcorona.com/indonesia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        if (checkConnectivity()){
            loadUrlData();
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

    private void loadUrlData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray mJsonArray = new JSONArray(response);
                        for(int i=0;i<mJsonArray.length();i++) {
                            JSONObject jsonData = mJsonArray.getJSONObject(i);
                            Indonesia.name = jsonData.getString("name");
                            Indonesia.positif = jsonData.getString("positif");
                            Indonesia.sembuh = jsonData.getString("sembuh");
                            Indonesia.meninggal = jsonData.getString("meninggal");
                            Indonesia.dirawat = jsonData.getString("dirawat");
                        }

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(IndonesiaActivityF12.this);
        requestQueue.add(stringRequest);
    }

    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            // Toast.makeText(getApplicationContext(), "Sin conexión a Internet...", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}