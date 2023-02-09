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
import com.mng.sistemadeliveryandroid.R;
import com.mng.sistemadeliveryandroid.modelo.DetallePedido;
import com.mng.sistemadeliveryandroid.modelo.Producto;

import java.util.List;

public class verPedidoDetalleAdapter extends RecyclerView.Adapter<verPedidoDetalleAdapter.MiViewHolder> {


    private LayoutInflater layoutInflater;
    private Context context;
    private List<DetallePedido> detallePedidos;
    private List<Producto> productos;
    private View root;

    public verPedidoDetalleAdapter(View root, List<DetallePedido> detallePedidos) {
        this.root = root;
        this.layoutInflater = LayoutInflater.from(root.getContext());
        this.context = root.getContext();
        this.detallePedidos = detallePedidos;
        this.productos = productos;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_ver_pedido_detalle, parent, false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        DetallePedido dp = detallePedidos.get(position);
        holder.tvNombrevpd.setText(dp.getProducto().getNombre());
        holder.tvPreciovpd.setText("$"+ dp.getProducto().getPrecioProducto());
        Glide.with(context)
                .load(dp.getProducto().getImagen())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.ivFoto1);

    }

              @Override
    public int getItemCount() {
        return detallePedidos.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombrevpd, tvPreciovpd;
        private ImageView ivFoto1;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto1 = itemView.findViewById(R.id.ivFoto1);
            tvNombrevpd = itemView.findViewById(R.id.tvNombrevpd);
            tvPreciovpd = itemView.findViewById(R.id.tvPreciovpd);
        }
    }
}
