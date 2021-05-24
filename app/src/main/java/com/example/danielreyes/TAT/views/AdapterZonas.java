package com.example.danielreyes.TAT.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.danielreyes.TAT.ListadoClientesActivity;
import com.example.danielreyes.TAT.controllers.Zonas;
import java.util.ArrayList;
import com.example.danielreyes.TAT.R;

public class AdapterZonas extends BaseAdapter {
    Context c;
    LayoutInflater inflater;
    ArrayList<Zonas> zonas;

    public long getItemId(int i) {
        return (long) i;
    }

    public AdapterZonas(Context context, ArrayList<Zonas> arrayList) {
        this.c = context;
        this.zonas = arrayList;
    }

    public int getCount() {
        return this.zonas.size();
    }

    public Object getItem(int i) {
        return this.zonas.get(i);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (this.inflater == null) {
            this.inflater = (LayoutInflater) this.c.getSystemService("layout_inflater");
        }
        if (view == null) {
            view = this.inflater.inflate(R.layout.zonas_list, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.nombre_zona)).setText(((Zonas) this.zonas.get(i)).getNombre());
        ((TextView) view.findViewById(R.id.descripcion_zona)).setText(((Zonas) this.zonas.get(i)).getDescripcion());
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AdapterZonas.this.c, ListadoClientesActivity.class);
                intent.putExtra("IDE_ZONA", String.valueOf(((Zonas) AdapterZonas.this.zonas.get(i)).getId()));
                intent.putExtra("NAME_ZONA", ((Zonas) AdapterZonas.this.zonas.get(i)).getNombre());
                AdapterZonas.this.c.startActivity(intent);
            }
        });
        return view;
    }
}
