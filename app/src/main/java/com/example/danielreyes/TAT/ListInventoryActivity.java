package com.example.danielreyes.TAT;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import com.example.danielreyes.TAT.controllers.Items;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.models.DBtablas;
import com.example.danielreyes.TAT.views.AdapterItemsSearch;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;

public class ListInventoryActivity extends AppCompatActivity {
    AdapterItemsSearch adapter;
    String bodegaLogeado;
    DBconexion controller = new DBconexion(this);
    String iduserLogeado;
    ArrayList<Items> inventario = new ArrayList();
    ProgressDialog itemsDialog;
    ProgressDialog itemsquanty;
    ListView listViewInventario;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            ListInventoryActivity.this.getSupportFragmentManager().beginTransaction();
            ListInventoryActivity.this.getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_home /*2131296427*/:
                    ListInventoryActivity.this.startActivity(new Intent(ListInventoryActivity.this.getApplicationContext(), PedidosActivity.class));
                    break;
                case R.id.navigation_albums /*2131296429*/:
                    ListInventoryActivity.this.startActivity(new Intent(ListInventoryActivity.this.getApplicationContext(), opcionesActivity.class));
                    break;
                case R.id.navigation_dashboard /*2131296430*/:
                    return true;
                case R.id.navigation_notifications /*2131296432*/:
                    ListInventoryActivity.this.startActivity(new Intent(ListInventoryActivity.this.getApplicationContext(), ZonasActivity.class));
                    break;
                case R.id.navigation_songs /*2131296433*/:
                    ListInventoryActivity.this.startActivity(new Intent(ListInventoryActivity.this.getApplicationContext(), SynPedidosActivity.class));
                    break;
            }
            return false;
        }
    };
    String nameLogeado;
    ProgressDialog prgDialog;
    SearchView sv;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_list_inventory);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        this.bodegaLogeado = usuario.getBodega();
        getSupportActionBar();
        getSupportActionBar().setTitle(this.nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.listViewInventario = (ListView) findViewById(R.id.listProductos);
        this.sv = (SearchView) findViewById(R.id.sv);
        this.adapter = new AdapterItemsSearch(this, this.inventario);
        listInventario(this.bodegaLogeado);
        this.sv.setOnQueryTextListener(new OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                return false;
            }

            public boolean onQueryTextChange(String str) {
                ListInventoryActivity.this.getSearchInventario(str, ListInventoryActivity.this.bodegaLogeado);
                return false;
            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inventario_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.btn_syn_items) {
            return super.onOptionsItemSelected(menuItem);
        }
        this.controller.vaciarItems();
        syncdatosItems();
        this.controller.vaciarItemsquanti();
        syncdatosItemsquant();
        return true;
    }

    private void listInventario(String str) {
        DBconexion dBconexion = new DBconexion(this);
        dBconexion.open();
        Cursor listInventario = dBconexion.getListInventario(str);
        while (listInventario.moveToNext()) {
            String string = listInventario.getString(0);
            String string2 = listInventario.getString(6);
            Double valueOf = Double.valueOf(quanty(Integer.valueOf(listInventario.getInt(9)), str));
            Integer valueOf2 = Integer.valueOf(listInventario.getInt(9));
            if (valueOf.doubleValue() > 0.0d) {
                Items items = new Items();
                items.setNombre(string);
                items.setPrecioventa(string2);
                items.setStock(valueOf);
                items.setId(valueOf2);
                this.inventario.add(items);
            }
        }
        dBconexion.close();
        this.listViewInventario.setAdapter(this.adapter);
    }

    private void getSearchInventario(String str, String str2) {
        this.inventario.clear();
        DBconexion dBconexion = new DBconexion(this);
        dBconexion.open();
        Cursor searchInventario = dBconexion.searchInventario(str, str2);
        while (searchInventario.moveToNext()) {
            String string = searchInventario.getString(0);
            String string2 = searchInventario.getString(6);
            Double valueOf = Double.valueOf(quanty(Integer.valueOf(searchInventario.getInt(9)), str2));
            Integer valueOf2 = Integer.valueOf(searchInventario.getInt(9));
            if (valueOf.doubleValue() > 0.0d) {
                Items items = new Items();
                items.setNombre(string);
                items.setPrecioventa(string2);
                items.setStock(valueOf);
                items.setId(valueOf2);
                this.inventario.add(items);
            }
        }
        dBconexion.close();
        this.listViewInventario.setAdapter(this.adapter);
    }

    public double quanty(Integer num, String str) {
        String valueOf = String.valueOf(num);
        Double.valueOf(0.0d);
        SQLiteDatabase readableDatabase = new DBconexion(this).getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM ospos_item_quantities WHERE item_id = ? AND location_id = ?", new String[]{valueOf, str});
        if (rawQuery.moveToNext()) {
            Double valueOf2 = Double.valueOf(rawQuery.getDouble(2));
            readableDatabase.close();
            return valueOf2.doubleValue();
        }
        readableDatabase.close();
        return 0.0d;
    }

    public void syncdatosItems() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllItems().size() == 0) {
            this.itemsDialog = new ProgressDialog(this);
            this.itemsDialog.setTitle("Sincronizando Datos...");
            this.itemsDialog.setMessage("Espere un momento");
            this.itemsDialog.show();
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkitems.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        ListInventoryActivity.this.itemsDialog.dismiss();
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put("name", jSONArray.getJSONObject(i2).getString("name"));
                                hashMap.put(DBtablas.category, jSONArray.getJSONObject(i2).getString(DBtablas.category));
                                hashMap.put(DBtablas.supplier_id, jSONArray.getJSONObject(i2).getString(DBtablas.supplier_id));
                                hashMap.put(DBtablas.item_number, jSONArray.getJSONObject(i2).getString(DBtablas.item_number));
                                hashMap.put("description", jSONArray.getJSONObject(i2).getString("description"));
                                hashMap.put(DBtablas.cost_price, jSONArray.getJSONObject(i2).getString(DBtablas.cost_price));
                                hashMap.put(DBtablas.unit_price, jSONArray.getJSONObject(i2).getString(DBtablas.unit_price));
                                hashMap.put("quantity", jSONArray.getJSONObject(i2).getString("quantity"));
                                hashMap.put(DBtablas.reorder_level, jSONArray.getJSONObject(i2).getString(DBtablas.reorder_level));
                                hashMap.put("item_id", jSONArray.getJSONObject(i2).getString("item_id"));
                                hashMap.put(DBtablas.allow_alt_description, jSONArray.getJSONObject(i2).getString(DBtablas.allow_alt_description));
                                hashMap.put(DBtablas.is_serialized, jSONArray.getJSONObject(i2).getString(DBtablas.is_serialized));
                                hashMap.put("deleted", jSONArray.getJSONObject(i2).getString("deleted"));
                                hashMap.put(DBtablas.custom1, jSONArray.getJSONObject(i2).getString(DBtablas.custom1));
                                hashMap.put(DBtablas.custom2, jSONArray.getJSONObject(i2).getString(DBtablas.custom2));
                                hashMap.put(DBtablas.custom3, jSONArray.getJSONObject(i2).getString(DBtablas.custom3));
                                hashMap.put(DBtablas.custom4, jSONArray.getJSONObject(i2).getString(DBtablas.custom4));
                                hashMap.put(DBtablas.custom5, jSONArray.getJSONObject(i2).getString(DBtablas.custom5));
                                hashMap.put(DBtablas.custom6, jSONArray.getJSONObject(i2).getString(DBtablas.custom6));
                                hashMap.put(DBtablas.custom7, jSONArray.getJSONObject(i2).getString(DBtablas.custom7));
                                hashMap.put(DBtablas.custom8, jSONArray.getJSONObject(i2).getString(DBtablas.custom8));
                                hashMap.put(DBtablas.custom9, jSONArray.getJSONObject(i2).getString(DBtablas.custom9));
                                hashMap.put(DBtablas.custom10, jSONArray.getJSONObject(i2).getString(DBtablas.custom10));
                                hashMap.put("niff", jSONArray.getJSONObject(i2).getString("niff"));
                                hashMap.put("tributario", jSONArray.getJSONObject(i2).getString("tributario"));
                                hashMap.put(DBtablas.old_price_venta, jSONArray.getJSONObject(i2).getString(DBtablas.old_price_venta));
                                hashMap.put(DBtablas.tipo, jSONArray.getJSONObject(i2).getString(DBtablas.tipo));
                                hashMap.put(DBtablas.autoProduccion, jSONArray.getJSONObject(i2).getString(DBtablas.autoProduccion));
                                hashMap.put(DBtablas.Unid, jSONArray.getJSONObject(i2).getString(DBtablas.Unid));
                                hashMap.put(DBtablas.pic, jSONArray.getJSONObject(i2).getString(DBtablas.pic));
                                hashMap.put(DBtablas.niifC, jSONArray.getJSONObject(i2).getString(DBtablas.niifC));
                                hashMap.put(DBtablas.tribuC, jSONArray.getJSONObject(i2).getString(DBtablas.tribuC));
                                hashMap.put(DBtablas.percent_tax, jSONArray.getJSONObject(i2).getString(DBtablas.percent_tax));
                                hashMap.put("img", jSONArray.getJSONObject(i2).getString("img"));
                                hashMap.put("video", jSONArray.getJSONObject(i2).getString("video"));
                                hashMap.put("descripcion", jSONArray.getJSONObject(i2).getString("descripcion"));
                                hashMap.put("id_subcategoria", jSONArray.getJSONObject(i2).getString("id_subcategoria"));
                                hashMap.put(DBtablas.comision, jSONArray.getJSONObject(i2).getString(DBtablas.comision));
                                hashMap.put(DBtablas.oferta, jSONArray.getJSONObject(i2).getString(DBtablas.oferta));
                                hashMap.put(DBtablas.ind, jSONArray.getJSONObject(i2).getString(DBtablas.ind));
                                ListInventoryActivity.this.controller.insertItems(hashMap);
                            }
                            Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "Sincronizacion de productos exitosa!", 1).show();
                        } catch (JSONException e) {
                            Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "error en la sincronizacion de items!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    ListInventoryActivity.this.itemsDialog.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
            return;
        }
        Toast.makeText(getApplicationContext(), "Ya  hay datos en los items", 1).show();
    }

    public void syncdatosItemsquant() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllItemsquanty().size() == 0) {
            this.itemsquanty = new ProgressDialog(this);
            this.itemsquanty.setTitle("Sincronizando Inventario actual...");
            this.itemsquanty.setMessage("Espera un momento");
            this.itemsquanty.setProgressStyle(0);
            this.itemsquanty.show();
            this.itemsquanty.setCancelable(false);
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkitemsquanty.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        ListInventoryActivity.this.itemsquanty.dismiss();
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put("item_id", jSONArray.getJSONObject(i2).getString("item_id"));
                                hashMap.put("location_id", jSONArray.getJSONObject(i2).getString("location_id"));
                                hashMap.put("quantity", jSONArray.getJSONObject(i2).getString("quantity"));
                                hashMap.put(DBtablas.auto_id, jSONArray.getJSONObject(i2).getString(DBtablas.auto_id));
                                hashMap.put(DBtablas.mod, jSONArray.getJSONObject(i2).getString(DBtablas.mod));
                                hashMap.put("lote", jSONArray.getJSONObject(i2).getString("lote"));
                                hashMap.put("fecha_vence", jSONArray.getJSONObject(i2).getString("fecha_vence"));
                                hashMap.put("sucursal", jSONArray.getJSONObject(i2).getString("sucursal"));
                                ListInventoryActivity.this.controller.insertItemsquanty(hashMap);
                            }
                            Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "Sincronizacion de datos quantities exitosa!", 1).show();
                        } catch (JSONException e) {
                            Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "error en la sincronizacion quantity!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    ListInventoryActivity.this.itemsquanty.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        }
    }

    public void syncQuantities() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        this.controller.getAllItemsquanty();
        this.prgDialog = new ProgressDialog(this);
        this.prgDialog.setTitle("Sincronizando Inventario...");
        this.prgDialog.setMessage("Espera un momento");
        this.prgDialog.show();
        asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkitemsquanty.php", new AsyncHttpResponseHandler() {
            public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                if (i == 200) {
                    try {
                        JSONArray jSONArray = new JSONArray(new String(bArr));
                        HashMap hashMap = new HashMap();
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            hashMap.put("item_id", jSONArray.getJSONObject(i2).getString("item_id"));
                            hashMap.put("location_id", jSONArray.getJSONObject(i2).getString("location_id"));
                            hashMap.put("quantity", jSONArray.getJSONObject(i2).getString("quantity"));
                            hashMap.put(DBtablas.auto_id, jSONArray.getJSONObject(i2).getString(DBtablas.auto_id));
                            hashMap.put(DBtablas.mod, jSONArray.getJSONObject(i2).getString(DBtablas.mod));
                            hashMap.put("lote", jSONArray.getJSONObject(i2).getString("lote"));
                            hashMap.put("fecha_vence", jSONArray.getJSONObject(i2).getString("fecha_vence"));
                            hashMap.put("sucursal", jSONArray.getJSONObject(i2).getString("sucursal"));
                            ListInventoryActivity.this.controller.insertItemsquanty(hashMap);
                        }
                        Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "Sincronizacion de Inventario exitosa!", 1).show();
                        ListInventoryActivity.this.finish();
                    } catch (JSONException e) {
                        Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "error en la sincronizacion quantity!", 1).show();
                        e.printStackTrace();
                    }
                }
            }

            public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                ListInventoryActivity.this.prgDialog.hide();
                if (i == HttpStatus.SC_NOT_FOUND) {
                    Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                    Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                } else {
                    Toast.makeText(ListInventoryActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                }
            }
        });
    }
}
