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


import com.mng.sistemadeliveryandroid.modelo.Pedido;
import com.mng.sistemadeliveryandroid.databinding.FragmentVerpedidoBinding;

import java.util.List;

public class verPedidoFragment extends Fragment {

    private verPedidoViewModel rViewModel;
    private FragmentVerpedidoBinding  binding;
    private verPedidoAdapter adapter;
    private RecyclerView recyclerViewLista;

    public static verPedidoFragment newInstance() {
        return new verPedidoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rViewModel = new ViewModelProvider(this).get(verPedidoViewModel.class);
        binding = FragmentVerpedidoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerViewLista = binding.RVLista;

        rViewModel.getVerPedidoMutable().observe(getViewLifecycleOwner(), new Observer<List<Pedido>>() {
            @Override
            public void onChanged(List<Pedido> pedidos) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                        getContext(),
                        LinearLayoutManager.VERTICAL,

                        false
                );
                recyclerViewLista.setLayoutManager(linearLayoutManager);
                adapter = new verPedidoAdapter(root, pedidos);
                recyclerViewLista.setAdapter(adapter);
            }
        });

        rViewModel.setVerPedidos();
        return root;
    }
}









/*

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inmuebles, container, false);
        mViewModel = new ViewModelProvider(this).get(InmueblesViewModel.class);
        RVLista = (RecyclerView) rootView.findViewById(R.id.RVLista);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        mViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {

                RVLista.setLayoutManager(linearLayoutManager);
                inmueblesAdapter = new InmueblesAdapter(inmuebles,rootView);

                RVLista.setAdapter(inmueblesAdapter);
            }
        });
        mViewModel.setInmuebles();
        return rootView;
    }

*/

