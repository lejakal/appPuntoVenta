package com.example.danielreyes.TAT.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;

public class DBconexion extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "androidsqlite.db";
    public static final int DATABASE_VERSION = 17;
    private SQLiteDatabase db;

    public DBconexion(Context context) {
        super(context, "androidsqlite.db", null, 17);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_PEOPLE);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_CLIENTE);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_ZONA);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_ASIGNACION);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_CATEGORY);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_CATEGORIAS);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_SUBCATEGORIAS);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_ITEMS);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_ITEMS_QUANT);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_EMPLOYEES);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_USER_LOGUEADO);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_SALES);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_SALES_SUSPEND);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_SALES_ITEMS_SUSPEND);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_ITEMS_TAXES_SUSPEND);
        sQLiteDatabase.execSQL(DBtablas.CREAR_TABLA_SALES_PAYMENTS_SUSPEND);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_people");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_customers");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_zonas");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospost_asingcli");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_category");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_categorias");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_subcatecorias");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_items");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_item_quantities");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_employees");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_user_logueado");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_sales");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_sales_suspended");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_sales_suspended_items");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_sales_suspended_items_taxes");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ospos_sales_suspended_payments");
        onCreate(sQLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getAllpeople() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospos_people", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllcustomer() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospos_customers", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllemployees() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospos_employees", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllzonas() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospos_zonas", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllasignaciones() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospost_asingcli", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public void vaciarItems() {
        getWritableDatabase().execSQL("DELETE FROM ospos_items");
    }

    public void vaciarItemsquanti() {
        getWritableDatabase().execSQL("DELETE FROM ospos_item_quantities");
    }

    public void vaciarPeople() {
        getWritableDatabase().execSQL("DELETE FROM ospos_people");
    }

    public void vaciarCustomer() {
        getWritableDatabase().execSQL("DELETE FROM ospos_customers");
    }

    public void vaciarAsig() {
        getWritableDatabase().execSQL("DELETE FROM ospost_asingcli");
    }

    public ArrayList<HashMap<String, String>> getAllItems() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospos_items", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllItemsquanty() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospos_item_quantities", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllcategory() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospos_category", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllcategorias() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospos_categorias", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllsubcategorias() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospos_subcatecorias", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllsuspend() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospos_sales_suspended", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllsuspend_items() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospos_sales_suspended_items", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllsuspend_itemsIDsuspend(String str) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM ospos_sales_suspended_items WHERE sale_id = ? ", new String[]{str});
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllsuspend_items_tax() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospos_sales_suspended_items_taxes", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllsuspend_payments() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM ospos_sales_suspended_payments", null);
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllVentasHoy(String str) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM ospos_sales WHERE sale_time = ? ", new String[]{str});
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllVentasFecha(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM ospos_sales WHERE sale_time >= ? AND sale_time <= ? ", new String[]{str, str2});
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public void insertPeople(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBtablas.first_name, (String) hashMap.get(DBtablas.first_name));
        contentValues.put(DBtablas.last_name, (String) hashMap.get(DBtablas.last_name));
        contentValues.put(DBtablas.phone_number, (String) hashMap.get(DBtablas.phone_number));
        contentValues.put("email", (String) hashMap.get("email"));
        contentValues.put(DBtablas.address_1, (String) hashMap.get(DBtablas.address_1));
        contentValues.put(DBtablas.address_2, (String) hashMap.get(DBtablas.address_2));
        contentValues.put(DBtablas.city, (String) hashMap.get(DBtablas.city));
        contentValues.put(DBtablas.state, (String) hashMap.get(DBtablas.state));
        contentValues.put(DBtablas.zip, (String) hashMap.get(DBtablas.zip));
        contentValues.put(DBtablas.country, (String) hashMap.get(DBtablas.country));
        contentValues.put(DBtablas.comments, (String) hashMap.get(DBtablas.comments));
        contentValues.put("person_id", (String) hashMap.get("person_id"));
        contentValues.put(DBtablas.DigVerif, (String) hashMap.get(DBtablas.DigVerif));
        contentValues.put(DBtablas.apellidos, (String) hashMap.get(DBtablas.apellidos));
        contentValues.put(DBtablas.tipoPeople, (String) hashMap.get(DBtablas.tipoPeople));
        contentValues.put("img", (String) hashMap.get("img"));
        contentValues.put(DBtablas.categoCliente, (String) hashMap.get(DBtablas.categoCliente));
        contentValues.put(DBtablas.afiliado, (String) hashMap.get(DBtablas.afiliado));
        writableDatabase.insert(DBtablas.TABLA_PEOPLE, null, contentValues);
        writableDatabase.close();
    }

    public void insertCustomer(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("person_id", (String) hashMap.get("person_id"));
        contentValues.put(DBtablas.account_number, (String) hashMap.get(DBtablas.account_number));
        contentValues.put(DBtablas.taxable, (String) hashMap.get(DBtablas.taxable));
        contentValues.put("deleted", (String) hashMap.get("deleted"));
        contentValues.put("niff", (String) hashMap.get("niff"));
        contentValues.put("tributario", (String) hashMap.get("tributario"));
        contentValues.put(DBtablas.empresa, (String) hashMap.get(DBtablas.empresa));
        contentValues.put(DBtablas.actividad, (String) hashMap.get(DBtablas.actividad));
        contentValues.put(DBtablas.nacimiento, (String) hashMap.get(DBtablas.nacimiento));
        contentValues.put(DBtablas.tipo_cliente, (String) hashMap.get(DBtablas.tipo_cliente));
        contentValues.put(DBtablas.estadoCli, (String) hashMap.get(DBtablas.estadoCli));
        contentValues.put(DBtablas.tipodoc, (String) hashMap.get(DBtablas.tipodoc));
        contentValues.put("niffA", (String) hashMap.get("niffA"));
        contentValues.put("tribA", (String) hashMap.get("tribA"));
        contentValues.put(DBtablas.niifDev, (String) hashMap.get(DBtablas.niifDev));
        contentValues.put(DBtablas.tribDev, (String) hashMap.get(DBtablas.tribDev));
        contentValues.put(DBtablas.niifDcto, (String) hashMap.get(DBtablas.niifDcto));
        contentValues.put(DBtablas.tribDcto, (String) hashMap.get(DBtablas.tribDcto));
        writableDatabase.insert(DBtablas.TABLA_CLIENTE, null, contentValues);
        writableDatabase.close();
    }

    public void insertZonas(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBtablas.nom_zona, (String) hashMap.get(DBtablas.nom_zona));
        contentValues.put("descripcion", (String) hashMap.get("descripcion"));
        contentValues.put(DBtablas.id_zna, (String) hashMap.get(DBtablas.id_zna));
        contentValues.put(DBtablas.clave_zna, (String) hashMap.get(DBtablas.clave_zna));
        writableDatabase.insert(DBtablas.TABLA_ZONA, null, contentValues);
        writableDatabase.close();
    }

    public void insertEmployees(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", (String) hashMap.get("username"));
        contentValues.put("password", (String) hashMap.get("password"));
        contentValues.put("person_id", (String) hashMap.get("person_id"));
        contentValues.put("deleted", (String) hashMap.get("deleted"));
        contentValues.put("niff", (String) hashMap.get("niff"));
        contentValues.put("tributario", (String) hashMap.get("tributario"));
        contentValues.put("niffA", (String) hashMap.get("niffA"));
        contentValues.put("tribA", (String) hashMap.get("tribA"));
        contentValues.put(DBtablas.rol, (String) hashMap.get(DBtablas.rol));
        contentValues.put(DBtablas.id_empleado, (String) hashMap.get(DBtablas.id_empleado));
        contentValues.put("location_id", (String) hashMap.get("location_id"));
        writableDatabase.insert(DBtablas.TABLA_EMPLOYEES, null, contentValues);
        writableDatabase.close();
    }

    public void cerrar() {
        close();
    }

    public void insertAsignaciones(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBtablas.idasig, (String) hashMap.get(DBtablas.idasig));
        contentValues.put(DBtablas.idcliente_asig, (String) hashMap.get(DBtablas.idcliente_asig));
        contentValues.put(DBtablas.idvendedor_asig, (String) hashMap.get(DBtablas.idvendedor_asig));
        contentValues.put(DBtablas.fecha_asig, (String) hashMap.get(DBtablas.fecha_asig));
        contentValues.put(DBtablas.idruta_asig, (String) hashMap.get(DBtablas.idruta_asig));
        contentValues.put(DBtablas.observacion_asig, (String) hashMap.get(DBtablas.observacion_asig));
        contentValues.put("hora", (String) hashMap.get("hora"));
        contentValues.put(DBtablas.reasigna_asig, (String) hashMap.get(DBtablas.reasigna_asig));
        contentValues.put(DBtablas.idzona_asig, (String) hashMap.get(DBtablas.idzona_asig));
        writableDatabase.insert(DBtablas.TABLA_ASIGNACION, null, contentValues);
        writableDatabase.close();
    }

    public void insertItems(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", (String) hashMap.get("name"));
        contentValues.put(DBtablas.category, (String) hashMap.get(DBtablas.category));
        contentValues.put(DBtablas.supplier_id, (String) hashMap.get(DBtablas.supplier_id));
        contentValues.put(DBtablas.item_number, (String) hashMap.get(DBtablas.item_number));
        contentValues.put("description", (String) hashMap.get("description"));
        contentValues.put(DBtablas.cost_price, (String) hashMap.get(DBtablas.cost_price));
        contentValues.put(DBtablas.unit_price, (String) hashMap.get(DBtablas.unit_price));
        contentValues.put("quantity", (String) hashMap.get("quantity"));
        contentValues.put(DBtablas.reorder_level, (String) hashMap.get(DBtablas.reorder_level));
        contentValues.put("item_id", (String) hashMap.get("item_id"));
        contentValues.put(DBtablas.allow_alt_description, (String) hashMap.get(DBtablas.allow_alt_description));
        contentValues.put(DBtablas.is_serialized, (String) hashMap.get(DBtablas.is_serialized));
        contentValues.put("deleted", (String) hashMap.get("deleted"));
        contentValues.put(DBtablas.custom1, (String) hashMap.get(DBtablas.custom1));
        contentValues.put(DBtablas.custom2, (String) hashMap.get(DBtablas.custom2));
        contentValues.put(DBtablas.custom3, (String) hashMap.get(DBtablas.custom3));
        contentValues.put(DBtablas.custom4, (String) hashMap.get(DBtablas.custom4));
        contentValues.put(DBtablas.custom5, (String) hashMap.get(DBtablas.custom5));
        contentValues.put(DBtablas.custom6, (String) hashMap.get(DBtablas.custom6));
        contentValues.put(DBtablas.custom7, (String) hashMap.get(DBtablas.custom7));
        contentValues.put(DBtablas.custom8, (String) hashMap.get(DBtablas.custom8));
        contentValues.put(DBtablas.custom9, (String) hashMap.get(DBtablas.custom9));
        contentValues.put(DBtablas.custom10, (String) hashMap.get(DBtablas.custom10));
        contentValues.put("niff", (String) hashMap.get("niff"));
        contentValues.put("tributario", (String) hashMap.get("tributario"));
        contentValues.put(DBtablas.old_price_venta, (String) hashMap.get(DBtablas.old_price_venta));
        contentValues.put(DBtablas.tipo, (String) hashMap.get(DBtablas.tipo));
        contentValues.put(DBtablas.autoProduccion, (String) hashMap.get(DBtablas.autoProduccion));
        contentValues.put(DBtablas.Unid, (String) hashMap.get(DBtablas.Unid));
        contentValues.put(DBtablas.old_compra, (String) hashMap.get(DBtablas.old_compra));
        contentValues.put(DBtablas.pic, (String) hashMap.get(DBtablas.pic));
        contentValues.put(DBtablas.niifC, (String) hashMap.get(DBtablas.niifC));
        contentValues.put(DBtablas.tribuC, (String) hashMap.get(DBtablas.tribuC));
        contentValues.put(DBtablas.percent_tax, (String) hashMap.get(DBtablas.percent_tax));
        contentValues.put("img", (String) hashMap.get("img"));
        contentValues.put("video", (String) hashMap.get("video"));
        contentValues.put("descripcion", (String) hashMap.get("descripcion"));
        contentValues.put("id_subcategoria", (String) hashMap.get("id_subcategoria"));
        contentValues.put(DBtablas.comision, (String) hashMap.get(DBtablas.comision));
        contentValues.put(DBtablas.oferta, (String) hashMap.get(DBtablas.oferta));
        contentValues.put(DBtablas.ind, (String) hashMap.get(DBtablas.ind));
        writableDatabase.insert(DBtablas.TABLA_ITEMS, null, contentValues);
        writableDatabase.close();
    }

    public void insertItemsquanty(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item_id", (String) hashMap.get("item_id"));
        contentValues.put("location_id", (String) hashMap.get("location_id"));
        contentValues.put("quantity", (String) hashMap.get("quantity"));
        contentValues.put(DBtablas.auto_id, (String) hashMap.get(DBtablas.auto_id));
        contentValues.put(DBtablas.mod, (String) hashMap.get(DBtablas.mod));
        contentValues.put("lote", (String) hashMap.get("lote"));
        contentValues.put("fecha_vence", (String) hashMap.get("fecha_vence"));
        contentValues.put("sucursal", (String) hashMap.get("sucursal"));
        writableDatabase.insert(DBtablas.TABLA_ITEMS_QUANT, null, contentValues);
        writableDatabase.close();
    }

    public void insertCategory(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_categoria", (String) hashMap.get("id_categoria"));
        contentValues.put("name", (String) hashMap.get("name"));
        contentValues.put(DBtablas.name_en, (String) hashMap.get(DBtablas.name_en));
        contentValues.put("img", (String) hashMap.get("img"));
        contentValues.put("video", (String) hashMap.get("video"));
        writableDatabase.insert(DBtablas.TABLA_CATEGORY, null, contentValues);
        writableDatabase.close();
    }

    public void insertCategorias(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_categoria", (String) hashMap.get("id_categoria"));
        contentValues.put("name", (String) hashMap.get("name"));
        writableDatabase.insert(DBtablas.TABLA_CATEGORIAS, null, contentValues);
        writableDatabase.close();
    }

    public void insertSubcategorias(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_subcategoria", (String) hashMap.get("id_subcategoria"));
        contentValues.put(DBtablas.subcategoria_nombre, (String) hashMap.get(DBtablas.subcategoria_nombre));
        contentValues.put("id_categoria", (String) hashMap.get("id_categoria"));
        writableDatabase.insert(DBtablas.TABLA_SUBCATEGORIAS, null, contentValues);
        writableDatabase.close();
    }

    public void insertSuspend(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sale_time", (String) hashMap.get("sale_time"));
        contentValues.put("customer_id", (String) hashMap.get("customer_id"));
        contentValues.put(DBtablas.employee_id_suspended, (String) hashMap.get(DBtablas.employee_id_suspended));
        contentValues.put("comment", (String) hashMap.get("comment"));
        contentValues.put("sale_id", (String) hashMap.get("sale_id"));
        contentValues.put("payment_type", (String) hashMap.get("payment_type"));
        contentValues.put(DBtablas.idcoti, (String) hashMap.get(DBtablas.idcoti));
        contentValues.put(DBtablas.mesa, (String) hashMap.get(DBtablas.mesa));
        contentValues.put(DBtablas.pedido, (String) hashMap.get(DBtablas.pedido));
        contentValues.put(DBtablas.vendedor_suspended, (String) hashMap.get(DBtablas.vendedor_suspended));
        contentValues.put(DBtablas.cambio_suspended, (String) hashMap.get(DBtablas.cambio_suspended));
        contentValues.put("observaciones", (String) hashMap.get("observaciones"));
        contentValues.put("sucursal", (String) hashMap.get("sucursal"));
        writableDatabase.insert(DBtablas.TABLA_SALES_SUSPEND, null, contentValues);
        writableDatabase.close();
    }

    public void insertSuspend_items(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sale_id", (String) hashMap.get("sale_id"));
        contentValues.put("item_id", (String) hashMap.get("item_id"));
        contentValues.put("description", (String) hashMap.get("description"));
        contentValues.put(DBtablas.serialnumber_susp, (String) hashMap.get(DBtablas.serialnumber_susp));
        contentValues.put("line", (String) hashMap.get("line"));
        contentValues.put(DBtablas.quantity_purchased_susp, (String) hashMap.get(DBtablas.quantity_purchased_susp));
        contentValues.put(DBtablas.item_cost_price_susp, (String) hashMap.get(DBtablas.item_cost_price_susp));
        contentValues.put(DBtablas.item_unit_price_susp, (String) hashMap.get(DBtablas.item_unit_price_susp));
        contentValues.put(DBtablas.discount_percent_susp, (String) hashMap.get(DBtablas.discount_percent_susp));
        contentValues.put(DBtablas.item_location_susp, (String) hashMap.get(DBtablas.item_location_susp));
        contentValues.put("suspended_id", (String) hashMap.get("suspended_id"));
        contentValues.put("lote", (String) hashMap.get("lote"));
        contentValues.put("fecha_vence", (String) hashMap.get("fecha_vence"));
        writableDatabase.insert(DBtablas.TABLA_SALES_ITEMS_SUSPEND, null, contentValues);
        writableDatabase.close();
    }

    public void insertSuspend_items_tax(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sale_id", (String) hashMap.get("sale_id"));
        contentValues.put("item_id", (String) hashMap.get("item_id"));
        contentValues.put("line", (String) hashMap.get("line"));
        contentValues.put("name", (String) hashMap.get("name"));
        contentValues.put(DBtablas.percent_suspended, (String) hashMap.get(DBtablas.percent_suspended));
        contentValues.put(DBtablas.id_taxes_suspended, (String) hashMap.get(DBtablas.id_taxes_suspended));
        writableDatabase.insert(DBtablas.TABLA_SALES_ITEMS_TAXES_SUSPEND, null, contentValues);
        writableDatabase.close();
    }

    public void insertSuspend_payments(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sale_id", (String) hashMap.get("sale_id"));
        contentValues.put("payment_type", (String) hashMap.get("payment_type"));
        contentValues.put(DBtablas.payment_amount_susp, (String) hashMap.get(DBtablas.payment_amount_susp));
        contentValues.put(DBtablas.dias_credito_susp, (String) hashMap.get(DBtablas.dias_credito_susp));
        writableDatabase.insert(DBtablas.TABLA_SALES_PAYMENTS_SUSPEND, null, contentValues);
        writableDatabase.close();
    }

    public DBconexion open() throws SQLException {
        this.db = getWritableDatabase();
        return this;
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

    public Cursor GetAllData() {
        return getReadableDatabase().rawQuery("SELECT * FROM ospos_zonas", null);
    }

    public void insertSalesSuspended(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBtablas.sale_date_suspended, (String) hashMap.get(DBtablas.sale_date_suspended));
        contentValues.put("sale_time", (String) hashMap.get("sale_time"));
        contentValues.put("customer_id", (String) hashMap.get("customer_id"));
        contentValues.put(DBtablas.employee_id_suspended, (String) hashMap.get(DBtablas.employee_id_suspended));
        contentValues.put("estate", (String) hashMap.get("estate"));
        writableDatabase.insert(DBtablas.TABLA_SALES_SUSPEND, null, contentValues);
        writableDatabase.close();
    }

    public void insertLogueado(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", (String) hashMap.get("username"));
        contentValues.put("password", (String) hashMap.get("password"));
        contentValues.put("person_id", (String) hashMap.get("person_id"));
        contentValues.put("location_id", (String) hashMap.get("location_id"));
        contentValues.put("rutas", (String) hashMap.get("rutas"));
        writableDatabase.insert(DBtablas.TABLA_USERLOGUEADO, null, contentValues);
        writableDatabase.close();
    }

    public String last_idsuspended() {
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT  * FROM ospos_sales_suspended", null);
        return rawQuery.moveToLast() ? rawQuery.getString(4) : "0";
    }

    public void insertItemsSalesSuspended(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sale_id", (String) hashMap.get("sale_id"));
        contentValues.put("item_id", (String) hashMap.get("item_id"));
        contentValues.put(DBtablas.quantity_purchased_susp, (String) hashMap.get(DBtablas.quantity_purchased_susp));
        contentValues.put(DBtablas.item_cost_price_susp, (String) hashMap.get(DBtablas.item_cost_price_susp));
        contentValues.put(DBtablas.item_unit_price_susp, (String) hashMap.get(DBtablas.item_unit_price_susp));
        contentValues.put(DBtablas.item_location_susp, (String) hashMap.get(DBtablas.item_location_susp));
        contentValues.put("estate", (String) hashMap.get("estate"));
        writableDatabase.insert(DBtablas.TABLA_SALES_ITEMS_SUSPEND, null, contentValues);
        writableDatabase.close();
    }

    public String estate_idsuspend(String str) {
        String valueOf = String.valueOf(Integer.valueOf(0));
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT * FROM ospos_sales_suspended WHERE estate = ? AND employee_id = ? ", new String[]{valueOf, str});
        return rawQuery.moveToFirst() ? rawQuery.getString(4) : "No";
    }

    public String cliente_suspend(String str) {
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT * FROM ospos_sales_suspended WHERE sale_id = ? ", new String[]{str});
        return rawQuery.moveToFirst() ? rawQuery.getString(1) : "No";
    }

    public void finalizarSalesSuspended(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("payment_type", (String) hashMap.get("payment_type"));
        contentValues.put("observaciones", (String) hashMap.get(DBtablas.observacion_asig));
        contentValues.put("estate", (String) hashMap.get("estate"));
        writableDatabase.update(DBtablas.TABLA_SALES_SUSPEND, contentValues, "sale_id = ?", new String[]{(String) hashMap.get("suspend_id")});
        writableDatabase.close();
    }

    public void pagoSuspended(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sale_id", (String) hashMap.get("suspend_id"));
        contentValues.put("payment_type", (String) hashMap.get("Tipo"));
        contentValues.put(DBtablas.payment_amount_susp, (String) hashMap.get("Valor"));
        contentValues.put(DBtablas.dias_credito_susp, (String) hashMap.get("dias"));
        writableDatabase.insert(DBtablas.TABLA_SALES_PAYMENTS_SUSPEND, null, contentValues);
        writableDatabase.close();
    }

    public void cancelarSalesSuspended(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(DBtablas.TABLA_SALES_SUSPEND, "sale_id = ?", new String[]{(String) hashMap.get("suspend_id")});
        writableDatabase.close();
    }

    public void cancelarItemsSuspended(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(DBtablas.TABLA_SALES_ITEMS_SUSPEND, "sale_id = ? AND item_id = ?", new String[]{(String) hashMap.get("suspend_id"), (String) hashMap.get("item_id")});
        writableDatabase.close();
    }

    public void editItemsSuspended(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBtablas.quantity_purchased_susp, (String) hashMap.get(DBtablas.quantity_purchased_susp));
        writableDatabase.update(DBtablas.TABLA_SALES_ITEMS_SUSPEND, contentValues, "sale_id = ? AND item_id = ?", new String[]{(String) hashMap.get("sale_id"), (String) hashMap.get("item_id")});
        writableDatabase.close();
    }

    public ArrayList<HashMap<String, String>> getAllsuspendAdd(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM ospos_sales_suspended WHERE sale_id = ? AND employee_id = ? ", new String[]{str2, str});
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllsuspendItemsAdd(String str) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM ospos_sales_suspended_items WHERE sale_id = ? ", new String[]{str});
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                hashMap.put("ver_edad", rawQuery.getString(4));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> getAllsuspendPayments(String str) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM ospos_sales_suspended_items WHERE sale_id = ? ", new String[]{str});
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", rawQuery.getString(0));
                hashMap.put("userName", rawQuery.getString(1));
                hashMap.put("ver_apellido", rawQuery.getString(2));
                hashMap.put("ver_salario", rawQuery.getString(3));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return arrayList;
    }

    public String suspendJSONfromSQLite(String str, String str2) {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM ospos_sales_suspended WHERE sale_id = ? AND employee_id = ? ", new String[]{str2, str});
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put(DBtablas.sale_date_suspended, rawQuery.getString(0));
                hashMap.put("customer_id", rawQuery.getString(1));
                hashMap.put(DBtablas.employee_id_suspended, rawQuery.getString(2));
                hashMap.put("comment", rawQuery.getString(3));
                hashMap.put("sale_id", rawQuery.getString(4));
                hashMap.put("payment_type", rawQuery.getString(5));
                hashMap.put(DBtablas.observacion_asig, rawQuery.getString(11));
                hashMap.put("sucursal", rawQuery.getString(12));
                hashMap.put("sale_time", rawQuery.getString(14));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return new GsonBuilder().create().toJson(arrayList);
    }

    public String suspendItemsJSONfromSQLite(String str, String str2) {
        ArrayList<HashMap<String, String>>
                arrayList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM ospos_sales_suspended_items WHERE sale_id = ? ", new String[]{str});
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("sale_id", str2);
                hashMap.put("item_id", rawQuery.getString(1));
                hashMap.put(DBtablas.quantity_purchased_susp, rawQuery.getString(5));
                hashMap.put(DBtablas.item_cost_price_susp, rawQuery.getString(6));
                hashMap.put(DBtablas.item_unit_price_susp, rawQuery.getString(7));
                hashMap.put(DBtablas.item_location_susp, rawQuery.getString(9));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return new GsonBuilder().create().toJson(arrayList);
    }

    public String suspendPaymentsJSONfromSQLite(String str, String str2) {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM ospos_sales_suspended_payments WHERE sale_id = ? ", new String[]{str});
        if (rawQuery.moveToFirst()) {
            do {
                HashMap hashMap = new HashMap();
                hashMap.put("sale_id", str2);
                hashMap.put("payment_type", rawQuery.getString(1));
                hashMap.put(DBtablas.payment_amount_susp, rawQuery.getString(2));
                hashMap.put("diascredito", rawQuery.getString(3));
                arrayList.add(hashMap);
            } while (rawQuery.moveToNext());
        }
        writableDatabase.close();
        return new GsonBuilder().create().toJson(arrayList);
    }

    public String customerSalesJSONfromSQLite(String str) {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
                        HashMap hashMap = new HashMap();
                hashMap.put("idcustomer", str);
                arrayList.add(hashMap);
        return new GsonBuilder().create().toJson(arrayList);
    }

    public void insertSalesfinal(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sale_time", (String) hashMap.get("sale_time"));
        contentValues.put("hora", (String) hashMap.get("hora"));
        contentValues.put("customer_id", (String) hashMap.get("customer_id"));
        contentValues.put("sale_id", (String) hashMap.get("sale_id"));
        contentValues.put("payment_type", (String) hashMap.get("payment_type"));
        contentValues.put(DBtablas.value_payment_sales, (String) hashMap.get(DBtablas.value_payment_sales));
        contentValues.put("observaciones", (String) hashMap.get("observaciones"));
        contentValues.put("suspended_id", (String) hashMap.get("suspended_id"));
        writableDatabase.insert(DBtablas.TABLA_SALES, null, contentValues);
        writableDatabase.close();
    }

    public String userLogueado(String str, String str2) {
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT * FROM ospos_employees WHERE username = ? AND person_id = ? ", new String[]{str, str2});
        return rawQuery.moveToLast() ? rawQuery.getString(2) : "No";
    }

    public String nameLogueado() {
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT  * FROM ospos_user_logueado", null);
        return rawQuery.moveToLast() ? rawQuery.getString(0) : "No";
    }

    public String idLogueado() {
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT  * FROM ospos_user_logueado", null);
        return rawQuery.moveToLast() ? rawQuery.getString(2) : "No";
    }

    public String idbodegaUser() {
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT  * FROM ospos_user_logueado", null);
        return rawQuery.moveToLast() ? rawQuery.getString(3) : "No";
    }

    public void updateStock(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("quantity", (String) hashMap.get(DBtablas.quantity_purchased_susp));
        writableDatabase.update(DBtablas.TABLA_ITEMS_QUANT, contentValues, "item_id = ?", new String[]{(String) hashMap.get("item_id")});
        writableDatabase.close();
    }

    public void updateCartera(HashMap<String, String> hashMap) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("saldo", (String) hashMap.get("cartera"));
        writableDatabase.update(DBtablas.TABLA_CLIENTE, contentValues, "person_id = ?", new String[]{(String) hashMap.get("customer")});
        writableDatabase.close();
    }

    public String stockActual(String str, String str2) {
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT * FROM ospos_item_quantities WHERE item_id = ? AND location_id = ? ", new String[]{str, str2});
        return rawQuery.moveToLast() ? rawQuery.getString(2) : "No";
    }

    public String totalSuspend(String str, int i) {
        String str2 = "";
        String[] strArr = new String[1];
        int i2 = 0;
        strArr[0] = str;
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT * FROM ospos_sales_suspended_items WHERE sale_id = ? ", strArr);
        double d = 0.0d;
        while (i2 < i) {
            if (!rawQuery.moveToNext()) {
                return "No";
            }
            d += Double.parseDouble(rawQuery.getString(5)) * Double.parseDouble(rawQuery.getString(7));
            str2 = String.valueOf(d);
            i2++;
        }
        return str2;
    }

    public String totalesSuspendedUser(int i, String str) {
        str = "";
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT * FROM ospos_sales_suspended_payments ", null);
        double d = 0.0d;
        for (int i2 = 0; i2 < i; i2++) {
            if (!rawQuery.moveToNext()) {
                return "0";
            }
            d += rawQuery.getDouble(2);
            str = String.valueOf(d);
        }
        return str;
    }

    public String totalesVentasHoy(int i, String str) {
        String str2 = "";
        String[] strArr = new String[1];
        int i2 = 0;
        strArr[0] = str;
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT * FROM ospos_sales WHERE sale_time = ? ", strArr);
        double d = 0.0d;
        while (i2 < i) {
            if (!rawQuery.moveToNext()) {
                return "0";
            }
            d += rawQuery.getDouble(5);
            str2 = String.valueOf(d);
            i2++;
        }
        return str2;
    }

    public String totalesVentasFecha(int i, String str, String str2) {
        String str3 = "";
        String[] r3 = new String[2];
        int i2 = 0;
        r3[0] = str;
        r3[1] = str2;
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT * FROM ospos_sales WHERE sale_time >= ? AND sale_time <= ? ", r3);
        double d = 0.0d;
        while (i2 < i) {
            if (!rawQuery.moveToNext()) {
                return "0";
            }
            d += rawQuery.getDouble(5);
            str3 = String.valueOf(d);
            i2++;
        }
        return str3;
    }

    public String idzonaact(String str) {
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT * FROM ospos_zonas WHERE id_zona = ? ", new String[]{str});
        return rawQuery.moveToLast() ? rawQuery.getString(6) : "No";
    }

    public Cursor searchClientes(String str, String str2, String str3) {
        String[] strArr = new String[]{"person_id", DBtablas.first_name};
        if (str == null || str.length() <= 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM ospos_people AS p INNER JOIN ospost_asingcli AS a ON p.person_id=a.idcliente INNER JOIN ospos_user_logueado AS u ON a.idvendedor=u.person_id WHERE idvendedor = ");
            stringBuilder.append(str2);
            stringBuilder.append(" AND ");
            stringBuilder.append(DBtablas.idzona_asig);
            stringBuilder.append(" = ");
            stringBuilder.append(str3);
            stringBuilder.append("");
            return this.db.rawQuery(stringBuilder.toString(), null);
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("SELECT * FROM ospos_people AS p INNER JOIN ospost_asingcli AS a ON p.person_id=a.idcliente INNER JOIN ospos_user_logueado AS u ON a.idvendedor=u.person_id WHERE first_name LIKE '%");
        stringBuilder2.append(str);
        stringBuilder2.append("%' AND ");
        stringBuilder2.append(DBtablas.idvendedor_asig);
        stringBuilder2.append(" = ");
        stringBuilder2.append(str2);
        stringBuilder2.append(" AND ");
        stringBuilder2.append(DBtablas.idzona_asig);
        stringBuilder2.append(" = ");
        stringBuilder2.append(str3);
        stringBuilder2.append("");
        return this.db.rawQuery(stringBuilder2.toString(), null);
    }

    public Cursor getListClientes(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ospos_people AS p INNER JOIN ospost_asingcli AS a ON p.person_id=a.idcliente INNER JOIN ospos_user_logueado AS u ON a.idvendedor=u.person_id WHERE idvendedor = ");
        stringBuilder.append(str);
        stringBuilder.append(" AND ");
        stringBuilder.append(DBtablas.idzona_asig);
        stringBuilder.append(" = ");
        stringBuilder.append(str2);
        stringBuilder.append("");
        return this.db.rawQuery(stringBuilder.toString(), null);
    }

    public Cursor getListVentasHoy(String str) {
        return this.db.rawQuery("SELECT * FROM ospos_sales WHERE sale_time = ? ", new String[]{str});
    }

    public Cursor getListZonas() {
        return this.db.rawQuery("SELECT * FROM ospos_zonas", null);
    }

    public Cursor getListVentasFecha(String str, String str2) {
        return this.db.rawQuery("SELECT * FROM ospos_sales WHERE sale_time >= ? AND sale_time <= ? ", new String[]{str, str2});
    }

    public Cursor getListInventario(String str) {
        Double.valueOf(0.0d);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ospos_items AS i INNER JOIN ospos_item_quantities AS q ON i.item_id=q.item_id WHERE location_id = ");
        stringBuilder.append(str);
        stringBuilder.append(" ");
        return this.db.rawQuery(stringBuilder.toString(), null);
    }

    public Cursor searchInventario(String str, String str2) {
        String[] strArr = new String[]{"person_id", DBtablas.first_name};
        if (str == null || str.length() <= 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM ospos_items AS i INNER JOIN ospos_item_quantities AS q ON i.item_id=q.item_id WHERE location_id = ");
            stringBuilder.append(str2);
            stringBuilder.append(" ");
            return this.db.rawQuery(stringBuilder.toString(), null);
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("SELECT * FROM ospos_items AS i INNER JOIN ospos_item_quantities AS q ON i.item_id=q.item_id  WHERE name LIKE '%");
        stringBuilder2.append(str);
        stringBuilder2.append("%' AND ");
        stringBuilder2.append("location_id");
        stringBuilder2.append(" = ");
        stringBuilder2.append(str2);
        stringBuilder2.append(" ");
        return this.db.rawQuery(stringBuilder2.toString(), null);
    }

    public void deletedSalesSuspend(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(DBtablas.TABLA_SALES_SUSPEND, "sale_id=?", new String[]{str});
        writableDatabase.close();
    }

    public void deletedSalesSuspendItems(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(DBtablas.TABLA_SALES_ITEMS_SUSPEND, "sale_id=?", new String[]{str});
        writableDatabase.close();
    }

    public void deletedSalesSuspendPayments(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(DBtablas.TABLA_SALES_PAYMENTS_SUSPEND, "sale_id=?", new String[]{str});
        writableDatabase.close();
    }
}
