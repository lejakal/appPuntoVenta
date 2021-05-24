package com.example.danielreyes.TAT;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.danielreyes.TAT.controllers.ClienteActual;
import com.example.danielreyes.TAT.controllers.Items;
import com.example.danielreyes.TAT.controllers.PedidoActual;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.models.DBtablas;
import com.example.danielreyes.TAT.views.AdapterItemsSearchSuspend;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ListItemsPedidoActivity extends AppCompatActivity {
    AdapterItemsSearchSuspend adapter;
    String apellCustomer;
    String apellido_usuario;
    String bodegaLogeado;
    String ccCustomer;
    DBconexion controller = new DBconexion(this);
    String dirCustomer;
    int idCustomer;
    String id_suspended;
    String id_usuario;
    TextView idpedido;
    String iduser;
    String iduserLogeado;
    ArrayList<Items> inventario = new ArrayList();
    ListView listViewInventario;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            ListItemsPedidoActivity.this.getSupportFragmentManager().beginTransaction();
            ListItemsPedidoActivity.this.getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_home /*2131296427*/:
                    ListItemsPedidoActivity.this.startActivity(new Intent(ListItemsPedidoActivity.this.getApplicationContext(), PedidosActivity.class));
                    break;
                case R.id.navigation_albums /*2131296429*/:
                    ListItemsPedidoActivity.this.startActivity(new Intent(ListItemsPedidoActivity.this.getApplicationContext(), opcionesActivity.class));
                    break;
                case R.id.navigation_dashboard /*2131296430*/:
                    ListItemsPedidoActivity.this.startActivity(new Intent(ListItemsPedidoActivity.this.getApplicationContext(), ListInventoryActivity.class));
                    break;
                case R.id.navigation_notifications /*2131296432*/:
                    return true;
                case R.id.navigation_songs /*2131296433*/:
                    ListItemsPedidoActivity.this.startActivity(new Intent(ListItemsPedidoActivity.this.getApplicationContext(), SynPedidosActivity.class));
                    break;
            }
            return false;
        }
    };
    String nameCustomer;
    String nameLogeado;
    String nombre_usuario;
    SearchView sv;
    String usuario;
    TextView valorsuspend;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_list_items_pedido);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        Usuario usuario = new Usuario();
        this.iduserLogeado = usuario.getIduser();
        this.nameLogeado = usuario.getNombre();
        this.bodegaLogeado = usuario.getBodega();
        Intent intent = getIntent();
        this.iduser = intent.getStringExtra("ID_USER");
        this.usuario = intent.getStringExtra("NAME_USER");
        this.nombre_usuario = intent.getStringExtra("NOMBRE_USER");
        this.apellido_usuario = intent.getStringExtra("APELLIDO_USER");
        ClienteActual clienteActual = new ClienteActual();
        this.idCustomer = clienteActual.getId().intValue();
        this.nameCustomer = clienteActual.getNombres();
        this.apellCustomer = clienteActual.getApellidos();
        this.ccCustomer = clienteActual.getCedula();
        this.dirCustomer = clienteActual.getDireccion();
        this.id_suspended = String.valueOf(new PedidoActual().getId());
        this.idpedido = (TextView) findViewById(R.id.id_suspended);
        this.idpedido.setText(this.id_suspended);
        ((TextView) findViewById(R.id.nameCliente)).setText(this.nameCustomer);
        ((TextView) findViewById(R.id.apelCliente)).setText(this.apellCustomer);
        ((TextView) findViewById(R.id.ccCliente)).setText(this.ccCustomer);
        ((TextView) findViewById(R.id.dirCliente)).setText(this.dirCustomer);
        this.valorsuspend = (TextView) findViewById(R.id.valor_suspended);
        String totalSuspend = this.controller.totalSuspend(this.id_suspended, this.controller.getAllsuspend_itemsIDsuspend(this.id_suspended).size());
        TextView textView = this.valorsuspend;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$ ");
        stringBuilder.append(totalSuspend);
        textView.setText(stringBuilder.toString());
        getSupportActionBar();
        getSupportActionBar().setTitle(this.nameLogeado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((int) R.drawable.icon_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        if (this.id_suspended.equals("0")) {
            addNewUser(String.valueOf(this.idCustomer), this.iduserLogeado);
        } else {
            this.listViewInventario = (ListView) findViewById(R.id.listProductosPedido);
            this.sv = (SearchView) findViewById(R.id.sv);
            this.adapter = new AdapterItemsSearchSuspend(this, this.inventario);
            listInventario(this.bodegaLogeado, this.id_suspended);
            this.sv.setOnQueryTextListener(new OnQueryTextListener() {
                public boolean onQueryTextSubmit(String str) {
                    return false;
                }

                public boolean onQueryTextChange(String str) {
                    ListItemsPedidoActivity.this.getSearchInventario(str, ListItemsPedidoActivity.this.bodegaLogeado, ListItemsPedidoActivity.this.id_suspended);
                    return false;
                }
            });
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
            Toast.makeText(getApplicationContext(), "error en la creacion del pedido", 1).show();
            return;
        }
        this.controller.insertSalesSuspended(hashMap);
        new PedidoActual().setId(Integer.valueOf(this.controller.last_idsuspended()));
        finish();
        startActivity(getIntent());
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

    private void listInventario(String str, String str2) {
        DBconexion dBconexion = new DBconexion(this);
        dBconexion.open();
        Cursor listInventario = dBconexion.getListInventario(str);
        while (listInventario.moveToNext()) {
            String string = listInventario.getString(0);
            String string2 = listInventario.getString(6);
            Double valueOf = Double.valueOf(quanty(Integer.valueOf(listInventario.getInt(9)), str));
            Integer valueOf2 = Integer.valueOf(listInventario.getInt(9));
            if (valueOf.doubleValue() > 0.0d) {
                Items items = new Items();
                items.setNombre(string);
                items.setPrecioventa(string2);
                items.setStock(valueOf);
                items.setId(valueOf2);
                items.setIdsuspend(str2);
                this.inventario.add(items);
            }
        }
        dBconexion.close();
        this.listViewInventario.setAdapter(this.adapter);
    }

    private void getSearchInventario(String str, String str2, String str3) {
        this.inventario.clear();
        DBconexion dBconexion = new DBconexion(this);
        dBconexion.open();
        Cursor searchInventario = dBconexion.searchInventario(str, str2);
        while (searchInventario.moveToNext()) {
            String string = searchInventario.getString(0);
            String string2 = searchInventario.getString(6);
            Double valueOf = Double.valueOf(quanty(Integer.valueOf(searchInventario.getInt(9)), str2));
            Integer valueOf2 = Integer.valueOf(searchInventario.getInt(9));
            if (valueOf.doubleValue() > 0.0d) {
                Items items = new Items();
                items.setNombre(string);
                items.setPrecioventa(string2);
                items.setStock(valueOf);
                items.setId(valueOf2);
                items.setIdsuspend(str3);
                this.inventario.add(items);
            }
        }
        dBconexion.close();
        this.listViewInventario.setAdapter(this.adapter);
    }

    public double quanty(Integer num, String str) {
        String valueOf = String.valueOf(num);
        Double.valueOf(0.0d);
        Cursor rawQuery = new DBconexion(this).getReadableDatabase().rawQuery("SELECT * FROM ospos_item_quantities WHERE item_id = ? AND location_id = ?", new String[]{valueOf, str});
        if (rawQuery.moveToNext()) {
            return Double.valueOf(rawQuery.getDouble(2)).doubleValue();
        }
        return 0.0d;
    }

    public void finalizarSuspend(String str) {
        Intent intent = new Intent(getApplicationContext(), TipoPagoActivity.class);
        intent.putExtra("ID_USER", this.id_usuario);
        intent.putExtra("NAME_USER", this.usuario);
        intent.putExtra("suspend_id", str);
        intent.putExtra("CLI_NAME", this.nameCustomer);
        intent.putExtra("CLI_APELL", this.apellCustomer);
        intent.putExtra("CLI_CC", this.ccCustomer);
        intent.putExtra("CLI_DIR", this.dirCustomer);
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
