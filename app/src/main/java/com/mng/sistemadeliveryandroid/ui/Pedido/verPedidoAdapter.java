package com.mng.sistemadeliveryandroid.ui.Pedido;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mng.sistemadeliveryandroid.modelo.Pedido;
import com.mng.sistemadeliveryandroid.R;


import java.util.List;

public class verPedidoAdapter extends RecyclerView.Adapter<verPedidoAdapter.MiViewHolder> {


    private LayoutInflater layoutInflater;
    private Context context;
    private List<Pedido> pedido;
    private View root;

    public verPedidoAdapter(View root, List<Pedido> pedido) {
        this.root = root;
        this.layoutInflater = LayoutInflater.from(root.getContext());
        this.context = root.getContext();
        this.pedido = pedido;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_verpedido, parent, false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        Pedido pedi = pedido.get(position);
        holder.tvidPedido.setText(pedi.getIdPedido()+"");
        holder.tvfechaPedido.setText(pedi.getFechaPedido());
        holder.tvidMontoFinal.setText("$"+pedi.getMontoFinal());

        holder.cvVerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("pedido", pedi);
                Navigation.findNavController(root).navigate(R.id.nav_VerPedidosDetalleFragment, bundle);
            }
        });
    }

              @Override
    public int getItemCount() {
        return pedido.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {

        private CardView cvVerPedido;
        private TextView tvidPedido, tvfechaPedido, tvidMontoFinal;


        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            cvVerPedido = itemView.findViewById(R.id.cvVerPedido);
            tvidPedido = itemView.findViewById(R.id.tvidPedido);
            tvfechaPedido = itemView.findViewById(R.id.tvfechaPedido);
            tvidMontoFinal = itemView.findViewById(R.id.tvidMontoFinal);
        }
    }
}
