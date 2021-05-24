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

import com.example.danielreyes.TAT.InventoryActivity;
import com.example.danielreyes.TAT.R;
import com.example.danielreyes.TAT.controllers.Items;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterItems extends Adapter<AdapterItems.ItemsViewHolder> {
    private Activity activity;
    Context c;
    String idsuspended;
    private List<Items> list;
    private int resource;

    public class ItemsViewHolder extends ViewHolder implements OnClickListener {
        private TextView idsuspend;
        ItemClickListener itemClickListener;
        private TextView itemsCant;
        private TextView itemsId;
        private TextView itemsNombre;
        private TextView itemsPrecio;

        public ItemsViewHolder(View view) {
            super(view);
            this.itemsNombre = (TextView) view.findViewById(R.id.item_nombre);
            this.itemsPrecio = (TextView) view.findViewById(R.id.item_precio);
            this.idsuspend = (TextView) view.findViewById(R.id.item_ide);
            this.itemsCant = (TextView) view.findViewById(R.id.item_cant);
            this.idsuspend = (TextView) view.findViewById(R.id.idsuspend);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            this.itemClickListener.onItemClick();
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    public AdapterItems(List<Items> list, int i, Context context) {
        this.c = context;
        this.list = list;
        this.resource = i;
    }

    public ItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(this.resource, viewGroup, false));
    }

    public void onBindViewHolder(ItemsViewHolder itemsViewHolder, int i) {
        final Items items = (Items) this.list.get(i);
        itemsViewHolder.itemsNombre.setText(items.getNombre());
        itemsViewHolder.itemsCant.setText(String.valueOf(items.getStock()));
        itemsViewHolder.idsuspend.setText(items.getIdsuspend());
        String format = new DecimalFormat("##,##0.00").format(Double.valueOf(items.getPrecioventa()));
        TextView access$300 = itemsViewHolder.itemsPrecio;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$ ");
        stringBuilder.append(format);
        access$300.setText(stringBuilder.toString());
        itemsViewHolder.setItemClickListener(new ItemClickListener() {
            public void onItemClick() {
                AdapterItems.this.openDetailActivity(items.getIdsuspend(), items.getIduser(), String.valueOf(items.getId()), items.getNombre(), items.getPrecioventa(), items.getPreciocompra(), items.getBodega(), items.getStock());
            }
        });
    }

    public int getItemCount() {
        return this.list.size();
    }

    private void openDetailActivity(String str, String str2, String str3, String str4, String str5, String str6, String str7, Double d) {
        Intent intent = new Intent(this.c, InventoryActivity.class);
        intent.putExtra("IDE_ITEM", str3);
        intent.putExtra("IDE_SUSPENDED", str);
        intent.putExtra("ID_USER", str2);
        intent.putExtra("NAME_ITEM", str4);
        intent.putExtra("PRECIO_ITEM", str5);
        intent.putExtra("COMPRA_ITEM", str6);
        intent.putExtra("BODEGA_ITEM", str7);
        intent.putExtra("STOCK_ITEM", d);
        this.c.startActivity(intent);
    }
}
