package com.example.sis_ver01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import android.graphics.PointF;
import android.view.MenuItem;
import android.widget.*;

import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;
import java.lang.*;
import java.util.ArrayList;
import android.database.sqlite.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    private static final String TAG = ".MainActivity";
    private final String TMAP_API_KEY = "7ed391e6-77ff-443a-af35-afd43acc863e";
    private  TMapView tmap;
    private ArrayList<MapPoint>c_mapPoint = new ArrayList<MapPoint>();
    private  ArrayList<TMapMarkerItem>d_maker = new ArrayList<TMapMarkerItem>();
    TMapMarkerItem item_tmp;

    private final String dbName = "STRUCTURE.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        navigationView = (NavigationView)findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        toolbar.bringToFront();
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.main_layout_tmap);
        tmap = new TMapView(this);

        tmap.setSKTMapApiKey(TMAP_API_KEY);
        layout.addView(tmap);
        tmap.setCenterPoint(127.074937, 36.798934);
        addMarker();
        showMarker();
        tmap.setZoomLevel(16);

        tmap.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {
            @Override
            public boolean onPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                TMapMarkerItem a = new TMapMarkerItem();
                for (int i = 0; i < arrayList.size(); i++) {
                    a = arrayList.get(i);
                    System.out.println(a.getName());
                }

                String b = a.getName();
                String[] array = b.split("/");

                if (array[0].equals("1")) {
                    if (array[1].equals("본관")) {
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        intent.putExtra("name", "본관");
                        startActivity(intent);
                    }
                    else if (array[1].equals("원화관")) {
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        intent.putExtra("name", "원화관");
                        startActivity(intent);
                    }
                    else if (array[1].equals("공학관")) {
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        intent.putExtra("name", "공학관");
                        startActivity(intent);
                    }
                    else if (array[1].equals("자연관")) {
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        intent.putExtra("name", "자연관");
                        startActivity(intent);
                    }
                    else if (array[1].equals("보건관")) {
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        intent.putExtra("name", "보건관");
                        startActivity(intent);
                    }
                    else if (array[1].equals("인문관")) {
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        intent.putExtra("name", "인문관");
                        startActivity(intent);
                    }
                    else if (array[1].equals("오랜지식당")) {
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        intent.putExtra("name", "오랜지식당");
                        startActivity(intent);
                    }
                    else if (array[1].equals("기숙사")) {
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        intent.putExtra("name", "기숙사");
                        startActivity(intent);
                    }
                    else if (array[1].equals("산학관")) {
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        intent.putExtra("name", "산학관");
                        startActivity(intent);
                    }
                    else if (array[1].equals("학생회관")) {
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        intent.putExtra("name", "학생회관");
                        startActivity(intent);
                    }
                    else if (array[1].equals("도서관")) {
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        intent.putExtra("name", "중앙도서관");
                        startActivity(intent);
                    }
                    else if (array[1].equals("체육관(스포츠과학관)")){
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        intent.putExtra("name", "체육관(스포츠과학관)");
                        startActivity(intent);
                    }
                }
                return false;
            }

            @Override
            public boolean onPressUpEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                return false;
            }
        });
    }

    public void addMarker() {
        SQLiteDatabase readDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        String tableName = "STRUCTURE";
        Cursor c = readDB.rawQuery("SELECT * FROM " + tableName, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    //테이블에서 두개의 컬럼값을 가져와서
                    String Name = c.getString(c.getColumnIndex("NAME"));
                    float Latitude = c.getFloat(c.getColumnIndex("LATITUDE"));
                    float Longitude = c.getFloat(c.getColumnIndex("LONGITUDE"));
                    c_mapPoint.add(new MapPoint(Name, Latitude, Longitude, "-333"));

                } while (c.moveToNext());
            }
        }
        readDB.close();
}

    public void showMarker(){
        Context context;
        for(int i=0; i<c_mapPoint.size(); i++){
            TMapPoint point = new TMapPoint(c_mapPoint.get(i).getLatitude(), c_mapPoint.get(i).getLongitude());
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.maker);

            item_tmp=new TMapMarkerItem();

            item_tmp.setIcon(bitmap);
            item_tmp.setTMapPoint(point);
            item_tmp.setName(c_mapPoint.get(i).getName());
            item_tmp.setVisible(item_tmp.VISIBLE);

            item_tmp.setCanShowCallout(true);

            String name = c_mapPoint.get(i).getName();
            String[] array = name.split("/");

            item_tmp.setCalloutTitle(array[1]);
            item_tmp.setAutoCalloutVisible(false);

            d_maker.add(item_tmp);
            tmap.addMarkerItem(c_mapPoint.get(i).getName(), item_tmp);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.internal_facility:
                Toast.makeText(MainActivity.this, "Internal_facility Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.external_facility:
                Toast.makeText(MainActivity.this, "External_facility Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.link_sunmoon:
                Toast.makeText(MainActivity.this, "link_sunmoon Selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}

