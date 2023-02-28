package com.mng.sistemadeliveryandroid.ui.Pago;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mng.sistemadeliveryandroid.R;
import com.mng.sistemadeliveryandroid.modelo.Pago;
import com.mng.sistemadeliveryandroid.databinding.FragmentPagoBinding;
import com.mng.sistemadeliveryandroid.modelo.Pedido;

import java.time.Instant;

public class PagoFragment extends Fragment {

    private PagoViewModel mViewModel;
    private FragmentPagoBinding binding;
    private Context context;
    private Spinner spinner1;
    private Pago pago;
    private Pedido pedi;
    private Instant instant = Instant.now();

    public static PagoFragment newInstance() {
        return new PagoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = FragmentPagoBinding.inflate(inflater, container, false);
        View root= binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(PagoViewModel.class);
        mViewModel.obtenerTipoPago();
        pago = new Pago();


        mViewModel.getTipoPago().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(String[] tipoPago) {

                spinner1 = root.findViewById(R.id.spMPago);

                Context context = root.getContext();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, tipoPago);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

                spinner1.setAdapter(adapter);

                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }});
            }
        });

        binding.btRealizarPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pago.setIdPago(0);
                pago.setIdPedidoPago(0);
                pago.setFechaPago(instant.toString());
                pago.setIdTipoPagoP(binding.spMPago.getSelectedItemPosition() + 1);
                mViewModel.crearPago(pago);

                pedi = new Pedido();

                pedi.setIdPedido(0);
                pedi.setEstado(0);
                pedi.setFechaPedido(instant.toString());
                pedi.setFechaEntrega(instant.toString());
                pedi.setIdUsuarioPedido(0);
                pedi.setLatitudPedido("0");
                pedi.setLongitudPedido("0");
                pedi.setMontoFinal(0.0);
                mViewModel.modificarPedidoUsuario2(pedi, v);


            }
        });


        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PagoViewModel.class);
        // TODO: Use the ViewModel
    }

}