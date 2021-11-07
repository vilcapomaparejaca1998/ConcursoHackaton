package com.example.concurso;
import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Service;

public class ServicioSP extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {

    }
    @Override
    public int onStartCommand(Intent intent, int flag, int idProcess) {

        //Insertar codigo que deseas que se ejecute en segundo plano
        Toast.makeText(this, "Servicio Iniciado", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        //Insertar codigo que permite detener el servicio
        Toast.makeText(this, "Servicio detenido", Toast.LENGTH_LONG).show();
    }

}
