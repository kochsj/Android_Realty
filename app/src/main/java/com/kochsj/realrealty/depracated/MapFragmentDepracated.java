package com.kochsj.realrealty.depracated;




// Google Map w/ pins
//public class MapFragmentDepracated extends Fragment implements OnMapReadyCallback {

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
