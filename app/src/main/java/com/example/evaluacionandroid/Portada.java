package com.example.evaluacionandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Portada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

    }
    public void Pantalla2(View view) {
        Intent intent = new Intent(this, Cronometro.class);
        startActivity(intent);
    }
    public void Pantalla3(View view) {
        Intent intent = new Intent(this, Sensor.class);
        startActivity(intent);
    }
    public void Pantalla4(View view) {
        Intent intent = new Intent(this, Mapas.class);
        startActivity(intent);
    }
}
