package com.kochsj.realrealty.ui.detail;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kochsj.realrealty.models.House;

public class DetailViewModel extends ViewModel {

    private MutableLiveData<String> mStreetAddress;
    private MutableLiveData<String> mBeds;
    private MutableLiveData<String> mBaths;

    private String[] houseArray;


    public DetailViewModel() {
        mStreetAddress = new MutableLiveData<>();
        mBeds = new MutableLiveData<>();
        mBaths = new MutableLiveData<>();

    }

    public LiveData<String> getStreetAddress() {
        return mStreetAddress;
    }
    public LiveData<String> getBeds() {
        return mBeds;
    }
    public LiveData<String> getBaths() {
        return mBaths;
    }


    public void setHouseArray(Bundle bundle) {
        houseArray = bundle.getStringArray("house");
        setTextFromHouseArray();
    }

    private void setTextFromHouseArray() {
        mStreetAddress.setValue(houseArray[0] + "\n" + houseArray[1] + ", " + houseArray[2] + " " + houseArray[3]);
        mBeds.setValue(houseArray[4]);
        mBaths.setValue(houseArray[5]);
    }

    public House getHouseFromHouseArray() {
        return new House(
                houseArray[7],
                houseArray[0],
                houseArray[1],
                houseArray[2],
                houseArray[3],
                houseArray[6],
                houseArray[4],
                houseArray[5],
                Double.parseDouble(houseArray[9]),
                Double.parseDouble(houseArray[10])
        );
    }

}
