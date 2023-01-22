package com.mng.sistemadeliveryandroid.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class TipoPago implements Serializable {

    private int idTipoPago;
    private String metodo;
    private String nombrePagoD;



    public TipoPago(){}
    public TipoPago(int idTipoPago, String metodo, String nombrePagoD) {
        this.idTipoPago = idTipoPago;
        this.metodo = metodo;
        this.nombrePagoD = nombrePagoD;

    }


    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getNombrePagoD() {
        return nombrePagoD;
    }

    public void setNombrePagoD(String nombrePagoD) {
        this.nombrePagoD = nombrePagoD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoPago tipoPago = (TipoPago) o;
        return idTipoPago == tipoPago.idTipoPago;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoPago);
    }


}

