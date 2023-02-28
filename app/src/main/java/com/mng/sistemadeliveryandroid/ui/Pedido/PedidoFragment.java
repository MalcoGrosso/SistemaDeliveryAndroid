package com.mng.sistemadeliveryandroid.ui.Pedido;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mng.sistemadeliveryandroid.R;
import com.mng.sistemadeliveryandroid.databinding.FragmentPedidoBinding;
import com.mng.sistemadeliveryandroid.modelo.Pedido;
import com.mng.sistemadeliveryandroid.modelo.Producto;

import java.util.List;


public class PedidoFragment extends Fragment {

    private FragmentPedidoBinding binding;
    private PedidoViewModel mPedidoViewModel;
    private TextView tvNumeroPedido, tvMontoPagar;
    private Button btPagar;
    private Context context;
    private RecyclerView recyclerViewLista;
    private PedidoAdapter adapter1;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentPedidoBinding.inflate(inflater,  container, false);
        View root = binding.getRoot();
        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        recyclerViewLista = binding.RVDPMPedido;
        btPagar = binding.btPagar;
        Bundle bundle = this.getArguments();
        Object z = bundle.get("pedido");
        adapter1 = (PedidoAdapter) bundle.getSerializable("pedido");


        context = this.getContext();
        inicializarVista(root);
        mPedidoViewModel.getPedido().observe(getViewLifecycleOwner(), new Observer<Pedido>() {
            @Override
            public void onChanged(Pedido pedido) {

                tvNumeroPedido.setText(pedido.getIdPedido()+"");
                tvMontoPagar.setText("$"+""+pedido.getMontoFinal()+"");



                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                        getContext(),
                        LinearLayoutManager.VERTICAL,

                        false
                );

                recyclerViewLista.setLayoutManager(linearLayoutManager);


                recyclerViewLista.setAdapter(adapter1);






            }
        });


        btPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Navigation.findNavController(root).navigate(R.id.nav_PagoFragment,bundle);
            }
        });

        return root;

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