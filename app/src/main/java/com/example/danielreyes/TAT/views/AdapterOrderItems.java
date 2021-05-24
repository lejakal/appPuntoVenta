package com.example.danielreyes.TAT.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.danielreyes.TAT.controllers.Items;
import java.util.List;
import com.example.danielreyes.TAT.R;

public class AdapterOrderItems extends ArrayAdapter<Items> {

    static class ViewHolder {
        TextView itemCant;
        TextView itemId;
        TextView itemIde;
        TextView itemNombre;
        TextView itemPrecio;

        ViewHolder() {
        }
    }

    public AdapterOrderItems(Context context, List<Items> list) {
        super(context, 0, list);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        if (view == null) {
            view = layoutInflater.inflate(R.layout.order_items, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.itemNombre = (TextView) view.findViewById(R.id.item_nombre);
            viewHolder.itemPrecio = (TextView) view.findViewById(R.id.item_precio);
            viewHolder.itemIde = (TextView) view.findViewById(R.id.item_ide);
            viewHolder.itemCant = (TextView) view.findViewById(R.id.item_cant);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Items items = (Items) getItem(i);
        viewHolder.itemIde.setText(String.valueOf(items.getId()));
        viewHolder.itemNombre.setText(items.getNombre());
        viewHolder.itemPrecio.setText(items.getPrecioventa());
        viewHolder.itemCant.setText(String.valueOf(items.getStock()));
        return view;
    }
}
