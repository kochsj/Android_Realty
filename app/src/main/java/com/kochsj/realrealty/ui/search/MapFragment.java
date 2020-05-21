package com.kochsj.realrealty.ui.search;


import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kochsj.realrealty.R;
import com.kochsj.realrealty.models.House;

import java.util.ArrayList;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment supportMapFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_map, container, false);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);
        if (supportMapFragment == null) {
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
        ArrayList<House> listOfHousesForSale = new ArrayList<House>();
        listOfHousesForSale.add(new House(
                "1234",
                "123 Main St",
                "Seattle",
                "WA",
                "98101",
                "",
                "3",
                "2",
                0,
                47.6,
                -122.3
        ));



        mMap = googleMap;
        mMap.setMapStyle(new MapStyleOptions(getResources().getString(R.string.style_json)));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker m) {
                Log.d("TAG", "onMarkerClick: " + m.getTitle());
                return true;
            }
        });

        LatLng seattle = new LatLng(47.6, -122.3);
        float zoom = 12.0f;
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(seattle, zoom);

        addMarkers(listOfHousesForSale);
        mMap.animateCamera(cameraUpdate);
    }

    private void addMarkers(ArrayList<House> houseArrayList) {

        for (House house : houseArrayList) {
            LatLng latLng = new LatLng(house.latitude, house.longitude);
            mMap.addMarker(new MarkerOptions().position(latLng).title(house.streetAddress));
        }
    }

}
