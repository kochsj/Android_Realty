package com.kochsj.realrealty.ui.more.team;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OurTeamViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public OurTeamViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is our team's fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
