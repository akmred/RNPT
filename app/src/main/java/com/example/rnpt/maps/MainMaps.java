package com.example.rnpt.maps;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rnpt.R;
import com.example.rnpt.fragments.Fragment_settings;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

public class MainMaps {
    private static final int PERMISSION_REQUEST_CODE = 10;
    Activity activity;
    TextView textViewLocate;

    public  MainMaps(Activity activity, TextView textViewLocate){

        this.activity = activity;
        this.textViewLocate = textViewLocate;

        requestPemissions();
        createApiClient();

    }

    private void createApiClient() {
    }

    private void requestPemissions() {
        // Проверим на пермиссии, и если их нет, запросим у пользователя
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            requestLocation();
        }else {
            requestLocationPermissions();
        }

    }

    // запрос координат
    private void requestLocation() {
        // Если пермиссии все таки нет - то просто выйдем, приложение не имеет смысла
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        // Получить менеджер геолокации
        LocationManager locationManager = (LocationManager) activity.getSystemService(LOCATION_SERVICE);
        // Будем получать геоположение каждые 10 секунд или каждые 10 метров
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double lat = location.getLatitude(); // Широта
                double lng = location.getLongitude(); // Долгота
                //
                LatLng currentPosition = new LatLng(lat, lng);
                getAddress(currentPosition);

//                Fragment_settings fragment_settings = (Fragment_settings) activityFragment.findFragmentById(R.id.context_main);
//                fragment_settings.onLocate(lat, lng);


            }

            // Получаем адрес по координатам
            private void getAddress(final LatLng location){
                final Geocoder geocoder = new Geocoder(activity);
                // Поскольку geocoder работает по интернету, создадим отдельный поток
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final List<Address> addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);

                            textViewLocate.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewLocate.setText(addresses.get(0).getAddressLine(0));
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }


            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });


    }

    private void requestLocationPermissions() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Запросим эти две пермиссии у ползователя
            ActivityCompat.requestPermissions(activity,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    PERMISSION_REQUEST_CODE);
        }
    }


}
