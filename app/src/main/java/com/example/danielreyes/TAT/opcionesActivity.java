package com.example.danielreyes.TAT;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.example.danielreyes.TAT.controllers.Usuario;

public class opcionesActivity extends AppCompatActivity {
    String iduserLogeado;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            opcionesActivity.this.getSupportFragmentManager().beginTransaction();
            opcionesActivity.this.getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_home /*2131296427*/:
                    opcionesActivity.this.startActivity(new Intent(opcionesActivity.this.getApplicationContext(), PedidosActivity.class));
                    break;
                case R.id.navigation_albums /*2131296429*/:
                    opcionesActivity.this.startActivity(new Intent(opcionesActivity.this.getApplicationContext(), opcionesActivity.class));
                    break;
                case R.id.navigation_dashboard /*2131296430*/:
                    return true;
                case R.id.navigation_notifications /*2131296432*/:
                    opcionesActivity.this.startActivity(new Intent(opcionesActivity.this.getApplicationContext(), ZonasActivity.class));
                    break;
                case R.id.navigation_songs /*2131296433*/:
                    opcionesActivity.this.startActivity(new Intent(opcionesActivity.this.getApplicationContext(), SynPedidosActivity.class));
                    break;
            }
            return false;
        }
    };
    String nameLogeado;
    TextView txtclientes;
    TextView txtperfil;
    TextView txtsesion;
    TextView txtventas;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_opciones);
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        getSupportActionBar();
        getSupportActionBar().setTitle(this.nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.txtventas = (TextView) findViewById(R.id.btn_ventas);
        this.txtclientes = (TextView) findViewById(R.id.btn_clientes);
        this.txtperfil = (TextView) findViewById(R.id.btn_perfil);
        this.txtsesion = (TextView) findViewById(R.id.btn_sesion);
        this.txtventas.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                opcionesActivity.this.startActivity(new Intent(opcionesActivity.this.getApplicationContext(), repoVentasActivity.class));
            }
        });
        this.txtclientes.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                opcionesActivity.this.startActivity(new Intent(opcionesActivity.this.getApplicationContext(), misClientesActivity.class));
            }
        });
        this.txtperfil.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                opcionesActivity.this.startActivity(new Intent(opcionesActivity.this.getApplicationContext(), perfilActivity.class));
            }
        });
        this.txtsesion.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                intent.setFlags(268435456);
                opcionesActivity.this.startActivity(intent);
            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        bottomNavigationView.getMenu().getItem(4).setChecked(true);
    }
}
