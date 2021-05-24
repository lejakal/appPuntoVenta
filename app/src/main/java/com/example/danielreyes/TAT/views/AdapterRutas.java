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
import com.example.danielreyes.TAT.R;
import com.example.danielreyes.TAT.controllers.Zonas;

import java.util.List;

public class AdapterRutas extends Adapter<AdapterRutas.ItemsViewHolder> {
    private Activity activity;
    Context c;
    private List<Zonas> list;
    private int resource;

    public class ItemsViewHolder extends ViewHolder implements OnClickListener {
        ItemClickListener itemClickListener;
        private TextView zonaDecrip;
        private TextView zonaId;
        private TextView zonaNombre;

        public ItemsViewHolder(View view) {
            super(view);
            this.zonaId = (TextView) view.findViewById(R.id.zona_ide);
            this.zonaNombre = (TextView) view.findViewById(R.id.nombre_zona);
            this.zonaDecrip = (TextView) view.findViewById(R.id.descripcion_zona);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            this.itemClickListener.onItemClick();
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    public AdapterRutas(List<Zonas> list, int i, Context context) {
        this.c = context;
        this.list = list;
        this.resource = i;
    }

    public ItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.zonas_list, viewGroup, false));
    }

    public void onBindViewHolder(ItemsViewHolder itemsViewHolder, int i) {
        final Zonas zonas = (Zonas) this.list.get(i);
        itemsViewHolder.zonaNombre.setText(zonas.getNombre());
        itemsViewHolder.zonaDecrip.setText(zonas.getDescripcion());
        itemsViewHolder.setItemClickListener(new ItemClickListener() {
            public void onItemClick() {
                AdapterRutas.this.openDetailActivity(zonas.getidUser(), String.valueOf(zonas.getId()), zonas.getNombre());
            }
        });
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
