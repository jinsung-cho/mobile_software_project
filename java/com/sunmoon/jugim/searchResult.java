package com.sunmoon.jugim;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class searchResult extends AppCompatActivity {
    private final String dbName = "STRUCTURE.db";
    TextView major_text1, major_text2, major_text3, major_text4,  prof_text1, prof_text2, prof_text3, prof_text4, prof_text5;
    String structure, name, major, phone, room;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        major_text1 = (TextView)findViewById(R.id.major_text1) ;
        major_text2 = (TextView)findViewById(R.id.major_text2) ;
        major_text3 = (TextView)findViewById(R.id.major_text3) ;
        major_text4 = (TextView)findViewById(R.id.major_text4) ;
        prof_text1 = (TextView)findViewById(R.id.prof_text1) ;
        prof_text2 = (TextView)findViewById(R.id.prof_text2) ;
        prof_text3 = (TextView)findViewById(R.id.prof_text3) ;
        prof_text4 = (TextView)findViewById(R.id.prof_text4) ;
        prof_text5 = (TextView)findViewById(R.id.prof_text5) ;

        int c_int=0;

        Intent inten = getIntent();
        String ob_name = inten.getExtras().getString("text");//받아온 관 이름
        String result_str;
        SQLiteDatabase readDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

        String tableName = "MAJOR";
        Cursor c_major = readDB.rawQuery("SELECT * FROM " + tableName + " WHERE NAME LIKE '%" + ob_name + "%'" , null);
        if (c_major != null) {
            if (c_major.moveToFirst()) {
                do {
                    name = c_major.getString(c_major.getColumnIndex("NAME"));
                    phone = c_major.getString(c_major.getColumnIndex("PHONE"));
                    room = c_major.getString(c_major.getColumnIndex("ROOM"));

                    result_str = "학과: " + name + "\n전화번호: " + phone + "\n" + "과사무실: " + room;
                    if(c_int == 0) {
                        prof_text1 = (TextView)findViewById(R.id.prof_text1) ;
                        major_text1.setText(result_str);
                    }else if(c_int == 1) {
                        major_text2.setText(result_str);
                    }
                    c_int++;
                } while (c_major.moveToNext());
            }
        }

        c_int = 0;
        tableName = "PROFESSOR";
        Cursor c_prof = readDB.rawQuery("SELECT * FROM " + tableName + " WHERE NAME LIKE '%" + ob_name + "%'" , null);
        if (c_prof != null) {
            if (c_prof.moveToFirst()) {
                do {
                    structure = c_prof.getString(c_prof.getColumnIndex("STRUCTURE_NAME"));
                    name = c_prof.getString(c_prof.getColumnIndex("NAME"));
                    major = c_prof.getString(c_prof.getColumnIndex("MAJOR"));
                    phone = c_prof.getString(c_prof.getColumnIndex("PHONE"));
                    room = c_prof.getString(c_prof.getColumnIndex("ROOM"));

                    result_str = "교수명: " + name + "   학과: " + major + "\n전화번호: " + phone + "\n건물: " + structure + "   호실: " + room;
                    if(c_int == 0) {
                        prof_text1 = (TextView)findViewById(R.id.prof_text1) ;
                        prof_text1.setText(result_str);
                    }else if(c_int == 1){
                        prof_text2.setText(result_str);
                    }else if(c_int == 2){
                        prof_text3.setText(result_str);
                    }
                    else if(c_int == 3){
                        prof_text4.setText(result_str);
                    }
                    else if(c_int == 4){
                        prof_text5.setText(result_str);
                    }
                    c_int++;
                } while (c_prof.moveToNext());
            }
        }

        readDB.close();
    }
}
