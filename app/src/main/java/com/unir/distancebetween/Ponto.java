package com.unir.distancebetween;

public class Ponto {

    private double latitude;
    private double longitude;
    private double altitude;

    public Ponto(){
        this.latitude = 0;
        this.longitude = 0;
        this.altitude = 0;
    }
    public Ponto(double latitude, double longitude, double altitude) {
        this();
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public Ponto(double latitude, double longitude) {
        this();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String imprimir(){
        String aux = "Latitude: " + String.format("%.2f",this.latitude) + "\n";
        aux += "Longitude: " + String.format("%.2f",this.longitude) + "\n";
        aux += "Altitude: " + String.format("%.2f", this.altitude) + "\n";
        return aux;
    }

    public String imprimirFormatado(){
        String aux = "**************************\n"+
                "Latitude: " + String.format("%.2f",this.latitude) + "\n" +
                "Longitude: " + String.format("%.2f",this.longitude) + "\n" +
                "Altitude: " + String.format("%.2f", this.altitude) + "\n" +
                "**************************\n";
        return aux;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
