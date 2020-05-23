package com.kochsj.realrealty.services;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kochsj.realrealty.models.Agent;
import com.kochsj.realrealty.models.House;
import com.kochsj.realrealty.models.User;

import java.util.HashMap;

public class UserDatabaseService {
    private final String uid;
    private final CollectionReference userCollection;
    private User user;

    public UserDatabaseService(String uid){
        this.uid = uid;
        this.userCollection = FirebaseFirestore.getInstance().collection("users");
        this.user = null;
    }

    public void addUserToDatabase(User user) {
        Log.d("setuserdata", "updateUserData: setting........");

        userCollection.document(uid).set(user.constructUserHashMap());
    }

    public void addFavoriteHouseToUsersFavorites(House house) {
        userCollection.document(uid).collection("favorites").document(house.zpid).set(house.constructHouseHashMap());
    }

    public void removeFavoriteHouse(String zpid) {
        userCollection.document(uid).collection("favorites").document(zpid).delete();
    }

    public Task<QuerySnapshot> getFavoriteHouses() {
        return userCollection.document(uid).collection("favorites").get();
    }

    public void updateUserData(User user) {
        Log.d("updateuserdata", "updateUserData: updating........");
        userCollection.document(uid).update(user.constructUserHashMap());
    }

    public Task<DocumentSnapshot> getUserData() {
        // returns a Task - To use: use .addOnCompleteListener method to implement data use case
        return userCollection.document(uid).get();
    }

    //user data from document snapshot
    public User userFromSnapshot(DocumentSnapshot snapshot) {
        String firstName = (String)snapshot.get("first_name");
        String lastName = (String)snapshot.get("last_name");
        String phoneNumber = (String)snapshot.get("phone_number");
        String email = (String)snapshot.get("email");
        HashMap house = (HashMap)snapshot.get("house");
        HashMap agent = (HashMap)snapshot.get("agent");
        String profilePictureUrl = (String)snapshot.get("profile_picture_url");


        return new User(
                uid,
                firstName,
                lastName,
                phoneNumber,
                email,
                new House(
                        (String)house.get("zpid"),
                        (String)house.get("streetAddress"),
                        (String)house.get("city"),
                        (String)house.get("state"),
                        (String)house.get("zipCode"),
                        (String)house.get("photoURL"),
                        (String)house.get("beds"),
                        (String)house.get("baths"),
                        (double)house.get("latitude"),
                        (double)house.get("longitude")
                ),
                new Agent(
                        (String)agent.get("firstName"),
                        (String)agent.get("lastName"),
                        (String)agent.get("phoneNumber"),
                        (String)agent.get("email"),
                        (String)agent.get("company")
                ),
                profilePictureUrl
        );
    }

    public House houseFromSnapshot(DocumentSnapshot snapshot, String zpid) {
        String streetAddress = (String)snapshot.get("street_address");
        String city = (String)snapshot.get("city");
        String state = (String)snapshot.get("state");
        String zipCode = (String)snapshot.get("zip_code");
        String photoURL = (String)snapshot.get("photo_url");
        String beds = (String)snapshot.get("beds");
        String baths = (String)snapshot.get("baths");
        double latitude = (double)snapshot.get("latitude");
        double longitude = (double)snapshot.get("longitude");

        return new House(
                zpid,
                streetAddress,
                city,
                state,
                zipCode,
                photoURL,
                beds,
                baths,
                latitude,
                longitude
        );
    }
}
