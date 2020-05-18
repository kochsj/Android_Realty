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
    public int timeStamp;

    public House(String zpid, String streetAddress, String city, String state, String zipCode, String photoURL, String beds, String baths, int timeStamp){
        this.zpid = zpid;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state =state;
        this.zipCode = zipCode;
        this.photoURL = photoURL;
        this.beds = beds;
        this.baths = baths;
        this.timeStamp = timeStamp;
    }

}
