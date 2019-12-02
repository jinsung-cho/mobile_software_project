package com.example.test;

public class MapPoint {
    private String Name;
    private  double lat;
    private  double lng;
    private String ID;

    public MapPoint(){
        super();

    }

    public MapPoint(String Name,double lat,double lng, String ID){
        this.ID= ID;
        this.Name = Name;
        this.lat = lat;
        this.lng = lng;

    }

    public String getName(){
        return Name;

    }
    public void setName(String Name){
        this.Name=Name;

    }

    public double getLatitude(){
        return lat;
    }
    public void setLatitude(double lat){
        this.lat= lat;

    }
    public double getLongitude(){
        return lng;

    }
    public void setLongitude(double lng){
        this.lng = lng;
    }
    public void setID(String ID){
        this.ID=ID;
    }
    public String getID(){
        return this.ID;
    }
}

