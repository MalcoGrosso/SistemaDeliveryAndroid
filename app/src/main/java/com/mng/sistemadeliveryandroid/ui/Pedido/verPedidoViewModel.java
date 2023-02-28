package com.mng.sistemadeliveryandroid.ui.Pedido;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.mng.sistemadeliveryandroid.modelo.Pedido;
import com.mng.sistemadeliveryandroid.request.ApiRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class verPedidoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Pedido>> pedidosMutable;
    private Context context;

    public verPedidoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public LiveData<List<Pedido>> getVerPedidoMutable() {
        if (pedidosMutable == null) {
            pedidosMutable = new MutableLiveData<>();
        }
        return pedidosMutable;
    }

    public void setVerPedidos(){
        Pedido pedido;
        SharedPreferences svp = context.getSharedPreferences("token",0);
        String token = svp.getString("token","-1");
        Call<List<Pedido>> tokenPromesa = ApiRetrofit.getServiceSistemaDelivery().obtenerPedidos(token);
        tokenPromesa.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if(response.isSuccessful()){

                    List<Pedido> pedidos = response.body();
                    pedidosMutable.postValue(pedidos);;
                }

            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}