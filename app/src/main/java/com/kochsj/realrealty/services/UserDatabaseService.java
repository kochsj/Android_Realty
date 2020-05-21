package com.kochsj.realrealty.services;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
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

    // collection reference

//    Agent agentFromJson(String str) {
//        final jsonData = json.decode(str);
//        return Agent.fromMap(jsonData);
//    }
//
//    String agentToJson(Agent agent) {
//        final dyn = agent.toMap();
//        return json.encode(dyn);
//    }
//
//    House houseFromJson(String str) {
//        final jsonData = json.decode(str);
//        return House.fromMap(jsonData);
//    }
//
//    String houseToJson(House house) {
//        final dyn = house.toMap();
//        return json.encode(dyn);
//    }



    //            Agent a = new Agent(
//                    "Ben",
//                    "Testcase",
//                    "42512345678",
//                    "test@test.test",
//                    "Real Realty"
//            );
//            House h = new House(
//                    "12345677",
//                    "123 Main St",
//                    "Redmond",
//                    "WA",
//                    "98052",
//                    "",
//                    "2",
//                    "2",
//                    123
//            );
//            User u = new User(
//                    userID,
//                    "Stephen",
//                    "Koch",
//                    "4258675309",
//                    "lou@lou.lou",
//                    h,
//                    a,
//                    "myprofilepictureurl.com"
//            );
//
//            db.collection("users")
//                    .add(u.constructUserHashMap())
//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w(TAG, "Error adding document", e);
//                        }
//                    });

    public void addUserToDatabase(User user) {
        Log.d("setuserdata", "updateUserData: setting........");

        userCollection.document(uid).set(user.constructUserHashMap());
    }


    public void updateUserData(User user) {
        Log.d("updateuserdata", "updateUserData: updating........");
        userCollection.document(uid).update(user.constructUserHashMap());
    }

    public Task<DocumentSnapshot> getUserData() {

        return userCollection.document(uid).get();

//        final Task<DocumentSnapshot> userData = userCollection.document(uid)
//                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if(task.isSuccessful()){
//                            DocumentSnapshot snapshot = task.getResult();
//                            user = _userFromSnapshot(snapshot);
//                        } else {
//                            Log.d("UDS", "get failed with ", task.getException());
//                        }
//                    }
//                });
//
//        return user;
    }

    //user data from document snapshot
    public User userFromSnapshot(DocumentSnapshot snapshot) {
//    print("making user data...");
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
                        (long) house.get("timeStamp"),
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
}
