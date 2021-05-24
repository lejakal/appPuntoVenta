package com.example.danielreyes.TAT;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.controllers.Zonas;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.views.AdapterZonas;
import java.util.ArrayList;

public class misClientesActivity extends AppCompatActivity {
    AdapterZonas adapter;
    DBconexion controller = new DBconexion(this);
    String iduserLogeado;
    ListView listViewZonas;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            misClientesActivity.this.getSupportFragmentManager().beginTransaction();
            misClientesActivity.this.getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_home /*2131296427*/:
                    misClientesActivity.this.startActivity(new Intent(misClientesActivity.this.getApplicationContext(), PedidosActivity.class));
                    break;
                case R.id.navigation_albums /*2131296429*/:
                    return true;
                case R.id.navigation_dashboard /*2131296430*/:
                    misClientesActivity.this.startActivity(new Intent(misClientesActivity.this.getApplicationContext(), ListInventoryActivity.class));
                    break;
                case R.id.navigation_notifications /*2131296432*/:
                    misClientesActivity.this.startActivity(new Intent(misClientesActivity.this.getApplicationContext(), ZonasActivity.class));
                    break;
                case R.id.navigation_songs /*2131296433*/:
                    misClientesActivity.this.startActivity(new Intent(misClientesActivity.this.getApplicationContext(), SynPedidosActivity.class));
                    break;
            }
            return false;
        }
    };
    String nameLogeado;
    ArrayList<Zonas> zonaslist = new ArrayList();

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_mis_clientes);
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        getSupportActionBar();
        getSupportActionBar().setTitle(this.nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.listViewZonas = (ListView) findViewById(R.id.listadoZonas);
        this.adapter = new AdapterZonas(this, this.zonaslist);
        listadoZonas();
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        bottomNavigationView.getMenu().getItem(4).setChecked(true);
    }

    private void listadoZonas() {
        DBconexion dBconexion = new DBconexion(this);
        dBconexion.open();
        Cursor listZonas = dBconexion.getListZonas();
        while (listZonas.moveToNext()) {
            Zonas zonas = new Zonas();
            zonas.setId(Integer.valueOf(listZonas.getInt(6)));
            zonas.setNombre(listZonas.getString(1));
            zonas.setDescripcion(listZonas.getString(4));
            this.zonaslist.add(zonas);
        }
        dBconexion.close();
        this.listViewZonas.setAdapter(this.adapter);
    }
}
