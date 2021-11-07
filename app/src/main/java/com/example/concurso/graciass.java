package com.example.concurso;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class graciass extends AppCompatActivity {
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graciass);
        DisplayMetrics medidaVentana=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidaVentana);
        int ancho=medidaVentana.widthPixels;
        int alto=medidaVentana.heightPixels;
        getWindow().setLayout((int) (ancho*0.85),(int) (alto*0.35));
        boton=(Button) findViewById(R.id.botondecontinuar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botoncito=new Intent(graciass.this,MainActivity.class);
                startActivity(botoncito);
            }
        });
    }
}