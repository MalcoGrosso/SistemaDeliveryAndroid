package com.mng.sistemadeliveryandroid.ui.Productos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Instant;

import com.mng.sistemadeliveryandroid.R;
import com.mng.sistemadeliveryandroid.databinding.FragmentProductosBinding;
import com.mng.sistemadeliveryandroid.modelo.Producto;
import com.mng.sistemadeliveryandroid.modelo.Pedido;
import com.mng.sistemadeliveryandroid.ui.Pedido.PedidoAdapter;

import java.util.List;

public class ProductosFragment extends Fragment {

    private ProductosViewModel rViewModel;
    private FragmentProductosBinding  binding;
    private ProductosAdapter adapter;
    private PedidoAdapter adapter1;
    private RecyclerView recyclerViewLista;
    private Button btHacerPedido;
    private Pedido pedido;
    private Instant instant = Instant.now();
    private Context context;
    Bundle bundle = new Bundle();


    public static ProductosFragment newInstance() {
        return new ProductosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rViewModel = new ViewModelProvider(this).get(ProductosViewModel.class);
        binding = FragmentProductosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerViewLista = binding.RVLista;
        btHacerPedido = binding.btHacerPedido;
        context = this.getContext();

        rViewModel.getProductosMutable().observe(getViewLifecycleOwner(), new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                        getContext(),
                        LinearLayoutManager.VERTICAL,

                        false
                );
                recyclerViewLista.setLayoutManager(linearLayoutManager);
                adapter = new ProductosAdapter(root, productos);
                adapter1 = new PedidoAdapter(root, productos);
                bundle.putSerializable("pedido", adapter1);
                recyclerViewLista.setAdapter(adapter);

            }
        });

        btHacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rViewModel.modificarPedidoUsuario(pedido);

                Navigation.findNavController(root).navigate(R.id.nav_PedidoFragment, bundle);
            }
        });

        rViewModel.setProductos();

        pedido = new Pedido();
        pedido.setIdPedido(0);
        pedido.setIdEmpleadoPedido(2);
        pedido.setEstado(0);
        pedido.setFechaPedido(instant.toString());
        pedido.setFechaEntrega(instant.toString());
        pedido.setIdUsuarioPedido(0);
        pedido.setLatitudPedido(rViewModel.obtenerLatitud());
        pedido.setLongitudPedido(rViewModel.obtenerLongitud());
        pedido.setMontoFinal(0.0);



        return root;
    }
}


