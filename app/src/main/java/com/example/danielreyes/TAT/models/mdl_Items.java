package com.example.danielreyes.TAT.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.danielreyes.TAT.controllers.Items;
import java.util.ArrayList;
import java.util.List;

public class mdl_Items {
    private SQLiteDatabase db;
    private DBconexion helper;
    private ContentValues values = new ContentValues();

    public mdl_Items(Context context) {
        this.helper = new DBconexion(context);
    }

    public Items buscar(Integer num) {
        String valueOf = String.valueOf(num);
        this.db = this.helper.getReadableDatabase();
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_items", new String[]{valueOf});
        if (!rawQuery.moveToNext()) {
            return null;
        }
        Items items = new Items();
        items.setId(num);
        items.setNombre(rawQuery.getString(0));
        items.setPrecioventa(rawQuery.getString(6));
        return items;
    }

    public double quanty(Integer num) {
        String valueOf = String.valueOf(num);
        Double.valueOf(0.0d);
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_item_quantities WHERE item_id = ?", new String[]{valueOf});
        if (rawQuery.moveToNext()) {
            return Double.valueOf(rawQuery.getDouble(2)).doubleValue();
        }
        return 0.0d;
    }

    public List<Items> itemsList() {
        ArrayList arrayList = new ArrayList();
        this.db = this.helper.getReadableDatabase();
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_items", null);
        while (rawQuery.moveToNext()) {
            Items items = new Items();
            if (quanty(Integer.valueOf(rawQuery.getInt(9))) > 0.0d) {
                items.setId(Integer.valueOf(rawQuery.getInt(9)));
                items.setNombre(rawQuery.getString(0));
                items.setPrecioventa(rawQuery.getString(6));
                items.setStock(Double.valueOf(quanty(Integer.valueOf(rawQuery.getInt(9)))));
                arrayList.add(items);
            }
        }
        return arrayList;
    }

    public List<Items> itemsListOrder(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        this.db = this.helper.getReadableDatabase();
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_items WHERE id_subcategoria = ?", new String[]{str});
        while (rawQuery.moveToNext()) {
            Items items = new Items();
            if (quanty(Integer.valueOf(rawQuery.getInt(9))) > 0.0d) {
                items.setId(Integer.valueOf(rawQuery.getInt(9)));
                items.setNombre(rawQuery.getString(0));
                items.setPreciocompra(rawQuery.getString(5));
                items.setPrecioventa(rawQuery.getString(6));
                items.setStock(Double.valueOf(quanty(Integer.valueOf(rawQuery.getInt(9)))));
                items.setBodega("9");
                items.setIdsuspend(str2);
                items.setIduser(str3);
                arrayList.add(items);
            }
        }
        return arrayList;
    }

    public mdl_Items open() throws SQLException {
        this.db = this.helper.getWritableDatabase();
        return this;
    }

    public void close() {
        this.helper.close();
    }

    public Cursor select(String str, String[] strArr) throws SQLException {
        Cursor query = this.db.query(str, strArr, null, null, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
        }
        return query;
    }

    public Cursor select(String str, String[] strArr, String str2, String str3) throws SQLException {
        SQLiteDatabase sQLiteDatabase = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append("=");
        stringBuilder.append(str3);
        Cursor query = sQLiteDatabase.query(str, strArr, stringBuilder.toString(), null, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
        }
        return query;
    }

    public Cursor select(String str, String[] strArr, String str2, String str3, String str4, String str5) throws SQLException {
        SQLiteDatabase sQLiteDatabase = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append("=");
        stringBuilder.append(str3);
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(str4);
        stringBuilder3.append(" ");
        stringBuilder3.append(str5);
        Cursor query = sQLiteDatabase.query(str, strArr, stringBuilder2, null, null, null, stringBuilder3.toString(), null);
        if (query != null) {
            query.moveToFirst();
        }
        return query;
    }

    public Cursor select(String str, String[] strArr, String str2, long j) throws SQLException {
        SQLiteDatabase sQLiteDatabase = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append("=");
        stringBuilder.append(j);
        Cursor query = sQLiteDatabase.query(str, strArr, stringBuilder.toString(), null, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
        }
        return query;
    }

    public Cursor getItemsListByKeyword(String str) {
        SQLiteDatabase readableDatabase = this.helper.getReadableDatabase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT  * FROM ospos_items WHERE name  LIKE  '%");
        stringBuilder.append(str);
        stringBuilder.append("%' ");
        Cursor rawQuery = readableDatabase.rawQuery(stringBuilder.toString(), null);
        if (rawQuery == null) {
            return null;
        }
        if (rawQuery.moveToFirst()) {
            return rawQuery;
        }
        rawQuery.close();
        return null;
    }

    public Cursor selectPeople(String str, String str2, String str3) throws SQLException {
        String[] strArr = new String[]{DBtablas.first_name, DBtablas.apellidos};
        SQLiteDatabase sQLiteDatabase = this.db;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append("LIKE");
        stringBuilder.append(str3);
        Cursor query = sQLiteDatabase.query(str, strArr, stringBuilder.toString(), null, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
        }
        return query;
    }
}
