package com.kochsj.realrealty.ui.search;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kochsj.realrealty.R;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment supportMapFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_map, container, false);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);
        if(supportMapFragment == null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            supportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.fragment_map, supportMapFragment).commit();
        }

        supportMapFragment.getMapAsync(this);

        return root;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapStyle(new MapStyleOptions(getResources().getString(R.string.style_json)));

        LatLng seattle = new LatLng(47.6, -122.3);
        float zoom = 12.0f;
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(seattle, zoom);

        mMap.addMarker(new MarkerOptions().position(seattle).title("Marker in Seattle"));
        mMap.animateCamera(cameraUpdate);
    }

//    public void hideMap(View view) {
//        View mapContainer = findViewById(R.id.map_container);
//        View viewPager = findViewById(R.id.center_view);
//
//        mapContainer.setVisibility(View.GONE);
//        viewPager.setVisibility(View.VISIBLE);
//    }

}

//public class MapFragment extends Fragment implements OnMapReadyCallback {
//
//    private MapViewModel mapViewModel;
//    private GoogleMap mMap;
//
//
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        mapViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MapViewModel.class);
//        View root = inflater.inflate(R.layout.activity_maps, container, false);
//
////        final TextView textView = root.findViewById(R.id.text_home);
////
////        mapViewModel.getText().observe((LifecycleOwner) this, new Observer<String>() {
////            @Override
////            public void onChanged(@Nullable String s) {
////                textView.setText(s);
////            }
////        });
//
//        return root;
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        mMap.setMapStyle(new MapStyleOptions(getResources().getString(R.string.style_json)));
//
//        LatLng seattle = new LatLng(47.6, -122.3);
//        float zoom = 12.0f;
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(seattle, zoom);
//
//        mMap.addMarker(new MarkerOptions().position(seattle).title("Marker in Seattle"));
//        mMap.animateCamera(cameraUpdate);
//    }
//}
