package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class boGun extends AppCompatActivity implements View.OnClickListener{
    Button floor1,floor2,floor3,floor4,floor5;
    ImageView imageview;
    TextView b_Name;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bogun);
        floor1 = (Button) findViewById(R.id.floor1);
        floor2 = (Button) findViewById(R.id.floor2);
        floor3 = (Button) findViewById(R.id.floor3);
        floor4 = (Button) findViewById(R.id.floor4);

        imageview = (ImageView)findViewById(R.id.imageView2);
        Intent inten = getIntent();

        floor1.setOnClickListener(this) ;
        floor2.setOnClickListener(this) ;
        floor3.setOnClickListener(this) ;
        floor4.setOnClickListener(this) ;

    }
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.floor1 :
                //질의하라구
                imageview.setImageResource(R.drawable.sample);
                break ;
            case R.id.floor2 :
                imageview.setImageResource(R.drawable.sample);
                break ;
            case R.id.floor3 :
                imageview.setImageResource(R.drawable.sample);
                break ;
            case R.id.floor4 :
                imageview.setImageResource(R.drawable.sample);
                break ;
            case R.id.floor5 :
                imageview.setImageResource(R.drawable.sample);
                break ;
        }
    }

}
