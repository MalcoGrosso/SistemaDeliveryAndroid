package com.mng.sistemadeliveryandroid.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class Pago implements Serializable {

    private int idPago;
    private Usuario idUsuarioPago;
    private int idEmpleadoPago;
    private Pedido idPedidoPago;
    private int estadoPago;
    private String FechaPago;
    private TipoPago idTipoPagoP;



    public Pago(){}
    public Pago(int idPago, Usuario idUsuarioPago, int idEmpleadoPago, Pedido idPedidoPago, int estadoPago, String fechaPago, TipoPago idTipoPagoP) {
        this.idPago = idPago;
        this.idUsuarioPago = idUsuarioPago;
        this.idEmpleadoPago = idEmpleadoPago;
        this.idPedidoPago = idPedidoPago;
        this.estadoPago  = estadoPago;
        this.FechaPago = fechaPago;
        this.idTipoPagoP = idTipoPagoP;
    }


    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Usuario getIdUsuarioPago() {
        return idUsuarioPago;
    }

    public void setIdUsuarioPago(Usuario idUsuarioPago) {
        this.idUsuarioPago = idUsuarioPago;
    }

    public int getIdEmpleadoPago() {
        return idEmpleadoPago;
    }

    public void setIdEmpleadoPago(int idEmpleadoPago) {
        this.idEmpleadoPago = idEmpleadoPago;
    }

    public Pedido getIdPedidoPago() {
        return idPedidoPago;
    }

    public void setIdPedidoPago(Pedido idPedidoPago) {
        this.idPedidoPago = idPedidoPago;
    }

    public int getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(int estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getFechaPago() {
        String dia="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d = dateFormat.parse(getFechaPago());

            dia = formato.format(d);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return dia;
    }

    public void setFechaPago(){
        this.FechaPago = FechaPago;
    }


    public TipoPago getIdTipoPagoP() {
        return idTipoPagoP;
    }

    public void setIdTipoPagoP(TipoPago idTipoPagoP) {
        this.idTipoPagoP = idTipoPagoP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pago pago = (Pago) o;
        return idPago == pago.idPago;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPago);
    }


}
