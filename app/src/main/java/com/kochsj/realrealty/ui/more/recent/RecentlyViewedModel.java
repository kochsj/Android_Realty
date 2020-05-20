package com.kochsj.realrealty.ui.more.recent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecentlyViewedModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RecentlyViewedModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is where recently viewed properties will be shown");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
