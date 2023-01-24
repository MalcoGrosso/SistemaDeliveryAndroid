package com.mng.sistemadeliveryandroid.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class LeerMapa implements OnMapReadyCallback {
    private LatLng delivery;
    private Context context;
    private GoogleMap map;



    public LeerMapa(Context context) {

        this.delivery= new LatLng( -33.223820,-66.228090);
        this.context= context;
    }

    @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            map= googleMap;
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            Marker marcadorDelivery=googleMap.addMarker(new MarkerOptions().position(delivery));
            marcadorDelivery.setTitle("Delivery");
            marcadorDelivery.setPosition(delivery);
            obtenerUltimaUbicacion();
        }
    private void obtenerUltimaUbicacion() {
        FusedLocationProviderClient fl= LocationServices.getFusedLocationProviderClient(context);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fl.getLastLocation().addOnSuccessListener(context.getMainExecutor(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng ua= new LatLng(location.getLatitude(),location.getLongitude());
                    double lati = ua.latitude;
                    double longi = ua.longitude;
                    SharedPreferences latitud = context.getSharedPreferences("lati", 0);
                    SharedPreferences.Editor editor = latitud.edit();
                    editor.putString("lati", String.valueOf(lati));
                    editor.commit();
                    SharedPreferences longitud = context.getSharedPreferences("longi", 0);
                    SharedPreferences.Editor edi = longitud.edit();
                    edi.putString("longi", String.valueOf(longi));
                    edi.commit();

                    map.addMarker(new MarkerOptions().position(ua))

                            .setTitle("Mi ubicacion");
                    CameraPosition camPos= new CameraPosition.Builder()
                            .target(ua)
                            .zoom(15)
                            .bearing(45)
                            .tilt(40)
                            .build();
                    CameraUpdate caUpdate= CameraUpdateFactory.newCameraPosition(camPos);
                    map.animateCamera(caUpdate);

                }
            }
        });
    }

    }

