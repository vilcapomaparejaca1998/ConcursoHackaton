package com.example.concurso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class informacion_pantalla extends AppCompatActivity {
    ImageButton botoncito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_pantalla);
        botoncito=(ImageButton) findViewById(R.id.flecha);
        botoncito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botoncito=new Intent(informacion_pantalla.this,MainActivity.class);
                startActivity(botoncito);
            }
        });
    }
    public void RegresarInicio(View view){
        Intent intent=new Intent(informacion_pantalla.this,MainActivity.class);
        startActivity(intent);
    }
    public void RegresarContacto(View view){
        Intent intent=new Intent(informacion_pantalla.this,mostrar_contacto.class);
        startActivity(intent);
    }
    public void RegresarRecomendaciones(View view){
        Intent intent=new Intent(informacion_pantalla.this,MainActivity.class);
        startActivity(intent);
    }
}