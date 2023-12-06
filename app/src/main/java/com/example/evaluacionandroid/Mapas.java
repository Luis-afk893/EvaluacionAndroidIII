package com.example.evaluacionandroid;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapas extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    EditText txtLatitud, txtLongitud;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);

        txtLatitud = findViewById(R.id.textLatitud);
        txtLongitud = findViewById(R.id.textLongitud);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapClickListener(this);

        LatLng chile = new LatLng(-33.500407,-70.7020207);
        mMap.addMarker(new MarkerOptions().position(chile).title("Chile"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(chile));
    }
    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText(""+latLng.latitude);
        txtLongitud.setText(""+latLng.longitude);
    }
    public void regresar(View view) {
        Intent siguiente = new Intent(this, Portada.class);
        startActivity(siguiente);
    }
    }


