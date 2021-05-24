package com.example.danielreyes.TAT.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.danielreyes.TAT.controllers.Items;
import java.text.DecimalFormat;
import java.util.ArrayList;
import com.example.danielreyes.TAT.R;

public class AdapterItemsSearch extends BaseAdapter {
    ArrayList<Items> articulos;
    Context c;
    LayoutInflater inflater;

    public long getItemId(int i) {
        return (long) i;
    }

    public AdapterItemsSearch(Context context, ArrayList<Items> arrayList) {
        this.c = context;
        this.articulos = arrayList;
    }

    public int getCount() {
        return this.articulos.size();
    }

    public Object getItem(int i) {
        return this.articulos.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (this.inflater == null) {
            this.inflater = (LayoutInflater) this.c.getSystemService("layout_inflater");
        }
        if (view == null) {
            view = this.inflater.inflate(R.layout.items_list, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.item_nombre)).setText(((Items) this.articulos.get(i)).getNombre());
        ((TextView) view.findViewById(R.id.item_cant)).setText(String.valueOf(((Items) this.articulos.get(i)).getStock()));
        String format = new DecimalFormat("##,##0.00").format(Double.valueOf(((Items) this.articulos.get(i)).getPrecioventa()));
        TextView textView = (TextView) view.findViewById(R.id.item_precio);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$ ");
        stringBuilder.append(format);
        textView.setText(stringBuilder.toString());
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        return view;
    }
}
