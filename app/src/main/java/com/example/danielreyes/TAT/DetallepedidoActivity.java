package com.example.danielreyes.TAT;

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
import com.example.danielreyes.TAT.controllers.ClienteActual;
import com.example.danielreyes.TAT.controllers.PedidoActual;
import com.example.danielreyes.TAT.controllers.Pedidos;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.views.DTPedidoFragment;
import com.example.danielreyes.TAT.views.DTPedidoItemsFragment;
import java.text.DecimalFormat;
import java.util.HashMap;

public class DetallepedidoActivity extends AppCompatActivity {
    String apellido_usuario;
    DBconexion controller = new DBconexion(this);
    String customer_id_suspend;
    String idpedido;
    String idsalesuspend;
    TextView idsuspended;
    String iduser;
    String iduserLogeado;
    String idusuario;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            DetallepedidoActivity.this.getSupportFragmentManager().beginTransaction();
            DetallepedidoActivity.this.getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_home /*2131296427*/:
                    DetallepedidoActivity.this.startActivity(new Intent(DetallepedidoActivity.this.getApplicationContext(), PedidosActivity.class));
                    break;
                case R.id.navigation_albums /*2131296429*/:
                    DetallepedidoActivity.this.startActivity(new Intent(DetallepedidoActivity.this.getApplicationContext(), opcionesActivity.class));
                    break;
                case R.id.navigation_dashboard /*2131296430*/:
                    DetallepedidoActivity.this.startActivity(new Intent(DetallepedidoActivity.this.getApplicationContext(), ListInventoryActivity.class));
                    break;
                case R.id.navigation_notifications /*2131296432*/:
                    DetallepedidoActivity.this.startActivity(new Intent(DetallepedidoActivity.this.getApplicationContext(), ZonasActivity.class));
                    break;
                case R.id.navigation_songs /*2131296433*/:
                    DetallepedidoActivity.this.startActivity(new Intent(DetallepedidoActivity.this.getApplicationContext(), SynPedidosActivity.class));
                    break;
            }
            return false;
        }
    };
    String nameLogeado;
    String nombre_usuario;
    String totalsuspended;
    TextView txttotalsuspended;
    String usuario;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_detallepedido);
        this.idsuspended = (TextView) findViewById(R.id.idpedido);
        this.txttotalsuspended = (TextView) findViewById(R.id.totalpedido);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        Intent intent = getIntent();
        this.iduser = intent.getStringExtra("ID_USER");
        this.usuario = intent.getStringExtra("NAME_USER");
        this.nombre_usuario = intent.getStringExtra("NOMBRE_USER");
        this.apellido_usuario = intent.getStringExtra("APELLIDO_USER");
        this.idsalesuspend = intent.getStringExtra("ID_SALES_SUSPEND");
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        getSupportActionBar();
        getSupportActionBar().setTitle(this.nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        bundle = getIntent().getExtras();
        if (this.idsalesuspend != null) {
            this.idpedido = this.idsalesuspend;
        } else if (bundle != null) {
            this.idpedido = ((Pedidos) bundle.getSerializable("detalle")).getId().toString();
        }
        this.idsuspended.setText(this.idpedido);
        String totalSuspend = this.controller.totalSuspend(this.idpedido, this.controller.getAllsuspend_itemsIDsuspend(this.idpedido).size());
        if (totalSuspend.equals("")) {
            this.txttotalsuspended.setText("$ ");
        } else {
            totalSuspend = new DecimalFormat("##,##0.00").format(Double.valueOf(totalSuspend));
            TextView textView = this.txttotalsuspended;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("$ ");
            stringBuilder.append(totalSuspend);
            textView.setText(stringBuilder.toString());
        }
        DTPedidoFragment dTPedidoFragment = new DTPedidoFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("ID_USER", this.iduserLogeado);
        bundle2.putString("ID_SUSPENDED", this.idpedido);
        dTPedidoFragment.setArguments(bundle2);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.fragment_pedido, dTPedidoFragment, "tag");
        beginTransaction.addToBackStack("tag");
        beginTransaction.commit();
        DTPedidoItemsFragment dTPedidoItemsFragment = new DTPedidoItemsFragment();
        new Bundle().putString("ID_USER", this.iduserLogeado);
        bundle2.putString("ID_SUSPENDED", this.idpedido);
        dTPedidoItemsFragment.setArguments(bundle2);
        FragmentTransaction beginTransaction2 = getSupportFragmentManager().beginTransaction();
        beginTransaction2.add(R.id.fragment_pedido_items, dTPedidoItemsFragment, "tag");
        beginTransaction2.addToBackStack("tag");
        beginTransaction2.commit();
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detalle_pedido, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.btn_agregar) {
            agregarSuspend(this.idpedido);
            return true;
        } else if (itemId != R.id.btn_eliminar) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            eliminarSuspend(this.idpedido);
            return true;
        }
    }

    public void agregarSuspend(String str) {
        this.customer_id_suspend = this.controller.cliente_suspend(str);
        Intent intent = new Intent(this, ListItemsPedidoActivity.class);
        new PedidoActual().setId(Integer.valueOf(str));
        intent.putExtra("ID_SALES_SUSPEND", str);
        enviar_customer(this.customer_id_suspend);
        startActivity(intent);
    }

    public void enviar_customer(String str) {
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_people WHERE person_id = ?", new String[]{str});
        if (rawQuery.moveToNext()) {
            String string = rawQuery.getString(12);
            String string2 = rawQuery.getString(0);
            String string3 = rawQuery.getString(13);
            String string4 = rawQuery.getString(1);
            str = rawQuery.getString(7);
            ClienteActual clienteActual = new ClienteActual();
            clienteActual.setId(Integer.valueOf(string));
            clienteActual.setNombres(string2);
            clienteActual.setApellidos(string3);
            clienteActual.setCedula(string4);
            clienteActual.setDireccion(str);
        }
    }

    public void eliminarSuspend(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("suspend_id", str);
        if (str == null || str == "") {
            Toast.makeText(getApplicationContext(), "Error no se pudo finalizar el pedido", 0).show();
            return;
        }
        this.controller.cancelarSalesSuspended(hashMap);
        envioZonas();
    }

    public void envioZonas() {
        startActivity(new Intent(getApplicationContext(), ZonasActivity.class));
    }
}
