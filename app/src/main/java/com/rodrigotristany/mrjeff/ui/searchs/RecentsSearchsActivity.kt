package com.rodrigotristany.mrjeff.ui.searchs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.rodrigotristany.mrjeff.R
import com.rodrigotristany.mrjeff.data.cities.models.City
import kotlinx.android.synthetic.main.activity_recent_searchs.*
import javax.inject.Inject

class RecentsSearchsActivity : AppCompatActivity(), RecentSearchMVP.View {

    @Inject
    lateinit var presenter : RecentSearchMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_searchs)
        presenter.initialize()
    }

    override fun showCityList(cities: List<City>) {
        cities_recycler_view.adapter = SearchsListAdapter(cities) { city ->
            showSnackBar("You have clicked ${city.name}")
        }
    }

    override fun showSnackBar(message: String?) {
        Snackbar.make(activity_view, message?: "Error", Snackbar.LENGTH_SHORT)
    }

    override fun showLoader() {
        indeterminateBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        indeterminateBar.visibility = View.INVISIBLE
    }
}
