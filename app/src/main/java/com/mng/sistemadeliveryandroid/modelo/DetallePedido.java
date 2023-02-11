package com.mng.sistemadeliveryandroid.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class DetallePedido implements Serializable {

    private int idDetallePedido;
    private Usuario usuario;
    private int idProductoDP;
    private Producto producto;
    private Double precioPedido;
    private int IdentificadorDetallePedido;
    private Pedido pedido;



    public DetallePedido(){}
    public DetallePedido(int idDetallePedido,  Usuario usuario, int idProductoDP, Producto producto, Double precioPedido, int IdentificadorDetallePedido, Pedido pedido ) {
        this.idDetallePedido = idDetallePedido;
        this.usuario = usuario;
        this.idProductoDP = idProductoDP;
        this.producto = producto;
        this.precioPedido = precioPedido;
        this.IdentificadorDetallePedido = IdentificadorDetallePedido;
        this.pedido = pedido;

    }

    public DetallePedido(int idDetallePedido,  int idProductoDP,  Double precioPedido, int IdentificadorDetallePedido) {
        this.idDetallePedido = idDetallePedido;
        this.idProductoDP = idProductoDP;
        this.precioPedido = precioPedido;
        this.IdentificadorDetallePedido = IdentificadorDetallePedido;


    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public int getIdProductoDP() {
        return idProductoDP;
    }

    public void setIdProductoDP(int idProductoDP) {
        this.idProductoDP = idProductoDP;
    }

    public Double getPrecioPedido() {
        return precioPedido;
    }

    public void setPrecioPedido(Double precioPedido) {
        this.precioPedido = precioPedido;
    }

    public int getIdentificadorDetallePedido() {
        return IdentificadorDetallePedido;
    }

    public void setIdentificadorDetallePedido(int identificadorDetallePedido) {
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
