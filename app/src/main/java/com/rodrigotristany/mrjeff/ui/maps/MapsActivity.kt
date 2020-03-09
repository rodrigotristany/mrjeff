package com.rodrigotristany.mrjeff.ui.maps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
import com.rodrigotristany.mrjeff.internal.App
import com.rodrigotristany.mrjeff.internal.extensions.animateTo
import com.rodrigotristany.mrjeff.ui.maps.di.DaggerMapsComponent
import com.rodrigotristany.mrjeff.ui.searchs.RecentSearchesActivity
import kotlinx.android.synthetic.main.activity_maps.*
import javax.inject.Inject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapsMVP.View {

    @Inject
    lateinit var presenter : MapsMVP.Presenter

    companion object {
        private lateinit var mMap: GoogleMap
    }

    private lateinit var city: City

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        initInjector()
        val cityJson = intent.extras?.getString(RecentSearchesActivity.SELECTED_CITY)
        cityJson?.let{
            city = Gson().fromJson(cityJson, City::class.java)
        }
        initializeView()
        presenter.setView(this)
        presenter.searchCityWeather(city)
    }

    private fun initInjector() {
        DaggerMapsComponent.builder()
            .applicationComponent((application as App).applicationComponent)
            .build()
            .inject(this)
    }

    private fun initializeView() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
        setLocationPoint(city.lat, city.lng)
    }

    override fun setLocationPoint(lat: Double, lng: Double){
        val cityPoint = LatLng(lat,lng)
        mMap.apply {
            this.clear()
            this.addMarker(MarkerOptions().position(cityPoint))
            this.moveCamera(CameraUpdateFactory.newLatLng(cityPoint))
        }
    }

    override fun displayCityInfo(temperature: Double) {
        city_text_view.text = getString(R.string.city_full_name, city.name, city.countryName)
        population_text_view.text = getString(R.string.city_population_text, city.population.toString())
        timezone_text_view.text = getString(R.string.city_timezone_text, city.timezone.gmtOffset.toString())
        determinateBar.animateTo(temperature.toInt(), 1000)
    }

    override fun showToast(message: String?) {
        Toast.makeText(this, message?: "Error", Toast.LENGTH_SHORT).show()
    }

    override fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        progressBar.visibility = View.INVISIBLE
    }
}