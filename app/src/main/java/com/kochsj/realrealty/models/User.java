package com.kochsj.realrealty.models;


import java.util.HashMap;

public class User {
    // from firebase
    String uid;

    // required to register
    String firstName;
    String lastName;
    String phoneNumber;
    String email;

    // can be added in user's profile
    House house;
    Agent agent;
    String profilePictureURL;

    public User(String uid, String firstName, String lastName, String phoneNumber, String email, House house, Agent agent, String profilePictureURL) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.house  = house;
        this.agent = agent;
        this.profilePictureURL = profilePictureURL;
    }

    public HashMap<String, Object> constructUserHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("first_name", firstName);
        map.put("last_name", lastName);
        map.put("phone_number", phoneNumber);
        map.put("email", email);
        map.put("house", house);
        map.put("agent", agent);
        map.put("profile_picture_url", profilePictureURL);

        return map;
    }


}
