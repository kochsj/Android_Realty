package com.kochsj.realrealty.ui.profile.agent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AgentViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public AgentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
