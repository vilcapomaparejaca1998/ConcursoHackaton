package com.example.concurso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class buscar_ayuda extends AppCompatActivity {
    private ImageView cem;
    private ImageView chat100;
    private ImageView comisarias;
    private ImageView fiscalia;
    private ImageView flora;
    private ImageView linea100;
    ImageButton botoncito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_ayuda);
        botoncito=(ImageButton) findViewById(R.id.flecha);
        botoncito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botoncito=new Intent(buscar_ayuda.this,MainActivity.class);
                startActivity(botoncito);
            }
        });
        cem=(ImageView) findViewById(R.id.cem);
        cem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buscar_ayuda.this,cem.class));
            }
        });
        chat100=(ImageView) findViewById(R.id.chat100);
        chat100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buscar_ayuda.this,chat100.class));
            }
        });
        comisarias=(ImageView) findViewById(R.id.comisaria);
        comisarias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buscar_ayuda.this,comisarias.class));
            }
        });
        fiscalia=(ImageView) findViewById(R.id.fiscalia);
        fiscalia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buscar_ayuda.this,fiscalia.class));
            }
        });
        flora=(ImageView) findViewById(R.id.flora);
        flora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buscar_ayuda.this,flora.class));
            }
        });
        linea100=(ImageView) findViewById(R.id.linea100);
        linea100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buscar_ayuda.this,linea100.class));
            }
        });

    }

    public void RegresarInicio(View view){
        Intent intent=new Intent(buscar_ayuda.this,MainActivity.class);
        startActivity(intent);
    }
    public void RegresarContacto(View view){
        Intent intent=new Intent(buscar_ayuda.this,mostrar_contacto.class);
        startActivity(intent);
    }
    public void RegresarRecomendaciones(View view){
        Intent intent=new Intent(buscar_ayuda.this,MainActivity.class);
        startActivity(intent);
    }
}