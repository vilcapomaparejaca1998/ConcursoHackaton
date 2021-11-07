package com.example.concurso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

public class comisarias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comisarias);
        DisplayMetrics medidaVentana=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidaVentana);
        int ancho=medidaVentana.widthPixels;
        int alto=medidaVentana.heightPixels;
        getWindow().setLayout((int) (ancho*0.85),(int) (alto*0.5));
    }
}