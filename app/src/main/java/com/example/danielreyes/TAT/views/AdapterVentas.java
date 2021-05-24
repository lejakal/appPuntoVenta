package com.example.danielreyes.TAT.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.danielreyes.TAT.R;
import com.example.danielreyes.TAT.controllers.Facturas;

import java.util.ArrayList;

public class AdapterVentas extends BaseAdapter {
    Context c;
    LayoutInflater inflater;
    ArrayList<Facturas> ventas;

    public long getItemId(int i) {
        return (long) i;
    }

    public AdapterVentas(Context context, ArrayList<Facturas> arrayList) {
        this.c = context;
        this.ventas = arrayList;
    }

    public int getCount() {
        return this.ventas.size();
    }

    public Object getItem(int i) {
        return this.ventas.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (this.inflater == null) {
            this.inflater = (LayoutInflater) this.c.getSystemService("layout_inflater");
        }
        if (view == null) {
            view = this.inflater.inflate(R.layout.layout_ventas, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.idFacturaTxt)).setText(((Facturas) this.ventas.get(i)).getIdFactura());
        ((TextView) view.findViewById(R.id.fechaTxt)).setText(((Facturas) this.ventas.get(i)).getFechaHora());
        ((TextView) view.findViewById(R.id.cliTxt)).setText(((Facturas) this.ventas.get(i)).getCliente());
        ((TextView) view.findViewById(R.id.valorTxt)).setText(((Facturas) this.ventas.get(i)).getValortotal());
        ((TextView) view.findViewById(R.id.tipopagoTxt)).setText(((Facturas) this.ventas.get(i)).getDescripcion());
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        return view;
    }
}
