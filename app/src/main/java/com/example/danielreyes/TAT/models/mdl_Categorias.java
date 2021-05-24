package com.example.danielreyes.TAT.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.danielreyes.TAT.controllers.Categorias;
import java.util.ArrayList;
import java.util.List;

public class mdl_Categorias {
    private SQLiteDatabase db;
    private DBconexion helper;
    private ContentValues values = new ContentValues();

    public mdl_Categorias(Context context) {
        this.helper = new DBconexion(context);
    }

    public Categorias buscar(Integer num) {
        String valueOf = String.valueOf(num);
        this.db = this.helper.getReadableDatabase();
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_categorias", new String[]{valueOf});
        if (!rawQuery.moveToNext()) {
            return null;
        }
        Categorias categorias = new Categorias();
        categorias.setId(num);
        categorias.setNombre(rawQuery.getString(1));
        return categorias;
    }

    public List<Categorias> categoriasList() {
        ArrayList arrayList = new ArrayList();
        this.db = this.helper.getReadableDatabase();
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM ospos_categorias", null);
        while (rawQuery.moveToNext()) {
            Categorias categorias = new Categorias();
            categorias.setId(Integer.valueOf(rawQuery.getInt(0)));
            categorias.setNombre(rawQuery.getString(1));
            arrayList.add(categorias);
        }
        return arrayList;
    }
}
