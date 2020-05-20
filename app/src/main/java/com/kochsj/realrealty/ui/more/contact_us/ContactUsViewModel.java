package com.kochsj.realrealty.ui.more.contact_us;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactUsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ContactUsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the contact us fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
