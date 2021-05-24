package com.example.danielreyes.TAT.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.controllers.Zonas;
import java.util.ArrayList;
import java.util.List;

public class mdl_Rutas {
    private SQLiteDatabase db;
    private DBconexion helper;
    String rutadia;
    private ContentValues values = new ContentValues();
    String iduserLogeado;

    public mdl_Rutas(Context context) {
        this.helper = new DBconexion(context);
    }

    public List<Zonas> zonasList(String str) {
        ArrayList arrayList = new ArrayList();
        this.db = this.helper.getReadableDatabase();
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_zonas", null);

            while (rawQuery.moveToNext()) {

                Zonas zonas = new Zonas();
                Usuario usuario = new Usuario();
                iduserLogeado = usuario.getRutas();

                zonas.setId(Integer.valueOf(rawQuery.getInt(6)));
                zonas.setNombre(rawQuery.getString(1));
                zonas.setDescripcion(rawQuery.getString(4));
                zonas.setidUser(str);
                arrayList.add(zonas);
            }
            return arrayList;

    }

    public mdl_Rutas open() throws SQLException {
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
}
