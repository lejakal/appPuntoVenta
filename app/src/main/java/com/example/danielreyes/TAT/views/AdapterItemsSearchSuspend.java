package com.example.danielreyes.TAT.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.danielreyes.TAT.InventoryActivity;
import com.example.danielreyes.TAT.controllers.ClienteActual;
import com.example.danielreyes.TAT.controllers.Items;
import java.text.DecimalFormat;
import java.util.ArrayList;
import com.example.danielreyes.TAT.R;

public class AdapterItemsSearchSuspend extends BaseAdapter {
    ArrayList<Items> articulos;
    Context c;
    LayoutInflater inflater;

    public long getItemId(int i) {
        return (long) i;
    }

    public AdapterItemsSearchSuspend(Context context, ArrayList<Items> arrayList) {
        this.c = context;
        this.articulos = arrayList;
    }

    public int getCount() {
        return this.articulos.size();
    }

    public Object getItem(int i) {
        return this.articulos.get(i);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (this.inflater == null) {
            this.inflater = (LayoutInflater) this.c.getSystemService("layout_inflater");
        }
        if (view == null) {
            view = this.inflater.inflate(R.layout.layout_items_suspend, viewGroup, false);
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
                Intent intent = new Intent(AdapterItemsSearchSuspend.this.c, InventoryActivity.class);
                intent.putExtra("IDE_ITEM", String.valueOf(((Items) AdapterItemsSearchSuspend.this.articulos.get(i)).getId()));
                intent.putExtra("IDE_SUSPENDED", ((Items) AdapterItemsSearchSuspend.this.articulos.get(i)).getIdsuspend());
                intent.putExtra("NAME_ITEM", ((Items) AdapterItemsSearchSuspend.this.articulos.get(i)).getNombre());
                intent.putExtra("PRECIO_ITEM", ((Items) AdapterItemsSearchSuspend.this.articulos.get(i)).getPrecioventa());
                intent.putExtra("COMPRA_ITEM", ((Items) AdapterItemsSearchSuspend.this.articulos.get(i)).getPreciocompra());
                intent.putExtra("BODEGA_ITEM", ((Items) AdapterItemsSearchSuspend.this.articulos.get(i)).getBodega());
                intent.putExtra("STOCK_ITEM", ((Items) AdapterItemsSearchSuspend.this.articulos.get(i)).getStock());
                ClienteActual clienteActual = new ClienteActual();
                String nombres = clienteActual.getNombres();
                String apellidos = clienteActual.getApellidos();
                String cedula = clienteActual.getCedula();
                String direccion = clienteActual.getDireccion();
                intent.putExtra("CLI_NAME", nombres);
                intent.putExtra("CLI_APELL", apellidos);
                intent.putExtra("CLI_CC", cedula);
                intent.putExtra("CLI_DIR", direccion);
                AdapterItemsSearchSuspend.this.c.startActivity(intent);
            }
        });
        return view;
    }
}
