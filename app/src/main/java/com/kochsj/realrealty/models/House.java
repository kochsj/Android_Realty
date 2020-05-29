package com.kochsj.realrealty.models;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

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
    public String squareFootage;
    public String lotSize;
    public String yearBuilt;
    public String propertyType;
    public long timeStamp;
    public double latitude;
    public double longitude;

    public House(
            String zpid,
            String streetAddress,
            String city,
            String state,
            String zipCode,
            String photoURL,
            String beds,
            String baths,
            String squareFootage,
            String lotSize,
            String yearBuilt,
            String propertyType,
            double latitude,
            double longitude
        )
        {
            this.zpid = zpid;
            this.streetAddress = streetAddress;
            this.city = city;
            this.state =state;
            this.zipCode = zipCode;
            this.photoURL = photoURL;
            this.beds = beds;
            this.baths = baths;
            this.squareFootage = squareFootage;
            this.lotSize =lotSize;
            this.yearBuilt = yearBuilt;
            this.propertyType = propertyType;
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
        map.put("square_footage", squareFootage);
        map.put("lot_size", lotSize);
        map.put("year_built", yearBuilt);
        map.put("property_type", propertyType);
        map.put("time_stamp", timeStamp);
        map.put("latitude", latitude);
        map.put("longitude", longitude);

        return map;
    }

    public String[] detailViewStringArrayFromHouse() {
        String[] stringArray = new String[15];

        stringArray[0] = this.zpid;
        stringArray[1] = this.streetAddress;
        stringArray[2] = this.city;
        stringArray[3] = this.state;
        stringArray[4] = this.zipCode;
        stringArray[5] = this.photoURL;
        stringArray[6] = this.beds;
        stringArray[7] = this.baths;
        stringArray[8] = this.squareFootage;
        stringArray[9] = this.lotSize;
        stringArray[10] = this.yearBuilt;
        stringArray[11] = this.propertyType;
        stringArray[12] = Long.toString(this.timeStamp);
        stringArray[13] = Double.toString(this.latitude);
        stringArray[14] = Double.toString(this.longitude);

        return stringArray;
    }

    @Override
    public int compareTo(House h) {
        return Long.compare(getTimeStamp(), h.getTimeStamp());
    }

    public static House houseFromJsonObject(JSONObject jsonObject, String zpid, @Nullable String photoURL) throws JSONException {
        String streetAddress = jsonObject.getString("addressLine1");
        String city = jsonObject.getString("city");
        String state = jsonObject.getString("state");
        String zipCode = jsonObject.getString("zipCode");
        photoURL = photoURL != null ? photoURL : "";
        String beds = String.valueOf(jsonObject.getInt("bedrooms"));
        String baths = String.valueOf(jsonObject.getInt("bathrooms"));
        String squareFootage = String.valueOf(jsonObject.getInt("squareFootage"));
        String lotSize = String.valueOf(jsonObject.getInt("lotSize"));
        String yearBuilt = String.valueOf(jsonObject.getInt("yearBuilt"));
        String propertyType = jsonObject.getString("propertyType");
        double latitude = jsonObject.getDouble("latitude");
        double longitude = jsonObject.getDouble("longitude");

        return new House(
                zpid,
                streetAddress,
                city,
                state,
                zipCode,
                photoURL,
                beds,
                baths,
                squareFootage,
                lotSize,
                yearBuilt,
                propertyType,
                latitude,
                longitude
        );


    }
}
