package com.kochsj.realrealty.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kochsj.realrealty.R;

public class FavoritesFragment extends Fragment {

//    private FavoritesViewModel favoritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        favoritesViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(FavoritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        final ViewGroup favoritesContainer = root.findViewById(R.id.favorites_insert_container);
        View tile = inflater.inflate(R.layout.widget_favorite_house_tile, null);
        View tile2 = inflater.inflate(R.layout.widget_favorite_house_tile, null);
        View tile3 = inflater.inflate(R.layout.widget_favorite_house_tile, null);
        View tile4 = inflater.inflate(R.layout.widget_favorite_house_tile, null);



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
}
