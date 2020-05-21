package com.kochsj.realrealty.ui.more.team;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.kochsj.realrealty.R;

public class OurTeamFragment extends Fragment {
    private OurTeamViewModel ourTeamViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ourTeamViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(OurTeamViewModel.class);
        View root = inflater.inflate(R.layout.fragment_more_our_team, container, false);
        final TextView textView = root.findViewById(R.id.text_more_our_team);

        ourTeamViewModel.getText().observe((LifecycleOwner) this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}
