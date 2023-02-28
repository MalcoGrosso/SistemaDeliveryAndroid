package com.mng.sistemadeliveryandroid.ui.Pedido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mng.sistemadeliveryandroid.R;
import com.mng.sistemadeliveryandroid.modelo.DetallePedido;
import com.mng.sistemadeliveryandroid.modelo.Producto;
import com.mng.sistemadeliveryandroid.ui.Productos.ProductosViewModel;

import java.io.Serializable;
import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.MiViewHolder> implements Serializable {


    private LayoutInflater layoutInflater;
    private Context context;
    private List<Producto> productos;
    private View root;
    private DetallePedido detallePedido;
    private ProductosViewModel rViewModel;
    private String z;
    private List<Producto> prod;


    public PedidoAdapter(View root, List<Producto> productos) {
        this.root = root;
        this.layoutInflater = LayoutInflater.from(root.getContext());
        this.context = root.getContext();
        this.productos = productos;
        this.prod = productos;
        this.detallePedido = detallePedido;


    }



    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_mostrar_pedido, parent, false);
        return new MiViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull PedidoAdapter.MiViewHolder holder, int position) {

        Producto produ = prod.get(position);
        if(produ.getCanti() != 0) {
            holder.tvNombreMPedido.setText(produ.getNombre() + "");
            holder.tvPrecioMPedido.setText("$" + produ.getPrecioProducto() * produ.getCanti());
            holder.tvMPedidoCantidad.setText(produ.getCanti() + "");
        }else{
            holder.tvNombreMPedido.setText(" ");
            holder.tvPrecioMPedido.setText(" ");
            holder.tvMPedidoCantidad.setText(" ");
        }
    }

    @Override
    public int getItemCount() {return productos.size();}

    public class MiViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombreMPedido, tvPrecioMPedido, tvMPedidoCantidad;




        public MiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombreMPedido = itemView.findViewById(R.id.tvNombreMPedido);
            tvPrecioMPedido = itemView.findViewById(R.id.tvPrecioMPedido);
            tvMPedidoCantidad = itemView.findViewById(R.id.tvMPedidoCantidad);
        }
    }


}
