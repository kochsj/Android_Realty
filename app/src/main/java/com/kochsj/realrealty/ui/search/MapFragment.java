package com.kochsj.realrealty.ui.search;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kochsj.realrealty.R;
import com.kochsj.realrealty.services.RealtyMoleAPIService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MapFragment extends Fragment implements View.OnClickListener {
    private View root;
    private WebView mWebView;

    private RealtyMoleAPIService realtyMoleAPIService;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_map, container, false);
        this.realtyMoleAPIService = new RealtyMoleAPIService();

        root.findViewById(R.id.web_view_add_to_favorites).setOnClickListener(this);
        mWebView = (WebView) root.findViewById(R.id.web_view);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
//        mWebView.getSettings().setPluginsEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.loadUrl("https://www.zillow.com/homes");

        return root;
    }

    public static String get_match(String s, String p) {
        // returns first match of p in s for first group in regular expression
        Matcher m = Pattern.compile(p).matcher(s);
        return m.find() ? m.group() : "";
    }

    @Override
    public void onClick(View v) {
//        https://www.zillow.com/homedetails/4011-N-Frace-Ave-Tacoma-WA-98407/49340155_zpid/
        String url = mWebView.getUrl();
        Log.d("TAG", "onClick: " + url);

        String zpidParam = get_match(url, "/\\d{1,12}_zpid/");
        String addressParam = get_match(url, "/\\d{1,10}(.*?)\\d{5}");

        String address = addressParam.substring(1);
        String zpid = get_match(zpidParam, "\\d{1,12}");

        Log.d("TAG", "onClick: " + address);
        Log.d("TAG", "onClick: " + zpid);

        try {
            JSONObject locationJSON = realtyMoleAPIService.getLocationData(address);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}


//public class MapFragment extends Fragment implements OnMapReadyCallback {

//    private GoogleMap mMap;
//    private SupportMapFragment supportMapFragment;
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View root = inflater.inflate(R.layout.fragment_map, container, false);
//        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);
//        if (supportMapFragment == null) {
//            FragmentManager fragmentManager = getFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            supportMapFragment = SupportMapFragment.newInstance();
//            fragmentTransaction.replace(R.id.fragment_map, supportMapFragment).commit();
//        }
//
//        supportMapFragment.getMapAsync(this);
//
//        return root;
//    }
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        final House[] listOfHousesForSale = {
//                new House(
//                        "1234",
//                        "123 Main St",
//                        "Seattle",
//                        "WA",
//                        "98101",
//                        "",
//                        "3",
//                        "2",
//                        47.6,
//                        -122.3
//                ),
//                new House(
//                        "4455",
//                        "99 W Stewart",
//                        "Seattle",
//                        "WA",
//                        "98101",
//                        "",
//                        "2",
//                        "5",
//                        47.61,
//                        -122.31
//                ),
//                new House(
//                        "9933",
//                        "77 Park Parkway",
//                        "Seattle",
//                        "WA",
//                        "98101",
//                        "",
//                        "4",
//                        "6",
//                        47.63,
//                        -122.23
//                )
//        };
//
//
//
//        mMap = googleMap;
//        mMap.setMapStyle(new MapStyleOptions(getResources().getString(R.string.style_json)));
//
//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker m) {
////                Log.d("onMarkerClick", m.getTitle());
//                int index = Integer.parseInt(m.getTitle());
//                House house = listOfHousesForSale[index];
//                MainApplication.userDatabaseService.addHouseToRecentlyViewed(house);
//
//                Bundle bundle = DetailFragment.createArgsBundleForDetailView(house);
//                Navigation.findNavController(getView()).navigate(R.id.navigation_detail_view, bundle);
//                return true;
//            }
//        });
//
//        LatLng seattle = new LatLng(47.6, -122.3);
//        float zoom = 12.0f;
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(seattle, zoom);
//
//        addMarkers(listOfHousesForSale);
//        mMap.animateCamera(cameraUpdate);
//    }
//
//    private void addMarkers(House[] houseArrayList) {
//        int index = 0;
//
//        for (House house : houseArrayList) {
//            LatLng latLng = new LatLng(house.latitude, house.longitude);
//            mMap.addMarker(new MarkerOptions().position(latLng).title(Integer.toString(index)));
//            index += 1;
////            mMap.addMarker(new MarkerOptions().position(latLng).title(" " + "\r\n" + house.streetAddress + "\r\n" + house.city + ", " + house.state + " " + house.zipCode + "\r\nBeds: " + house.beds + "\r\nBaths: "+ house.baths));
//        }
//    }
//
//}
