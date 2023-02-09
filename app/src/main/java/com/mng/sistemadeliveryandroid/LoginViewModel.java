package com.mng.sistemadeliveryandroid;

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

import com.mng.sistemadeliveryandroid.modelo.User;
import com.mng.sistemadeliveryandroid.request.ApiRetrofit;
import com.mng.sistemadeliveryandroid.modelo.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> error_visibility;
    private Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Integer> getErrorVisibility() {
        if (error_visibility == null) { error_visibility = new MutableLiveData<>(); }
        return error_visibility;
    }
    public void login(String email, String password) {

        User usuario = new User(email, password);
        Log.d("salida",usuario.getEmail());
        Call<String> tokenPromesa = ApiRetrofit.getServiceSistemaDelivery().login(usuario);
        Log.d("salida",tokenPromesa.toString());
        tokenPromesa.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String token = response.body();
                    SharedPreferences sharedP = context.getSharedPreferences("token", 0);
                    SharedPreferences.Editor editor = sharedP.edit();
                    editor.putString("token", "Bearer " + token);
                    editor.commit();

                    Intent i = new Intent(context, MenuPrincipal.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);


                }else{
                    Toast.makeText(context, "Email o Password Incorrecto", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error en el servidor.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void restablecerPassword(String email){
        Call<Usuario> reestablecerPasswordPromesa = ApiRetrofit.getServiceSistemaDelivery().emailPedido(email);
        reestablecerPasswordPromesa.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "Se envio email para reestablecer la contraseña.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Sin respuesta.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error en el servidor.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void crearUsuario() {
        Intent i = new Intent(context, CrearUsuarioActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }





}
