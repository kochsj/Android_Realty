package com.kochsj.realrealty.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kochsj.realrealty.MainApplication;
import com.kochsj.realrealty.models.User;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    private User user = MainApplication.getUserData();

    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is a profile fragment for: " + user.firstName);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
