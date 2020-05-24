package com.kochsj.realrealty.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kochsj.realrealty.MainApplication;
import com.kochsj.realrealty.R;
import com.kochsj.realrealty.models.House;
import com.kochsj.realrealty.services.UserDatabaseService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritesFragment extends Fragment implements View.OnClickListener {
    private UserDatabaseService userDatabaseService = MainApplication.getUserDatabaseService();
    private ViewGroup favoritesContainer;
    private FavoritesViewModel favoritesViewModel;

    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // assign layout targets
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        favoritesContainer = root.findViewById(R.id.favorites_insert_container);

//        // assign view model
//        favoritesViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(FavoritesViewModel.class);
//        favoritesViewModel.setViewInflater(inflater);
//        favoritesViewModel.setViewContext(getContext());
//
////        ArrayList<View> listOfHouses = favoritesViewModel.getFavoriteTiles();
//
//        // observe View change
//        final Observer<ArrayList<View>> viewObserver = new Observer<ArrayList<View>>() {
//            @Override
//            public void onChanged(@Nullable final ArrayList<View> listOfHouses) {
//                for (View house : listOfHouses){
//                    favoritesContainer.addView(house);
//                }
//            }
//        };
//
//        favoritesViewModel.getFavoriteTiles().



        userDatabaseService.getFavoriteHouses().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot snapshot = task.getResult();
                    List<DocumentSnapshot> favoriteHouses = snapshot.getDocuments();

                    for (DocumentSnapshot house : favoriteHouses) {
                        House favoriteHouse = userDatabaseService.houseFromSnapshot(house, house.getId());
                        addFavoritesTileToFavoritesPage(inflater, favoriteHouse);
                    }
                }
            }
        });


        return root;
    }


    private void addFavoritesTileToFavoritesPage(LayoutInflater inflater, House house) {
        View tile = inflater.inflate(R.layout.widget_favorite_house_tile, null);
//        Log.d("TAG", "addFavoritesTileToFavoritesPage: " + house.zpid);

        Button removeFromFavoritesButton = tile.findViewById(R.id.dismiss);
        removeFromFavoritesButton.setId(Integer.parseInt(house.zpid));

        TextView streetAddress = tile.findViewById(R.id.favorite_house_street_Address);
        TextView beds = tile.findViewById(R.id.favorite_house_beds);
        ImageView imageView = tile.findViewById(R.id.favorite_image_view);

        getImageWithPicassoOrStock(house.photoURL, imageView);
        streetAddress.setText(house.streetAddress + "\n" + house.city + ", " + house.state + " " + house.zipCode);
        beds.setText(house.beds);

        favoritesContainer.addView(tile);
    }

    private void getImageWithPicassoOrStock(String imageUri, ImageView imageView) {
        // tries to get image from url - or sets static stock
        try {
            Picasso.with(getContext()).load(imageUri).into(imageView);
        } catch(Exception e) {
            imageView.setImageResource(R.drawable.house_for_sale);
        }
    }

    @Override
    public void onClick(View v) {
        UserDatabaseService userDatabaseService = MainApplication.getUserDatabaseService();

        String viewID = Integer.toString(v.getId());
        userDatabaseService.removeFavoriteHouse(viewID);
    }
}
