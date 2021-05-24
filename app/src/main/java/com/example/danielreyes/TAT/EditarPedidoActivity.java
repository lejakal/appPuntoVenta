package com.example.danielreyes.TAT;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.models.DBtablas;
import java.text.DecimalFormat;
import java.util.HashMap;

public class EditarPedidoActivity extends AppCompatActivity {
    String bodegaLogeado;
    TextView campoIdSuspend;
    TextView campoNombre;
    TextView campoPrecio;
    Button cancelar;
    String cant;
    DBconexion controller = new DBconexion(this);
    Button deleteItemBtn;
    Button guardar;
    String iditem;
    String idsuspend;
    String iduserLogeado;
    String nameLogeado;
    String nombreitem;
    String precio;
    String stock;
    String usuario;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_editar_pedido);
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
        Intent intent = getIntent();
        this.idsuspend = intent.getExtras().getString("ID_SUSPEND");
        this.iditem = intent.getExtras().getString("ID_ITEM");
        this.nombreitem = intent.getExtras().getString("NOMBRE_ITEM");
        this.cant = intent.getExtras().getString("CANTIDAD");
        this.precio = intent.getExtras().getString("VENTA");
        this.stock = this.controller.stockActual(this.iditem, this.bodegaLogeado);
        String format = new DecimalFormat("##,##0.00").format(Double.valueOf(this.precio));
        this.guardar = (Button) findViewById(R.id.btn_guardar);
        this.cancelar = (Button) findViewById(R.id.btn_cancelar);
        this.campoIdSuspend = (TextView) findViewById(R.id.txt_idsuspend);
        this.campoNombre = (TextView) findViewById(R.id.name_item);
        this.campoPrecio = (TextView) findViewById(R.id.price_item);
        final EditText editText = (EditText) findViewById(R.id.cantidad);
        this.campoIdSuspend.setText(this.idsuspend);
        this.campoNombre.setText(this.nombreitem);
        TextView textView = this.campoPrecio;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$ ");
        stringBuilder.append(format);
        textView.setText(stringBuilder.toString());
        editText.setText(this.cant);
        this.guardar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                double parseDouble = Double.parseDouble(editText.getText().toString());
                double parseDouble2 = Double.parseDouble(EditarPedidoActivity.this.cant);
                double parseDouble3 = Double.parseDouble(EditarPedidoActivity.this.stock);
                double d = (parseDouble3 + parseDouble2) - parseDouble;
                String valueOf = String.valueOf(d);
                if (d >= 0.0d) {
                    Context applicationContext = EditarPedidoActivity.this.getApplicationContext();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("stockUpdate-> ");
                    stringBuilder.append(d);
                    stringBuilder.append(" cantEscrita-> ");
                    stringBuilder.append(parseDouble);
                    stringBuilder.append(" cantSuspend->");
                    stringBuilder.append(parseDouble2);
                    stringBuilder.append(" stockActual->");
                    stringBuilder.append(parseDouble3);
                    Toast.makeText(applicationContext, stringBuilder.toString(), 1).show();
                    EditarPedidoActivity.this.editItem(EditarPedidoActivity.this.iditem, EditarPedidoActivity.this.idsuspend, editText.getText().toString(), valueOf);
                    return;
                }
                new Builder(EditarPedidoActivity.this).setMessage((CharSequence) "No existe esa cantidad en el inventario").setNegativeButton((CharSequence) "Intente de nuevo", null).create().show();
            }
        });
        this.cancelar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(EditarPedidoActivity.this.getApplicationContext(), DetallepedidoActivity.class);
                intent.putExtra("ID_SALES_SUSPEND", EditarPedidoActivity.this.idsuspend);
                EditarPedidoActivity.this.startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editar_item, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.btn_eliminar) {
            return super.onOptionsItemSelected(menuItem);
        }
        displayDialogEliminar(this.idsuspend, this.iditem);
        Context applicationContext = getApplicationContext();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Item id!");
        stringBuilder.append(this.iditem);
        Toast.makeText(applicationContext, stringBuilder.toString(), 1).show();
        return true;
    }

    public void editItem(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("sale_id", str2);
        hashMap.put("item_id", str);
        hashMap.put(DBtablas.quantity_purchased_susp, str3);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("item_id", str);
        hashMap2.put(DBtablas.quantity_purchased_susp, str4);
        if (str2 == null || str2 == "") {
            Toast.makeText(getApplicationContext(), "error en la editacion", 1).show();
            return;
        }
        this.controller.editItemsSuspended(hashMap);
        this.controller.updateStock(hashMap2);
        callHomeActivity(str2);
    }

    public void callHomeActivity(String str) {
        Toast.makeText(getApplicationContext(), "Producto Modificado correctamente!", 1).show();
        Intent intent = new Intent(getApplicationContext(), DetallepedidoActivity.class);
        intent.putExtra("ID_SALES_SUSPEND", str);
        startActivity(intent);
    }

    private void displayDialogEliminar(final String str, final String str2) {
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Eliminar el producto");
        dialog.setContentView(R.layout.layout_dialog);
        this.deleteItemBtn = (Button) dialog.findViewById(R.id.btn_deleted);
        this.deleteItemBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                double parseDouble = Double.parseDouble(EditarPedidoActivity.this.cant);
                double parseDouble2 = Double.parseDouble(EditarPedidoActivity.this.stock);
                double d = parseDouble2 + parseDouble;
                String valueOf = String.valueOf(d);
                Context applicationContext = EditarPedidoActivity.this.getApplicationContext();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("stockUpdate-> ");
                stringBuilder.append(d);
                stringBuilder.append(" cantSuspend->");
                stringBuilder.append(parseDouble);
                stringBuilder.append(" stockActual->");
                stringBuilder.append(parseDouble2);
                Toast.makeText(applicationContext, stringBuilder.toString(), 1).show();
                EditarPedidoActivity.this.editItem(EditarPedidoActivity.this.iditem, EditarPedidoActivity.this.idsuspend, String.valueOf(parseDouble), valueOf);
                EditarPedidoActivity.this.eliminarItem(str, str2);
            }
        });
        dialog.show();
    }

    public void eliminarItem(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("suspend_id", str);
        hashMap.put("item_id", str2);
        if (str == null || str == "") {
            Context applicationContext = getApplicationContext();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error no se pudo eliminar el producto del pedido ");
            stringBuilder.append(str);
            Toast.makeText(applicationContext, stringBuilder.toString(), 1).show();
            return;
        }
        this.controller.cancelarItemsSuspended(hashMap);
        envioZonas(str);
    }

    public void envioZonas(String str) {
        Intent intent = new Intent(getApplicationContext(), DetallepedidoActivity.class);
        intent.putExtra("ID_SALES_SUSPEND", str);
        startActivity(intent);
    }
}
