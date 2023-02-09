package com.mng.sistemadeliveryandroid.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mng.sistemadeliveryandroid.R;
import com.mng.sistemadeliveryandroid.modelo.Usuario;

import java.time.Instant;

public class PerfilFragment extends Fragment {

    private PerfilViewModel vmPerfil;
    private EditText etId,etNombre,etApellido,etDireccion,etEmail,etTelefono,etPassword;
    private Button btAccion;
    private Instant instant = Instant.now();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vmPerfil = new ViewModelProvider(this).get(PerfilViewModel.class);
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        vmPerfil.getMutableUsuario().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                etId.setText(usuario.getIdUsuario()+"");
                etNombre.setText(usuario.getNombre());
                etApellido.setText(usuario.getApellido());
                etDireccion.setText(usuario.getDireccion()+"");
                etTelefono.setText(usuario.getTelefono());
                etEmail.setText(usuario.getEmail());

            }
        });

        vmPerfil.getMutableUsuarioEnable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean valor) {
                etEmail.setEnabled(valor);
                etNombre.setEnabled(valor);
                etApellido.setEnabled(valor);
                etDireccion.setEnabled(valor);
                etTelefono.setEnabled(valor);
                etPassword.setEnabled(valor);
            }
        });

        vmPerfil.getMutableTextoBoton().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String textoBoton) {
                btAccion.setText(textoBoton);
            }
        });
        inicializarVista(view);
        return view;
    }
    public void inicializarVista(View view){
        etId=view.findViewById(R.id.etId);
        etNombre = view.findViewById(R.id.etNombre);
        etApellido= view.findViewById(R.id.etApellido);
        etDireccion= view.findViewById(R.id.etDireccion);
        etTelefono= view.findViewById(R.id.etTelefono);
        etEmail=view.findViewById(R.id.etEmail);
        etPassword= view.findViewById(R.id.etPassword);
        btAccion= view.findViewById(R.id.btEdit);
        vmPerfil.ObtenerUsuario();
        btAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoBoton=  btAccion.getText().toString();
                int idUsuario = Integer.parseInt(etId.getText().toString());
                String nombre = etNombre.getText().toString();
                String apellido= etApellido.getText().toString();
                String direccion= etDireccion.getText().toString() ;
                String telefono= etTelefono.getText().toString();
                String email= etEmail.getText().toString();
                String password= etPassword.getText().toString();
                Usuario usu = new Usuario(idUsuario,nombre,apellido,direccion,email,password,telefono);
                vmPerfil.actualizarPerfil(textoBoton,usu);
            }
        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}