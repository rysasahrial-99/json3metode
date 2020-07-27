package com.example.myapplication.retrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Provinsi;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ProvAdapter;
import com.example.myapplication.httpH.IndonesiaActivityF11;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ProvinsiActivityF23 extends AppCompatActivity {
    private String TAG = IndonesiaActivityF11.class.getSimpleName();
    private RecyclerView recyclerView;
    private ProvAdapter adapter;
    ArrayList<Provinsi> webLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provinsi20);

        recyclerView = (RecyclerView) findViewById(R.id.recwah);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        webLists = new ArrayList<>();

        if (checkConnectivity()){
            getResponse();
        }


    }

    private void getResponse(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiProv.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiProv api = retrofit.create(ApiProv.class);

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

                            adapter = new ProvAdapter(webLists, ProvinsiActivityF23.this);
                            recyclerView.setAdapter(adapter);

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