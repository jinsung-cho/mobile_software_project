package com.sunmoon.jugim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class library extends AppCompatActivity implements View.OnClickListener{
    Button floor1,floor2,floor3,floor4,floor5,floorf1, info;
    ImageView imageview;
    TextView b_Name;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);
        floorf1 = (Button) findViewById(R.id.floorf1);
        floor1 = (Button) findViewById(R.id.floor1);
        floor2 = (Button) findViewById(R.id.floor2);
        floor3 = (Button) findViewById(R.id.floor3);
        floor4 = (Button) findViewById(R.id.floor4);
        floor5 = (Button) findViewById(R.id.floor5);
        info = (Button)findViewById(R.id.info);
        //b_Name = (TextView) findViewById(R.id.b_Name);
        imageview = (ImageView)findViewById(R.id.imageView2);
        Intent inten = getIntent();
        String name = inten.getExtras().getString("name");//받아온 관 이름
        //b_Name.setText(name);
        floor1.setOnClickListener(this) ;
        floor2.setOnClickListener(this) ;
        floor3.setOnClickListener(this) ;
        floor4.setOnClickListener(this) ;
        floor5.setOnClickListener(this) ;
        floorf1.setOnClickListener(this) ;
        info.setOnClickListener(this);

    }
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.floorf1 :
                imageview.setImageResource(R.drawable.libraryb1);
                break ;
            case R.id.floor1 :
                //질의하라구
                imageview.setImageResource(R.drawable.library1);
                break ;
            case R.id.floor2 :
                imageview.setImageResource(R.drawable.library2);
                break ;
            case R.id.floor3 :
                imageview.setImageResource(R.drawable.library3);
                break ;
            case R.id.floor4 :
                imageview.setImageResource(R.drawable.library4);
                break ;
            case R.id.floor5 :
                imageview.setImageResource(R.drawable.library5);
                break ;
            case R.id.info :
                imageview.setImageResource(R.drawable.libraryinfo);
                break ;

        }
    }
}
