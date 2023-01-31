package com.mng.sistemadeliveryandroid.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Pedido implements Serializable {

    private int idPedido;
    private int idEmpleadoPedido;
    private String fechaPedido;
    private String fechaEntrega;
    private int estado;
    private int idUsuarioPedido;
    private Usuario usuario;
    private String latitudPedido;
    private String longitudPedido;
    private double montoFinal;


    public Pedido() {}
    public Pedido(int idPedido, int idEmpleadoPedido, String fechaPedido, String fechaEntrega, int estado, int idUsuarioPedido, Usuario usuario, String latitudPedido, String longitudPedido, double montoFinal) {
        this.idPedido = idPedido;
        this.idEmpleadoPedido = idEmpleadoPedido;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.idUsuarioPedido = idUsuarioPedido;
        this.usuario = usuario;
        this.latitudPedido = latitudPedido;
        this.longitudPedido = longitudPedido;
        this.montoFinal = montoFinal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdEmpleadoPedido() {
        return idEmpleadoPedido;
    }

    public void setIdEmpleadoPedido(int idEmpleadoPedido) {
        this.idEmpleadoPedido = idEmpleadoPedido;
    }

    public String getFechaPedido() {
        String dia="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d = dateFormat.parse(fechaPedido);

            dia = formato.format(d);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return dia;
    }


    public String getFechaEntrega() {
        String dia="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d = dateFormat.parse(fechaEntrega);

            dia = formato.format(d);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return dia;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdUsuarioPedido() {
        return idUsuarioPedido;
    }

    public void setIdUsuarioPedido(int idUsuarioPedido) {
        this.idUsuarioPedido = idUsuarioPedido;
    }

    public String getLatitudPedido() {
        return latitudPedido;
    }

    public void setLatitudPedido(String latitudPedido) {
        this.latitudPedido = latitudPedido;
    }

    public String getLongitudPedido() {
        return longitudPedido;
    }

    public void setLongitudPedido(String longitudPedido) {
        this.longitudPedido = longitudPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return idPedido == pedido.idPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido);
    }
}
