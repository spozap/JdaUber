package com.example.uberapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class History {

    String pasajero;
    String conductor;
    String UbicacionSalida;
    String UbicacionDestino;
    String FechaActual;
    String km;

    public History(String conductor , String pasajero , String UbiAct, String UbiDest,String km) {
        this.UbicacionDestino = UbiAct;
        this.UbicacionSalida = UbiDest;
        this.conductor = conductor;
        this.pasajero = pasajero;
        this.km = km;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        this.FechaActual = dateFormat.format(date);
    }

    public History(){

    }

    public String getUbicacionSalida() {
        return UbicacionSalida;
    }

    public void setUbicacionSalida(String ubicacionSalida) {
        UbicacionSalida = ubicacionSalida;
    }

    public String getUbicacionDestino() {
        return UbicacionDestino;
    }

    public void setUbicacionDestino(String ubicacionDestino) {
        UbicacionDestino = ubicacionDestino;
    }

    public String getFechaActual() {
        return FechaActual;
    }

    public void setFechaActual(String fechaActual) {
        FechaActual = fechaActual;
    }

    public String getPasajero() {
        return pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }
}
