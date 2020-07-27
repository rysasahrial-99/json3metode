package com.example.myapplication.Ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import com.example.model.Indonesia;
import com.example.myapplication.R;


public class IndonesiaActivityF10 extends AppCompatActivity {
    private TextView sembuh;
    private TextView rawat;
    private TextView meninggal;
    private TextView positif;
    private TextView negara;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        rawat = findViewById(R.id.txtrawat);
        rawat.setText(Indonesia.dirawat);

        sembuh = findViewById(R.id.txtSembuh);
        sembuh.setText(Indonesia.sembuh);

        meninggal = findViewById(R.id.txtmeninggal);
        meninggal.setText(Indonesia.meninggal);

        positif = findViewById(R.id.txtTotal);
        positif.setText(Indonesia.positif);

        negara = findViewById(R.id.txtnegara);
        negara.setText("Kasus di " + Indonesia.name);

    }
}