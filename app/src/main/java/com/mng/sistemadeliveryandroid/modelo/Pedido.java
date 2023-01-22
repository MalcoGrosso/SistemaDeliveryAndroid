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
    private Usuario idUsuarioPedido;
    private String latitudPedido;
    private String longitudPedido;


    public Pedido() {}
    public Pedido(int idPedido, int idEmpleadoPedido, String fechaPedido, String fechaEntrega, int estado, Usuario idUsuarioPedido, String latitudPedido, String longitudPedido) {
        this.idPedido = idPedido;
        this.idEmpleadoPedido = idEmpleadoPedido;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.idUsuarioPedido = idUsuarioPedido;
        this.latitudPedido = latitudPedido;
        this.longitudPedido = longitudPedido;
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


    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Usuario getIdUsuarioPedido() {
        return idUsuarioPedido;
    }

    public void setIdUsuarioPedido(Usuario idUsuarioPedido) {
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
