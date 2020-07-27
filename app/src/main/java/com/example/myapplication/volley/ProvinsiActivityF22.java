package com.example.myapplication.volley;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.model.Provinsi;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ProvAdapter;
import com.example.myapplication.httpH.IndonesiaActivityF11;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProvinsiActivityF22 extends AppCompatActivity {
    private String TAG = IndonesiaActivityF11.class.getSimpleName();
    private RecyclerView recyclerView;
    private ProvAdapter adapter;
    ArrayList<Provinsi> webLists;
    private static String url = "https://api.kawalcorona.com/indonesia/provinsi/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provinsi20);

        recyclerView = (RecyclerView) findViewById(R.id.recwah);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        webLists = new ArrayList<>();

        if (checkConnectivity()){
            loadUrlData();
        }


    }

    private void loadUrlData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonData = jsonArray.getJSONObject(i);
                            JSONObject j = jsonData.getJSONObject("attributes");
                            Provinsi developers = new Provinsi();
                            developers.FID = j.getInt("FID");
                            developers.Kode_Provi = j.getString("Kode_Provi");
                            developers.Provinsi = j.getString("Provinsi");
                            developers.Kasus_Posi = j.getString("Kasus_Posi");
                            developers.Kasus_Meni = j.getString("Kasus_Meni");
                            developers.Kasus_Semb = j.getString("Kasus_Semb");
                            webLists.add(developers);
                        }

                        adapter = new ProvAdapter(webLists, ProvinsiActivityF22.this);
                        recyclerView.setAdapter(adapter);
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

        RequestQueue requestQueue = Volley.newRequestQueue(ProvinsiActivityF22.this);
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