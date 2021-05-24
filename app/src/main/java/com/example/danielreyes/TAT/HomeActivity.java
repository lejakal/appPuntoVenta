package com.example.danielreyes.TAT;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.danielreyes.TAT.controllers.PedidoItems;
import com.example.danielreyes.TAT.controllers.People;
import com.example.danielreyes.TAT.models.ConexionSQLiteHelper;
import com.example.danielreyes.TAT.models.DBconexion;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    String apellido_usuario;
    ConexionSQLiteHelper conn;
    int iduser;
    Integer idusuario;
    ListView listViewZona;
    ArrayList<String> listaInformacion;
    ArrayList<People> listaZonas;
    ArrayList<PedidoItems> listaped;
    String nombre_usuario;
    String usuario;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_home);
        Intent intent = getIntent();
        this.iduser = intent.getIntExtra("ID_USER", -1);
        this.usuario = intent.getStringExtra("NAME_USER");
        this.nombre_usuario = intent.getStringExtra("NOMBRE_USER");
        this.apellido_usuario = intent.getStringExtra("APELLIDO_USER");
        this.listViewZona = (ListView) findViewById(R.id.listZonas);
        consultarListaZonas();
        this.listViewZona.setAdapter(new ArrayAdapter(this, 17367043, this.listaInformacion));
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home /*2131296427*/:
                        HomeActivity.this.startActivity(new Intent(HomeActivity.this, PedidosActivity.class));
                        break;
                    case R.id.navigation_dashboard /*2131296430*/:
                        HomeActivity.this.startActivity(new Intent(HomeActivity.this, principalActivity.class));
                        break;
                    case R.id.navigation_notifications /*2131296432*/:
                        HomeActivity.this.startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        break;
                }
                return false;
            }
        });
    }

    private void consultarListaZonas() {
        this.listaZonas = new ArrayList();
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_people", null);
        while (rawQuery.moveToNext()) {
            People people = new People();
            people.setId(Integer.valueOf(rawQuery.getInt(11)));
            people.setNombre(rawQuery.getString(0));
            people.setApellido(rawQuery.getString(13));
            people.setCedula(rawQuery.getString(1));
            this.listaZonas.add(people);
        }
        obtenerLista();
    }

    private void consultarListaitemspedido() {
        this.listaped = new ArrayList();
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_sales_suspended_items WHERE sale_id = ?", new String[]{"40"});
        while (rawQuery.moveToNext()) {
            this.listaped.add(new PedidoItems());
        }
        obtenerLista();
    }

    private void obtenerLista() {
        this.listaInformacion = new ArrayList();
        for (int i = 0; i < this.listaped.size(); i++) {
            ArrayList arrayList = this.listaInformacion;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("‣ID->");
            stringBuilder.append(((PedidoItems) this.listaped.get(i)).getIditem());
            stringBuilder.append("N->");
            stringBuilder.append(((PedidoItems) this.listaped.get(i)).getNombreitem());
            arrayList.add(stringBuilder.toString());
        }
    }
}
