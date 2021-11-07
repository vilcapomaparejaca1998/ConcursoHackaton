package com.example.concurso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class formulario_contacto extends AppCompatActivity {
    //Variables generales
    private EditText txtNomC, txtNumC;
    private Button btnSeleccionar;
    private String nombreContacto, numeroContacto;
    ArrayList<Contacto> listaContacto = new ArrayList<>();
    private int idCont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_contacto);
        txtNomC = findViewById(R.id.txtNomc);
        txtNumC = findViewById(R.id.txtNomc);
        nombreContacto = getIntent().getStringExtra("nombreContacto");
        numeroContacto = getIntent().getStringExtra("numeroContacto");

        if(nombreContacto!=null && numeroContacto!=null){
            String archivos[] = fileList();
            String contactos[];
            String datoC[];
            if(ArchivoExiste(archivos, "Hi.txt")){
                try{
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

                    txtNomC.setText(nombreContacto);
                    txtNumC.setText(numeroContacto);
                    for(int i=0; i<listaContacto.size(); i++){
                        if(listaContacto.get(i).getNumero().equals(numeroContacto)){
                            idCont = i;
                        }
                    }
                }catch (IOException ex){
                }
            }

        }

        btnSeleccionar = findViewById(R.id.btnSeleccionarC);
        btnSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent --> Es una variable que nos permite interactuar con otras aplicaciones
                //ACTION PICK --> Nos permite seleccionar un elemento de nuestra lista de contactos
                Intent misContactos = new Intent(Intent.ACTION_PICK);
                //Con esto indicamos que nos queremos comunicar con los contactos que están guardados en el celular
                misContactos.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(misContactos, 1);//Este nos permite conocer la ubicación del contacto
            }
        });
    }
    //Metodo para el boton guardar
    public void Guardar(View leer){
        try {
            if(nombreContacto!=null && numeroContacto!=null){
                String archivos[] = fileList();
                if(ArchivoExiste(archivos, "Hi.txt")) {
                    //Eliminar archivo
                    deleteFile("Hi.txt");
                    //Escribimos en el texto
                    OutputStreamWriter escribirArchivo = new OutputStreamWriter(openFileOutput("Hi.txt", Activity.MODE_PRIVATE));
                    listaContacto.get(idCont).setNombre(txtNomC.getText().toString());
                    listaContacto.get(idCont).setNumero(txtNumC.getText().toString());
                    for (int i = 0; i < listaContacto.size(); i++) {
                        escribirArchivo.write(listaContacto.get(i).getNombre() + "α" + listaContacto.get(i).getNumero() + "Θ");
                    }
                    escribirArchivo.flush();
                    escribirArchivo.close();
                }
            } else {
                String contactosCompleto = "";
                String archivos[] = fileList();
                if (ArchivoExiste(archivos, "Hi.txt")) {
                    //Leer archivo
                    InputStreamReader abrirArchivo = new InputStreamReader(openFileInput("Hi.txt"));
                    BufferedReader br = new BufferedReader(abrirArchivo);
                    String linea = br.readLine();

                    while (linea != null) {
                        contactosCompleto = contactosCompleto + linea;
                        linea = br.readLine();
                    }
                    br.close();
                    abrirArchivo.close();
                }

                //Escribimos en el texto
                OutputStreamWriter escribirArchivo = new OutputStreamWriter(openFileOutput("Hi.txt", Activity.MODE_PRIVATE));
                escribirArchivo.write(contactosCompleto + txtNomC.getText().toString() + "α" + txtNumC.getText().toString() + "Θ");
                escribirArchivo.flush();
                escribirArchivo.close();
                Toast.makeText(this, "Contactos guardado correctamente", Toast.LENGTH_LONG).show();
            }

        }catch (IOException e){
        }

        //Nos redirecciona al layout MostrarCont
       // Intent mostrarCont = new Intent(MainActivity.this,MostrarCont.class);
        //startActivity(mostrarCont);
    }

    private boolean ArchivoExiste(String[] archivos, String nomArchivo){
        for(int i=0; i < archivos.length; i++){
            if(nomArchivo.equals(archivos[i])){
                return true;
            }
        }
        return false;
    }

}