package com.kochsj.realrealty;

import android.app.Application;

import com.kochsj.realrealty.models.User;
import com.kochsj.realrealty.services.UserDatabaseService;


public class MainApplication extends Application {
    public static String UID;
    public static User userData;
    public static UserDatabaseService userDatabaseService;


    public static void setUID(String UID) {
        MainApplication.UID = UID;
    }

    public static String getUID() {
        return UID;
    }

    public static void setUserData(User userData) {
        MainApplication.userData = userData;
    }

    public static User getUserData() {
        return userData;
    }

    public static UserDatabaseService getUserDatabaseService() {
        return userDatabaseService;
    }

    public static void setUserDatabaseService(UserDatabaseService userDatabaseService) {
        MainApplication.userDatabaseService = userDatabaseService;
    }
}

