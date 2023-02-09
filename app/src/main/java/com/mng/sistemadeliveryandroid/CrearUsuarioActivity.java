package com.mng.sistemadeliveryandroid;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.mng.sistemadeliveryandroid.modelo.Usuario;

public class CrearUsuarioActivity extends AppCompatActivity {

    private CrearUsuarioViewModel vmCrearUsuario;
    private EditText etId,etNombre,etApellido,etDireccion,etEmail,etTelefono,etPassword;
    private Button btAccion;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearusuario);
        context = this.getApplicationContext();
        vmCrearUsuario = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
                .create(CrearUsuarioViewModel.class);
        inicializarVista();


    }


    public void inicializarVista(){
        etId= findViewById(R.id.etId);
        etNombre = findViewById(R.id.etNombre);
        etApellido= findViewById(R.id.etApellido);
        etDireccion= findViewById(R.id.etDireccion);
        etTelefono= findViewById(R.id.etTelefono);
        etEmail= findViewById(R.id.etEmail);
        etPassword= findViewById(R.id.etPassword);
        btAccion= findViewById(R.id.btCrearUsuario);
        btAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = etNombre.getText().toString();
                String apellido =  etApellido.getText().toString();
                String direccion =  etDireccion.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String telefono = etTelefono.getText().toString();
                vmCrearUsuario.crearUsuario(nombre,apellido,direccion,email,password,telefono);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        etNombre.setText("");
        etApellido.setText("");
        etDireccion.setText("");
        etTelefono.setText("");
        etEmail.setText("");
        etPassword.setText("");

    }

}