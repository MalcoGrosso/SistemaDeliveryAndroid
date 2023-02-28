package com.mng.sistemadeliveryandroid.ui.Productos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mng.sistemadeliveryandroid.R;
import com.mng.sistemadeliveryandroid.modelo.Producto;
import com.mng.sistemadeliveryandroid.modelo.DetallePedido;
import com.mng.sistemadeliveryandroid.request.ApiRetrofit;


import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.MiViewHolder> implements Serializable {


    private LayoutInflater layoutInflater;
    private Context context;
    private List<Producto> productos;
    private View root;
    private DetallePedido detallePedido;
    private ProductosViewModel rViewModel;
    private String z;

    public ProductosAdapter(View root, List<Producto> productos) {
        this.root = root;
        this.layoutInflater = LayoutInflater.from(root.getContext());
        this.context = root.getContext();
        this.productos = productos;
        this.detallePedido = detallePedido;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_productos, parent, false);
        return new MiViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        Producto produ = productos.get(position);
        holder.tvNombre.setText(produ.getNombre());
        holder.tvDetalles.setText("$"+produ.getPrecioProducto());
        holder.tvCantidad.setText(produ.getCanti()+"");


        Glide.with(context)
                .load(produ.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.ivFoto);
        holder.btHacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            detallePedido = new DetallePedido();
            detallePedido.setIdDetallePedido(0);
            detallePedido.setIdentificadorDetallePedido(0);
            detallePedido.setPrecioPedido(produ.getPrecioProducto());
            detallePedido.setIdProductoDP(produ.getIdProducto());
            agregarDetallePedido(detallePedido);
            produ.setCanti(produ.getCanti()+1);
            holder.tvCantidad.setText(produ.getCanti()+"");
            }


        });

        holder.btQuitarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(produ.getCanti() > 0){
                detallePedido = new DetallePedido();
                detallePedido.setIdDetallePedido(0);
                detallePedido.setIdentificadorDetallePedido(0);
                detallePedido.setPrecioPedido(produ.getPrecioProducto());
                detallePedido.setIdProductoDP(produ.getIdProducto());
                QuitarDetallePedido(detallePedido);
                produ.setCanti(produ.getCanti()-1);
                holder.tvCantidad.setText(produ.getCanti()+"");
                }
                else{
                    holder.tvCantidad.setText(produ.getCanti()+"");
                }
            }
        });
    }

              @Override
    public int getItemCount() {return productos.size();}

    public class MiViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDetalles, tvNombre, tvCantidad;
        private ImageView ivFoto;
        private Button btHacerPedido, btQuitarPedido;


        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDetalles = itemView.findViewById(R.id.tvDetalles);
            btHacerPedido = itemView.findViewById(R.id.btAgregar);
            btQuitarPedido = itemView.findViewById(R.id.btQuitar);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
        }
    }

    public void agregarDetallePedido(DetallePedido detallePedido) {

        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String token = sp.getString("token", "-1");
        Call<DetallePedido> adp = ApiRetrofit.getServiceSistemaDelivery().AgregarDetallePedido(token, detallePedido);
        adp.enqueue(new Callback<DetallePedido>() {
            @Override
            public void onResponse(Call<DetallePedido> call, Response<DetallePedido> response) {
                if (response.isSuccessful()) {

                } else {
                    Toast.makeText(context, "No se pudo guardar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetallePedido> call, Throwable t) {
                Toast.makeText(context, "hubo un error inesperado" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }



    public void QuitarDetallePedido(DetallePedido detallePedido) {

        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String token = sp.getString("token", "-1");
        Call<DetallePedido> qdp = ApiRetrofit.getServiceSistemaDelivery().QuitarDetallePedido(token, detallePedido);
        qdp.enqueue(new Callback<DetallePedido>() {
            @Override
            public void onResponse(Call<DetallePedido> call, Response<DetallePedido> response) {
                if (response.isSuccessful()) {

                } else {
                    Toast.makeText(context, "No se pudo guardar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetallePedido> call, Throwable t) {
                Toast.makeText(context, "hubo un error inesperado" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}


