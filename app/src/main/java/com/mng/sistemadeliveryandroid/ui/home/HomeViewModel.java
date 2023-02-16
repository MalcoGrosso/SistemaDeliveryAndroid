package com.mng.sistemadeliveryandroid.ui.home;

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
import com.mng.sistemadeliveryandroid.modelo.Producto;
import com.mng.sistemadeliveryandroid.modelo.Usuario;
import com.mng.sistemadeliveryandroid.request.ApiRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<LeerMapa> mutableLeerMapa;
    private MutableLiveData<List<Producto>> productosMutable;


    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();
    }
    public LiveData<LeerMapa> getMutableLeerMapa(){
        if(mutableLeerMapa==null){
            mutableLeerMapa=new MutableLiveData<>();
        }
        return mutableLeerMapa;
    }

    public void leerMapa(){

        mutableLeerMapa.setValue(new LeerMapa(context));
    }

    public LiveData<List<Producto>> getProductosMutable() {
        if (productosMutable == null) {
            productosMutable = new MutableLiveData<>();
        }
        return productosMutable;
    }

    public void crearPedido(Pedido pedido) {

        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String token = sp.getString("token", "-1");
        Call<Pedido> inm = ApiRetrofit.getServiceSistemaDelivery().crearPedido(token, pedido);
        inm.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                if (response.isSuccessful()) {

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

    public String obtenerLatitud(){

        SharedPreferences sp = context.getSharedPreferences("lati", 0);
        String latitud = sp.getString("lati", "-1");
        return  latitud;
    }

    public String obtenerLongitud(){

        SharedPreferences sp = context.getSharedPreferences("longi", 0);
        String longitud = sp.getString("longi", "-1");
        return  longitud;
    }


}