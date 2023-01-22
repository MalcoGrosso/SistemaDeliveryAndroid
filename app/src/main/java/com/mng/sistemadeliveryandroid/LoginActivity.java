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

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;
    private EditText etMail, etPassword;
    private Button btLogin;
    private Button btRestablecer;
    private Button btCrearUsuario;
    private TextView tvError;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this.getApplicationContext();
        solicitarPermisos();
        initializeViews();
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
                .create(LoginViewModel.class);
        viewModel.getErrorVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {
                tvError.setVisibility(visibility);
            }
        });

        //Button listener
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etMail.getText().toString().equals("") || etMail.getText().toString()==null ){
                    Toast.makeText(context, "El campo e-mail no debe ser vacio.", Toast.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().equals("") || etPassword.getText().toString()==null ){
                    Toast.makeText(context, "El campo Password no debe ser vacio.", Toast.LENGTH_SHORT).show();
                } else{
                viewModel.login(etMail.getText().toString(),etPassword.getText().toString());
                }
            }
        });

        btCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.crearUsuario();
            }
        });

        btRestablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etMail.getText().toString().equals("") || etMail.getText().toString()==null ){
                    Toast.makeText(context, "El campo e-mail no debe ser vacio.", Toast.LENGTH_SHORT).show();
                }
                else{
                    viewModel.restablecerPassword(etMail.getText().toString());
                }
            }
        });

        etMail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                tvError.setVisibility(View.INVISIBLE);
            }
        });
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                tvError.setVisibility(View.INVISIBLE);
            }
        });


        etMail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                tvError.setVisibility(View.INVISIBLE);
            }
        });
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                tvError.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void initializeViews() {
        etMail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRestablecer = findViewById(R.id.btRestablecer);
        btCrearUsuario = findViewById(R.id.btCrearUsuario);
        tvError = findViewById(R.id.tvError);

    }

    private void solicitarPermisos() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
        }

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        etPassword.setText("");
        etMail.setText("");
        etMail.requestFocus();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }




}