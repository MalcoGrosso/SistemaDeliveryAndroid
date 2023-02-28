package com.mng.sistemadeliveryandroid.ui.Pedido;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;



import com.mng.sistemadeliveryandroid.modelo.DetallePedido;
import com.mng.sistemadeliveryandroid.modelo.Pedido;
import com.mng.sistemadeliveryandroid.request.ApiRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class verPedidoDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<List<DetallePedido>> detallePedidoMutable;
    private Context context;
    private Pedido pedi;

    public verPedidoDetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public LiveData<List<DetallePedido>> getInmueblesMutable() {
        if (detallePedidoMutable == null) {
            detallePedidoMutable = new MutableLiveData<>();
        }
        return detallePedidoMutable;
    }

    public void setInmuebles(Bundle bundle){
        pedi = (Pedido) bundle.getSerializable("pedido");
        SharedPreferences si = context.getSharedPreferences("token",0);
        String token = si.getString("token","-1");
        Call<List<DetallePedido>> tokenPromesa = ApiRetrofit.getServiceSistemaDelivery().obtenerVerPedidoDetalle(token, pedi.getIdPedido());
        tokenPromesa.enqueue(new Callback<List<DetallePedido>>() {
            @Override
            public void onResponse(Call<List<DetallePedido>> call, Response<List<DetallePedido>> response) {
                if(response.isSuccessful()){

                    List<DetallePedido> dp = response.body();
                    detallePedidoMutable.postValue(dp);;
                }

            }

            @Override
            public void onFailure(Call<List<DetallePedido>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}