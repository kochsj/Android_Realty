package com.kochsj.realrealty.ui.profile;

import android.content.Intent;
import android.os.Bundle;
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

import com.kochsj.realrealty.R;
import com.kochsj.realrealty.activities.MapsActivity;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private ProfileViewModel profileViewModel;
    private View root;

    private Button mHouseButton;
    private Button mAgentButton;
    private Button mDocumentsButton;
    private Button mPreferencesButton;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.profileViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(ProfileViewModel.class);
        this.root = inflater.inflate(R.layout.fragment_profile, container, false);

        mHouseButton = root.findViewById(R.id.profile_house_button);
        mAgentButton = root.findViewById(R.id.profile_agent_button);
        mDocumentsButton = root.findViewById(R.id.profile_documents_button);
        mPreferencesButton = root.findViewById(R.id.profile_preferences_button);

        mHouseButton.setOnClickListener(this);
        mAgentButton.setOnClickListener(this);
        mDocumentsButton.setOnClickListener(this);
        mPreferencesButton.setOnClickListener(this);

        final TextView textView = root.findViewById(R.id.text_profile);

        profileViewModel.getText().observe((LifecycleOwner) this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_agent_button:
                break;
            case R.id.profile_house_button:
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_documents_button:
                break;
            case R.id.profile_preferences_button:
                break;
        }

    }
}