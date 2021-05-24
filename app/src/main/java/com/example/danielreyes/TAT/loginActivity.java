package com.example.danielreyes.TAT;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.example.danielreyes.TAT.controllers.Usuario;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.models.checkuser;
import com.example.danielreyes.TAT.models.syncActivity;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class loginActivity extends AppCompatActivity {
    String bodegaLogeado;
    DBconexion controller = new DBconexion(this);
    String idbodega;
    String idlogueado;
    String iduserLogeado;
    String nameuserLogeado;
    String nombrelogueado;
    String txtpassword;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        supportRequestWindowFeature(1);
        setContentView((int) R.layout.activity_login);
        this.nombrelogueado = this.controller.nameLogueado();
        this.idlogueado = this.controller.idLogueado();
        this.idbodega = this.controller.idbodegaUser();
        Usuario usuario = new Usuario();
        usuario.setIduser(this.idlogueado);
        usuario.setNombre(this.nombrelogueado);
        usuario.setBodega(this.idbodega);
        this.iduserLogeado = usuario.getIduser();
        this.nameuserLogeado = usuario.getNombre();
        this.bodegaLogeado = usuario.getBodega();
        final EditText editText = (EditText) findViewById(R.id.txtpass);
        Button button = (Button) findViewById(R.id.btnok);
        if (this.nombrelogueado.equals("No") && this.idlogueado.equals("No")) {
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (loginActivity.this.controller.getAllemployees().size() == 0) {
                        final String obj = editText.getText().toString();
                        Volley.newRequestQueue(loginActivity.this).add(new checkuser(obj, new Listener<String>() {
                            public void onResponse(String str) {
                                try {
                                    JSONObject jSONObject = new JSONObject(str);
                                    boolean z = jSONObject.getBoolean("success");
                                    if (obj.equals("")) {
                                        Toast.makeText(loginActivity.this.getApplicationContext(), "No has ingresado ningun dato", 1).show();
                                    } else if (z) {
                                        int i = jSONObject.getInt("personid");
                                        String string = jSONObject.getString("firstnames");
                                        String string2 = jSONObject.getString("lastnames");
                                        String string3 = jSONObject.getString("dni");
                                        String string4 = jSONObject.getString("phone");
                                        String string5 = jSONObject.getString("email");
                                        String string6 = jSONObject.getString("address");
                                        String string7 = jSONObject.getString("username");
                                        String string8 = jSONObject.getString("img");
                                        String string9 = jSONObject.getString("location_id");
                                        String string10 = jSONObject.getString("rutas");
                                        Intent intent = new Intent(loginActivity.this, syncActivity.class);
                                        intent.putExtra("ID_USER", i);
                                        intent.putExtra("NOMBRE_USER", string);
                                        intent.putExtra("APELLIDO_USER", string2);
                                        intent.putExtra("DNI_USER", string3);
                                        intent.putExtra("PHONE_USER", string4);
                                        intent.putExtra("EMAIL_USER", string5);
                                        intent.putExtra("ADDRESS_USER", string6);
                                        intent.putExtra("NAME_USER", string7);
                                        intent.putExtra("IMG_USER", string8);
                                        Usuario usuario = new Usuario();
                                        usuario.setIduser(String.valueOf(i));
                                        usuario.setNombre(string7);
                                        usuario.setBodega(string9);
                                        usuario.setRutas(string10);
                                        HashMap hashMap = new HashMap();
                                        hashMap.put("username", string7);
                                        hashMap.put("password", "");
                                        hashMap.put("person_id", String.valueOf(i));
                                        hashMap.put("location_id", string9);
                                        hashMap.put("rutas", string10);
                                        loginActivity.this.controller.insertLogueado(hashMap);
                                        loginActivity.this.startActivity(intent);
                                    } else {
                                        new Builder(loginActivity.this).setMessage((CharSequence) "Contraseña incorrecta").setNegativeButton((CharSequence) "Intente de nuevo", null).create().show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }));
                        return;
                    }
                    loginActivity.this.IngresoActivo();
                }
            });
        } else {
            IngresoActivo();
        }
    }

    public void PrimerIngreso(final String str) {
        Volley.newRequestQueue(this).add(new checkuser(str, new Listener<String>() {
            public void onResponse(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    boolean z = jSONObject.getBoolean("success");
                    if (str.equals("")) {
                        Toast.makeText(loginActivity.this.getApplicationContext(), "Debes ingresar la contraseña", 1).show();
                    } else if (z) {
                        int i = jSONObject.getInt("personid");
                        String string = jSONObject.getString("firstnames");
                        String string2 = jSONObject.getString("lastnames");
                        String string3 = jSONObject.getString("dni");
                        String string4 = jSONObject.getString("phone");
                        String string5 = jSONObject.getString("email");
                        String string6 = jSONObject.getString("address");
                        String string7 = jSONObject.getString("username");
                        String string8 = jSONObject.getString("img");
                        Intent intent = new Intent(loginActivity.this, syncActivity.class);
                        intent.putExtra("ID_USER", i);
                        intent.putExtra("NOMBRE_USER", string);
                        intent.putExtra("APELLIDO_USER", string2);
                        intent.putExtra("DNI_USER", string3);
                        intent.putExtra("PHONE_USER", string4);
                        intent.putExtra("EMAIL_USER", string5);
                        intent.putExtra("ADDRESS_USER", string6);
                        intent.putExtra("NAME_USER", string7);
                        intent.putExtra("IMG_USER", string8);
                        Usuario usuario = new Usuario();
                        usuario.setIduser(String.valueOf(i));
                        usuario.setNombre(string7);
                        usuario.setApellido(string2);
                        Usuario.setNombres(string);
                        usuario.setCedula(str);
                        Usuario.setPass(str);
                        usuario.setDireccion(string6);
                        usuario.setTelefono(string4);
                        HashMap hashMap = new HashMap();
                        hashMap.put("username", string7);
                        hashMap.put("password", "");
                        hashMap.put("person_id", String.valueOf(i));
                        loginActivity.this.controller.insertLogueado(hashMap);
                        loginActivity.this.startActivity(intent);
                    } else {
                        new Builder(loginActivity.this).setMessage((CharSequence) "Contraseña incorrecta").setNegativeButton((CharSequence) "Intente de nuevo", null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void IngresoActivo() {
        String userLogueado = this.controller.userLogueado(this.nameuserLogeado, this.iduserLogeado);
        Context applicationContext;
        StringBuilder stringBuilder;
        if (userLogueado.equals(this.iduserLogeado)) {
            applicationContext = getApplicationContext();
            stringBuilder = new StringBuilder();
            stringBuilder.append("El usuario ya se encuentra logueado");
            stringBuilder.append(userLogueado);
            Toast.makeText(applicationContext, stringBuilder.toString(), 1).show();
            startActivity(new Intent(getApplicationContext(), PedidosActivity.class));
            return;
        }
        applicationContext = getApplicationContext();
        stringBuilder = new StringBuilder();
        stringBuilder.append("El usuario ya NOO SE encuentra logueado");
        stringBuilder.append(userLogueado);
        Toast.makeText(applicationContext, stringBuilder.toString(), 1).show();
        startActivity(new Intent(getApplicationContext(), loginActivity.class));
    }
}
