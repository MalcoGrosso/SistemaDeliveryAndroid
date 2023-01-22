package com.mng.sistemadeliveryandroid.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class DetallePedido implements Serializable {

    private int idDetallePedido;
    private Usuario idUsuarioDP;
    private Producto idProductoDP;
    private Double precioPedido;
    private Pedido IdentificadorDetallePedido;



    public DetallePedido(){}
    public DetallePedido(int idDetallePedido, Usuario idUsuarioDP, Producto idProductoDP, Double precioPedido, Pedido IdentificadorDetallePedido ) {
        this.idDetallePedido = idDetallePedido;
        this.idUsuarioDP = idUsuarioDP;
        this.idProductoDP = idProductoDP;
        this.precioPedido = precioPedido;
        this.IdentificadorDetallePedido = IdentificadorDetallePedido;

    }


    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Usuario getIdUsuarioDP() {
        return idUsuarioDP;
    }

    public void setIdUsuarioDP(Usuario idUsuarioDP) {
        this.idUsuarioDP = idUsuarioDP;
    }

    public Producto getIdProductoDP() {
        return idProductoDP;
    }

    public void setIdProductoDP(Producto idProductoDP) {
        this.idProductoDP = idProductoDP;
    }

    public Double getPrecioPedido() {
        return precioPedido;
    }

    public void setPrecioPedido(Double precioPedido) {
        this.precioPedido = precioPedido;
    }

    public Pedido getIdentificadorDetallePedido() {
        return IdentificadorDetallePedido;
    }

    public void setIdentificadorDetallePedido(Pedido identificadorDetallePedido) {
        IdentificadorDetallePedido = identificadorDetallePedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePedido detallePedido = (DetallePedido) o;
        return idDetallePedido == detallePedido.idDetallePedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetallePedido);
    }


}
