package com.example.danielreyes.TAT;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.danielreyes.TAT.controllers.Facturas;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.views.AdapterVentas;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class repoVentasActivity extends AppCompatActivity {
    AdapterVentas adapter;
    ImageView buscarf;
    Calendar calendario = Calendar.getInstance();
    Calendar calendariofin = Calendar.getInstance();
    DBconexion controller = new DBconexion(this);
    OnDateSetListener date = new OnDateSetListener() {
        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            repoVentasActivity.this.calendario.set(1, i);
            repoVentasActivity.this.calendario.set(2, i2);
            repoVentasActivity.this.calendario.set(5, i3);
            repoVentasActivity.this.actualizarInput();
        }
    };
    OnDateSetListener datefin = new OnDateSetListener() {
        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            repoVentasActivity.this.calendariofin.set(1, i);
            repoVentasActivity.this.calendariofin.set(2, i2);
            repoVentasActivity.this.calendariofin.set(5, i3);
            repoVentasActivity.this.actualizarInputFin();
        }
    };
    EditText fechafin;
    EditText fechaini;
    String iduserLogeado;
    String rutadia;
    ListView listViewPedidos;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            repoVentasActivity.this.getSupportFragmentManager().beginTransaction();
            repoVentasActivity.this.getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_home /*2131296427*/:
                    repoVentasActivity.this.startActivity(new Intent(repoVentasActivity.this.getApplicationContext(), PedidosActivity.class));
                    break;
                case R.id.navigation_albums /*2131296429*/:
                    return true;
                case R.id.navigation_dashboard /*2131296430*/:
                    repoVentasActivity.this.startActivity(new Intent(repoVentasActivity.this.getApplicationContext(), ListInventoryActivity.class));
                    break;
                case R.id.navigation_notifications /*2131296432*/:
                    repoVentasActivity.this.startActivity(new Intent(repoVentasActivity.this.getApplicationContext(), ZonasActivity.class));
                    break;
                case R.id.navigation_songs /*2131296433*/:
                    repoVentasActivity.this.startActivity(new Intent(repoVentasActivity.this.getApplicationContext(), SynPedidosActivity.class));
                    break;
            }
            return false;
        }
    };
    String nameLogeado;
    ArrayList<Facturas> pedidoslist = new ArrayList();
    TextView totalVentas;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_repo_ventas);
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        this.rutadia = usuario.getRutas();
        getSupportActionBar();
        getSupportActionBar().setTitle(this.nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.totalVentas = (TextView) findViewById(R.id.totalventas);
        this.fechaini = (EditText) findViewById(R.id.inicio);
        this.fechaini.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new DatePickerDialog(repoVentasActivity.this, repoVentasActivity.this.date, repoVentasActivity.this.calendario.get(1), repoVentasActivity.this.calendario.get(2), repoVentasActivity.this.calendario.get(5)).show();
            }
        });
        this.fechafin = (EditText) findViewById(R.id.fin);
        this.fechafin.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new DatePickerDialog(repoVentasActivity.this, repoVentasActivity.this.datefin, repoVentasActivity.this.calendariofin.get(1), repoVentasActivity.this.calendariofin.get(2), repoVentasActivity.this.calendariofin.get(5)).show();
            }
        });
        this.buscarf = (ImageView) findViewById(R.id.btn_buscar);
        this.listViewPedidos = (ListView) findViewById(R.id.listPedidos);
        this.adapter = new AdapterVentas(this, this.pedidoslist);
        listPedidosHoy(String.valueOf(DateFormat.format("yyyy-MM-dd", new Date())));
        this.buscarf.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                repoVentasActivity.this.consultaVentas(repoVentasActivity.this.fechaini.getText().toString(), repoVentasActivity.this.fechafin.getText().toString());
            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        bottomNavigationView.getMenu().getItem(4).setChecked(true);
    }

    private void actualizarInput() {
        this.fechaini.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(this.calendario.getTime()));
    }

    private void actualizarInputFin() {
        this.fechafin.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(this.calendariofin.getTime()));
    }

    public void consultaVentas(String str, String str2) {
        this.pedidoslist.clear();
        DBconexion dBconexion = new DBconexion(this);
        dBconexion.open();
        Cursor listVentasFecha = dBconexion.getListVentasFecha(str, str2);
        while (listVentasFecha.moveToNext()) {
            String string = listVentasFecha.getString(0);
            String string2 = listVentasFecha.getString(4);
            String string3 = listVentasFecha.getString(3);
            String string4 = listVentasFecha.getString(2);
            String string5 = listVentasFecha.getString(5);
            Facturas facturas = new Facturas();
            facturas.setFechaHora(string);
            facturas.setCliente(name_cliente(string4));
            facturas.setIdFactura(string3);
            string = new DecimalFormat("##,##0.00").format(Double.valueOf(string5));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("$ ");
            stringBuilder.append(string);
            facturas.setValortotal(stringBuilder.toString());
            facturas.setDescripcion(string2.equals("1") ? "Efectivo" : "Credito");
            this.pedidoslist.add(facturas);
        }
        dBconexion.close();
        this.listViewPedidos.setAdapter(this.adapter);
        str = this.controller.totalesVentasFecha(this.controller.getAllVentasFecha(str, str2).size(), str, str2);
        if (str.equals("")) {
            this.totalVentas.setText("$ ");
            return;
        }
        str = new DecimalFormat("##,##0.00").format(Double.valueOf(str));
        TextView textView = this.totalVentas;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("$ ");
        stringBuilder2.append(str);
        textView.setText(stringBuilder2.toString());
    }

    private void listPedidosHoy(String str) {
        DBconexion dBconexion = new DBconexion(this);
        dBconexion.open();
        Cursor listVentasHoy = dBconexion.getListVentasHoy(str);
        while (listVentasHoy.moveToNext()) {
            String string = listVentasHoy.getString(0);
            String string2 = listVentasHoy.getString(4);
            String string3 = listVentasHoy.getString(3);
            String string4 = listVentasHoy.getString(2);
            String string5 = listVentasHoy.getString(5);
            Facturas facturas = new Facturas();
            facturas.setFechaHora(string);
            facturas.setCliente(name_cliente(string4));
            facturas.setIdFactura(string3);
            string = new DecimalFormat("##,##0.00").format(Double.valueOf(string5));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("$ ");
            stringBuilder.append(string);
            facturas.setValortotal(stringBuilder.toString());
            facturas.setDescripcion(string2.equals("1") ? "Efectivo" : "Credito ");
            this.pedidoslist.add(facturas);
        }
        dBconexion.close();
        this.listViewPedidos.setAdapter(this.adapter);
        str = this.controller.totalesVentasHoy(this.controller.getAllVentasHoy(str).size(), str);
        if (str.equals("")) {
            this.totalVentas.setText("$ ");
            return;
        }
        str = new DecimalFormat("##,##0.00").format(Double.valueOf(str));
        TextView textView = this.totalVentas;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("$ ");
        stringBuilder2.append(str);
        textView.setText(stringBuilder2.toString());
    }

    public String name_cliente(String str) {
        str = String.valueOf(str);
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_people WHERE person_id = ?", new String[]{str});
        if (!rawQuery.moveToNext()) {
            return " ";
        }
        String string = rawQuery.getString(0);
        str = rawQuery.getString(13);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        stringBuilder.append(" ");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public String valor_venta(String str) {
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_sales_suspended_payments WHERE sale_id = ?", new String[]{str});
        return rawQuery.moveToNext() ? rawQuery.getString(2) : " ";
    }

    public String ide_venta(String str) {
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_sales_suspended_payments WHERE sale_id = ?", new String[]{str});
        return rawQuery.moveToNext() ? rawQuery.getString(4) : " ";
    }
}
