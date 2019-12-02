package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class hakSeng extends AppCompatActivity implements View.OnClickListener{
    Button floor1,floor2,floor31,floor32,floorf1;
    ImageView imageview;
    TextView b_Name;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hakseng);
        floorf1 = (Button) findViewById(R.id.floorf1);
        floor1 = (Button) findViewById(R.id.floor1);
        floor2 = (Button) findViewById(R.id.floor2);
        floor31= (Button) findViewById(R.id.floor31);
        floor32 = (Button) findViewById(R.id.floor32);

        imageview = (ImageView)findViewById(R.id.imageView2);
        Intent inten = getIntent();

        floorf1.setOnClickListener(this) ;
        floor1.setOnClickListener(this) ;
        floor2.setOnClickListener(this) ;
        floor31.setOnClickListener(this) ;
        floor32.setOnClickListener(this) ;

    }
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.floorf1 :
                //질의하라구
                imageview.setImageResource(R.drawable.sample);
                break ;
            case R.id.floor1 :
                imageview.setImageResource(R.drawable.sample);
                break ;
            case R.id.floor2 :
                imageview.setImageResource(R.drawable.h2);
                break ;
            case R.id.floor31 :
                imageview.setImageResource(R.drawable.h31);
                break ;
            case R.id.floor32 :
                imageview.setImageResource(R.drawable.h32);
                break ;
        }
    }
}
