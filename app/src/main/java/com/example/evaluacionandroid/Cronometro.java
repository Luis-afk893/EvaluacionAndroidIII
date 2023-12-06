package com.example.evaluacionandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Cronometro extends AppCompatActivity{
    private TextView textViewTiempo;
    private Button buttonIniciar;
    private Button buttonPausar;
    private Button buttonReanudar;
    private Button buttonResetear;
    private boolean cronometroEnEjecucion = false;
    private long tiempoTranscurrido = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        textViewTiempo = findViewById(R.id.textViewTiempo);
        buttonIniciar = findViewById(R.id.buttonIniciar);
        buttonPausar = findViewById(R.id.buttonDetener);
        buttonReanudar = findViewById(R.id.buttonReanudar);
        buttonResetear = findViewById(R.id.buttonResetear);
        buttonPausar.setVisibility(View.GONE);
        buttonReanudar.setVisibility(View.GONE);
        buttonResetear.setVisibility(View.GONE);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                textViewTiempo.setText(formatoTiempo(tiempoTranscurrido));
                return true;
            }
        });
        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarCronometro();
                buttonIniciar.setVisibility(View.GONE);
                buttonPausar.setVisibility(View.VISIBLE);
                buttonResetear.setVisibility(View.GONE);
            }
        });
        buttonPausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausarCronometro();
                buttonPausar.setVisibility(View.GONE);
                buttonReanudar.setVisibility(View.VISIBLE);
                buttonResetear.setVisibility(View.VISIBLE);
            }
        });
        buttonReanudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reanudarCronometro();
                buttonPausar.setVisibility(View.VISIBLE);
                buttonReanudar.setVisibility(View.GONE);
                buttonResetear.setVisibility(View.GONE);
            }
        });
        buttonResetear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetearCronometro();
                buttonIniciar.setVisibility(View.VISIBLE);
                buttonPausar.setVisibility(View.GONE);
                buttonReanudar.setVisibility(View.GONE);
                buttonResetear.setVisibility(View.GONE);
            }
        });
    }
    private void iniciarCronometro() {
        if (!cronometroEnEjecucion) {
            cronometroEnEjecucion = true;
            tiempoTranscurrido = 0;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (cronometroEnEjecucion) {
                        try {
                            Thread.sleep(1000);
                            tiempoTranscurrido += 1000;
                            handler.sendEmptyMessage(0);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
    private void pausarCronometro() {
        cronometroEnEjecucion = false;
    }
    private void reanudarCronometro() {
        if (!cronometroEnEjecucion) {
            cronometroEnEjecucion = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (cronometroEnEjecucion) {
                        try {
                            Thread.sleep(1000);
                            tiempoTranscurrido += 1000;
                            handler.sendEmptyMessage(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
    private void resetearCronometro() {
        cronometroEnEjecucion = false;
        tiempoTranscurrido = 0;
        handler.sendEmptyMessage(0);
    }
    private String formatoTiempo(long milisegundos) {
        int segundos = (int) (milisegundos / 1000);
        int minutos = segundos / 60;
        segundos = segundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }
    public void regresar(View view) {
        Intent siguiente = new Intent(this, Portada.class);
        startActivity(siguiente);
    }
    public void Pantalla2(View view) {
        Intent intent = new Intent(this, Sensor.class);
        startActivity(intent);
    }
}
