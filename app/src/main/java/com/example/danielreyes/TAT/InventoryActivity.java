package com.example.danielreyes.TAT;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.models.DBtablas;
import java.text.DecimalFormat;
import java.util.HashMap;

public class InventoryActivity extends AppCompatActivity {
    String apellCustomer;
    String bodegaLogeado;
    TextView campoId;
    TextView campoNombre;
    TextView campoPrecio;
    TextView campoStock;
    Button cancelar;
    String ccCustomer;
    CheckBox checkCambio;
    DBconexion controller = new DBconexion(this);
    String dirCustomer;
    Button guardar;
    String iduserLogeado;
    String nameCustomer;
    String nameLogeado;
    String prueba;
    Double saleCantidad;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_inventory);
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        this.bodegaLogeado = usuario.getBodega();
        getSupportActionBar();
        getSupportActionBar().setTitle(this.nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        Intent intent = getIntent();
        final String string = intent.getExtras().getString("IDE_ITEM");
        final String string2 = intent.getExtras().getString("IDE_SUSPENDED");
        String string3 = intent.getExtras().getString("NAME_ITEM");
        final String string4 = intent.getExtras().getString("PRECIO_ITEM");
        final String string5 = intent.getExtras().getString("COMPRA_ITEM");
        intent.getExtras().getString("BODEGA_ITEM");
        final double d = intent.getExtras().getDouble("STOCK_ITEM");
        this.nameCustomer = intent.getExtras().getString("CLI_NAME");
        this.apellCustomer = intent.getExtras().getString("CLI_APELL");
        this.ccCustomer = intent.getExtras().getString("CLI_CC");
        this.dirCustomer = intent.getExtras().getString("CLI_DIR");
        this.guardar = (Button) findViewById(R.id.btn_guardar);
        this.cancelar = (Button) findViewById(R.id.btn_cancelar);
        this.campoNombre = (TextView) findViewById(R.id.name_item);
        this.campoPrecio = (TextView) findViewById(R.id.price_item);
        this.campoStock = (TextView) findViewById(R.id.stock_item);
        this.checkCambio = (CheckBox) findViewById(R.id.chckCambio);
        final EditText editText = (EditText) findViewById(R.id.cantidad);
        String format = new DecimalFormat("##,##0.00").format(Double.valueOf(string4));
        this.campoNombre.setText(string3);
        TextView textView = this.campoPrecio;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$ ");
        stringBuilder.append(format);
        textView.setText(stringBuilder.toString());
        this.campoStock.setText(String.valueOf(d));
        this.guardar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (editText.getText().toString().equals("")) {
                    new Builder(InventoryActivity.this).setMessage((CharSequence) "No has ingresado la cantidad").setNegativeButton((CharSequence) "Intente de nuevo", null).create().show();
                    return;
                }
                double parseDouble = Double.parseDouble(editText.getText().toString());
                Double.valueOf(d - parseDouble);
                if (parseDouble <= d) {
                    String valueOf = String.valueOf(Double.valueOf(d - parseDouble));
                    if (InventoryActivity.this.checkCambio.isChecked()) {
                        InventoryActivity.this.addNewItem(string, string2, InventoryActivity.this.iduserLogeado, editText.getText().toString(), "0", "0", InventoryActivity.this.bodegaLogeado, valueOf);
                        return;
                    } else {
                        InventoryActivity.this.addNewItem(string, string2, InventoryActivity.this.iduserLogeado, editText.getText().toString(), string5, string4, InventoryActivity.this.bodegaLogeado, valueOf);
                        return;
                    }
                }
                new Builder(InventoryActivity.this).setMessage((CharSequence) "No existe esa cantidad en el inventario").setNegativeButton((CharSequence) "Intente de nuevo", null).create().show();
            }
        });
        this.cancelar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(InventoryActivity.this.getApplicationContext(), ListItemsPedidoActivity.class);
                intent.putExtra("CLI_NAME", InventoryActivity.this.nameCustomer);
                intent.putExtra("CLI_APELL", InventoryActivity.this.apellCustomer);
                intent.putExtra("CLI_CC", InventoryActivity.this.ccCustomer);
                intent.putExtra("CLI_DIR", InventoryActivity.this.dirCustomer);
                InventoryActivity.this.startActivity(intent);
            }
        });
    }

    public void addNewItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        HashMap hashMap = new HashMap();
        hashMap.put("sale_id", str2);
        hashMap.put("item_id", str);
        hashMap.put(DBtablas.quantity_purchased_susp, str4);
        hashMap.put(DBtablas.item_cost_price_susp, str5);
        hashMap.put(DBtablas.item_unit_price_susp, str6);
        hashMap.put(DBtablas.item_location_susp, str7);
        hashMap.put("estate", "0");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("item_id", str);
        hashMap2.put(DBtablas.quantity_purchased_susp, str8);
        if (str2 == null || str2 == "") {
            Toast.makeText(getApplicationContext(), "error en el ingreso", 1).show();
            return;
        }
        this.controller.insertItemsSalesSuspended(hashMap);
        this.controller.updateStock(hashMap2);
        callHomeActivity(str2, str3);
    }

    public void callHomeActivity(String str, String str2) {
        Intent intent = new Intent(getApplicationContext(), ListItemsPedidoActivity.class);
        intent.putExtra("ID_SALES_SUSPEND", str);
        intent.putExtra("ID_USER", str2);
        intent.putExtra("CLI_NAME", this.nameCustomer);
        intent.putExtra("CLI_APELL", this.apellCustomer);
        intent.putExtra("CLI_CC", this.ccCustomer);
        intent.putExtra("CLI_DIR", this.dirCustomer);
        startActivity(intent);
    }
}
