package com.example.concurso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button botoncito;
    Button botoncito2;
    BottomNavigationView barrita;
    SmsManager sms = SmsManager.getDefault();
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        barrita = findViewById(R.id.bottom_navigation);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this,ForegroundService.class);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            startForegroundService(intent);
        }else{
            startForegroundService(intent);
        }
        botoncito = (Button) findViewById(R.id.btnInfo);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 44);
        }
        botoncito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this,ForegroundService.class));
                Intent botoncito = new Intent(MainActivity.this, informacion_pantalla.class);
                startActivity(botoncito);
            }
        });
        botoncito2 = (Button) findViewById(R.id.btnAyuda);
        botoncito2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this,ForegroundService.class));
               Intent botoncito2 = new Intent(MainActivity.this, buscar_ayuda.class);
                startActivity(botoncito2);
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action, keycode;
        action = event.getAction();
        keycode = event.getKeyCode();
        switch (keycode) {
            case KeyEvent.KEYCODE_VOLUME_UP: {
                if (KeyEvent.ACTION_UP == action) {
                    checkSMSStatePermission();




                }
                break;
            }
        }
        return super.dispatchKeyEvent(event);

    }
    private void checkSMSStatePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para enviar SMS.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso para enviar SMS!");
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            getLocation();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public void RegresarContacto(View view){
        Intent intent=new Intent(MainActivity.this,mostrar_contacto.class);
        startActivity(intent);
    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        Double longitud = addresses.get(0).getLongitude();
                        Double latitud=addresses.get(0).getLatitude();
                        String mensaje3=addresses.get(0).getCountryName();
                        String mensaje4=addresses.get(0).getLocality();
                        String mensaje5=addresses.get(0).getAddressLine(0);
                        String mensaje=Double.toString(longitud);
                        String mensaje2=Double.toString(latitud);
                        String numero[]={"+51993170786","+51934690171","+51918764904"};
                        String text = "Ayudame!!!Estoy aqui "+mensaje5;
                        for (int i=0;i<numero.length;i++){
                            sms.sendTextMessage(numero[i], null, text, null, null);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}