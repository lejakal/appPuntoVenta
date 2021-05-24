package com.example.danielreyes.TAT.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.danielreyes.TAT.controllers.Subcategorias;
import java.util.ArrayList;
import java.util.List;

public class mdl_Subcategorias {
    private SQLiteDatabase db;
    private DBconexion helper;
    private ContentValues values = new ContentValues();

    public mdl_Subcategorias(Context context) {
        this.helper = new DBconexion(context);
    }

    public Subcategorias buscar(Integer num) {
        String valueOf = String.valueOf(num);
        this.db = this.helper.getReadableDatabase();
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_subcatecorias", new String[]{valueOf});
        if (!rawQuery.moveToNext()) {
            return null;
        }
        Subcategorias subcategorias = new Subcategorias();
        subcategorias.setId(num);
        subcategorias.setNombre(rawQuery.getString(1));
        return subcategorias;
    }

    public List<Subcategorias> categoriasList() {
        ArrayList arrayList = new ArrayList();
        this.db = this.helper.getReadableDatabase();
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_subcatecorias", null);
        while (rawQuery.moveToNext()) {
            Subcategorias subcategorias = new Subcategorias();
            subcategorias.setId(Integer.valueOf(rawQuery.getInt(0)));
            subcategorias.setNombre(rawQuery.getString(1));
            arrayList.add(subcategorias);
        }
        return arrayList;
    }
}
