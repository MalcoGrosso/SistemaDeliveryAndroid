package com.mng.sistemadeliveryandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.mng.sistemadeliveryandroid.databinding.MenuPrincipalBinding;
import com.mng.sistemadeliveryandroid.modelo.Usuario;
import com.mng.sistemadeliveryandroid.request.ApiRetrofit;
import com.mng.sistemadeliveryandroid.ui.logout.LogoutFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuPrincipal extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private MenuPrincipalBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MenuPrincipalBinding.inflate(getLayoutInflater());
        context = this.getApplicationContext();
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
      /*  binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        setHeader(navigationView);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                    || super.onSupportNavigateUp();
    }
    private void setHeader(NavigationView navigationView) {

        View header = navigationView.getHeaderView(0);
        TextView nombre = header.findViewById(R.id.tvNombre);
        TextView email = header.findViewById(R.id.tvEmail);
        TextView direccion = header.findViewById(R.id.tvDireccion);
        TextView telefono = header.findViewById(R.id.tvTelefono);
        SharedPreferences sp= navigationView.getContext().getSharedPreferences("token",0);
        String token =sp.getString("token","");

        Call<Usuario> UsuarioPerfil = ApiRetrofit.getServiceSistemaDelivery().obtenerPerfil(token);
        UsuarioPerfil.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    Usuario usu = response.body();

                    nombre.setText(usu.getNombre());
                    email.setText(usu.getEmail());
                    direccion.setText(usu.getDireccion());
                    telefono.setText(usu.getTelefono());

                }

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        MenuPrincipal.this.finish();

    }
}