package com.example.sis_ver01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;


public class SubActivity extends AppCompatActivity {
    TextView txt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building);
        txt = (TextView)findViewById(R.id.textView2);

        Intent inten = getIntent();
        String name = inten.getExtras().getString("name");
        txt.setText(name);
    }
}
