package fido.umbridge

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

import java.util.HashMap

/*
public class CheckActivity extends SuperActivity implements OnMapReadyCallback {
	private GoogleMap map;
	private boolean s=true;
	private FusedLocationProviderClient locationClient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check);
		((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
		locationClient = LocationServices.getFusedLocationProviderClient(this);
		currentLat = new LatLng(37.56, 126.97);
	}

	@Override
	protected EnumMenuType getMenuType() {
		return EnumMenuType.CHECK;
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		map = googleMap;
		if(map!=null){
			googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLat));
			googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
		}
		registerCurrentLocationUpdater();
	}
	private static LatLng currentLat;
	private void registerCurrentLocationUpdater() {
		locationClient.requestLocationUpdates(new LocationRequest()
				.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
				.setInterval(1000)
				.setFastestInterval(500), new LocationCallback() {
			@Override
			public void onLocationResult(LocationResult locationResult) {
				super.onLocationResult(locationResult);
				Location loc = locationResult.getLastLocation();
				if (loc != null) {
					currentLat = new LatLng(loc.getLatitude(), loc.getLongitude());
					if(s){
						map.moveCamera(CameraUpdateFactory.newLatLng(currentLat));
						map.animateCamera(CameraUpdateFactory.zoomTo(17));
						s=!s;
					}
					if (currentMarker == null) {
						Drawable drawable = getDrawable(R.drawable.main_marker);
						Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
						Canvas canvas = new Canvas(bitmap);
						drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
						drawable.draw(canvas);
						currentMarker = mMap.addMarker(new MarkerOptions().position(currentLat).title("현재 위치").icon(BitmapDescriptorFactory.fromBitmap(bitmap)).anchor(0.5f, 0.5f));
					}
				}
			}
		}, Looper.myLooper());
	}

}
*/
