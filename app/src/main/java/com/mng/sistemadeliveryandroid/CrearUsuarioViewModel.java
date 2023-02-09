package com.mng.sistemadeliveryandroid;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mng.sistemadeliveryandroid.modelo.User;
import com.mng.sistemadeliveryandroid.modelo.Usuario;
import com.mng.sistemadeliveryandroid.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearUsuarioViewModel extends AndroidViewModel {

    private Context context;

    public CrearUsuarioViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();

    }

    public void crearUsuario(String nombre, String apellido, String direccion, String email, String password, String telefono){

        Usuario usuario = new Usuario(nombre, apellido, direccion, email, password, telefono);
            Call tokenPromesa = ApiRetrofit.getServiceSistemaDelivery().nuevo(usuario);
            Log.d("salida", tokenPromesa.toString());
            tokenPromesa.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    Log.d("salida", response.toString());
                    if (response.isSuccessful()) {

                        Toast.makeText(context.getApplicationContext(), "Usuario Creado Correctamente.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(context, LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);


                    } else {
                        Log.d("salida", "Crear Usuario sin respuesta");

                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(context, "Ocurrio un error en el servidor.", Toast.LENGTH_SHORT).show();

                }
            });

    }


}