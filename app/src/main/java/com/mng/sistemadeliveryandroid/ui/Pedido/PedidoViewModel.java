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
import androidx.lifecycle.ViewModel;

import com.mng.sistemadeliveryandroid.modelo.Pedido;
import com.mng.sistemadeliveryandroid.modelo.Producto;
import com.mng.sistemadeliveryandroid.modelo.Usuario;
import com.mng.sistemadeliveryandroid.request.ApiRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidoViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<Pedido> mPedido ;
    private Pedido ped;
    private Context context;


    public PedidoViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();
    }

    public LiveData<Pedido> getPedido(){
        if(mPedido==null){
            mPedido=new MutableLiveData<>();
        }
        return mPedido;
    }

    public void obtenerPedido(){
        SharedPreferences sp = context.getSharedPreferences("token",0);
        String token = sp.getString("token","-1");
        Call<Pedido> op = ApiRetrofit.getServiceSistemaDelivery().obtenerPedido(token);
        op.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                if(response.isSuccessful()){
                    Pedido pedido = response.body();

                    mPedido.postValue(pedido);
                }

            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }




}



