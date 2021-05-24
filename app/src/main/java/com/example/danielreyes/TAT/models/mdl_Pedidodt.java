package com.example.danielreyes.TAT.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.danielreyes.TAT.controllers.PedidoItems;
import com.example.danielreyes.TAT.controllers.Pedidosdtl;
import java.util.ArrayList;
import java.util.List;

public class mdl_Pedidodt {
    private SQLiteDatabase db;
    private DBconexion helper;
    private ContentValues values = new ContentValues();

    public mdl_Pedidodt(Context context) {
        this.helper = new DBconexion(context);
    }

    public List<Pedidosdtl> pedidoDetalle(String str) {
        ArrayList arrayList = new ArrayList();
        this.db = this.helper.getReadableDatabase();
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_sales_suspended WHERE sale_id = ?", new String[]{str});
        while (rawQuery.moveToNext()) {
            Pedidosdtl pedidosdtl = new Pedidosdtl();
            pedidosdtl.setId(Integer.valueOf(rawQuery.getInt(4)));
            pedidosdtl.setFechaHora(rawQuery.getString(0));
            pedidosdtl.setCliente(nombreCliente(Integer.valueOf(rawQuery.getInt(1))));
            pedidosdtl.setCedula(cedulaCliente(Integer.valueOf(rawQuery.getInt(1))));
            pedidosdtl.setDireccion(direccionCliente(Integer.valueOf(rawQuery.getInt(1))));
            pedidosdtl.setDescripcion(rawQuery.getString(11));
            pedidosdtl.setPago(tipoPago(Integer.valueOf(rawQuery.getInt(4))));
            arrayList.add(pedidosdtl);
        }
        return arrayList;
    }

    public String nombreCliente(Integer num) {
        String valueOf = String.valueOf(num);
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_people WHERE person_id = ?", new String[]{valueOf});
        if (!rawQuery.moveToNext()) {
            return "";
        }
        String string = rawQuery.getString(0);
        valueOf = rawQuery.getString(13);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        stringBuilder.append(" ");
        stringBuilder.append(valueOf);
        return stringBuilder.toString();
    }

    public String cedulaCliente(Integer num) {
        String valueOf = String.valueOf(num);
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_people WHERE person_id = ?", new String[]{valueOf});
        return rawQuery.moveToNext() ? rawQuery.getString(1) : "";
    }

    public String direccionCliente(Integer num) {
        String valueOf = String.valueOf(num);
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_people WHERE person_id = ?", new String[]{valueOf});
        if (!rawQuery.moveToNext()) {
            return "";
        }
        String string = rawQuery.getString(4);
        valueOf = rawQuery.getString(7);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        stringBuilder.append(" ");
        stringBuilder.append(valueOf);
        return stringBuilder.toString();
    }

    public List<PedidoItems> pedidoItemsDetalle(String str) {
        ArrayList arrayList = new ArrayList();
        this.db = this.helper.getReadableDatabase();
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_sales_suspended_items WHERE sale_id = ?", new String[]{str});
        while (rawQuery.moveToNext()) {
            PedidoItems pedidoItems = new PedidoItems();
            pedidoItems.setIditem(rawQuery.getString(1));
            pedidoItems.setNombreitem(nombreItems(Integer.valueOf(rawQuery.getInt(1))));
            pedidoItems.setIdsuspend(str);
            pedidoItems.setCantidad(rawQuery.getString(5));
            pedidoItems.setCompra(rawQuery.getString(6));
            pedidoItems.setVenta(rawQuery.getString(7));
            arrayList.add(pedidoItems);
        }
        return arrayList;
    }

    public String nombreItems(Integer num) {
        String valueOf = String.valueOf(num);
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_items WHERE item_id = ?", new String[]{valueOf});
        return rawQuery.moveToNext() ? rawQuery.getString(0) : "";
    }

    public String tipoPago(Integer num) {
        String valueOf = String.valueOf(num);
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_sales_suspended_payments WHERE sale_id = ?", new String[]{valueOf});
        if (!rawQuery.moveToNext()) {
            return "";
        }
        Integer valueOf2 = Integer.valueOf(rawQuery.getInt(1));
        valueOf = rawQuery.getString(3);
        if (valueOf2.intValue() == 1) {
            valueOf = "Efectivo";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Credito: ");
            stringBuilder.append(valueOf);
            stringBuilder.append(" dias");
            valueOf = stringBuilder.toString();
        }
        return valueOf;
    }
}
