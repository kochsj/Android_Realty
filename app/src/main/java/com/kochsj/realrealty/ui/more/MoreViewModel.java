package com.kochsj.realrealty.ui.more;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoreViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MoreViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is more view fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
