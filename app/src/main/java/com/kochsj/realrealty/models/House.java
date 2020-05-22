package com.kochsj.realrealty.models;

public class House {
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

    public House(String zpid, String streetAddress, String city, String state, String zipCode, String photoURL, String beds, String baths, long timeStamp, double latitude, double longitude){
        this.zpid = zpid;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state =state;
        this.zipCode = zipCode;
        this.photoURL = photoURL;
        this.beds = beds;
        this.baths = baths;
        this.timeStamp = timeStamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String[] detailViewStringArrayFromHouse() {
        String[] stringArray = new String[7];

        stringArray[0] = this.streetAddress;
        stringArray[1] = this.city;
        stringArray[2] = this.state;
        stringArray[3] = this.zipCode;
        stringArray[4] = this.beds;
        stringArray[5] = this.baths;
        stringArray[6] = this.photoURL;

        return stringArray;
    }

}
