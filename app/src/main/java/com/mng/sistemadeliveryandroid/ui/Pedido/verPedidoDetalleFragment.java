package com.mng.sistemadeliveryandroid.ui.Pedido;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mng.sistemadeliveryandroid.databinding.FragmentVerPedidoDetalleBinding;
import com.mng.sistemadeliveryandroid.modelo.DetallePedido;

import java.util.List;

public class verPedidoDetalleFragment extends Fragment {

    private verPedidoDetalleViewModel rViewModel;
    private FragmentVerPedidoDetalleBinding  binding;
    private verPedidoDetalleAdapter adapter;
    private RecyclerView recyclerViewLista;

    public static verPedidoDetalleFragment newInstance() {
        return new verPedidoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rViewModel = new ViewModelProvider(this).get(verPedidoDetalleViewModel.class);
        binding = FragmentVerPedidoDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerViewLista = binding.RVListaVerPedidoDetalle;

        rViewModel.getInmueblesMutable().observe(getViewLifecycleOwner(), new Observer<List<DetallePedido>>() {
            @Override
            public void onChanged(List<DetallePedido> detallePedidos) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                        getContext(),
                        LinearLayoutManager.VERTICAL,

                        false
                );
                recyclerViewLista.setLayoutManager(linearLayoutManager);
                adapter = new verPedidoDetalleAdapter(root, detallePedidos);
                recyclerViewLista.setAdapter(adapter);
            }
        });

        rViewModel.setInmuebles(getArguments());
        return root;
    }
}


