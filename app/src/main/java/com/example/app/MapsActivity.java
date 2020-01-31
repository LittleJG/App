package com.example.app;

import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private DrawerLayout drawer;
    private GoogleMap mMap;

    //declara variaveis para pera

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng saoLeo = new LatLng( -29.7608, -51.1522);
        LatLng Poa = new LatLng(-30.0277, -51.2287);

        mMap.addMarker(new MarkerOptions().position(saoLeo).title("Marker in São Leopoldo").icon(
                BitmapDescriptorFactory.fromResource(R.drawable.icon_maps)));//adiciona uma imagem ao icone que aponta a licalização no mapa
        mMap.moveCamera(CameraUpdateFactory.newLatLng(saoLeo));

        mMap.addMarker(new MarkerOptions().position(Poa).title("Marker in Porto Alegre").icon(
                BitmapDescriptorFactory.fromResource(R.drawable.icon_maps)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Poa));

        //seta o zoo no mapa
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(saoLeo, 12.0f));

        //Adicona controle do Zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //Verifica se é permitido buscar a localização atual
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }else {//se ainda a permissão ainda não tenha sido concedida irá solicitar permissão
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            }
        }

        //adiciona uma linha reta entre pontos fixos
        mMap.addPolyline(new PolylineOptions()
                .add(saoLeo,Poa)
                .width(10)
                .color(Color.BLACK));

        //Adicona um circulo em torno da localização
        mMap.addCircle(
                new CircleOptions()
                        .center(saoLeo)//ponto de localização a ser circulado
                        .radius(1580.0)//metros ao redor do ponto de localização
                        .strokeWidth(3f)//espeçura da linha ao entorno do cirulo
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.argb(70,150,50,50))//alpha é a trasparencia do cirulo no mapa
        );

    }
}
