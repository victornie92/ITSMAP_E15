package dk.itsmap.e15.grp4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import dk.itsmap.e15.grp4.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import dk.itsmap.e15.grp4.model.ToiletLocation;
import dk.itsmap.e15.grp4.util.ToiletLocationLoader;

import dk.itsmap.e15.grp4.model.InfoLocation;
import dk.itsmap.e15.grp4.util.InfoLocationLoader;

import dk.itsmap.e15.grp4.model.FoodLocation;
import dk.itsmap.e15.grp4.util.FoodLocationLoader;



import java.util.ArrayList;


public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private int mapType = GoogleMap.MAP_TYPE_SATELLITE;

    // GPSTracker class
    GPSTracker gps;
    private boolean userLocationKnown = false;
    private boolean showingToilet = false;
    private boolean showingInfo = false;
    private boolean showingFood = false;
    private boolean infostate, toiletstate, foodstate = false;
    int lowestRounded = 0;
    String lowestRoundedAndName;



    private boolean tracing = false;
    private double userLatitude;
    private double userLongitude;

    ArrayList <ToiletLocation> toiletLocations;
    ArrayList <InfoLocation> infoLocations;
    ArrayList <FoodLocation> foodLocations;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        ToiletLocationLoader loaderToilet = new ToiletLocationLoader(this);
        toiletLocations = loaderToilet.getToiletLocationList();

        InfoLocationLoader loaderInfo = new InfoLocationLoader(this);
        infoLocations = loaderInfo.getInfoLocationList();

        FoodLocationLoader loaderFood = new FoodLocationLoader(this);
        foodLocations = loaderFood.getFoodLocationList();

        setUpMapIfNeeded();

        //Default startup showing map for northsidefestival in satelite
        mMap.setMapType(mapType);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(56.148596, 10.172943), 16.5f));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    public void LocateMe(View v)
    {
        //Mock for testing
        /*userLatitude = 56.149070;
        userLongitude = 10.173088;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(userLatitude, userLongitude), 16.5f));
        setUpMap();*/


        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            userLocationKnown = true;
            gps = new GPSTracker(MapsActivity.this);
            userLatitude = gps.getLatitude(); // returns latitude
            userLongitude = gps.getLongitude(); // returns longitude
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + lat + "\nLong: " + lon, Toast.LENGTH_LONG).show();
            if (userLocationKnown) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(userLatitude, userLongitude), 16.5f));
                setUpMap();
            }
        }

            else {
                userLocationKnown = false;
                showGPSAlert();
            }
        }

    private void showGPSAlert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You have to activate your GPS for this functionality")
                .setCancelable(false)
                .setPositiveButton("Settings",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        alertDialogBuilder.setTitle("Alert");
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void findInfo(View v)
    {
        showingInfo = true;
        if(infoLocations!=null && infoLocations.size()>0){

                    InfoLocation tempLocation;

                    for(int i=0; i<infoLocations.size(); i++) {
                        tempLocation = infoLocations.get(i);

                //add markers
                mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(tempLocation.getLatitude(), tempLocation.getLongitude()))
                                .title(tempLocation.getName())
                                .snippet(tempLocation.getDescription())
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.informations))
                );

            }

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(56.148596, 10.172943), 16.5f));

        }

    }

    public void findFood(View v)
    {
        showingFood = true;
        if(foodLocations!=null && foodLocations.size()>0){

            FoodLocation tempLocation;

            for(int i=0; i<foodLocations.size(); i++) {
                tempLocation = foodLocations.get(i);

                //add markers
                mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(tempLocation.getLatitude(), tempLocation.getLongitude()))
                                .title(tempLocation.getName())
                                .snippet(tempLocation.getDescription())
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.restaurants))
                );

            }

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(56.148596, 10.172943), 16.5f));

        }
    }

    public void findToilets(View v)
    {
        showingToilet = true;
        if(toiletLocations!=null && toiletLocations.size()>0){

            ToiletLocation tempLocation;

            for(int i=0; i<toiletLocations.size(); i++) {
                tempLocation = toiletLocations.get(i);

                //add markers
                mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(tempLocation.getLatitude(), tempLocation.getLongitude()))
                                .title(tempLocation.getName())
                                .snippet(tempLocation.getDescription())
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.toilet))
                );

            }

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(56.148596, 10.172943), 16.5f));

        }

    }

    public void nearestInfo(View v)
    {
        if (userLatitude == 0 || userLongitude == 0) {
            Toast.makeText(MapsActivity.this, "Please Locate your position", Toast.LENGTH_SHORT).show();
        }
        else {
            infostate = true;
            calcDistance();
        }

    }

    public void nearestRestaurant(View v)
    {
        if (userLatitude == 0 || userLongitude == 0) {
            Toast.makeText(MapsActivity.this, "Please Locate your position", Toast.LENGTH_SHORT).show();
        }
        else {
            foodstate = true;
            calcDistance();
        }
    }

    public void nearestToilet(View v)
    {
        if (userLatitude == 0 || userLongitude == 0) {
            Toast.makeText(MapsActivity.this, "Please Locate your position", Toast.LENGTH_SHORT).show();
        }
        else {
            toiletstate = true;
            calcDistance();
        }
    }

    public void calcDistance() {
        if (infostate) {
            if (infoLocations != null && infoLocations.size() > 0) {
                InfoLocation tempLocation;

                for (int i = 0; i < infoLocations.size(); i++) {
                    tempLocation = infoLocations.get(i);
                    double lat = tempLocation.getLatitude();
                    double lon = tempLocation.getLongitude();
                    String name = tempLocation.getName();

                    //Calc for Info distance from user and returning lowest value (shortest way)
                    findShortestWay(lat, lon, name);
                }
                showBox();
                resetStats();
            }
        } else if (toiletstate) {
            if (toiletLocations != null && toiletLocations.size() > 0) {
                ToiletLocation tempLocation;

                for (int i = 0; i < toiletLocations.size(); i++) {
                    tempLocation = toiletLocations.get(i);
                    double lat = tempLocation.getLatitude();
                    double lon = tempLocation.getLongitude();
                    String name = tempLocation.getName();

                    //Calc for toilet distance from user and returning lowest value (shortest way)
                    findShortestWay(lat, lon, name);

                }
                showBox();
                resetStats();
            }
        } else
        {
            if (foodLocations != null && foodLocations.size() > 0) {
                FoodLocation tempLocation;

                for (int i = 0; i < foodLocations.size(); i++) {
                    tempLocation = foodLocations.get(i);
                    double lat = tempLocation.getLatitude();
                    double lon = tempLocation.getLongitude();
                    String name = tempLocation.getName();


                    //Calc for restaurants distance from user and returning lowest value (shortest way)
                    findShortestWay(lat,lon,name);
                }
                showBox();
                resetStats();
            }
        }
    }

    public void showBox ()
    {
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setCanceledOnTouchOutside(true);
        TextView myMsg = new TextView(this);
        myMsg.setText(lowestRoundedAndName);
        myMsg.setTextSize(20);
        myMsg.setTextColor(getResources().getColor(R.color.ButtonText));
        myMsg.setBackgroundColor(getResources().getColor(R.color.Grey));
        myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
        alertDialog.setView(myMsg, 0, 30, 0, 0);
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.Grey);
        alertDialog.show();
    }



    public void findShortestWay(double lat, double lon, String name)
    {
        double distance;

        Location locationA = new Location("A");
        locationA.setLatitude(userLatitude);
        locationA.setLongitude(userLongitude);
        Location locationB = new Location("B");
        locationB.setLatitude(lat);
        locationB.setLongitude(lon);

        //Distance er angivet i meter
        distance = locationA.distanceTo(locationB);

        //Om til hele tal
        int i = (int) distance;

        //Afrunding til nærmeste 10'er
        int rounded = i % 10 > 5 ? ((i/10)*10)+10 : (i/10)*10;

        if(lowestRounded == 0 || rounded <=lowestRounded)
        {
            lowestRounded=rounded;
            lowestRoundedAndName = Integer.toString(rounded)+ " Meter from "+ name;
        }
    }

    public void resetStats()
    {
        toiletstate = false;
        foodstate = false;
        infostate = false;
        lowestRounded = 0;
        lowestRoundedAndName = null;
    }


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

    private void setUpMap() {
        if (userLocationKnown)
        {
            if (!tracing && !showingToilet && !showingInfo && !showingFood )
            {
                mMap.clear();
            }
        }
        mMap.addMarker(new MarkerOptions().position(new LatLng(userLatitude, userLongitude)).title("You are here!"));
    }
}
