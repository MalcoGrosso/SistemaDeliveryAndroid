package com.mng.sistemadeliveryandroid.ui.Pedido;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mng.sistemadeliveryandroid.CrearUsuarioActivity;
import com.mng.sistemadeliveryandroid.MainActivity;
import com.mng.sistemadeliveryandroid.R;
import com.mng.sistemadeliveryandroid.databinding.FragmentPedidoBinding;
import com.mng.sistemadeliveryandroid.modelo.Pedido;

import java.time.Instant;


public class PedidoFragment extends Fragment {

    private FragmentPedidoBinding binding;
    private PedidoViewModel mPedidoViewModel;
    private TextView tvNumeroPedido, tvMontoPagar;
    private Button btPagar;
    private Context context;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        View view = inflater.inflate(R.layout.fragment_pedido, container, false);
        binding = FragmentPedidoBinding.inflate(inflater, container, false);
        btPagar = binding.btPagar;
        context = this.getContext();
        inicializarVista(view);
        mPedidoViewModel.getPedido().observe(getViewLifecycleOwner(), new Observer<Pedido>() {
            @Override
            public void onChanged(Pedido pedido) {
                tvNumeroPedido.setText(pedido.getIdPedido()+"");
                tvMontoPagar.setText("$"+""+pedido.getMontoFinal()+"");
            }
        });

        btPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Navigation.findNavController(view).navigate(R.id.nav_PagoFragment,bundle);
            }
        });

        return view;

    }

    public void  inicializarVista(View view){

        tvNumeroPedido = view.findViewById(R.id.tvNumeroPedido);
        tvMontoPagar = view.findViewById(R.id.tvMontoPagar);
        btPagar = view.findViewById(R.id.btPagar);
        mPedidoViewModel.obtenerPedido();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);

        // TODO: Use the ViewModel
    }



}