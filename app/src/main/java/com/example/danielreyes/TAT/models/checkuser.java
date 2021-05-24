package com.example.danielreyes.TAT.models;

import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class checkuser extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://shared12.co-prueba.site/~ospostco/curso/checklogin.php";
    private Map<String, String> params = new HashMap();

    public checkuser(String str, Listener<String> listener) {
        super(1, LOGIN_REQUEST_URL, listener, null);
        this.params.put("password", str);
    }

    public Map<String, String> getParams() {
        return this.params;
    }
}
