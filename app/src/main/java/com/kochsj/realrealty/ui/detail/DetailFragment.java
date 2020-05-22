package com.kochsj.realrealty.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kochsj.realrealty.R;
import com.kochsj.realrealty.models.House;

public class DetailFragment extends Fragment {
    private DetailViewModel detailViewModel;
    private House houseToView;

//    public DetailFragment(House house){
//        this.houseToView = house;
//        this.detailViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(DetailViewModel.class);
//    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        final TextView textView = root.findViewById(R.id.text_detail);

        final String[] houseArray = getArguments().getStringArray("house");

        textView.setText(houseArray[1]);


//        detailViewModel.getText().observe((LifecycleOwner) this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
////                textView.setText(s);
//
//            }
//        });

        return root;
    }
}
