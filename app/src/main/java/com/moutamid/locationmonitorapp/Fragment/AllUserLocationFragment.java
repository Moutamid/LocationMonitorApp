package com.moutamid.locationmonitorapp.Fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.fxn.stash.Stash;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.moutamid.locationmonitorapp.Model.ChecksModel;
import com.moutamid.locationmonitorapp.Model.UserModel;
import com.moutamid.locationmonitorapp.R;
import com.moutamid.locationmonitorapp.helper.MapDialogClass;

import java.util.ArrayList;

public class AllUserLocationFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ArrayList<LatLng> locationArrayList;
    ArrayList<ChecksModel> locationModels;
    ArrayList<UserModel> userModels;

    private MapView mapView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.map_fragment, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        mapView.getMapAsync(this);
        locationModels = Stash.getArrayList("AllUserLocation", ChecksModel.class);
        userModels = Stash.getArrayList("AllUserLocation", UserModel.class);

        // in below line we are initializing our array list.
        locationArrayList = new ArrayList<>();

        // on below line we are adding our

        // locations in our array list.

        for (int i = 0; i < locationModels.size(); i++) {
            LatLng sydney = new LatLng(locationModels.get(i).lat, locationModels.get(i).lng);
            if (locationModels.get(i).lat > -90 && locationModels.get(i).lat < 90 && locationModels.get(i).lng > -180 && locationModels.get(i).lng < 180) {
                locationArrayList.add(sydney);

            }
        }
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        int height = 50;
        int width = 50;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.record);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        for (int i = 0; i < locationArrayList.size(); i++) {

            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title(locationModels.get(i).name).snippet(userModels.get(i).key).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationArrayList.get(i), 12.0f));
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    marker.hideInfoWindow();
                    //return true instead of false
                    MapDialogClass cdd = new MapDialogClass(getActivity(), marker.getSnippet());
                    cdd.show();
                    return true;
                }
            });
        }
    }
}
