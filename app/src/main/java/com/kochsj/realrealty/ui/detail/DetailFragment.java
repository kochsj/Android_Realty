package com.kochsj.realrealty.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.kochsj.realrealty.MainApplication;
import com.kochsj.realrealty.R;
import com.kochsj.realrealty.models.House;
import com.kochsj.realrealty.services.UserDatabaseService;

public class DetailFragment extends Fragment implements View.OnClickListener{
    private DetailViewModel detailViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // set layout targets
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        final TextView streetAddress = root.findViewById(R.id.detail_streetAddress);
        final TextView beds = root.findViewById(R.id.detail_beds_value);
        final TextView baths = root.findViewById(R.id.detail_baths_value);
        final Button addToFavoritesButton = root.findViewById(R.id.detail_add_to_favorites_button);
        addToFavoritesButton.setOnClickListener(this);

        // assign view model
        detailViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(DetailViewModel.class);
        detailViewModel.setHouseArray(getArguments());

        // observe mutable strings
        final Observer<String> streetAddressObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String h) { streetAddress.setText(h);
            }
        };
        final Observer<String> bedsObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String h) {
                beds.setText(h);
            }
        };
        final Observer<String> bathsObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String h) {
                baths.setText(h);
            }
        };

        detailViewModel.getStreetAddress().observe(getViewLifecycleOwner(), streetAddressObserver);
        detailViewModel.getBeds().observe(getViewLifecycleOwner(), bedsObserver);
        detailViewModel.getBaths().observe(getViewLifecycleOwner(), bathsObserver);

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
        House house = detailViewModel.getHouseFromHouseArray();

        // add a house to favorites in User DB
        UserDatabaseService userDatabaseService = MainApplication.getUserDatabaseService();
        userDatabaseService.addFavoriteHouseToUsersFavorites(house);
    }

}
