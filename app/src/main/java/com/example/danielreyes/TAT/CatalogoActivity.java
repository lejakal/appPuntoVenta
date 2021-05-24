package com.example.danielreyes.TAT;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.models.DBtablas;
import com.example.danielreyes.TAT.views.orderFragment;
import java.util.Date;
import java.util.HashMap;

public class CatalogoActivity extends AppCompatActivity {
    String apellido_usuario;
    DBconexion controller = new DBconexion(this);
    int idCustomer;
    String id_suspended;
    String id_usuario;
    String iduser;
    String iduserLogeado;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            CatalogoActivity.this.getSupportFragmentManager().beginTransaction();
            CatalogoActivity.this.getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_home /*2131296427*/:
                    CatalogoActivity.this.startActivity(new Intent(CatalogoActivity.this.getApplicationContext(), PedidosActivity.class));
                    break;
                case R.id.navigation_dashboard /*2131296430*/:
                    CatalogoActivity.this.startActivity(new Intent(CatalogoActivity.this.getApplicationContext(), principalActivity.class));
                    break;
                case R.id.navigation_notifications /*2131296432*/:
                    CatalogoActivity.this.callHomeActivity(CatalogoActivity.this.id_suspended);
                    break;
                case R.id.navigation_songs /*2131296433*/:
                    break;
            }
            CatalogoActivity.this.startActivity(new Intent(CatalogoActivity.this.getApplicationContext(), SynPedidosActivity.class));
            return false;
        }
    };
    String nameLogeado;
    String nombre_usuario;
    String usuario;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_catalogo);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        Intent intent = getIntent();
        this.iduser = intent.getStringExtra("ID_USER");
        this.usuario = intent.getStringExtra("NAME_USER");
        this.nombre_usuario = intent.getStringExtra("NOMBRE_USER");
        this.apellido_usuario = intent.getStringExtra("APELLIDO_USER");
        this.id_suspended = intent.getStringExtra("ID_SALES_SUSPEND");
        this.idCustomer = intent.getIntExtra("ID_CLIENTE", -1);
        intent.getStringExtra("CLI_NAME");
        intent.getStringExtra("CLI_APELL");
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        getSupportActionBar();
        getSupportActionBar().setTitle(this.nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        if (this.id_suspended != null) {
            callHomeActivity(this.id_suspended);
        } else {
            addNewUser(String.valueOf(this.idCustomer), this.iduserLogeado);
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    public void addNewUser(String str, String str2) {
        HashMap hashMap = new HashMap();
        String valueOf = String.valueOf(DateFormat.format("yyyy-MM-dd", new Date()));
        String valueOf2 = String.valueOf(DateFormat.format("HH:mm:ss", new Date()));
        hashMap.put("customer_id", str);
        hashMap.put(DBtablas.employee_id_suspended, str2);
        hashMap.put(DBtablas.sale_date_suspended, valueOf);
        hashMap.put("sale_time", valueOf2);
        hashMap.put("estate", "0");
        if (str == null || str == "") {
            Toast.makeText(getApplicationContext(), "Please enter User name", 1).show();
            return;
        }
        this.controller.insertSalesSuspended(hashMap);
        callHomeActivity(this.controller.last_idsuspended());
    }

    public void callHomeActivity(String str) {
        orderFragment orderfragment = new orderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ID_SUSPENDED", str);
        bundle.putString("ID_USER", this.iduserLogeado);
        orderfragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.fragment_containercat, orderfragment, "tag");
        beginTransaction.addToBackStack("tag");
        beginTransaction.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pedido_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.btn_cancelar) {
            cancelarSuspend(this.id_suspended);
            return true;
        } else if (itemId != R.id.btn_finalizar) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            finalizarSuspend(this.id_suspended);
            return true;
        }
    }

    public void finalizarSuspend(String str) {
        Intent intent = new Intent(getApplicationContext(), TipoPagoActivity.class);
        intent.putExtra("ID_USER", this.id_usuario);
        intent.putExtra("NAME_USER", this.usuario);
        intent.putExtra("suspend_id", str);
        startActivity(intent);
    }

    public void cancelarSuspend(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("suspend_id", str);
        if (str == null || str == "") {
            Toast.makeText(getApplicationContext(), "Error no se pudo finalizar el pedido", 1).show();
            return;
        }
        this.controller.cancelarSalesSuspended(hashMap);
        envioZonas();
    }

    public void envioZonas() {
        Intent intent = new Intent(getApplicationContext(), ZonasActivity.class);
        intent.putExtra("ID_USER", this.id_usuario);
        intent.putExtra("NAME_USER", this.usuario);
        intent.putExtra("NOMBRE_USER", this.nombre_usuario);
        intent.putExtra("APELLIDO_USER", this.apellido_usuario);
        startActivity(intent);
    }
}
