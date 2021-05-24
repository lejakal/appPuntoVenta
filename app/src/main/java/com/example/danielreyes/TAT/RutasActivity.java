package com.example.danielreyes.TAT;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.views.AdapterRutas;

public class RutasActivity extends AppCompatActivity {
    private SimpleCursorAdapter DBadapter;
    private DBconexion baseDatos;
    private Button botonAgregarPersona;
    private AdapterRutas cursorAdapter;
    private ListView listViewPersonas;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_rutas);
        this.listViewPersonas = (ListView) findViewById(R.id.listViewZonasC);
    }
}
