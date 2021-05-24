package com.example.danielreyes.TAT;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        Intent intent = getIntent();
        intent.getExtras().getString("IDE_ITEM");
        intent.getExtras().getString("NAME_ITEM");
        intent.getExtras().getString("PRECIO_ITEM");
        intent.getExtras().getString("STOCK_ITEM");
    }
}
