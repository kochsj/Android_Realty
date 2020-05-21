package com.kochsj.realrealty.ui.profile.agent;

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

public class AgentFragment extends Fragment {
    private AgentViewModel agentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        agentViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(AgentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile_agent, container, false);
        final TextView textView = root.findViewById(R.id.text_profile_agent);

        agentViewModel.getText().observe((LifecycleOwner) this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}
