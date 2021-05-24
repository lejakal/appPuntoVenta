package com.example.danielreyes.TAT;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.DBconexion;

public class perfilActivity extends AppCompatActivity {
    String cedulaLogeado;
    DBconexion controller = new DBconexion(this);
    String direcLogeado;
    String iduserLogeado;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            perfilActivity.this.getSupportFragmentManager().beginTransaction();
            perfilActivity.this.getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_home /*2131296427*/:
                    perfilActivity.this.startActivity(new Intent(perfilActivity.this.getApplicationContext(), PedidosActivity.class));
                    break;
                case R.id.navigation_albums /*2131296429*/:
                    return true;
                case R.id.navigation_dashboard /*2131296430*/:
                    perfilActivity.this.startActivity(new Intent(perfilActivity.this.getApplicationContext(), ListInventoryActivity.class));
                    break;
                case R.id.navigation_notifications /*2131296432*/:
                    perfilActivity.this.startActivity(new Intent(perfilActivity.this.getApplicationContext(), ZonasActivity.class));
                    break;
                case R.id.navigation_songs /*2131296433*/:
                    perfilActivity.this.startActivity(new Intent(perfilActivity.this.getApplicationContext(), SynPedidosActivity.class));
                    break;
            }
            return false;
        }
    };
    String nameLogeado;
    String nombresLogeado;
    String passLogeado;
    String telefonoLogeado;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_perfil);
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        this.cedulaLogeado = cedula(this.iduserLogeado);
        this.passLogeado = Usuario.getPass();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Usuario.getNombres());
        stringBuilder.append(" ");
        stringBuilder.append(usuario.getApellido());
        this.nombresLogeado = name(this.iduserLogeado);
        this.direcLogeado = direccion(this.iduserLogeado);;
        this.telefonoLogeado =telefono(this.iduserLogeado);;
        getSupportActionBar();
        getSupportActionBar().setTitle(this.nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        TextView textView = (TextView) findViewById(R.id.nameTxt);
        TextView textView2 = (TextView) findViewById(R.id.ccTxt);
        TextView textView3 = (TextView) findViewById(R.id.dirTxt);
        TextView textView4 = (TextView) findViewById(R.id.telTxt);
        TextView textView5 = (TextView) findViewById(R.id.passTxt);
        ((TextView) findViewById(R.id.usuarioTxt)).setText(this.nameLogeado);
        textView5.setText(this.passLogeado);
        textView.setText(this.nombresLogeado);
        textView2.setText(this.cedulaLogeado);
        textView3.setText(this.direcLogeado);
        textView4.setText(this.telefonoLogeado);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        bottomNavigationView.getMenu().getItem(4).setChecked(true);
    }

    public String name(String ide) {
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_people WHERE person_id = ?", new String[]{ide});
        return rawQuery.moveToNext() ? rawQuery.getString(0) : "naa";
    }
    public String apell(String ide) {
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_people WHERE person_id = ?", new String[]{ide});
        return rawQuery.moveToNext() ? rawQuery.getString(13) : "naa";
    }
    public String direccion(String ide) {
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_people WHERE person_id = ?", new String[]{ide});
        return rawQuery.moveToNext() ? rawQuery.getString(4) : "naa";
    }
    public String cedula(String ide) {
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_people WHERE person_id = ?", new String[]{ide});
        return rawQuery.moveToNext() ? rawQuery.getString(1) : "naa";
    }
    public String email(String ide) {
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_people WHERE person_id = ?", new String[]{ide});
        return rawQuery.moveToNext() ? rawQuery.getString(3) : "naa";
    }
    public String telefono(String ide) {
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_people WHERE person_id = ?", new String[]{ide});
        return rawQuery.moveToNext() ? rawQuery.getString(2) : "naa";
    }

}
