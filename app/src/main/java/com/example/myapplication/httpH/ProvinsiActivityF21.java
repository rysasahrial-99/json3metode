package com.example.myapplication.httpH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.model.Provinsi;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ProvAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProvinsiActivityF21 extends AppCompatActivity {
    private String TAG = IndonesiaActivityF11.class.getSimpleName();
    private RecyclerView recyclerView;
    private ProvAdapter adapter;
    ArrayList<Provinsi> webLists;
    private static String url = "https://api.kawalcorona.com/indonesia/provinsi/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provinsi20);
        if (checkConnectivity()){
            new GetStatus().execute();
        }



        recyclerView = (RecyclerView) findViewById(R.id.recwah);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        webLists = new ArrayList<>();



    }

    @SuppressLint("StaticFieldLeak")
    private class GetStatus extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonStr);
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


                    try {
                        adapter = new ProvAdapter(webLists, ProvinsiActivityF21.this);
                        recyclerView.setAdapter(adapter);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (final JSONException e) {



                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            return null;
        }
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