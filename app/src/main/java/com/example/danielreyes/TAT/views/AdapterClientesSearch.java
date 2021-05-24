package com.example.danielreyes.TAT.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.danielreyes.TAT.ListItemsPedidoActivity;
import com.example.danielreyes.TAT.controllers.ClienteActual;
import com.example.danielreyes.TAT.controllers.Customer;
import com.example.danielreyes.TAT.controllers.PedidoActual;
import java.util.ArrayList;
import com.example.danielreyes.TAT.R;

public class AdapterClientesSearch extends BaseAdapter {
    Context c;
    ArrayList<Customer> clientes;
    LayoutInflater inflater;

    public long getItemId(int i) {
        return (long) i;
    }

    public AdapterClientesSearch(Context context, ArrayList<Customer> arrayList) {
        this.c = context;
        this.clientes = arrayList;
    }

    public int getCount() {
        return this.clientes.size();
    }

    public Object getItem(int i) {
        return this.clientes.get(i);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
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
        //stringBuilder.append(((Customer) this.clientes.get(i)).getCartera());
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
                Intent intent = new Intent(AdapterClientesSearch.this.c, ListItemsPedidoActivity.class);
                ClienteActual clienteActual = new ClienteActual();
                clienteActual.setId(Integer.valueOf(((Customer) AdapterClientesSearch.this.clientes.get(i)).getId().intValue()));
                clienteActual.setNombres(((Customer) AdapterClientesSearch.this.clientes.get(i)).getNombres());
                clienteActual.setApellidos(((Customer) AdapterClientesSearch.this.clientes.get(i)).getApellidos());
                clienteActual.setCedula(((Customer) AdapterClientesSearch.this.clientes.get(i)).getCedula());
                clienteActual.setDireccion(((Customer) AdapterClientesSearch.this.clientes.get(i)).getDireccion());
                clienteActual.setCartera(((Customer) AdapterClientesSearch.this.clientes.get(i)).getCartera());
                new PedidoActual().setId(Integer.valueOf(0));
                AdapterClientesSearch.this.c.startActivity(intent);
            }
        });
        return view;
    }
}
