package com.example.evaluacionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Sensor extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private android.hardware.Sensor sensorAcelerometro;
    private RelativeLayout layoutPrincipal;
    private long ultimaActualizacion = 0;
    private float ultimaX, ultimaY, ultimaZ;
    private static final int INTERVALO_ACTUALIZACION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        layoutPrincipal = findViewById(R.id.layoutPrincipal);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorAcelerometro = sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER);

        if (sensorAcelerometro != null) {
            sensorManager.registerListener((SensorEventListener) this, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long tiempoActual = System.currentTimeMillis();

        if ((tiempoActual - ultimaActualizacion) > INTERVALO_ACTUALIZACION) {
            ultimaActualizacion = tiempoActual;

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float aceleracion = Math.abs(x + y + z - ultimaX - ultimaY - ultimaZ) / INTERVALO_ACTUALIZACION * 10000;

            if (aceleracion > 1500) {
                int colorAleatorio = Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
                layoutPrincipal.setBackgroundColor(colorAleatorio);
            }

            ultimaX = x;
            ultimaY = y;
            ultimaZ = z;
        }
    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorAcelerometro != null) {
            sensorManager.registerListener(this, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    public void regresar(View view) {
        Intent siguiente = new Intent(this, Portada.class);
        startActivity(siguiente);
    }
    public void Pantalla3(View view) {
        Intent intent = new Intent(this, Sensor.class);
        startActivity(intent);
    }
}
