package com.kochsj.realrealty.ui.profile.preferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PreferencesViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public PreferencesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the profile preferences fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
