package com.mng.sistemadeliveryandroid.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class Producto implements Serializable {

    private int idProducto;
    private String nombre;
    private String imagen;
    private double precioProducto;


    public Producto(){}
    public Producto(int idProducto, String nombre, String imagen, double precioProducto) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.imagen = imagen;
        this.precioProducto = precioProducto;
    }


    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto that = (Producto) o;
        return idProducto == that.idProducto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }


}

