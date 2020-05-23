package com.kochsj.realrealty.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kochsj.realrealty.R;
import com.kochsj.realrealty.models.House;

public class DetailFragment extends Fragment implements View.OnClickListener{
//    private DetailViewModel detailViewModel;
//    private House houseToView;

//    public DetailFragment(House house){
//        this.houseToView = house;
//        this.detailViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(DetailViewModel.class);
//    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        final TextView streetAddress = root.findViewById(R.id.detail_streetAddress);
        final TextView beds = root.findViewById(R.id.detail_beds_value);
        final TextView baths = root.findViewById(R.id.detail_baths_value);
        final Button addToFavoritesButton = root.findViewById(R.id.detail_add_to_favorites_button);

        addToFavoritesButton.setOnClickListener(this);


        final String[] houseArray = getArguments().getStringArray("house");

        streetAddress.setText(houseArray[0] + "\n" + houseArray[1] + ", " + houseArray[2] + " " + houseArray[3]);
//        city.setText(houseArray[1]);
//        state.setText(houseArray[2]);
//        zipCode.setText(houseArray[3]);
        beds.setText(houseArray[4]);
        baths.setText(houseArray[5]);

        return root;
    }

    public static Bundle createArgsBundleForDetailView(House house) {
        String[] houseStringArray = house.detailViewStringArrayFromHouse();
        Bundle bundle = new Bundle();
        bundle.putStringArray("house", houseStringArray);

        return bundle;
    }

    @Override
    public void onClick(View v) {
        // add a house to favorites in User DB

    }

}
