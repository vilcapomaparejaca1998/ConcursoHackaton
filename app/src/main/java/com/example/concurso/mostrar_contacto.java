package com.example.concurso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.tabs.TabLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class mostrar_contacto extends AppCompatActivity {
    //Variables globales
    ArrayList<Contacto> listaContacto = new ArrayList<>();
    private TableLayout tabla;
    private TableRow fila;
    private TextView nomC, numC, setting;
    private Button remove, edit;
    private String numeroCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_contacto);
        String archivos[] = fileList();
        String contactos[];
        String datoC[];
        tabla = (TableLayout) findViewById(R.id.lista);

        if(ArchivoExiste(archivos, "Hi.txt")){
            try {
                InputStreamReader abrirArchivo = new InputStreamReader(openFileInput("Hi.txt"));
                BufferedReader br = new BufferedReader(abrirArchivo);
                String linea = br.readLine();
                String contactosCompleto = "";
                while (linea != null) {
                    contactosCompleto = contactosCompleto + linea;
                    linea = br.readLine();
                }
                br.close();
                abrirArchivo.close();
                if(contactosCompleto.length()>0){
                    contactos = contactosCompleto.split("Θ");
                    for (int i = 0; i < contactos.length; i++) {
                        datoC = contactos[i].split("α");
                        listaContacto.add(new Contacto(datoC[0], datoC[1]));
                    }
                }

                TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
                TableRow.LayoutParams nomContacto = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
                TableRow.LayoutParams configuracion = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

                //Tabla
                for(int i=-1; i<listaContacto.size(); i++){
                    fila = new TableRow(this);
                    fila.setLayoutParams(layoutFila);
                    if(i == -1){
                        nomC = new TextView(this);
                        nomC.setText("Contacto");
                        nomC.setWidth(350);
                        nomC.setGravity(Gravity.CENTER);
                        nomC.setBackgroundColor(Color.BLACK);
                        nomC.setTextColor(Color.WHITE);
                        nomC.setPadding(10,10,10,10);
                        nomC.setLayoutParams(nomContacto);
                        fila.addView(nomC);
                        setting = new TextView(this);
                        setting.setWidth(700);
                        setting.setText("Configuración");
                        setting.setGravity(Gravity.CENTER);
                        setting.setBackgroundColor(Color.BLACK);
                        setting.setTextColor(Color.WHITE);
                        setting.setPadding(10,10,10,10);
                        setting.setLayoutParams(configuracion);
                        fila.addView(setting);
                        tabla.addView(fila);
                    } else {
                        nomC = new TextView(this);
                        nomC.setMaxWidth(350);
                        nomC.setHeight(350);
                        nomC.setText(listaContacto.get(i).getNombre());
                        nomC.setPadding(10,10,10,10);
                        nomC.setLayoutParams(nomContacto);
                        fila.addView(nomC);
                        edit = new Button(this);
                        edit.setText("Editar");
                        edit.setId(i);
                        edit.setWidth(150);
                        edit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                modificarContacto(v);
                            }
                        });
                        fila.addView(edit);
                        remove = new Button(this);
                        remove.setText("Eliminar");
                        remove.setId(i);
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                eliminarArchivo(v);
                            }
                        });
                        fila.addView(remove);
                        tabla.addView(fila);
                    }
                }
                //====================================================================
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private boolean ArchivoExiste(String[] archivos, String nomArchivo){
        for(int i=0; i < archivos.length; i++){
            if(nomArchivo.equals(archivos[i])){
                return true;
            }
        }
        return false;
    }
    private void agregarUsuario(View view){
        Intent intent=new Intent(mostrar_contacto.this,formulario_contacto.class);
        startActivity(intent);
    }
    public void RegresarInicio(View view){
        Intent intent=new Intent(mostrar_contacto.this,MainActivity.class);
        startActivity(intent);
    }
    public void RegresarRecomendaciones(View view){
        Intent intent=new Intent(mostrar_contacto.this,MainActivity.class);
        startActivity(intent);
    }
    private void modificarContacto(View v){
        String nomC = listaContacto.get(v.getId()).getNombre();
        String numC = listaContacto.get(v.getId()).getNumero();

        Intent intent = new Intent(mostrar_contacto.this,MainActivity.class);
        intent.putExtra("nombreContacto",nomC);
        intent.putExtra("numeroContacto",numC);
        startActivity(intent);
    }

    private void eliminarArchivo(View v){
        listaContacto.remove(v.getId());
        try{
            String archivos[] = fileList();
            if(ArchivoExiste(archivos, "Hi.txt")){
                //Eliminar archivo
                deleteFile("Hi.txt");
                //Escribimos en el texto
                OutputStreamWriter escribirArchivo = new OutputStreamWriter(openFileOutput("Hi.txt", Activity.MODE_PRIVATE));
                for(int i=0; i < listaContacto.size();i++){
                    escribirArchivo.write(listaContacto.get(i).getNombre() + "α" + listaContacto.get(i).getNumero() +"Θ");
                }
                escribirArchivo.flush();
                escribirArchivo.close();

            }
        } catch (IOException ex) {
        }
    }
}