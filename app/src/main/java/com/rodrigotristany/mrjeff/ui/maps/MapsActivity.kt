package com.rodrigotristany.mrjeff.ui.maps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.rodrigotristany.mrjeff.R
import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.ui.searchs.RecentSearchesActivity
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapsMVP.View {

    companion object {
        const val ON_RECENT_CITY_SELECTED = 1
    }

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        initializeView()
    }

    private fun initializeView() {
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, RecentSearchesActivity::class.java)
            startActivityForResult(intent, ON_RECENT_CITY_SELECTED)
        }
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ON_RECENT_CITY_SELECTED){
            data?.let {intent ->
                val cityJson = intent.getStringExtra(RecentSearchesActivity.SELECTED_CITY)
                Gson().fromJson(cityJson, City::class.java).let {
                    setLocationPoint(it.lat, it.lng)
                }

            }
        }
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun setLocationPoint(lat: Double, lng: Double){
        val cityPoint = LatLng(lat,lng)
        mMap.apply {
            this.clear()
            this.addMarker(MarkerOptions().position(cityPoint))
            this.moveCamera(CameraUpdateFactory.newLatLng(cityPoint))
        }
    }

    override fun showToast(message: String?) {
        Toast.makeText(this, message?: "Error", Toast.LENGTH_SHORT).show()
    }
}