package com.kochsj.realrealty.ui.more.calculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MortgageCalculatorViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MortgageCalculatorViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is the mortgage calculator fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
