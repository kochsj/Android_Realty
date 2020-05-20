package com.kochsj.realrealty.ui.profile.preferences;

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

public class PreferencesFragment extends Fragment {
    private PreferencesViewModel preferencesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        preferencesViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(PreferencesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        preferencesViewModel.getText().observe((LifecycleOwner) this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}
