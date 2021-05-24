package com.example.danielreyes.TAT.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.danielreyes.TAT.R;
import com.example.danielreyes.TAT.controllers.Customer;

import java.util.ArrayList;

public class AdapterListadoClientes extends BaseAdapter {
    Context c;
    ArrayList<Customer> clientes;
    LayoutInflater inflater;

    public long getItemId(int i) {
        return (long) i;
    }

    public AdapterListadoClientes(Context context, ArrayList<Customer> arrayList) {
        this.c = context;
        this.clientes = arrayList;
    }

    public int getCount() {
        return this.clientes.size();
    }

    public Object getItem(int i) {
        return this.clientes.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (this.inflater == null) {
            this.inflater = (LayoutInflater) this.c.getSystemService("layout_inflater");
        }
        if (view == null) {
            view = this.inflater.inflate(R.layout.layout_clientes, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.nameTxt)).setText(((Customer) this.clientes.get(i)).getNombres());
        ((TextView) view.findViewById(R.id.apellTxt)).setText(((Customer) this.clientes.get(i)).getApellidos());
        TextView textView = (TextView) view.findViewById(R.id.ccTxt);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("C.C. ");
        stringBuilder.append(((Customer) this.clientes.get(i)).getCedula());
        textView.setText(stringBuilder.toString());
        ((TextView) view.findViewById(R.id.dirTxt)).setText(((Customer) this.clientes.get(i)).getDireccion());
        textView = (TextView) view.findViewById(R.id.empresaTxt);
        stringBuilder = new StringBuilder();
        stringBuilder.append(((Customer) this.clientes.get(i)).getEmpresa());
        stringBuilder.append(" Tel.");
        stringBuilder.append(((Customer) this.clientes.get(i)).getTelefono());
        textView.setText(stringBuilder.toString());
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        return view;
    }
}
