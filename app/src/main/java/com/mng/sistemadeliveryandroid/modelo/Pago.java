package com.mng.sistemadeliveryandroid.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class Pago implements Serializable {

    private int idPago;
    private Usuario Usuario;
    private int idUsuarioPago;
    private int idEmpleadoPago;
    private Pedido Pedido;
    private int idPedidoPago;
    private String FechaPago;
    private TipoPago TipoPago;
    private int idTipoPagoP;



    public Pago(){}
    public Pago(int idPago, Usuario Usuario, int idUsuarioPago, int idEmpleadoPago, Pedido Pedido, int idPedidoPago,  String fechaPago, TipoPago TipoPago, int idTipoPagoP) {
        this.idPago = idPago;
        this.Usuario = Usuario;
        this.idUsuarioPago = idUsuarioPago;
        this.idEmpleadoPago = idEmpleadoPago;
        this.Pedido = Pedido;
        this.idPedidoPago = idPedidoPago;
        this.FechaPago = fechaPago;
        this.TipoPago = TipoPago;
        this.idTipoPagoP = idTipoPagoP;
    }


    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public com.mng.sistemadeliveryandroid.modelo.Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(com.mng.sistemadeliveryandroid.modelo.Usuario usuario) {
        Usuario = usuario;
    }

    public int getIdUsuarioPago() {
        return idUsuarioPago;
    }

    public void setIdUsuarioPago(int idUsuarioPago) {
        this.idUsuarioPago = idUsuarioPago;
    }

    public com.mng.sistemadeliveryandroid.modelo.Pedido getPedido() {
        return Pedido;
    }

    public void setPedido(com.mng.sistemadeliveryandroid.modelo.Pedido pedido) {
        Pedido = pedido;
    }

    public int getIdPedidoPago() {
        return idPedidoPago;
    }

    public void setIdPedidoPago(int idPedidoPago) {
        this.idPedidoPago = idPedidoPago;
    }

    public void setFechaPago(String fechaPago) {
        FechaPago = fechaPago;
    }

    public com.mng.sistemadeliveryandroid.modelo.TipoPago getTipoPago() {
        return TipoPago;
    }

    public void setTipoPago(com.mng.sistemadeliveryandroid.modelo.TipoPago tipoPago) {
        TipoPago = tipoPago;
    }

    public int getIdTipoPagoP() {
        return idTipoPagoP;
    }

    public void setIdTipoPagoP(int idTipoPagoP) {
        this.idTipoPagoP = idTipoPagoP;
    }

    public int getIdEmpleadoPago() {
        return idEmpleadoPago;
    }

    public void setIdEmpleadoPago(int idEmpleadoPago) {
        this.idEmpleadoPago = idEmpleadoPago;
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
