package com.example.danielreyes.TAT;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.TextView;
import android.widget.Toast;
import com.example.danielreyes.TAT.controllers.Customer;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.models.DBtablas;
import com.example.danielreyes.TAT.models.mdl_Items;
import com.example.danielreyes.TAT.views.AdapterClientesSearch;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;

public class ClientesZonasActivity extends AppCompatActivity {
    private static final String TAG = ClientesZonasActivity.class.getName().toString();
    AdapterClientesSearch adapter;
    TextView campoId;
    TextView campoNombre;
    TextView campoUser;
    ArrayList<Customer> clientes = new ArrayList();
    DBconexion controller = new DBconexion(this);
    Cursor cursor;
    String iduser;
    String iduserLogeado;
    ListView listViewClientes;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            ClientesZonasActivity.this.getSupportFragmentManager().beginTransaction();
            ClientesZonasActivity.this.getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_home /*2131296427*/:
                    ClientesZonasActivity.this.startActivity(new Intent(ClientesZonasActivity.this.getApplicationContext(), PedidosActivity.class));
                    break;
                case R.id.navigation_albums /*2131296429*/:
                    ClientesZonasActivity.this.startActivity(new Intent(ClientesZonasActivity.this.getApplicationContext(), opcionesActivity.class));
                    break;
                case R.id.navigation_dashboard /*2131296430*/:
                    ClientesZonasActivity.this.startActivity(new Intent(ClientesZonasActivity.this.getApplicationContext(), ListInventoryActivity.class));
                    break;
                case R.id.navigation_notifications /*2131296432*/:
                    return true;
                case R.id.navigation_songs /*2131296433*/:
                    ClientesZonasActivity.this.startActivity(new Intent(ClientesZonasActivity.this.getApplicationContext(), SynPedidosActivity.class));
                    break;
            }
            return false;
        }
    };
    mdl_Items models;
    String nameLogeado;
    ProgressDialog prgDialog;
    SearchView sv;
    String usuario;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_clientes_zonas);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        this.prgDialog = new ProgressDialog(this);
        this.prgDialog.setTitle("Sincronizando Clientes...");
        this.prgDialog.setMessage("Espera un momento");

        Intent intent = getIntent();
        final String string = intent.getExtras().getString("IDE_ZONA");
        String string2 = intent.getExtras().getString("NAME_ZONA");
        this.iduser = intent.getExtras().getString("ID_USER");
        getSupportActionBar();
        getSupportActionBar().setTitle(nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //idzonaact
        this.campoNombre = (TextView) findViewById(R.id.name_zona);
        this.listViewClientes = (ListView) findViewById(R.id.listClientes);
        this.sv = (SearchView) findViewById(R.id.sv);
        this.adapter = new AdapterClientesSearch(this, this.clientes);
        listClientes(this.iduserLogeado, string);
        this.campoNombre.setText(string2);
        this.sv.setOnQueryTextListener(new OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                return false;
            }

            public boolean onQueryTextChange(String str) {
                ClientesZonasActivity.this.getClientes(str, ClientesZonasActivity.this.iduserLogeado, string);
                return false;
            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    private void listClientes(String str, String str2) {
        DBconexion dBconexion = new DBconexion(this);
        dBconexion.open();
        Customer customer=null;
        Cursor listClientes = dBconexion.getListClientes(str, str2);
        while (listClientes.moveToNext()) {
            str2 = listClientes.getString(0);
            String string = listClientes.getString(13);
            String string2 = listClientes.getString(1);
            String string3 = listClientes.getString(4);
            String string4 = listClientes.getString(7);
            String string5 = listClientes.getString(18);
            Integer valueOf = Integer.valueOf(listClientes.getInt(11));
            customer = new Customer();
            customer.setNombres(str2);
            customer.setApellidos(string);
            customer.setCedula(string2);
            customer.setCartera(string5);
            customer.setTelefono(listClientes.getString(2));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(string3);
            stringBuilder.append(" ");
            stringBuilder.append(string4);
            customer.setDireccion(stringBuilder.toString());
            customer.setId(valueOf);
            customer.setEmpresa(name_tienda(valueOf));
            this.clientes.add(customer);
        }
        dBconexion.close();
        this.listViewClientes.setAdapter(this.adapter);
    }

    private void getClientes(String str, String str2, String str3) {
        this.clientes.clear();
        DBconexion dBconexion = new DBconexion(this);
        dBconexion.open();
        Customer customer=null;
        Cursor searchClientes = dBconexion.searchClientes(str, str2, str3);
        while (searchClientes.moveToNext()) {
            str2 = searchClientes.getString(0);
            str3 = searchClientes.getString(13);
            String string = searchClientes.getString(1);
            String string2 = searchClientes.getString(4);
            String string3 = searchClientes.getString(7);
            Integer valueOf = Integer.valueOf(searchClientes.getInt(11));
            customer = new Customer();
            customer.setNombres(str2);
            customer.setApellidos(str3);
            customer.setCedula(string);
            customer.setTelefono(searchClientes.getString(2));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(string2);
            stringBuilder.append(" ");
            stringBuilder.append(string3);
            customer.setDireccion(stringBuilder.toString());
            customer.setId(valueOf);
            customer.setEmpresa(name_tienda(valueOf));
            this.clientes.add(customer);
        }
        dBconexion.close();
        this.listViewClientes.setAdapter(this.adapter);
    }

    public String name_tienda(Integer num) {
        String valueOf = String.valueOf(num);
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_customers WHERE person_id = ?", new String[]{valueOf});
        return rawQuery.moveToNext() ? rawQuery.getString(6) : "naa";
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.clientes_zonas, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.btn_syn_clientes) {
            return super.onOptionsItemSelected(menuItem);
        }
        this.controller.vaciarPeople();
        this.controller.vaciarCustomer();
        this.controller.vaciarAsig();
        syncdatosPeople();
        syncdatosZonas();
        syncdatosCustomer();
        syncdatosAsignaciones();
        return true;
    }

    public void syncdatosPeople() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllpeople().size() == 0) {
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkpeople.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put(DBtablas.first_name, jSONArray.getJSONObject(i2).getString(DBtablas.first_name));
                                hashMap.put(DBtablas.last_name, jSONArray.getJSONObject(i2).getString(DBtablas.last_name));
                                hashMap.put(DBtablas.phone_number, jSONArray.getJSONObject(i2).getString(DBtablas.phone_number));
                                hashMap.put("email", jSONArray.getJSONObject(i2).getString("email"));
                                hashMap.put(DBtablas.address_1, jSONArray.getJSONObject(i2).getString(DBtablas.address_1));
                                hashMap.put(DBtablas.address_2, jSONArray.getJSONObject(i2).getString(DBtablas.address_2));
                                hashMap.put(DBtablas.city, jSONArray.getJSONObject(i2).getString(DBtablas.city));
                                hashMap.put(DBtablas.state, jSONArray.getJSONObject(i2).getString(DBtablas.state));
                                hashMap.put(DBtablas.zip, jSONArray.getJSONObject(i2).getString(DBtablas.zip));
                                hashMap.put(DBtablas.country, jSONArray.getJSONObject(i2).getString(DBtablas.country));
                                hashMap.put(DBtablas.comments, jSONArray.getJSONObject(i2).getString(DBtablas.comments));
                                hashMap.put("person_id", jSONArray.getJSONObject(i2).getString("person_id"));
                                hashMap.put(DBtablas.DigVerif, jSONArray.getJSONObject(i2).getString(DBtablas.DigVerif));
                                hashMap.put(DBtablas.apellidos, jSONArray.getJSONObject(i2).getString(DBtablas.apellidos));
                                hashMap.put(DBtablas.tipoPeople, jSONArray.getJSONObject(i2).getString(DBtablas.tipoPeople));
                                hashMap.put("img", jSONArray.getJSONObject(i2).getString("img"));
                                hashMap.put(DBtablas.categoCliente, jSONArray.getJSONObject(i2).getString(DBtablas.categoCliente));
                                hashMap.put(DBtablas.afiliado, jSONArray.getJSONObject(i2).getString(DBtablas.afiliado));
                                ClientesZonasActivity.this.controller.insertPeople(hashMap);
                            }
                            Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Sincronizacion de people exitosa!", 1).show();
                        } catch (JSONException e) {
                            Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "error en la sincronizacion de people!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Ya hay datos de people", 1).show();
        }
    }

    public void syncdatosCustomer() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllcustomer().size() == 0) {
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkcustomer.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put("person_id", jSONArray.getJSONObject(i2).getString("person_id"));
                                hashMap.put(DBtablas.account_number, jSONArray.getJSONObject(i2).getString(DBtablas.account_number));
                                hashMap.put(DBtablas.taxable, jSONArray.getJSONObject(i2).getString(DBtablas.taxable));
                                hashMap.put("deleted", jSONArray.getJSONObject(i2).getString("deleted"));
                                hashMap.put("niff", jSONArray.getJSONObject(i2).getString("niff"));
                                hashMap.put("tributario", jSONArray.getJSONObject(i2).getString("tributario"));
                                hashMap.put(DBtablas.empresa, jSONArray.getJSONObject(i2).getString(DBtablas.empresa));
                                hashMap.put(DBtablas.actividad, jSONArray.getJSONObject(i2).getString(DBtablas.actividad));
                                hashMap.put(DBtablas.nacimiento, jSONArray.getJSONObject(i2).getString(DBtablas.nacimiento));
                                hashMap.put(DBtablas.tipo_cliente, jSONArray.getJSONObject(i2).getString(DBtablas.tipo_cliente));
                                hashMap.put(DBtablas.estadoCli, jSONArray.getJSONObject(i2).getString(DBtablas.estadoCli));
                                hashMap.put(DBtablas.tipodoc, jSONArray.getJSONObject(i2).getString(DBtablas.tipodoc));
                                hashMap.put("niffA", jSONArray.getJSONObject(i2).getString("niffA"));
                                hashMap.put("tribA", jSONArray.getJSONObject(i2).getString("tribA"));
                                hashMap.put(DBtablas.niifDev, jSONArray.getJSONObject(i2).getString(DBtablas.niifDev));
                                hashMap.put(DBtablas.tribDev, jSONArray.getJSONObject(i2).getString(DBtablas.tribDev));
                                hashMap.put(DBtablas.niifDcto, jSONArray.getJSONObject(i2).getString(DBtablas.niifDcto));
                                hashMap.put(DBtablas.tribDcto, jSONArray.getJSONObject(i2).getString(DBtablas.tribDcto));
                                ClientesZonasActivity.this.controller.insertCustomer(hashMap);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "error en la sincronizacion de Customer!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        }
    }

    public void syncdatosZonas() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllzonas().size() == 0) {
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkzonas.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put(DBtablas.id_zona, jSONArray.getJSONObject(i2).getString(DBtablas.id_zona));
                                hashMap.put(DBtablas.nom_zona, jSONArray.getJSONObject(i2).getString(DBtablas.nom_zona));
                                hashMap.put(DBtablas.municipio_zona, jSONArray.getJSONObject(i2).getString(DBtablas.municipio_zona));
                                hashMap.put(DBtablas.departamento_zona, jSONArray.getJSONObject(i2).getString(DBtablas.departamento_zona));
                                hashMap.put("descripcion", jSONArray.getJSONObject(i2).getString("descripcion"));
                                hashMap.put(DBtablas.estado_zona, jSONArray.getJSONObject(i2).getString(DBtablas.estado_zona));
                                hashMap.put(DBtablas.id_zna, jSONArray.getJSONObject(i2).getString(DBtablas.id_zna));
                                hashMap.put(DBtablas.clave_zna, jSONArray.getJSONObject(i2).getString(DBtablas.clave_zna));
                                ClientesZonasActivity.this.controller.insertZonas(hashMap);
                            }
                            Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Sincronizacion de Zonas exitosa!", 1).show();
                            ClientesZonasActivity.this.finish();
                        } catch (JSONException e) {
                            Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "error en la sincronizacion de zonas!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        }
    }

    public void syncdatosAsignaciones() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllasignaciones().size() == 0) {
            this.prgDialog.show();
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkasignaciones.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put(DBtablas.idasig, jSONArray.getJSONObject(i2).getString(DBtablas.idasig));
                                hashMap.put(DBtablas.idcliente_asig, jSONArray.getJSONObject(i2).getString(DBtablas.idcliente_asig));
                                hashMap.put(DBtablas.idvendedor_asig, jSONArray.getJSONObject(i2).getString(DBtablas.idvendedor_asig));
                                hashMap.put(DBtablas.fecha_asig, jSONArray.getJSONObject(i2).getString(DBtablas.fecha_asig));
                                hashMap.put(DBtablas.idruta_asig, jSONArray.getJSONObject(i2).getString(DBtablas.idruta_asig));
                                hashMap.put(DBtablas.observacion_asig, jSONArray.getJSONObject(i2).getString(DBtablas.observacion_asig));
                                hashMap.put("hora", jSONArray.getJSONObject(i2).getString("hora"));
                                hashMap.put(DBtablas.reasigna_asig, jSONArray.getJSONObject(i2).getString(DBtablas.reasigna_asig));
                                hashMap.put(DBtablas.idzona_asig, jSONArray.getJSONObject(i2).getString(DBtablas.idzona_asig));
                                ClientesZonasActivity.this.controller.insertAsignaciones(hashMap);
                            }
                            Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Sincronizacion de Asignaciones exitosa!", 1).show();
                            ClientesZonasActivity.this.finish();
                        } catch (JSONException e) {
                            Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "error en la sincronizacion de Asignaciones!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    ClientesZonasActivity.this.prgDialog.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(ClientesZonasActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
            return;
        }
        Toast.makeText(getApplicationContext(), "Ya hay datos de Asignaciones", 1).show();
    }
}
