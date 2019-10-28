package com.example.uberapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class History {

    String UbicacionSalida;
    String UbicacionDestino;
    String FechaActual;

    public History(String UbiAct, String UbiDest) {
        this.UbicacionDestino = UbiAct;
        this.UbicacionSalida = UbiDest;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        this.FechaActual = dateFormat.format(date);
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
}
