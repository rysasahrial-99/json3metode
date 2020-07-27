package com.example.myapplication.Ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.Global;
import com.example.model.Indonesia;
import com.example.myapplication.R;

public class GlobalActivityF30 extends AppCompatActivity {
    private TextView positif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main30);
        positif = findViewById(R.id.txtTotal);
        positif.setText(Global.positif);
    }
}