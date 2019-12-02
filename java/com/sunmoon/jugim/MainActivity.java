package com.sunmoon.jugim;

import android.content.res.AssetManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.webkit.WebView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.widget.SearchView;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import android.graphics.PointF;

import android.widget.*;

import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.lang.*;

import android.database.sqlite.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    private static final String TAG = ".MainActivity";
    private final String TMAP_API_KEY = "7ed391e6-77ff-443a-af35-afd43acc863e";
    private TMapView tmap;
    private ArrayList<MapPoint> c_mapPoint = new ArrayList<MapPoint>();
    private ArrayList<TMapMarkerItem> d_maker = new ArrayList<TMapMarkerItem>();
    TMapMarkerItem item_tmp;

    SearchView searchView;
    RelativeLayout relativeLayout;
    LinearLayout mapLayout;
    View toolbarHeader;

    Context ctx;
    WebView webView;
    Toolbar toolbar;
    static private final String dbName = "STRUCTURE.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mapLayout = (LinearLayout) findViewById(R.id.mapLayout);
        mapLayout.bringToFront();
        toolbar.bringToFront();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Intent intent = new Intent(this, loading.class);
        startActivity(intent);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.main_layout_tmap);
        tmap = new TMapView(this);
        tmap.setSKTMapApiKey(TMAP_API_KEY);
        layout.addView(tmap);
        tmap.setCenterPoint(127.074937, 36.798934);
        initialize(this);
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
                        Intent intent = new Intent(getApplicationContext(), building.class);
                        intent.putExtra("name", "본관");
                        startActivity(intent);
                    } else if (array[1].equals("원화관")) {
                        Intent intent = new Intent(getApplicationContext(), wonHwa.class);
                        intent.putExtra("name", "원화관");
                        startActivity(intent);
                    } else if (array[1].equals("공학관")) {
                        Intent intent = new Intent(getApplicationContext(), gongHak.class);
                        intent.putExtra("name", "공학관");
                        startActivity(intent);
                    } else if (array[1].equals("자연관")) {
                        Intent intent = new Intent(getApplicationContext(), jaYeon.class);
                        intent.putExtra("name", "자연관");
                        startActivity(intent);
                    } else if (array[1].equals("보건관")) {
                        Intent intent = new Intent(getApplicationContext(), boGun.class);
                        intent.putExtra("name", "보건관");
                        startActivity(intent);
                    } else if (array[1].equals("인문관")) {
                        Intent intent = new Intent(getApplicationContext(), inMoon.class);
                        intent.putExtra("name", "인문관");
                        startActivity(intent);
                    } else if (array[1].equals("셔틀장")) {
                        Intent intent_web = new Intent(MainActivity.this, webActivity.class);
                        intent_web.putExtra("url", "https://smbus.sungmin.dev/");
                        startActivity(intent_web);
                    } else if (array[1].equals("기숙사")) {
                        Intent intent_web = new Intent(MainActivity.this, webActivity.class);
                        intent_web.putExtra("url", "https://dorm.sunmoon.ac.kr/");
                        startActivity(intent_web);
                    } else if (array[1].equals("산학관")) {
                        Intent intent = new Intent(getApplicationContext(), sanhak.class);
                        intent.putExtra("name", "산학관");
                        startActivity(intent);
                    } else if (array[1].equals("학생회관")) {
                        Intent intent = new Intent(getApplicationContext(), hakSeng.class);
                        intent.putExtra("name", "학생회관");
                        startActivity(intent);
                    } else if (array[1].equals("도서관")) {
                        Intent intent = new Intent(getApplicationContext(), library.class);
                        intent.putExtra("name", "중앙도서관");
                        startActivity(intent);
                    } else if (array[1].equals("체육관(스포츠과학관)")) {
                        Intent intent = new Intent(getApplicationContext(), sports.class);
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

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();
        searchView.setQueryHint("과.교수님 이름으로 검색");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.length() < 2) {
                    Toast.makeText(getApplicationContext(), "두글자 이상 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return false;
                }

                Intent intent = new Intent(getApplicationContext(), searchResult.class);
                intent.putExtra("text", s);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(getApplicationContext(), "두둥탁", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option:
                Toast.makeText(getApplicationContext(), "얄루", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_send) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_tools) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareMenuData() {
        MenuModel menuModel = new MenuModel("SIS", false, false, "");
        mapLayout.bringToFront();
        toolbar.bringToFront();
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel("건물", true, true, "");
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("본관", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("공학관", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("원화관", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("자연관", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("인문관", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("기숙사", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("학생회관", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("도서관", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("보건관", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("산학관", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("체육관(스포츠과학관)", false, false, "");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("시설물", true, true, "");
        headerList.add(menuModel);

        childModel = new MenuModel("셔틀장", false, false, "https://smbus.sungmin.dev/");
        childModelsList.add(childModel);

        childModel = new MenuModel("교직원 식당", false, false, "https://lily.sunmoon.ac.kr/Page/UnivLife/UnivLife07_05.aspx");
        childModelsList.add(childModel);

        childModel = new MenuModel("학생회관 식당", false, false, "https://lily.sunmoon.ac.kr/Page/UnivLife/UnivLife07_05.aspx?ca=003");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        webView = (WebView) findViewById(R.id.webView);

        menuModel = new MenuModel("홈페이지", true, true, "");
        headerList.add(menuModel);

        childModel = new MenuModel("선문대학교 메인 홈페이지", false, false, "https://lily.sunmoon.ac.kr/MainDefault.aspx");
        childModelsList.add(childModel);

        childModel = new MenuModel("선문대학교 입학 홈페이지", false, false, "http://ilove.sunmoon.ac.kr/");
        childModelsList.add(childModel);

        childModel = new MenuModel("선문대학교 중앙 도서관", false, false, "https://lib.sunmoon.ac.kr/");
        childModelsList.add(childModel);

        childModel = new MenuModel("선문대학교 e-강의동", false, false, "https://lms.sunmoon.ac.kr/ilos/introduce/introduce.acl");
        childModelsList.add(childModel);

        childModel = new MenuModel("선문대학교 포털 사이트", false, false, "https://lily.sunmoon.ac.kr/MainDefault.aspx");
        childModelsList.add(childModel);

        childModel = new MenuModel("선문대학교 성화학숙", false, false, "https://dorm.sunmoon.ac.kr/");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
    }

    private void populateExpandableList() {
        expandableListAdapter = new ExpandableListAdapters(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                MenuModel model2 = headerList.get(groupPosition);
                if(model2.getMenuName().equals("SIS")){
                    onBackPressed();
                }
                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        Intent intent_web = new Intent(MainActivity.this, webActivity.class);
                        intent_web.putExtra("url", headerList.get(groupPosition).url);
                        startActivity(intent_web);
                    }
                }
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    if (model.getMenuName().equals("본관")) {
                        Intent intent = new Intent(getApplicationContext(), building.class);
                        intent.putExtra("name", "본관");
                        startActivity(intent);
                    } else if (model.getMenuName().equals("원화관")) {
                        Intent intent = new Intent(getApplicationContext(), wonHwa.class);
                        intent.putExtra("name", "원화관");
                        startActivity(intent);
                    } else if (model.getMenuName().equals("공학관")) {
                        Intent intent = new Intent(getApplicationContext(), gongHak.class);
                        intent.putExtra("name", "공학관");
                        startActivity(intent);
                    } else if (model.getMenuName().equals("자연관")) {
                        Intent intent = new Intent(getApplicationContext(), jaYeon.class);
                        intent.putExtra("name", "자연관");
                        startActivity(intent);
                    } else if (model.getMenuName().equals("보건관")) {
                        Intent intent = new Intent(getApplicationContext(), boGun.class);
                        intent.putExtra("name", "보건관");
                        startActivity(intent);
                    } else if (model.getMenuName().equals("인문관")) {
                        Intent intent = new Intent(getApplicationContext(), inMoon.class);
                        intent.putExtra("name", "인문관");
                        startActivity(intent);
                    } else if (model.getMenuName().equals("산학관")) {
                        Intent intent = new Intent(getApplicationContext(), sanhak.class);
                        intent.putExtra("name", "산학관");
                        startActivity(intent);
                    } else if (model.getMenuName().equals("학생회관")) {
                        Intent intent = new Intent(getApplicationContext(), hakSeng.class);
                        intent.putExtra("name", "학생회관");
                        startActivity(intent);
                    } else if (model.getMenuName().equals("도서관")) {
                        Intent intent = new Intent(getApplicationContext(), library.class);
                        intent.putExtra("name", "중앙도서관");
                        startActivity(intent);
                    } else if (model.getMenuName().equals("체육관(스포츠과학관)")) {
                        Intent intent = new Intent(getApplicationContext(), sports.class);
                        intent.putExtra("name", "체육관(스포츠과학관)");
                        startActivity(intent);
                    }

                    if (model.url.length() > 0) {

                        Intent intent_web = new Intent(MainActivity.this, webActivity.class);
                        intent_web.putExtra("url", model.url);
                        startActivity(intent_web);
                    }
                }
                return false;
            }
        });
    }

    public void addMarker() {
        SQLiteDatabase readDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        String tableName = "STRUCTURE";
        Cursor c = readDB.rawQuery("SELECT * FROM " + tableName + ";", null);
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

    public void showMarker() {
        for (int i = 0; i < c_mapPoint.size(); i++) {
            TMapPoint point = new TMapPoint(c_mapPoint.get(i).getLatitude(), c_mapPoint.get(i).getLongitude());
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maker);

            item_tmp = new TMapMarkerItem();

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

    public static void initialize(Context ctx) {
        String ROOT_DIR = "/data/data/com.sunmoon.jugim/";
        File folder = new File(ROOT_DIR + "databases/");
        folder.mkdirs();
        File outfile = new File(ROOT_DIR + "databases/" + dbName);
        if (outfile.length() <= 0) {
            AssetManager assetManager = ctx.getResources().getAssets();
            try { InputStream is = assetManager.open(dbName, AssetManager.ACCESS_BUFFER);
                long filesize = is.available();
                byte [] tempdata = new byte[(int)filesize];
                is.read(tempdata);
                is.close();
                outfile.createNewFile();
                FileOutputStream fo = new FileOutputStream(outfile);
                fo.write(tempdata); fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
