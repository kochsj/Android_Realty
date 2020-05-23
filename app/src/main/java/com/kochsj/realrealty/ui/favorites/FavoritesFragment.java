package com.kochsj.realrealty.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class FavoritesFragment extends Fragment {
    private UserDatabaseService userDatabaseService = MainApplication.getUserDatabaseService();
    private ViewGroup favoritesContainer;

    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        favoritesContainer = root.findViewById(R.id.favorites_insert_container);


//        View tile = inflater.inflate(R.layout.widget_favorite_house_tile, null);
//        ImageView imageView = tile.findViewById(R.id.favorite_image_view);

//        String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
//        String imageUri2 = "https://i.imgur.com/KbwPb6Y.jpeg";
//        String imageUri3 = "";
//        String imageUri4 = "";
//
//        View tile2 = inflater.inflate(R.layout.widget_favorite_house_tile, null);
//        ImageView imageView2 = tile2.findViewById(R.id.favorite_image_view);
//
//        View tile3 = inflater.inflate(R.layout.widget_favorite_house_tile, null);
//        ImageView imageView3 = tile3.findViewById(R.id.favorite_image_view);
//
//        View tile4 = inflater.inflate(R.layout.widget_favorite_house_tile, null);
//        ImageView imageView4 = tile4.findViewById(R.id.favorite_image_view);

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

//        getImageWithPicassoOrStock(imageUri, imageView);
//        getImageWithPicassoOrStock(imageUri2, imageView2);
//        getImageWithPicassoOrStock(imageUri3, imageView3);
//        getImageWithPicassoOrStock(imageUri4, imageView4);
//
//
//        favoritesContainer.addView(tile);
//        favoritesContainer.addView(tile2);
//        favoritesContainer.addView(tile3);
//        favoritesContainer.addView(tile4);


        return root;
    }


    private void addFavoritesTileToFavoritesPage(LayoutInflater inflater, House house) {
        View tile = inflater.inflate(R.layout.widget_favorite_house_tile, null);
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
}
