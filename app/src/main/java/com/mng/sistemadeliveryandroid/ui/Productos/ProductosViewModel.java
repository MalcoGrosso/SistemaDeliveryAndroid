package com.mng.sistemadeliveryandroid.ui.Productos;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.mng.sistemadeliveryandroid.LoginActivity;
import com.mng.sistemadeliveryandroid.MainActivity;
import com.mng.sistemadeliveryandroid.R;
import com.mng.sistemadeliveryandroid.modelo.DetallePedido;
import com.mng.sistemadeliveryandroid.modelo.Pago;
import com.mng.sistemadeliveryandroid.modelo.Usuario;
import com.mng.sistemadeliveryandroid.modelo.Producto;
import com.mng.sistemadeliveryandroid.request.ApiRetrofit;
import com.mng.sistemadeliveryandroid.modelo.Pedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductosViewModel extends AndroidViewModel {
    private MutableLiveData<List<Producto>> productosMutable;
    private MutableLiveData<Pedido> pedidoMutable;
    private MutableLiveData<DetallePedido> detallePedidoMutable;
    private Context context;

    public ProductosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public LiveData<List<Producto>> getProductosMutable() {
        if (productosMutable == null) {
            productosMutable = new MutableLiveData<>();
        }
        return productosMutable;
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


    public void setProductos(){
        Usuario usuario;
        SharedPreferences sp = context.getSharedPreferences("token",0);
        String token = sp.getString("token","-1");
        Call<List<Producto>> tokenPromesa = ApiRetrofit.getServiceSistemaDelivery().obtenerProductosPedido(token);
        tokenPromesa.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                Log.d("salida", response.toString());
                if(response.isSuccessful()){

                    List<Producto> productos = response.body();
                    productosMutable.postValue(productos);
                }

            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void modificarPedidoUsuario(Pedido pedido) {

        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String token = sp.getString("token", "-1");
        Call<Pedido> inm = ApiRetrofit.getServiceSistemaDelivery().modificarPedidoUsuario(token, pedido);
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


}