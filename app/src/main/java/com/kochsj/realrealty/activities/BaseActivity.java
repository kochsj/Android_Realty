package com.kochsj.realrealty.activities;


import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.kochsj.realrealty.MainApplication;
import com.kochsj.realrealty.R;
import com.kochsj.realrealty.services.UserDatabaseService;

public class BaseActivity extends AppCompatActivity {
    private UserDatabaseService userDatabaseService = MainApplication.getUserDatabaseService();

    @VisibleForTesting
    public ProgressBar mProgressBar;

    public void setProgressBar(ProgressBar progressBar) {
        mProgressBar = progressBar;
    }

    public void showProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void hideKeyboard(View view) {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void removeFavorite(View view) {
        String viewID = Integer.toString(view.getId());
//        Log.d("TAG", "removeFavorite: " + viewID);
        userDatabaseService.removeFavoriteHouse(viewID);
        Navigation.findNavController(view).navigate(R.id.navigation_favorites);
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressBar();
    }

}