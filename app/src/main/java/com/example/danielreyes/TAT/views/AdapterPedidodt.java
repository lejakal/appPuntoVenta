package com.example.danielreyes.TAT.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.danielreyes.TAT.ClientesZonasActivity;
import com.example.danielreyes.TAT.controllers.Pedidosdtl;
import com.example.danielreyes.TAT.R;
import java.util.List;

public class AdapterPedidodt extends Adapter<AdapterPedidodt.ItemsViewHolder> {
    private Activity activity;
    Context c;
    private List<Pedidosdtl> list;
    private int resource;

    public class ItemsViewHolder extends ViewHolder implements OnClickListener {
        private TextView cedula;
        private TextView direccion;
        ItemClickListener itemClickListener;
        private TextView nombre;
        private TextView observaciones;
        private TextView tipopago;

        public ItemsViewHolder(View view) {
            super(view);
            this.nombre = (TextView) view.findViewById(R.id.nameCliente);
            this.cedula = (TextView) view.findViewById(R.id.ccCliente);
            this.direccion = (TextView) view.findViewById(R.id.dirCliente);
            this.observaciones = (TextView) view.findViewById(R.id.observacion);
            this.tipopago = (TextView) view.findViewById(R.id.tipoPayment);
        }

        public void onClick(View view) {
            this.itemClickListener.onItemClick();
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    public AdapterPedidodt(List<Pedidosdtl> list, int i, Context context) {
        this.c = context;
        this.list = list;
        this.resource = i;
    }

    public ItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detalle_pedidodt, viewGroup, false));
    }

    public void onBindViewHolder(ItemsViewHolder itemsViewHolder, int i) {
        Pedidosdtl pedidosdtl = (Pedidosdtl) this.list.get(i);
        itemsViewHolder.nombre.setText(pedidosdtl.getCliente());
        itemsViewHolder.cedula.setText(pedidosdtl.getCedula());
        itemsViewHolder.direccion.setText(pedidosdtl.getDireccion());
        itemsViewHolder.observaciones.setText(pedidosdtl.getDescripcion());
        itemsViewHolder.tipopago.setText(pedidosdtl.getPago());
    }

    public int getItemCount() {
        return this.list.size();
    }

    private void openDetailActivity(String str, String str2, String str3) {
        Intent intent = new Intent(this.c, ClientesZonasActivity.class);
        intent.putExtra("IDE_ZONA", str2);
        intent.putExtra("NAME_ZONA", str3);
        intent.putExtra("ID_USER", str);
        this.c.startActivity(intent);
    }
}
