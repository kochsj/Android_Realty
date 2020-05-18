package com.kochsj.realrealty.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kochsj.realrealty.models.Agent;
import com.kochsj.realrealty.models.House;
import com.kochsj.realrealty.models.User;

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

    public void updateUserData(User user) {
        userCollection.document(uid).update(user.constructUserHashMap());
    }

    public User getUserData() {

        final Task<DocumentSnapshot> userData = userCollection.document(uid)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot snapshot = task.getResult();
                            user = _userFromSnapshot(snapshot);
                        } else {
                            Log.d("UDS", "get failed with ", task.getException());
                        }
                    }
                });

        return user;
    }

    //user data from document snapshot
    private User _userFromSnapshot(DocumentSnapshot snapshot) {
//    print("making user data...");
        String firstName = (String)snapshot.get("first_name");
        String lastName = (String)snapshot.get("last_name");
        String phoneNumber = (String)snapshot.get("phone_number");
        String email = (String)snapshot.get("email");
        House house = (House)snapshot.get("house");
        Agent agent = (Agent)snapshot.get("agent");
        String profilePictureUrl = (String)snapshot.get("profile_picture_url");

        return new User(
                uid,
                firstName,
                lastName,
                phoneNumber,
                email,
                house,
                agent,
                profilePictureUrl
        );
    }
}
