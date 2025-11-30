package com.unir.distancebetween;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Ponto p1, p2;
    private String PROVIDER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        PROVIDER = LocationManager.GPS_PROVIDER;
        p1 = new Ponto();
        p2 = new Ponto();
    }

    public void reset(View v){
        p1 = new Ponto();
        p2 = new Ponto();
        TextView txtPontoA, txtPontB;
        txtPontoA = findViewById(R.id.textViewPontoA);
        txtPontB = findViewById(R.id.textViewPontoB);
        txtPontoA.setText("");
        txtPontB.setText("");
    }

    public void mostrarGoogleMaps(double latitude, double longitude){
        WebView wv = findViewById(R.id.webView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://www.google.com/maps/search/?api=1&query="
                + latitude + "," + longitude);
    }

    public void verPontoA(View v){
        mostrarGoogleMaps(p1.getLatitude(), p1.getLongitude());
    }

    public void verPontoB(View v){
        mostrarGoogleMaps(p2.getLatitude(), p2.getLongitude());
    }

    public void lerPontoA(View v){
        p1 = this.getPonto();
        TextView edtPto = findViewById(R.id.textViewPontoA);
        edtPto.setText(p1.imprimirFormatado());
    }

    public void lerPontoB(View v){
        p2 = this.getPonto();
        TextView edtPto = findViewById(R.id.textViewPontoB);
        edtPto.setText(p2.imprimirFormatado());
    }

    public Ponto getPonto(){
        LocationManager mLocManager = (LocationManager) getSystemService(MainActivity.this.LOCATION_SERVICE);
        LocationListener mLocListener = new MinhaLocalizacao();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return null;
        }

        mLocManager.requestLocationUpdates(PROVIDER, 0, 0, mLocListener);
        Location localAtual = mLocManager.getLastKnownLocation(PROVIDER);

        if (!mLocManager.isProviderEnabled(PROVIDER)) {
            Toast.makeText(MainActivity.this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show();
        }

        return new Ponto(localAtual.getLatitude(), localAtual.getLongitude(), localAtual.getAltitude());
    }

    public void calcularDistancia(View v) {
        LocationManager mLocManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        float[] resultado = new float[1];
        Location.distanceBetween(p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude(), resultado);

        if (mLocManager.isProviderEnabled(PROVIDER)) {
            String texto = "*** Distância: " + String.format("%.2f",resultado[0]/1000) + " Km\n";
            Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show();
        }
    }




}