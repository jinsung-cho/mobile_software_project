package com.sunmoon.jugim;

import android.content.Intent;

public class MenuModel {
    public String menuName, url;
    public boolean hasChildren, isGroup;

    public MenuModel(String menuName, boolean isGroup, boolean hasChildren, String url) {
        this.menuName = menuName;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
        this.url = url;
    }

    public String getMenuName() {
        return menuName;
    }

    private void startActivity(Intent i) {
    }
}
