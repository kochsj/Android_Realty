package com.kochsj.realrealty.ui.profile.house;

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

public class MyHouseFragment extends Fragment {
    private MyHouseViewModel myHouseViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        myHouseViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MyHouseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        myHouseViewModel.getText().observe((LifecycleOwner) this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        return root;
    }
}

//public class MyHouseFragment extends Fragment {
//    private MyHouseViewModel myHouseViewModel;
//
//
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
////        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
////            @Override
////            public void handleOnBackPressed() {
////                Log.d("tagging and bagging", "handleOnBackPressed: ");
////                Navigation.findNavController(getView()).navigate(R.id.navigation_profile);
////            }
////        };
////
////        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
//
//        myHouseViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MyHouseViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_map, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//
//        myHouseViewModel.getText().observe((LifecycleOwner) this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//
//        return root;
//    }
//}
