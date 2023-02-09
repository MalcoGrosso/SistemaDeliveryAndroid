package com.mng.sistemadeliveryandroid.ui.Pago;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import android.view.View;

import com.mng.sistemadeliveryandroid.MenuPrincipal;
import com.mng.sistemadeliveryandroid.R;
import com.mng.sistemadeliveryandroid.modelo.Pago;
import com.mng.sistemadeliveryandroid.modelo.Pedido;
import com.mng.sistemadeliveryandroid.request.ApiRetrofit;
import com.mng.sistemadeliveryandroid.ui.home.HomeFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends AndroidViewModel {

    private MutableLiveData<Pago> pago;
    private MutableLiveData<String[]> tipoPago;
    private Context context;


    public PagoViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();

    }

    public MutableLiveData<String[]> getTipoPago(){
        if(tipoPago==null){
            tipoPago=new MutableLiveData<>();
        }


        return tipoPago;
    }

    public void obtenerTipoPago() {
        getTipoPago();
        String[] tp = new String[5];
        tp[0] = "Tarjeta Debito";
        tp[1] = "Tarjeta Credito";
        tp[2] = "Transferencia";
        tp[3] = "QR";
        tp[4] = "Efectivo";

        tipoPago.setValue(tp);

    }

    public void crearPago(Pago pago) {

        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String token = sp.getString("token", "-1");
        Call<Pago> cp = ApiRetrofit.getServiceSistemaDelivery().crearPago(token, pago);
        cp.enqueue(new Callback<Pago>() {
            @Override
            public void onResponse(Call<Pago> call, Response<Pago> response) {
                if (response.isSuccessful()) {


                } else {
                    Toast.makeText(context, "No se pudo guardar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pago> call, Throwable t) {
                Toast.makeText(context, "hubo un error inesperado" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void modificarPedidoUsuario2(Pedido pedido, View view) {

        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String token = sp.getString("token", "-1");
        Call<Pedido> mpu2 = ApiRetrofit.getServiceSistemaDelivery().modificarPedidoUsuario2(token, pedido);
        mpu2.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                if (response.isSuccessful()) {

                    Bundle bundle = new Bundle();
                    Navigation.findNavController(view).navigate(R.id.nav_home, bundle);
                    Toast.makeText(context, "Pedido Realizado Correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "No se pudo guardar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Toast.makeText(context, "hubo un error inesperado" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}