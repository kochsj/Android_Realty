package com.kochsj.realrealty.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kochsj.realrealty.R;
import com.squareup.picasso.Picasso;

public class FavoritesFragment extends Fragment {

//    private FavoritesViewModel favoritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        favoritesViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(FavoritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        final ViewGroup favoritesContainer = root.findViewById(R.id.favorites_insert_container);
        View tile = inflater.inflate(R.layout.widget_favorite_house_tile, null);
        ImageView imageView = tile.findViewById(R.id.favorite_image_view);

//        imageView.setImageResource(R.drawable.house_for_sale);


        String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
        String imageUri2 = "https://i.imgur.com/KbwPb6Y.jpeg";
        String imageUri3 = "";
        String imageUri4 = "";

        View tile2 = inflater.inflate(R.layout.widget_favorite_house_tile, null);
        ImageView imageView2 = tile2.findViewById(R.id.favorite_image_view);

        View tile3 = inflater.inflate(R.layout.widget_favorite_house_tile, null);
        ImageView imageView3 = tile3.findViewById(R.id.favorite_image_view);

        View tile4 = inflater.inflate(R.layout.widget_favorite_house_tile, null);
        ImageView imageView4 = tile4.findViewById(R.id.favorite_image_view);

        getImageWithPicassoOrStock(imageUri, imageView);
        getImageWithPicassoOrStock(imageUri2, imageView2);
        getImageWithPicassoOrStock(imageUri3, imageView3);
        getImageWithPicassoOrStock(imageUri4, imageView4);


        favoritesContainer.addView(tile);
        favoritesContainer.addView(tile2);
        favoritesContainer.addView(tile3);
        favoritesContainer.addView(tile4);


//        favoritesViewModel.getText().observe((LifecycleOwner) this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

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
}
