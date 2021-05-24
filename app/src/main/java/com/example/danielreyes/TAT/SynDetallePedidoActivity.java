package com.example.danielreyes.TAT;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danielreyes.TAT.controllers.Pedidos;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.models.DBtablas;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpStatus;

public class SynDetallePedidoActivity extends AppCompatActivity {
    Button cancelar;
    DBconexion controller = new DBconexion(this);
    Button guardar;
    String idpedido;
    TextView idsuspended;
    String iduserLogeado;
    String nameLogeado;
    ProgressDialog pd;

    public void addSalesfin(String str, String str2) {
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_syn_detalle_pedido);
        this.pd = new ProgressDialog(this);
        this.pd.setTitle("Cargando sincronizacion...");
        this.pd.setMessage("Espera un momento");
        this.idsuspended = (TextView) findViewById(R.id.txt_idsuspend);
        this.guardar = (Button) findViewById(R.id.btn_guardar);
        this.cancelar = (Button) findViewById(R.id.btn_cancelar);
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        getSupportActionBar();
        getSupportActionBar().setTitle(this.nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            this.idpedido = ((Pedidos) bundle.getSerializable("detalle")).getId().toString();
        }
        this.idsuspended.setText(this.idpedido);
        this.guardar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SynDetallePedidoActivity.this.syncSQLiteSalesSuspend(SynDetallePedidoActivity.this.idpedido);
            }
        });
        this.cancelar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SynDetallePedidoActivity.this.startActivity(new Intent(SynDetallePedidoActivity.this.getApplicationContext(), SynPedidosActivity.class));
            }
        });
    }

    public void callHomeActivity(String str, String str2) {
        startActivity(new Intent(getApplicationContext(), CatalogoActivity.class));
    }

    public void syncSQLiteSalesSuspend(final String str) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllsuspendAdd(this.iduserLogeado, str).size() != 0) {
            this.pd.show();
            requestParams.put("suspendedJSON", this.controller.suspendJSONfromSQLite(this.iduserLogeado, str));
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/sincronizacion/insert_suspend.php", requestParams, new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        SynDetallePedidoActivity.this.pd.dismiss();
                        try {
                            JSONArray jSONArray = new JSONArray(new String(bArr));
                            System.out.println(jSONArray.length());
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                                String totalSuspend = SynDetallePedidoActivity.this.controller.totalSuspend(str, SynDetallePedidoActivity.this.controller.getAllsuspend_itemsIDsuspend(str).size());
                                HashMap hashMap = new HashMap();
                                hashMap.put("sale_time", jSONObject.get("date").toString());
                                hashMap.put("hora", jSONObject.get("time").toString());
                                hashMap.put("customer_id", jSONObject.get("customer").toString());
                                hashMap.put("sale_id", jSONObject.get("idsales").toString());
                                hashMap.put("payment_type", jSONObject.get("payment_type").toString());
                                hashMap.put(DBtablas.value_payment_sales, totalSuspend);
                                hashMap.put("observaciones", jSONObject.get("observaciones").toString());
                                hashMap.put("suspended_id", jSONObject.get("idsuspend").toString());
                                SynDetallePedidoActivity.this.controller.insertSalesfinal(hashMap);
                                SynDetallePedidoActivity.this.syncSQLiteSalesItemsSuspend(jSONObject.get("idsuspend").toString(), jSONObject.get("idsales").toString());
                                SynDetallePedidoActivity.this.syncSQLiteSalesSuspendPayments(jSONObject.get("idsuspend").toString(), jSONObject.get("idsales").toString());
                                SynDetallePedidoActivity.this.controller.deletedSalesSuspend(jSONObject.get("idsuspend").toString());
                                SynDetallePedidoActivity.this.controller.deletedSalesSuspendItems(jSONObject.get("idsuspend").toString());
                                SynDetallePedidoActivity.this.controller.deletedSalesSuspendPayments(jSONObject.get("idsuspend").toString());
                                Context applicationContext = SynDetallePedidoActivity.this.getApplicationContext();
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("Sincronizacion Completada Factura No. ");
                                stringBuilder.append(jSONObject.get("idsales").toString());
                                Toast.makeText(applicationContext, stringBuilder.toString(), 1).show();
                            }
                            Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Sincronizacion del Pedido Completada!", 1).show();
                            SynDetallePedidoActivity.this.startActivity(new Intent(SynDetallePedidoActivity.this.getApplicationContext(), SynPedidosActivity.class));
                        } catch (JSONException e) {
                            Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Error en el proceso de sincronizacion!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    SynDetallePedidoActivity.this.pd.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
            return;
        }
        Toast.makeText(getApplicationContext(), "Ya se sincronizo el pedido", 1).show();
    }

    public void syncSQLiteSalesItemsSuspend(String str, String str2) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllsuspendItemsAdd(str).size() != 0) {
            this.pd.show();
            requestParams.put("suspendedItemsJSON", this.controller.suspendItemsJSONfromSQLite(str, str2));
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/sincronizacion/insert_suspend_items.php", requestParams, new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        SynDetallePedidoActivity.this.pd.dismiss();
                        try {
                            System.out.println(new JSONArray(new String(bArr)).length());
                            Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Sincronizacion de los Items del Pedido Completada!", 1).show();
                            SynDetallePedidoActivity.this.startActivity(new Intent(SynDetallePedidoActivity.this.getApplicationContext(), SynPedidosActivity.class));
                        } catch (JSONException e) {
                            Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Error en el proceso de sincronizacion de los Items del Pedido!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    SynDetallePedidoActivity.this.pd.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
            return;
        }
        Toast.makeText(getApplicationContext(), "Ya se sincronizo los Items del Pedido", 1).show();
    }

    public void syncSQLiteSalesSuspendPayments(String str, String str2) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        if (this.controller.getAllsuspendPayments(str).size() != 0) {
            this.pd.show();
            requestParams.put("suspendedPaymentsJSON", this.controller.suspendPaymentsJSONfromSQLite(str, str2));
            asyncHttpClient.post("http://shared12.co-prueba.site/~ospostco/curso/sincronizacion/insert_payments.php", requestParams, new AsyncHttpResponseHandler() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        SynDetallePedidoActivity.this.pd.dismiss();
                        try {
                            System.out.println(new JSONArray(new String(bArr)).length());
                            Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Sincronizacion de Pagos del Pedido Completada!", 1).show();
                            SynDetallePedidoActivity.this.startActivity(new Intent(SynDetallePedidoActivity.this.getApplicationContext(), SynPedidosActivity.class));
                        } catch (JSONException e) {
                            Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Error en el proceso de sincronizacion de los Items del Pedido!", 1).show();
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    SynDetallePedidoActivity.this.pd.hide();
                    if (i == HttpStatus.SC_NOT_FOUND) {
                        Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Requested resource not found", 1).show();
                    } else if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Something went wrong at server end", 1).show();
                    } else {
                        Toast.makeText(SynDetallePedidoActivity.this.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", 1).show();
                    }
                }
            });
            return;
        }
        Toast.makeText(getApplicationContext(), "Ya se sincronizo los Pagos del Pedido", 1).show();
    }


}
