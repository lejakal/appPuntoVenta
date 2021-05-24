package com.example.danielreyes.TAT;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.danielreyes.TAT.controllers.ClienteActual;
import com.example.danielreyes.TAT.controllers.PedidoActual;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.views.FragmentZonas;

public class ZonasActivity extends AppCompatActivity {
    String apellido_usuario;
    String buscar_id_suspend;
    DBconexion controller = new DBconexion(this);
    String customer_id_suspend;
    String iduser;
    String iduserLogeado;
    String idusuario;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            ZonasActivity.this.getSupportFragmentManager().beginTransaction();
            ZonasActivity.this.getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_home /*2131296427*/:
                    ZonasActivity.this.startActivity(new Intent(ZonasActivity.this.getApplicationContext(), PedidosActivity.class));
                    break;
                case R.id.navigation_albums /*2131296429*/:
                    ZonasActivity.this.startActivity(new Intent(ZonasActivity.this.getApplicationContext(), opcionesActivity.class));
                    break;
                case R.id.navigation_dashboard /*2131296430*/:
                    ZonasActivity.this.startActivity(new Intent(ZonasActivity.this.getApplicationContext(), ListInventoryActivity.class));
                    break;
                case R.id.navigation_notifications /*2131296432*/:
                    ZonasActivity.this.callFragment();
                    return true;
                case R.id.navigation_songs /*2131296433*/:
                    ZonasActivity.this.startActivity(new Intent(ZonasActivity.this.getApplicationContext(), SynPedidosActivity.class));
                    break;
            }
            return false;
        }
    };
    private TextView mTextMessage;
    String nameLogeado;
    String nombre_usuario;
    String usuario;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_zonas);
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
        this.buscar_id_suspend = this.controller.estate_idsuspend(this.iduserLogeado);
        if (this.buscar_id_suspend != "No") {
            callCatalogoActivity(this.buscar_id_suspend);
        } else {
            callFragment();
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    public void callFragment() {
        FragmentZonas fragmentZonas = new FragmentZonas();
        Bundle bundle = new Bundle();
        bundle.putString("ID_USER", this.iduserLogeado);
        fragmentZonas.setArguments(bundle);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.fragment_container, fragmentZonas, "tag");
        beginTransaction.addToBackStack("tag");
        beginTransaction.commit();
    }

    public void callCatalogoActivity(String str) {
        this.customer_id_suspend = this.controller.cliente_suspend(str);
        Intent intent = new Intent(getApplicationContext(), ListItemsPedidoActivity.class);
        intent.putExtra("ID_USER", this.iduserLogeado);
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
            str = rawQuery.getString(4);
            ClienteActual clienteActual = new ClienteActual();
            clienteActual.setId(Integer.valueOf(string));
            clienteActual.setNombres(string2);
            clienteActual.setApellidos(string3);
            clienteActual.setCedula(string4);
            clienteActual.setDireccion(str);
        }
    }
}
