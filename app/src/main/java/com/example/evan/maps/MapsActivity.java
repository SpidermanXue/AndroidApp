package com.example.evan.maps;

import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements LocationListener {

    private static final String GOOGLE_API_KEY = "AIzaSyBj4eNM5-fJgcTHdtuoAu4b_RzNawxo4lQ";
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private final LatLng LOCATION_BURNABY = new LatLng(49.27645, -122.917587);
    private final LatLng LOCATION_SURREY = new LatLng(49.27645, -122.847587);
    private final LatLng LOCATION_CITY = new LatLng(49.27645, -122.407587); //zuobiao
    private EditText getAddress;
    private EditText search;
    private int PROXIMITY_RADIUS = 5000;
    private List<String> placename = new ArrayList<String>();
    private Button showbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView locationTv = (TextView) findViewById(R.id.latlongLocation);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);
    }

    public void onClick_Mylocation(View v) {
        //  CameraUpdate update = CameraUpdateFactory.newLatLng(LOCATION_CITY);
        // mMap.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
        // CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_CITY, 14);
        // mMap.addMarker(new MarkerOptions().position(LOCATION_CITY).title("EVAN!!!"));
        // mMap.animateCamera(update);
        mMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        System.out.println(bestProvider);
        Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            System.out.println("EVAN SMART");
            onLocationChanged(location);
        }
        System.out.println("MELODY");
        locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);
    }

    public void onClick_Burnaby(View v) {
        // CameraUpdate update = CameraUpdateFactory.newLatLng(LOCATION_BURNABY);
        mMap.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_BURNABY, 14);
        mMap.animateCamera(update);
    }

    public void onClick_Surrey(View v) {
        // CameraUpdate update = CameraUpdateFactory.newLatLng(LOCATION_SURREY);
        mMap.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_SURREY, 16);
        mMap.animateCamera(update);
    }

    public void onClick_Search(View v) {
        showbutton = (Button) findViewById(R.id.mapbutton4);
        showbutton.setVisibility(View.VISIBLE);
        getAddress = (EditText) findViewById(R.id.address);
        search = (EditText) findViewById(R.id.search_name);
        String strAddress = getAddress.getText().toString();
        String search_name = search.getText().toString();
        Geocoder coder = new Geocoder(this);
        List<Address> address;
        LatLng p1 = null;

        System.out.println("Start!!!!");
        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                //return null;
                System.out.println("can not find");
            }
            Address location = address.get(0);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            System.out.println(location.getLatitude());

            p1 = new LatLng(location.getLatitude(), location.getLongitude());

            mMap.setMapType((GoogleMap.MAP_TYPE_NORMAL));
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(p1, 12);
            mMap.addMarker(new MarkerOptions().position(p1).title("myhome"));
            mMap.animateCamera(update);
            if(!search_name.matches("")) {
                System.out.println("searching place");
                StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                //https://maps.googleapis.com/maps/api/geocode/json?address=1
                googlePlacesUrl.append("location=" + latitude + "," + longitude);
                googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
                googlePlacesUrl.append("&types=" + search_name);
                googlePlacesUrl.append("&sensor=true");
                googlePlacesUrl.append("&key=" + GOOGLE_API_KEY);

                GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask();
                Object[] toPass = new Object[2];
                toPass[0] = mMap;
                toPass[1] = googlePlacesUrl.toString();
                googlePlacesReadTask.execute(toPass);

               // placename = PlacesDisplayTask.placelist;
               // System.out.println("heihei" + placename);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return p1;
    }

    public void onClick_Jump(View v){
        System.out.println("i love melody");
        Thread timer = new Thread(){ //set up a thread to sleep, do mutiple thing at same time
            public void run(){
                try{
                    sleep(5000); // let splash sleep for 5 seconds
                }catch(InterruptedException e){
                    e.printStackTrace(); //print error
                }finally {
                    //start and jump to myactivity class

                    try{
                        Class rclass= Class.forName("com.example.evan.maps.SpinWheel");
                        Intent ourintent = new Intent(MapsActivity.this, rclass);
                        //pass a number tp flipperresult
                        startActivity(ourintent);
                    }catch(ClassNotFoundException e){
                        e.printStackTrace();

                    }

                }
            }
        };
        timer.start();//from Thread class, to start our thread

    }
}