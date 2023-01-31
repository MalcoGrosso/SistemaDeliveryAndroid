package com.mng.sistemadeliveryandroid.ui.Productos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.mng.sistemadeliveryandroid.MainActivity;
import com.mng.sistemadeliveryandroid.MenuPrincipal;
import com.mng.sistemadeliveryandroid.R;
import com.mng.sistemadeliveryandroid.databinding.FragmentProductosBinding;
import com.mng.sistemadeliveryandroid.modelo.Producto;
import com.mng.sistemadeliveryandroid.modelo.Pedido;
import com.mng.sistemadeliveryandroid.ui.Pedido.PedidoFragment;

import java.util.Date;
import java.util.List;

public class ProductosFragment extends Fragment {

    private ProductosViewModel rViewModel;
    private FragmentProductosBinding  binding;
    private ProductosAdapter adapter;
    private RecyclerView recyclerViewLista;
    private Button btHacerPedido;
    private Pedido pedido;
    private Date currentTime = Calendar.getInstance().getTime();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Instant instant = Instant.now();
    private Context context;


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
   //             rViewModel.consultarPagos();
                recyclerViewLista.setLayoutManager(linearLayoutManager);
                adapter = new ProductosAdapter(root, productos);
                recyclerViewLista.setAdapter(adapter);

            }
        });

        btHacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                rViewModel.modificarPedidoUsuario(pedido);
                Navigation.findNavController(root).navigate(R.id.nav_PedidoFragment, bundle);
    /*            Intent i = new Intent(context, PedidoFragment.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
*/
            }
        });

        rViewModel.setProductos();

        pedido = new Pedido();

        pedido.setIdPedido(0);
        pedido.setIdEmpleadoPedido(0);
        pedido.setEstado(0);
        pedido.setFechaPedido(instant.toString());
        pedido.setFechaEntrega(instant.toString());
        pedido.setIdUsuarioPedido(0);
        pedido.setLatitudPedido(rViewModel.obtenerLatitud());
        pedido.setLongitudPedido(rViewModel.obtenerLongitud());
        pedido.setMontoFinal(0.0);
        rViewModel.crearPedido(pedido);
        return root;
    }
}


