package com.example.evan.maps;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import java.util.List;

public class MapsActivity extends FragmentActivity implements LocationListener, View.OnKeyListener {

    private static final String GOOGLE_API_KEY = "AIzaSyBj4eNM5-fJgcTHdtuoAu4b_RzNawxo4lQ";
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private EditText getAddress;
    private EditText search;
    private Button start;
    private int PROXIMITY_RADIUS = 5000;
    private Button showbutton;
    LatLng p1 = null;
    double latitude;
    double longitude;
    private TextView locationTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getAddress = (EditText) findViewById(R.id.address);
        search = (EditText) findViewById(R.id.search_name);
        start = (Button) findViewById(R.id.mapbutton1);
        locationTv = (TextView) findViewById(R.id.latlongLocation);


        getAddress.setOnKeyListener(this);
        search.setOnKeyListener(this);
        setUpMapIfNeeded();
        onClick_Mylocation();
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
        locationTv = (TextView) findViewById(R.id.latlongLocation);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
      //  mMap.addMarker(new MarkerOptions().position(latLng).title("MyLocation"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);
    }

    public void onClick_Mylocation() {
        mMap.clear();
        mMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            onLocationChanged(location);
        }
       // locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);
    }

    public void onClick_Search(View v) {
        mMap.clear();
        String strAddress = getAddress.getText().toString().toLowerCase();
        String search_name0 = search.getText().toString().toLowerCase();
        String search_name = search_name0.replaceAll(" ", "_");
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
        if(strAddress.equals("")){
            //set local
            mMap.setMyLocationEnabled(true);
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String bestProvider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(bestProvider);
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }else {
             try {
                Geocoder coder = new Geocoder(this);
                List<Address> address;
                address = coder.getFromLocationName(strAddress, 5);
                if(address.isEmpty()){
                    mMap.setMyLocationEnabled(true);
                    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    Criteria criteria = new Criteria();
                    String bestProvider = locationManager.getBestProvider(criteria, true);
                    Location location = locationManager.getLastKnownLocation(bestProvider);
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }else {
                    Address location = address.get(0);
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
                }catch (IOException e) {
                e.printStackTrace();
                }
            }
            p1 = new LatLng(latitude, longitude);
            mMap.setMapType((GoogleMap.MAP_TYPE_NORMAL));
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(p1, 12);
            mMap.addMarker(new MarkerOptions().position(p1).title("MyLocation"));
            mMap.animateCamera(update);
            if(!search_name.matches("")) {
                StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
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
                showbutton = (Button) findViewById(R.id.mapbutton4);
                showbutton.setVisibility(View.VISIBLE);
                locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);


            }
    }

    public void onClick_Jump(View v){
        if(PlacesDisplayTask.placelist.size() >= 1) {
            try {
                Class rclass = Class.forName("com.example.evan.maps.PlaceList");
                Intent ourintent = new Intent(MapsActivity.this, rclass);
                startActivity(ourintent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present. Inject xml to java code to nevigation bar
        getMenuInflater().inflate(R.menu.menu_user_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        //filter for second call
        if (event.getAction()!=KeyEvent.ACTION_DOWN)
            return true;
        if(keyCode == 66) {
            int id = v.getId();
            switch(id){
                case R.id.address:
                    search.requestFocus();
                    break;
                case R.id.search_name:
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
                    start.performClick();
                    break;
            }
        }
        return false;
    }
}