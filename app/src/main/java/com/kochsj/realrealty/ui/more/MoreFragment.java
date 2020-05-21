package com.kochsj.realrealty.ui.more;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.kochsj.realrealty.MainApplication;
import com.kochsj.realrealty.R;
import com.kochsj.realrealty.wrappers.AuthWrapper;

public class MoreFragment extends Fragment implements View.OnClickListener {
    private MoreViewModel moreViewModel;
    private View root;
    private FirebaseAuth mAuth;

    private Button mMortgageCalculatorButton;
    private Button mContactUsButton;
    private Button mOurTeamButton;
    private Button mRecentlyViewedButton;
    private Button mSettingsButton;
    private Button mSignOutButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.moreViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MoreViewModel.class);
        this.root = inflater.inflate(R.layout.fragment_more, container, false);
        final TextView textView = root.findViewById(R.id.text_more);

        mAuth = FirebaseAuth.getInstance();

        moreViewModel.getText().observe((LifecycleOwner) this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        mMortgageCalculatorButton = root.findViewById(R.id.more_calculator_button);
        mContactUsButton = root.findViewById(R.id.more_contact_us_button);
        mOurTeamButton = root.findViewById(R.id.more_our_team_button);
        mRecentlyViewedButton = root.findViewById(R.id.more_recently_viewed_button);
        mSettingsButton = root.findViewById(R.id.more_settings);
        mSignOutButton = root.findViewById(R.id.more_sign_out_button);

        mMortgageCalculatorButton.setOnClickListener(this);
        mContactUsButton.setOnClickListener(this);
        mOurTeamButton.setOnClickListener(this);
        mRecentlyViewedButton.setOnClickListener(this);
        mSettingsButton.setOnClickListener(this);
        mSignOutButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
//        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        switch (v.getId()) {
            case R.id.more_calculator_button:
                Navigation.findNavController(v).navigate(R.id.navigation_more_calculator);
                break;
            case R.id.more_contact_us_button:
                Navigation.findNavController(v).navigate(R.id.navigation_more_contact_us);
                break;
            case R.id.more_our_team_button:
                Navigation.findNavController(v).navigate(R.id.navigation_more_our_team);
                break;
            case R.id.more_recently_viewed_button:
                Navigation.findNavController(v).navigate(R.id.navigation_more_recently_viewed);
                break;
            case R.id.more_settings:
                Navigation.findNavController(v).navigate(R.id.navigation_more_settings);
                break;
            case R.id.more_sign_out_button:
                signOut();
                break;
        }


    }

    private void signOut() {
        Log.d("morefragment", "signOut: inside method");

        MainApplication.setUID(null);
        MainApplication.setUserData(null);
        mAuth.signOut();

        Intent intent = new Intent(getActivity(), AuthWrapper.class);
        startActivity(intent);
    }
}
