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

import com.example.danielreyes.TAT.EditarPedidoActivity;
import com.example.danielreyes.TAT.R;
import com.example.danielreyes.TAT.controllers.PedidoItems;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterPedidoItems extends Adapter<AdapterPedidoItems.ItemsViewHolder> {
    private Activity activity;
    Context c;
    private List<PedidoItems> list;
    private int resource;

    public class ItemsViewHolder extends ViewHolder implements OnClickListener {
        private TextView bodega;
        private TextView cantidad;
        private TextView compra;
        ItemClickListener itemClickListener;
        private TextView nombreitem;
        private TextView venta;

        public ItemsViewHolder(View view) {
            super(view);
            this.nombreitem = (TextView) view.findViewById(R.id.nombreit);
            this.cantidad = (TextView) view.findViewById(R.id.cantidadit);
            this.venta = (TextView) view.findViewById(R.id.precioit);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            this.itemClickListener.onItemClick();
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    public AdapterPedidoItems(List<PedidoItems> list, int i, Context context) {
        this.c = context;
        this.list = list;
        this.resource = i;
    }

    public ItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detalle_pedidoitems, viewGroup, false));
    }

    public void onBindViewHolder(ItemsViewHolder itemsViewHolder, int i) {
        final PedidoItems pedidoItems = (PedidoItems) this.list.get(i);
        itemsViewHolder.nombreitem.setText(pedidoItems.getNombreitem());
        itemsViewHolder.cantidad.setText(pedidoItems.getCantidad());
        String format = new DecimalFormat("##,##0.00").format(Double.valueOf(pedidoItems.getVenta()));
        TextView access$200 = itemsViewHolder.venta;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$ ");
        stringBuilder.append(format);
        access$200.setText(stringBuilder.toString());
        itemsViewHolder.setItemClickListener(new ItemClickListener() {
            public void onItemClick() {
                AdapterPedidoItems.this.editarPedido(pedidoItems.getIdsuspend(), pedidoItems.getIditem(), pedidoItems.getNombreitem(), pedidoItems.getCantidad(), pedidoItems.getVenta());
            }
        });
    }

    public int getItemCount() {
        return this.list.size();
    }

    private void editarPedido(String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent(this.c, EditarPedidoActivity.class);
        intent.putExtra("ID_SUSPEND", str);
        intent.putExtra("ID_ITEM", str2);
        intent.putExtra("NOMBRE_ITEM", str3);
        intent.putExtra("CANTIDAD", str4);
        intent.putExtra("VENTA", str5);
        this.c.startActivity(intent);
    }
}
