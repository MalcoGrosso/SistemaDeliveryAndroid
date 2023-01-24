package com.mng.sistemadeliveryandroid.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mng.sistemadeliveryandroid.request.ApiRetrofit;
import com.mng.sistemadeliveryandroid.modelo.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Usuario> mUsuario;
    private MutableLiveData<String> textoBoton;
    private MutableLiveData<Boolean> usuarioEnable;
    private ApiRetrofit api;

 //   private ApiClient api= ApiClient.getApi();

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();

    }
    public LiveData<Usuario> getMutableUsuario(){
        if(mUsuario==null){
            mUsuario=new MutableLiveData<>();
        }
        return mUsuario;
    }
    public LiveData<Boolean> getMutableUsuarioEnable(){
        if(usuarioEnable==null){
            usuarioEnable=new MutableLiveData<>();
        }
        return usuarioEnable;
    }
    public LiveData<String> getMutableTextoBoton(){
        if(textoBoton==null){
            textoBoton=new MutableLiveData<>();
        }
        return textoBoton;
    }


    private String ObtenerToken(){
        SharedPreferences sp= context.getSharedPreferences("token",0);
        String token=sp.getString("token","-1");
        return token;

    };

    public void ObtenerUsuario(){
        // Propietario p= api.obtenerUsuarioActual();


        Usuario usuario;

        SharedPreferences sp= context.getSharedPreferences("token",0);
        String token=sp.getString("token","-1");
        Call<Usuario> tokenPromesa = ApiRetrofit.getServiceSistemaDelivery().obtenerPerfil(token);
        tokenPromesa.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Log.d("salida", response.toString());
                if (response.isSuccessful()) {

                    Usuario usuario = response.body();

                    mUsuario.setValue(usuario);

                } else {
                    Log.d("salida", "Usuario sin respuesta");

                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("salida ", t.getMessage());

            }
        });

        // mPropietario.setValue(p);

    }

    public void actualizarPerfil(@NonNull String boton, Usuario p){
        if(boton.equals("GUARDAR"))
        {

            Usuario usuario;

            SharedPreferences sp= context.getSharedPreferences("token",0);
            String token=sp.getString("token","-1");
            Call tokenPromesa = ApiRetrofit.getServiceSistemaDelivery().actualizarPerfil(token,p);
            Log.d("salida", tokenPromesa.toString());
            tokenPromesa.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    Log.d("salida", response.toString());
                    if (response.isSuccessful()) {

                        Usuario usuario = response.body();

                        Toast.makeText(context.getApplicationContext(), "Usuario Actualizado Correctamente.", Toast.LENGTH_SHORT).show();
                        mUsuario.setValue(usuario);

                    } else {
                        Log.d("salida", "Actualizar Usuario sin respuesta");

                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.d("salida ", t.getMessage());

                }
            });
            usuarioEnable.setValue(false);
            textoBoton.setValue("EDITAR");
        }
        else{
            usuarioEnable.setValue(true);
            textoBoton.setValue("GUARDAR");
        }
    }

}