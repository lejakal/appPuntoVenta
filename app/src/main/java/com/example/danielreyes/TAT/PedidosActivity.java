package com.example.danielreyes.TAT;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.danielreyes.TAT.controllers.Pedidos;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.ConexionSQLiteHelper;
import com.example.danielreyes.TAT.models.DBconexion;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PedidosActivity extends AppCompatActivity {
    String apellido_usuario;
    ConexionSQLiteHelper conn;
    DBconexion controller = new DBconexion(this);
    public SQLiteDatabase db;
    String iduser;
    String iduserLogeado;
    ListView listViewZona;
    ArrayList<String> listaInformacion;
    ArrayList<Pedidos> listaPedidos;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            PedidosActivity.this.getSupportFragmentManager().beginTransaction();
            PedidosActivity.this.getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_home /*2131296427*/:
                    return true;
                case R.id.navigation_albums /*2131296429*/:
                    PedidosActivity.this.startActivity(new Intent(PedidosActivity.this.getApplicationContext(), opcionesActivity.class));
                    break;
                case R.id.navigation_dashboard /*2131296430*/:
                    PedidosActivity.this.startActivity(new Intent(PedidosActivity.this.getApplicationContext(), ListInventoryActivity.class));
                    break;
                case R.id.navigation_notifications /*2131296432*/:
                    PedidosActivity.this.startActivity(new Intent(PedidosActivity.this.getApplicationContext(), ZonasActivity.class));
                    break;
                case R.id.navigation_songs /*2131296433*/:
                    PedidosActivity.this.startActivity(new Intent(PedidosActivity.this.getApplicationContext(), SynPedidosActivity.class));
                    break;
            }
            return false;
        }
    };
    String nameLogeado;
    String nombre_usuario;
    String usuario;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_pedidos);
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        getSupportActionBar();
        getSupportActionBar().setTitle(this.nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        Intent intent = getIntent();
        this.iduser = intent.getStringExtra("ID_USER");
        this.usuario = intent.getStringExtra("NAME_USER");
        this.nombre_usuario = intent.getStringExtra("NOMBRE_USER");
        this.apellido_usuario = intent.getStringExtra("APELLIDO_USER");
        this.listViewZona = (ListView) findViewById(R.id.listPedidos);
        TextView textView = (TextView) findViewById(R.id.totalpedido);
        consultarListaPedidos(this.iduserLogeado);
        String totalesSuspendedUser = this.controller.totalesSuspendedUser(this.controller.getAllsuspend_payments().size(), "0");
        if (totalesSuspendedUser.equals("")) {
            textView.setText("$ ");
        } else {
            totalesSuspendedUser = new DecimalFormat("##,##0.00").format(Double.valueOf(totalesSuspendedUser));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("$ ");
            stringBuilder.append(totalesSuspendedUser);
            textView.setText(stringBuilder.toString());
        }
        this.listViewZona.setAdapter(new ArrayAdapter(this, 17367043, this.listaInformacion));
        this.listViewZona.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Pedidos pedidos = (Pedidos) PedidosActivity.this.listaPedidos.get(i);
                Intent intent = new Intent(PedidosActivity.this, DetallepedidoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("detalle", pedidos);
                intent.putExtras(bundle);
                PedidosActivity.this.startActivity(intent);
            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
    }

    private void consultarListaPedidos(String str) {
        this.listaPedidos = new ArrayList();
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_sales_suspended WHERE employee_id = ? AND estate != ?", new String[]{str, "3"});
        while (rawQuery.moveToNext()) {
            Pedidos pedidos = new Pedidos();
            pedidos.setId(Integer.valueOf(rawQuery.getInt(4)));
            pedidos.setFechaHora(rawQuery.getString(0));
            pedidos.setCliente(name_cliente(rawQuery.getString(1)));
            pedidos.setDescripcion(rawQuery.getString(1));
            this.listaPedidos.add(pedidos);
        }
        obtenerLista();
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

    private void obtenerLista() {
        this.listaInformacion = new ArrayList();
        for (int i = 0; i < this.listaPedidos.size(); i++) {
            ArrayList arrayList = this.listaInformacion;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(((Pedidos) this.listaPedidos.get(i)).getId());
            stringBuilder.append("   ");
            stringBuilder.append(((Pedidos) this.listaPedidos.get(i)).getFechaHora());
            stringBuilder.append("   ");
            stringBuilder.append(((Pedidos) this.listaPedidos.get(i)).getCliente());
            arrayList.add(stringBuilder.toString());
        }
    }
}
