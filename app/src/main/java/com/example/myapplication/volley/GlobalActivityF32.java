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
import com.example.model.Global;
import com.example.model.Indonesia;
import com.example.myapplication.R;
import com.example.myapplication.Ui.IndonesiaActivityF10;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GlobalActivityF32 extends AppCompatActivity {

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
                        JSONObject jsonArray = new JSONObject(response);
                        Global.name = jsonArray.getString("name");
                        Global.positif = jsonArray.getString("value");
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

        RequestQueue requestQueue = Volley.newRequestQueue(GlobalActivityF32.this);
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