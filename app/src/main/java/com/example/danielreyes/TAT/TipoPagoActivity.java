package com.example.danielreyes.TAT;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.models.DBtablas;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TipoPagoActivity extends AppCompatActivity {
    String apellCustomer;
    Button cancelar;
    String ccCustomer;
    DBconexion controller = new DBconexion(this);
    String dirCustomer;
    Button guardar;
    String idsuspended;
    String iduser;
    String nameCustomer;
    String txtspinner;
    String typepago;
    String usuario;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_tipo_pago);
        new SimpleDateFormat("EEEE").format(new Date());
        Intent intent = getIntent();
        this.iduser = intent.getStringExtra("ID_USER");
        this.usuario = intent.getStringExtra("NAME_USER");
        this.idsuspended = intent.getStringExtra("suspend_id");
        this.nameCustomer = intent.getStringExtra("CLI_NAME");
        this.apellCustomer = intent.getStringExtra("CLI_APELL");
        this.ccCustomer = intent.getStringExtra("CLI_CC");
        this.dirCustomer = intent.getStringExtra("CLI_DIR");
        final String totalSuspend = this.controller.totalSuspend(this.idsuspended, this.controller.getAllsuspend_itemsIDsuspend(this.idsuspended).size());
        String format = new DecimalFormat("##,##0.00").format(Double.valueOf(totalSuspend));
        final EditText editText = (EditText) findViewById(R.id.diaspago);
        TextView textView = (TextView) findViewById(R.id.txt_idsuspend);
        TextView textView2 = (TextView) findViewById(R.id.valor_suspended);
        final TextView textView3 = (TextView) findViewById(R.id.observacion);
        ((TextView) findViewById(R.id.nameCliente)).setText(this.nameCustomer);
        ((TextView) findViewById(R.id.apelCliente)).setText(this.apellCustomer);
        ((TextView) findViewById(R.id.ccCliente)).setText(this.ccCustomer);
        ((TextView) findViewById(R.id.dirCliente)).setText(this.dirCustomer);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$ ");
        stringBuilder.append(format);
        textView2.setText(stringBuilder.toString());
        this.guardar = (Button) findViewById(R.id.btn_guardar);
        this.cancelar = (Button) findViewById(R.id.btn_cancelar);
        final TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.tpago);
        textView.setText(this.idsuspended);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerPay);
        spinner.setAdapter(new ArrayAdapter(this, 17367048, new String[]{"Efectivo", "Credito"}));
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ((TextView) view).setTextSize(18.0f);
                Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(i), 0).show();
                TipoPagoActivity.this.txtspinner = (String) adapterView.getItemAtPosition(i);
                if (TipoPagoActivity.this.txtspinner.equals("Efectivo")) {
                    editText.setVisibility(4);
                    textInputLayout.setVisibility(4);
                    TipoPagoActivity.this.typepago = "1";
                    return;
                }
                editText.setVisibility(0);
                textInputLayout.setVisibility(0);
                TipoPagoActivity.this.typepago = "2";
            }
        });
        this.guardar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                TipoPagoActivity.this.finalizarSuspend(TipoPagoActivity.this.idsuspended, TipoPagoActivity.this.typepago, editText.getText().toString(), totalSuspend, textView3.getText().toString());
            }
        });
        this.cancelar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(TipoPagoActivity.this.getApplicationContext(), ListItemsPedidoActivity.class);
                intent.putExtra("ID_SALES_SUSPEND", TipoPagoActivity.this.idsuspended);
                intent.putExtra("ID_USER", TipoPagoActivity.this.iduser);
                intent.putExtra("CLI_NAME", TipoPagoActivity.this.nameCustomer);
                intent.putExtra("CLI_APELL", TipoPagoActivity.this.apellCustomer);
                intent.putExtra("CLI_CC", TipoPagoActivity.this.ccCustomer);
                intent.putExtra("CLI_DIR", TipoPagoActivity.this.dirCustomer);
                TipoPagoActivity.this.startActivity(intent);
            }
        });
    }

    public void finalizarSuspend(String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        hashMap.put("suspend_id", str);
        hashMap.put("payment_type", str2);
        hashMap.put(DBtablas.observacion_asig, str5);
        hashMap.put("estate", "1");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("suspend_id", str);
        hashMap2.put("Tipo", str2);
        hashMap2.put("Valor", str4);
        hashMap2.put("dias", str3);
        if (str == null || str == "") {
            Toast.makeText(getApplicationContext(), "Error no se pudo finalizar el pedido", 1).show();
            return;
        }
        this.controller.finalizarSalesSuspended(hashMap);
        this.controller.pagoSuspended(hashMap2);
        envioPedidos(str);
    }

    public void envioPedidos(String str) {
        startActivity(new Intent(getApplicationContext(), ZonasActivity.class));
    }
}
