package com.example.danielreyes.TAT.views;

import android.app.Activity;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danielreyes.TAT.R;
import com.example.danielreyes.TAT.controllers.Items;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterInventory extends Adapter<AdapterInventory.ItemsViewHolder> {
    private Activity activity;
    private List<Items> list;
    private int resource;

    public class ItemsViewHolder extends ViewHolder {
        private TextView itemCant;
        private TextView itemId;
        private TextView itemIde;
        private TextView itemNombre;
        private TextView itemPrecio;
        private ImageView itemTrash;
        private ImageView itemUpdate;

        public ItemsViewHolder(View view) {
            super(view);
            this.itemNombre = (TextView) view.findViewById(R.id.item_nombre);
            this.itemPrecio = (TextView) view.findViewById(R.id.item_precio);
            this.itemIde = (TextView) view.findViewById(R.id.item_ide);
            this.itemCant = (TextView) view.findViewById(R.id.item_cant);
        }
    }

    public AdapterInventory(List<Items> list, int i, Activity activity) {
        this.list = list;
        this.resource = i;
        this.activity = activity;
    }

    public ItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(this.resource, viewGroup, false));
    }

    public void onBindViewHolder(ItemsViewHolder itemsViewHolder, int i) {
        Items items = (Items) this.list.get(i);
        itemsViewHolder.itemNombre.setText(items.getNombre());
        itemsViewHolder.itemCant.setText(String.valueOf(items.getStock()));
        String format = new DecimalFormat("##,##0.00").format(Double.valueOf(items.getPrecioventa()));
        TextView access$200 = itemsViewHolder.itemPrecio;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$ ");
        stringBuilder.append(format);
        access$200.setText(stringBuilder.toString());
    }

    public int getItemCount() {
        return this.list.size();
    }
}
