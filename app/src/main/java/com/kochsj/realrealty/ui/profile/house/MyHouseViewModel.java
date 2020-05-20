package com.kochsj.realrealty.ui.profile.house;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyHouseViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MyHouseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is where you see info for your house");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
