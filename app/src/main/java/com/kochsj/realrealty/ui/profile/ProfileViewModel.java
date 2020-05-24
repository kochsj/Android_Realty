package com.kochsj.realrealty.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kochsj.realrealty.MainApplication;
import com.kochsj.realrealty.models.User;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<String> mFirstName;
    private MutableLiveData<String> mLastName;
    private MutableLiveData<String> mPhoneNumber;
    private MutableLiveData<String> mEmail;

    private User user = MainApplication.getUserData();

    public ProfileViewModel() {
        mFirstName = new MutableLiveData<>();
        mLastName = new MutableLiveData<>();
        mPhoneNumber = new MutableLiveData<>();
        mEmail = new MutableLiveData<>();

        mFirstName.setValue(user.firstName);
        mLastName.setValue(user.lastName);
        mPhoneNumber.setValue(user.phoneNumber);
        mEmail.setValue(user.email);
    }

    public LiveData<String> getFirstName() {
        return mFirstName;
    }
    public LiveData<String> getLastName() {
        return mLastName;
    }
    public LiveData<String> getPhoneNumber() {
        return mPhoneNumber;
    }
    public LiveData<String> getEmail() {
        return mEmail;
    }


}
