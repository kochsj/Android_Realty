package com.kochsj.realrealty.ui.more.recent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kochsj.realrealty.MainApplication;
import com.kochsj.realrealty.R;
import com.kochsj.realrealty.models.House;
import com.kochsj.realrealty.services.UserDatabaseService;
import com.kochsj.realrealty.ui.detail.DetailFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecentlyViewedFragment extends Fragment implements View.OnClickListener {
    private RecentlyViewedModel recentlyViewedModel;
    private ViewGroup recentlyViewedContainer;
    private List<House> listOfRecentlyViewedHouses = new ArrayList<House>();
    private UserDatabaseService userDatabaseService = MainApplication.getUserDatabaseService();


    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_more_recently_viewed, container, false);
        recentlyViewedContainer = root.findViewById(R.id.recently_viewed_insert_container);

        userDatabaseService.getRecentlyViewedHouses().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot snapshot = task.getResult();
                    List<DocumentSnapshot> favoriteHouses = snapshot.getDocuments();
                    int index = 0;



                    for (DocumentSnapshot house : favoriteHouses) {
                        House recentlyViewedHouse = userDatabaseService.houseFromSnapshot(house, house.getId());
                        addFavoritesTileToRecentlyViewedPage(inflater, recentlyViewedHouse, index);
                        listOfRecentlyViewedHouses.add(recentlyViewedHouse);
                        index += 1;
                    }
                }
            }
        });


        return root;
    }

    private void getImageWithPicassoOrStock(String imageUri, ImageView imageView) {
        // tries to get image from url - or sets static stock
        try {
            Picasso.with(getContext()).load(imageUri).into(imageView);
        } catch(Exception e) {
            imageView.setImageResource(R.drawable.house_for_sale);
        }
    }

    private void addFavoritesTileToRecentlyViewedPage(LayoutInflater inflater, House house, int index) {
        View tile = inflater.inflate(R.layout.widget_favorite_house_tile, null);
//        Log.d("TAG", "addFavoritesTileToFavoritesPage: " + house.zpid);

        ImageView favoriteImageView = tile.findViewById(R.id.favorite_image_view);
        favoriteImageView.setOnClickListener(this);
        favoriteImageView.setId(index);

        Button removeFromFavoritesButton = tile.findViewById(R.id.dismiss);
        removeFromFavoritesButton.setId(Integer.parseInt(house.zpid));

        TextView streetAddress = tile.findViewById(R.id.favorite_house_street_Address);
        TextView beds = tile.findViewById(R.id.favorite_house_beds);
//        ImageView imageView = tile.findViewById(R.id.favorite_image_view);

        getImageWithPicassoOrStock(house.photoURL, favoriteImageView);
        streetAddress.setText(house.streetAddress + "\n" + house.city + ", " + house.state + " " + house.zipCode);
        beds.setText(house.beds);

        recentlyViewedContainer.addView(tile);
    }

    @Override
    public void onClick(View v) {
        int index = v.getId();
        Bundle bundle = DetailFragment.createArgsBundleForDetailView(listOfRecentlyViewedHouses.get(index));
        Navigation.findNavController(getView()).navigate(R.id.navigation_detail_view, bundle);
    }
}

