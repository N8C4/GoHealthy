package se.bolzyk.gohealthy.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import se.bolzyk.gohealthy.R;

/**
 * Created by Andreas Bolzyk on 2016-02-26.
 */
public class MapsFragment extends Fragment {

    private SupportMapFragment mapFragment;
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        final View rootView = inflater.inflate(R.layout.fragment_maps, container, false);
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragmentManager = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.mapview);

        mapFragment = SupportMapFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.mapview, mapFragment).commit();

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {

            setMapValues(googleMap);

            //use my location to zoom in of the area
            googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12));
                }
            });
            }
        });
    }


    private void setMapValues(final GoogleMap googleMap) {

        googleMap.setMyLocationEnabled(true);

        //iksu
        googleMap.addMarker(new MarkerOptions().position(new LatLng(63.818676, 20.318376)).title("Iksu Sport"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(63.821250, 20.275330)).title("Iksu Plus"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(63.836387, 20.166371)).title("Iksu Spa"));

        //tenton
        googleMap.addMarker(new MarkerOptions().position(new LatLng(63.845019, 20.328696)).title("Tenton"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(63.827485, 20.253854)).title("Tenton"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(63.857612, 20.313235)).title("Tenton"));

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}
