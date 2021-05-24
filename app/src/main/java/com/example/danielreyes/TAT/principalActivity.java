package com.example.danielreyes.TAT;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.models.DBtablas;
import com.example.danielreyes.TAT.models.mdl_Items;
import com.example.danielreyes.TAT.views.AdapterItemsSearch;
import com.example.danielreyes.TAT.views.SettingsFragment;
import com.example.danielreyes.TAT.views.inventoryFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;

public class principalActivity extends AppCompatActivity {
    private static final String TAG = ClientesZonasActivity.class.getName().toString();
    String apellido_usuario;
    DBconexion controller = new DBconexion(this);
    Cursor cursor;
    private AdapterItemsSearch customAdapter;
    String iduser;
    String iduserLogeado;
    String idusuario;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Intent intent = principalActivity.this.getIntent();
            principalActivity.this.iduser = intent.getStringExtra("ID_USER");
            principalActivity.this.usuario = intent.getStringExtra("NAME_USER");
            principalActivity.this.nombre_usuario = intent.getStringExtra("NOMBRE_USER");
            principalActivity.this.apellido_usuario = intent.getStringExtra("APELLIDO_USER");
            FragmentTransaction beginTransaction = principalActivity.this.getSupportFragmentManager().beginTransaction();
            principalActivity.this.getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_home /*2131296427*/:
                    principalActivity.this.startActivity(new Intent(principalActivity.this.getApplicationContext(), PedidosActivity.class));
                    break;
                case R.id.navigation_albums /*2131296429*/:
                    return true;
                case R.id.navigation_dashboard /*2131296430*/:
                    beginTransaction.replace(R.id.fragment_container, new inventoryFragment()).commit();
                    break;
                case R.id.navigation_notifications /*2131296432*/:
                    principalActivity.this.startActivity(new Intent(principalActivity.this.getApplicationContext(), ZonasActivity.class));
                    break;
                case R.id.navigation_songs /*2131296433*/:
                    principalActivity.this.startActivity(new Intent(principalActivity.this.getApplicationContext(), SynPedidosActivity.class));
                    break;
            }
            return false;
        }
    };
    private TextView mTextMessage;
    mdl_Items models;
    String nameLogeado;
    String nombre_usuario;
    ProgressDialog prgDialog;
    String usuario;
    mdl_Items varitems;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_principal);
        this.prgDialog = new ProgressDialog(this);
        this.prgDialog.setTitle("Sincronizando Inventario...");
        this.prgDialog.setMessage("Espera un momento");
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        getSupportActionBar();
        getSupportActionBar().setTitle(this.nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new inventoryFragment()).commit();
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
        syncQuantities();
        return true;
    }

    public void cuenta() {
        Intent intent = new Intent(this, SettingsFragment.class);
        intent.putExtra("ID_USER", this.idusuario);
        intent.putExtra("NAME_USER", this.usuario);
        intent.putExtra("NOMBRE_USER", this.nombre_usuario);
        intent.putExtra("APELLIDO_USER", this.apellido_usuario);
        startActivity(intent);
    }

    public void syncdatosItems() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllItems().size() == 0) {
            this.prgDialog.show();
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkitems.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
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
                                principalActivity.this.controller.insertItems(hashMap);
                            }
                            Toast.makeText(principalActivity.this.getApplicationContext(), "Sincronizacion de productos exitosa!", 1).show();
                            principalActivity.this.finish();
                        } catch (JSONException e) {
                            Toast.makeText(principalActivity.this.getApplicationContext(), "error en la sincronizacion de items!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(principalActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(principalActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(principalActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
            return;
        }
        Toast.makeText(getApplicationContext(), "El inventario esta actualizado", 1).show();
    }

    public void syncQuantities() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        this.controller.getAllItemsquanty();
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
                            principalActivity.this.controller.insertItemsquanty(hashMap);
                        }
                        Toast.makeText(principalActivity.this.getApplicationContext(), "Sincronizacion de Inventario exitosa!", 1).show();
                        principalActivity.this.finish();
                    } catch (JSONException e) {
                        Toast.makeText(principalActivity.this.getApplicationContext(), "error en la sincronizacion quantity!", 1).show();
                        e.printStackTrace();
                    }
                }
            }

            public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                principalActivity.this.prgDialog.hide();
                if (i == HttpStatus.SC_NOT_FOUND) {
                    Toast.makeText(principalActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                    Toast.makeText(principalActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                } else {
                    Toast.makeText(principalActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                }
            }
        });
    }
}
