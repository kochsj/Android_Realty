package com.kochsj.realrealty.ui.favorites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kochsj.realrealty.MainApplication;
import com.kochsj.realrealty.R;
import com.kochsj.realrealty.models.House;
import com.kochsj.realrealty.services.UserDatabaseService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoritesViewModel extends ViewModel {
    private UserDatabaseService userDatabaseService = MainApplication.getUserDatabaseService();

    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<View> mListOfViews;


    public FavoritesViewModel() {
        userDatabaseService.getFavoriteHouses().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot snapshot = task.getResult();
                    List<DocumentSnapshot> favoriteHouses = snapshot.getDocuments();

                    for (DocumentSnapshot house : favoriteHouses) {
                        House favoriteHouse = userDatabaseService.houseFromSnapshot(house, house.getId());
                        View tile = addFavoritesTileToFavoritesPage(mInflater, favoriteHouse);
                        mListOfViews.add(tile);
                    }
                }
            }
        });
    }

    public ArrayList<View> getFavoriteTiles() { return mListOfViews; }

    public void setViewInflater(LayoutInflater inflater) { mInflater = inflater; }

    public void setViewContext(Context context) { mContext = context; }

    public View addFavoritesTileToFavoritesPage(LayoutInflater inflater, House house) {
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

        return tile;
    }

    private void getImageWithPicassoOrStock(String imageUri, ImageView imageView) {
        // tries to get image from url - or sets static stock
        try {
            Picasso.with(mContext).load(imageUri).into(imageView);
        } catch(Exception e) {
            imageView.setImageResource(R.drawable.house_for_sale);
        }
    }
}
