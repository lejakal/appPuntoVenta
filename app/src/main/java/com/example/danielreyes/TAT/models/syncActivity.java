package com.example.danielreyes.TAT.models;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.example.danielreyes.TAT.PedidosActivity;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import com.example.danielreyes.TAT.R;

public class syncActivity extends AppCompatActivity {
    String apellido_usuario;
    ProgressDialog asignaciones;
    ProgressDialog categorias;
    ProgressDialog category;
    ProgressDialog clientes;
    DBconexion controller = new DBconexion(this);
    int iduser;
    String iduserLogeado;
    ProgressDialog items;
    ProgressDialog itemsquanty;
    String nameLogeado;
    String nombre_usuario;
    ProgressDialog people;
    ProgressDialog prgDialog;
    ProgressDialog subcategorias;
    String usuario;
    ProgressDialog zonas;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_sync);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        syncdatosPeople();
        syncdatosZonas();
        syncdatosCustomer();
        syncdatosAsignaciones();
        syncdatosEmployees();
        syncdatoscategory();
        syncdatoscategorias();
        syncdatossubcategorias();
        syncdatosItems();
        syncdatosItemsquant();
        Intent intent = getIntent();
        this.iduser = intent.getIntExtra("ID_USER", -1);
        this.usuario = intent.getStringExtra("NAME_USER");
        this.nombre_usuario = intent.getStringExtra("NOMBRE_USER");
        this.apellido_usuario = intent.getStringExtra("APELLIDO_USER");
        getSupportActionBar();
        getSupportActionBar().setTitle(this.nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    public void syncdatosPeople() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllpeople().size() == 0) {
            this.people = new ProgressDialog(this);
            this.people.setMessage("Espera un momento");
            this.people.setTitle("Sincronizando de Datos...");
            this.people.setProgressStyle(0);
            this.people.show();
            this.people.setCancelable(false);
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkpeople.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        syncActivity.this.people.dismiss();
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
                                hashMap.put(DBtablas.cartera, jSONArray.getJSONObject(i2).getString(DBtablas.cartera));
                                syncActivity.this.controller.insertPeople(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de people exitosa!", 1).show();
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion de people!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    syncActivity.this.people.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        }
    }

    public void syncdatosCustomer() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllcustomer().size() == 0) {
            this.clientes = new ProgressDialog(this);
            this.clientes.setMessage("Espera un momento");
            this.clientes.setTitle("Sincronizando Clientes...");
            this.clientes.setProgressStyle(0);
            this.clientes.setCancelable(false);
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkcustomer.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        syncActivity.this.clientes.dismiss();
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
                                syncActivity.this.controller.insertCustomer(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de Customer exitosa!", 1).show();
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion de Customer!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    syncActivity.this.clientes.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        }
    }

    public void syncdatosZonas() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllzonas().size() == 0) {
            this.zonas = new ProgressDialog(this);
            this.zonas.setMessage("Espera un momento");
            this.zonas.setTitle("Sincronizando Zonas...");
            this.zonas.setProgressStyle(0);
            this.zonas.show();
            this.zonas.setCancelable(false);
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkzonas.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        syncActivity.this.zonas.dismiss();
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
                                syncActivity.this.controller.insertZonas(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de Zonas exitosa!", 1).show();
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion de zonas!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    syncActivity.this.zonas.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        }
    }

    public void syncdatosAsignaciones() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllasignaciones().size() == 0) {
            this.asignaciones = new ProgressDialog(this);
            this.asignaciones.setMessage("Espera un momento");
            this.asignaciones.setTitle("Sincronizando Clientes...");
            this.asignaciones.setProgressStyle(0);
            this.asignaciones.show();
            this.asignaciones.setCancelable(false);
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkasignaciones.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        syncActivity.this.asignaciones.dismiss();
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
                                syncActivity.this.controller.insertAsignaciones(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de Asignaciones exitosa!", 1).show();
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion de Asignaciones!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    syncActivity.this.asignaciones.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        }
    }

    public void syncdatosEmployees() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllemployees().size() == 0) {
            this.prgDialog = new ProgressDialog(this);
            this.prgDialog.setTitle("Sincronizando de Datos...");
            this.prgDialog.setMessage("Espera un momento");
            this.prgDialog.setProgressStyle(0);
            this.prgDialog.show();
            this.prgDialog.setCancelable(false);
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkemployees.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        syncActivity.this.prgDialog.dismiss();
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put("username", jSONArray.getJSONObject(i2).getString("username"));
                                hashMap.put("password", jSONArray.getJSONObject(i2).getString("password"));
                                hashMap.put("person_id", jSONArray.getJSONObject(i2).getString("person_id"));
                                hashMap.put("deleted", jSONArray.getJSONObject(i2).getString("deleted"));
                                hashMap.put("niff", jSONArray.getJSONObject(i2).getString("niff"));
                                hashMap.put("tributario", jSONArray.getJSONObject(i2).getString("tributario"));
                                hashMap.put("niffA", jSONArray.getJSONObject(i2).getString("niffA"));
                                hashMap.put("tribA", jSONArray.getJSONObject(i2).getString("tribA"));
                                hashMap.put(DBtablas.rol, jSONArray.getJSONObject(i2).getString(DBtablas.rol));
                                hashMap.put(DBtablas.id_empleado, jSONArray.getJSONObject(i2).getString(DBtablas.id_empleado));
                                hashMap.put("location_id", jSONArray.getJSONObject(i2).getString("location_id"));
                                syncActivity.this.controller.insertEmployees(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de Usuarios exitosa!", 1).show();
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion de Usuarios!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    syncActivity.this.prgDialog.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
            return;
        }
        Toast.makeText(getApplicationContext(), "Ya hay datos de Usuarios", 1).show();
    }

    public void syncdatosItemsquant() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllItemsquanty().size() == 0) {
            this.itemsquanty = new ProgressDialog(this);
            this.itemsquanty.setTitle("Sincronizando Inventario...");
            this.itemsquanty.setMessage("Espera un momento");
            this.itemsquanty.setProgressStyle(0);
            this.itemsquanty.show();
            this.itemsquanty.setCancelable(false);
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkitemsquanty.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        syncActivity.this.itemsquanty.dismiss();
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
                                syncActivity.this.controller.insertItemsquanty(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de datos quantities exitosa!", 1).show();
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion quantity!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    syncActivity.this.itemsquanty.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        }
    }

    public void syncdatoscategory() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllcategory().size() == 0) {
            this.category = new ProgressDialog(this);
            this.category.setTitle("Sincronizando Categorias...");
            this.category.setMessage("Espera un momento");
            this.category.setProgressStyle(0);
            this.category.show();
            this.category.setCancelable(false);
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkcategory.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        syncActivity.this.category.dismiss();
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put("id_categoria", jSONArray.getJSONObject(i2).getString("id_categoria"));
                                hashMap.put("name", jSONArray.getJSONObject(i2).getString("name"));
                                hashMap.put(DBtablas.name_en, jSONArray.getJSONObject(i2).getString(DBtablas.name_en));
                                hashMap.put("img", jSONArray.getJSONObject(i2).getString("img"));
                                hashMap.put("video", jSONArray.getJSONObject(i2).getString("video"));
                                syncActivity.this.controller.insertCategory(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de category exitosa!", 1).show();
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion de category!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    syncActivity.this.category.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        }
    }

    public void syncdatoscategorias() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllcategorias().size() == 0) {
            this.categorias = new ProgressDialog(this);
            this.categorias.setTitle("Sincronizando Categorias...");
            this.categorias.setMessage("Espera un momento");
            this.categorias.setProgressStyle(0);
            this.categorias.show();
            this.categorias.setCancelable(false);
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkcategorias.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put("id_categoria", jSONArray.getJSONObject(i2).getString("id_categoria"));
                                hashMap.put("name", jSONArray.getJSONObject(i2).getString("name"));
                                syncActivity.this.controller.insertCategorias(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de categorias exitosa!", 1).show();
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion de categorias!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    syncActivity.this.categorias.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        }
    }

    public void syncdatossubcategorias() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllsubcategorias().size() == 0) {
            this.subcategorias = new ProgressDialog(this);
            this.subcategorias.setTitle("Sincronizando Subcategorias...");
            this.subcategorias.setMessage("Espera un momento");
            this.subcategorias.setProgressStyle(0);
            this.subcategorias.show();
            this.subcategorias.setCancelable(false);
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checksubcategorias.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        syncActivity.this.subcategorias.dismiss();
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put("id_subcategoria", jSONArray.getJSONObject(i2).getString("id_subcategoria"));
                                hashMap.put(DBtablas.subcategoria_nombre, jSONArray.getJSONObject(i2).getString(DBtablas.subcategoria_nombre));
                                hashMap.put("id_categoria", jSONArray.getJSONObject(i2).getString("id_categoria"));
                                syncActivity.this.controller.insertSubcategorias(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de Subcategorias exitosa!", 1).show();
                            syncActivity.this.finish();
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion de Subcategorias!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    syncActivity.this.subcategorias.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
            return;
        }
        Toast.makeText(getApplicationContext(), "Ya hay datos de Subcategorias", 1).show();
    }

    public void syncdatosItems() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllItems().size() == 0) {
            this.items = new ProgressDialog(this);
            this.items.setTitle("Sincronizando Productos...");
            this.items.setMessage("Espera un momento");
            this.items.setProgressStyle(0);
            this.items.show();
            this.items.setCancelable(false);
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checkitems.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        syncActivity.this.items.dismiss();
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
                                syncActivity.this.controller.insertItems(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de datos exitosa!", 1).show();
                            Intent intent = syncActivity.this.getIntent();
                            syncActivity.this.iduser = intent.getIntExtra("ID_USER", -1);
                            intent = new Intent(syncActivity.this.getApplicationContext(), PedidosActivity.class);
                            intent.putExtra("ID_USER", syncActivity.this.iduser);
                            intent.putExtra("NAME_USER", syncActivity.this.usuario);
                            intent.putExtra("NOMBRE_USER", syncActivity.this.nombre_usuario);
                            intent.putExtra("APELLIDO_USER", syncActivity.this.apellido_usuario);
                            syncActivity.this.startActivity(intent);
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion de items!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    syncActivity.this.items.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
            return;
        }
        Toast.makeText(getApplicationContext(), "Ya  hay datos en los items", 1).show();
        Intent intent = new Intent(getApplicationContext(), PedidosActivity.class);
        this.iduser = getIntent().getIntExtra("ID_USER", -1);
        intent.putExtra("ID_USER", String.valueOf(this.iduser));
        intent.putExtra("NAME_USER", this.usuario);
        intent.putExtra("NOMBRE_USER", this.nombre_usuario);
        intent.putExtra("APELLIDO_USER", this.apellido_usuario);
        startActivity(intent);
    }

    public void syncdatosSuspend() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllsuspend().size() == 0) {
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checksuspend.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put("sale_time", jSONArray.getJSONObject(i2).getString("sale_time"));
                                hashMap.put("customer_id", jSONArray.getJSONObject(i2).getString("customer_id"));
                                hashMap.put(DBtablas.employee_id_suspended, jSONArray.getJSONObject(i2).getString(DBtablas.employee_id_suspended));
                                hashMap.put("comment", jSONArray.getJSONObject(i2).getString("comment"));
                                hashMap.put("sale_id", jSONArray.getJSONObject(i2).getString("sale_id"));
                                hashMap.put("payment_type", jSONArray.getJSONObject(i2).getString("payment_type"));
                                hashMap.put(DBtablas.idcoti, jSONArray.getJSONObject(i2).getString(DBtablas.idcoti));
                                hashMap.put(DBtablas.mesa, jSONArray.getJSONObject(i2).getString(DBtablas.mesa));
                                hashMap.put(DBtablas.pedido, jSONArray.getJSONObject(i2).getString(DBtablas.pedido));
                                hashMap.put(DBtablas.vendedor_suspended, jSONArray.getJSONObject(i2).getString(DBtablas.vendedor_suspended));
                                hashMap.put(DBtablas.cambio_suspended, jSONArray.getJSONObject(i2).getString(DBtablas.cambio_suspended));
                                hashMap.put("observaciones", jSONArray.getJSONObject(i2).getString("observaciones"));
                                hashMap.put("sucursal", jSONArray.getJSONObject(i2).getString("sucursal"));
                                syncActivity.this.controller.insertSuspend(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de Suspended exitosa!", 1).show();
                            syncActivity.this.finish();
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion de Suspended!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Ya hay datos de Suspended", 1).show();
        }
    }

    public void syncdatosSuspend_items() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllsuspend_items().size() == 0) {
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checksuspenditems.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put("sale_id", jSONArray.getJSONObject(i2).getString("sale_id"));
                                hashMap.put("item_id", jSONArray.getJSONObject(i2).getString("item_id"));
                                hashMap.put("description", jSONArray.getJSONObject(i2).getString("description"));
                                hashMap.put(DBtablas.serialnumber_susp, jSONArray.getJSONObject(i2).getString(DBtablas.serialnumber_susp));
                                hashMap.put("line", jSONArray.getJSONObject(i2).getString("line"));
                                hashMap.put(DBtablas.quantity_purchased_susp, jSONArray.getJSONObject(i2).getString(DBtablas.quantity_purchased_susp));
                                hashMap.put(DBtablas.item_cost_price_susp, jSONArray.getJSONObject(i2).getString(DBtablas.item_cost_price_susp));
                                hashMap.put(DBtablas.item_unit_price_susp, jSONArray.getJSONObject(i2).getString(DBtablas.item_unit_price_susp));
                                hashMap.put(DBtablas.discount_percent_susp, jSONArray.getJSONObject(i2).getString(DBtablas.discount_percent_susp));
                                hashMap.put(DBtablas.item_location_susp, jSONArray.getJSONObject(i2).getString(DBtablas.item_location_susp));
                                hashMap.put("suspended_id", jSONArray.getJSONObject(i2).getString("suspended_id"));
                                hashMap.put("lote", jSONArray.getJSONObject(i2).getString("lote"));
                                hashMap.put("fecha_vence", jSONArray.getJSONObject(i2).getString("fecha_vence"));
                                syncActivity.this.controller.insertSuspend_items(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de Suspend_items exitosa!", 1).show();
                            syncActivity.this.finish();
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion de Suspend_items!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Ya hay datos de Suspend_items", 1).show();
        }
    }

    public void syncdatosSuspend_items_tax() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllsuspend_items_tax().size() == 0) {
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checksuspenditemstax.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put("sale_id", jSONArray.getJSONObject(i2).getString("sale_id"));
                                hashMap.put("item_id", jSONArray.getJSONObject(i2).getString("item_id"));
                                hashMap.put("line", jSONArray.getJSONObject(i2).getString("line"));
                                hashMap.put("name", jSONArray.getJSONObject(i2).getString("name"));
                                hashMap.put(DBtablas.percent_suspended, jSONArray.getJSONObject(i2).getString(DBtablas.percent_suspended));
                                hashMap.put(DBtablas.id_taxes_suspended, jSONArray.getJSONObject(i2).getString(DBtablas.id_taxes_suspended));
                                syncActivity.this.controller.insertSuspend_items_tax(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de Suspended_item_tax exitosa!", 1).show();
                            syncActivity.this.finish();
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion de Suspended_item_tax!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Ya hay datos de Suspended_item_tax", 1).show();
        }
    }

    public void syncdatosSuspend_payment() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllsuspend_payments().size() == 0) {
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/checksuspendpay.php", new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                hashMap.put("sale_id", jSONArray.getJSONObject(i2).getString("sale_id"));
                                hashMap.put("payment_type", jSONArray.getJSONObject(i2).getString("payment_type"));
                                hashMap.put(DBtablas.payment_amount_susp, jSONArray.getJSONObject(i2).getString(DBtablas.payment_amount_susp));
                                hashMap.put(DBtablas.dias_credito_susp, jSONArray.getJSONObject(i2).getString(DBtablas.dias_credito_susp));
                                syncActivity.this.controller.insertSuspend_payments(hashMap);
                            }
                            Toast.makeText(syncActivity.this.getApplicationContext(), "Sincronizacion de Suspendpayment exitosa!", 1).show();
                            syncActivity.this.finish();
                        } catch (JSONException e) {
                            Toast.makeText(syncActivity.this.getApplicationContext(), "error en la sincronizacion de Suspendpayment!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(syncActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Ya hay datos de Suspendpayment", 1).show();
        }
    }
}
