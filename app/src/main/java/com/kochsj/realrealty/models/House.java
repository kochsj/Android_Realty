package com.kochsj.realrealty.models;

import java.util.Calendar;
import java.util.HashMap;

public class House implements Comparable<House> {
    public String zpid;
    public String streetAddress;
    public String city;
    public String state;
    public String zipCode;
    public String photoURL;
    public String beds;
    public String baths;
    public long timeStamp;
    public double latitude;
    public double longitude;

    public House(String zpid, String streetAddress, String city, String state, String zipCode, String photoURL, String beds, String baths, double latitude, double longitude){
        this.zpid = zpid;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state =state;
        this.zipCode = zipCode;
        this.photoURL = photoURL;
        this.beds = beds;
        this.baths = baths;
        this.timeStamp = Calendar.getInstance().getTimeInMillis();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getCity() {
        return city;
    }

    public HashMap<String, Object> constructHouseHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("street_address", streetAddress);
        map.put("city", city);
        map.put("state", state);
        map.put("zip_code", zipCode);
        map.put("photo_url", photoURL);
        map.put("beds", beds);
        map.put("baths", baths);
        map.put("time_stamp", timeStamp);
        map.put("latitude", latitude);
        map.put("longitude", longitude);

        return map;
    }

    public String[] detailViewStringArrayFromHouse() {
        String[] stringArray = new String[11];

        stringArray[0] = this.streetAddress;
        stringArray[1] = this.city;
        stringArray[2] = this.state;
        stringArray[3] = this.zipCode;
        stringArray[4] = this.beds;
        stringArray[5] = this.baths;
        stringArray[6] = this.photoURL;

        stringArray[7] = this.zpid;
        stringArray[8] = Long.toString(this.timeStamp);
        stringArray[9] = Double.toString(this.latitude);
        stringArray[10] = Double.toString(this.longitude);

        return stringArray;
    }

    @Override
    public int compareTo(House h) {
        return Long.compare(getTimeStamp(), h.getTimeStamp());
    }
}
