package com.kochsj.realrealty.ui.more;

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
import androidx.navigation.Navigation;

import com.kochsj.realrealty.R;

public class MoreFragment extends Fragment implements View.OnClickListener {
    private MoreViewModel moreViewModel;
    private View root;

    private Button mMortgageCalculatorButton;
    private Button mContactUsButton;
    private Button mOurTeamButton;
    private Button mRecentlyViewedButton;
    private Button mSettingsButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.moreViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MoreViewModel.class);
        this.root = inflater.inflate(R.layout.fragment_more, container, false);
        final TextView textView = root.findViewById(R.id.text_more);

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

        mMortgageCalculatorButton.setOnClickListener(this);
        mContactUsButton.setOnClickListener(this);
        mOurTeamButton.setOnClickListener(this);
        mRecentlyViewedButton.setOnClickListener(this);
        mSettingsButton.setOnClickListener(this);

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
//                Intent intent = new Intent(getActivity(), MapsActivity.class);
//                startActivity(intent);
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
        }

    }
}
