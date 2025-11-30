package com.unir.distancebetween;

import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.NonNull;

public class MinhaLocalizacao implements LocationListener {

    public static double latidude;
    public static  double longitude;

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latidude = location.getLatitude();
        longitude = location.getLongitude();
    }
}
