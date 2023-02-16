package com.mng.sistemadeliveryandroid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.SupportMapFragment;
import com.mng.sistemadeliveryandroid.R;
import com.mng.sistemadeliveryandroid.databinding.FragmentHomeBinding;
import com.mng.sistemadeliveryandroid.modelo.Pedido;

import java.time.Instant;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private Instant instant = Instant.now();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        homeViewModel.getMutableLeerMapa().observe(getViewLifecycleOwner(), new Observer<LeerMapa>() {
            @Override
            public void onChanged(LeerMapa leerMapa) {
                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(leerMapa);
            }
        });
        homeViewModel.leerMapa();

        Pedido pedido = new Pedido();
        pedido.setIdPedido(0);
        pedido.setIdEmpleadoPedido(2);
        pedido.setEstado(0);
        pedido.setFechaPedido(instant.toString());
        pedido.setFechaEntrega(instant.toString());
        pedido.setIdUsuarioPedido(0);
        pedido.setLatitudPedido(homeViewModel.obtenerLatitud());
        pedido.setLongitudPedido(homeViewModel.obtenerLongitud());
        pedido.setMontoFinal(0.0);
        homeViewModel.crearPedido(pedido);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}