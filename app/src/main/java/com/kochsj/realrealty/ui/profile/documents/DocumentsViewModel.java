package com.kochsj.realrealty.ui.profile.documents;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DocumentsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public DocumentsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}