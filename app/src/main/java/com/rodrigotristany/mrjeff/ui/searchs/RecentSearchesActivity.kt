package com.rodrigotristany.mrjeff.ui.searchs

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.rodrigotristany.mrjeff.R
import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.internal.App
import com.rodrigotristany.mrjeff.ui.searchs.di.DaggerRecentSearchesComponent
import kotlinx.android.synthetic.main.activity_recent_searchs.*
import javax.inject.Inject

class RecentSearchesActivity : AppCompatActivity(), RecentSearchesMVP.View {

    @Inject
    lateinit var presenter : RecentSearchesMVP.Presenter

    companion object {
        const val SELECTED_CITY = "SELECTED_CITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_searchs)
        initInjector()
        initRecyclerView()
        presenter.initialize()
        presenter.setView(this)
    }

    private fun initRecyclerView() {
        cities_recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        cities_recycler_view.itemAnimator = DefaultItemAnimator()
        cities_recycler_view.setHasFixedSize(true)
        cities_recycler_view.layoutManager = LinearLayoutManager(this)
    }

    private fun initInjector() {
        DaggerRecentSearchesComponent.builder()
            .applicationComponent((application as App).applicationComponent)
            .build()
            .inject(this)
    }

    override fun showCityList(cities: List<City>) {
        cities_recycler_view.adapter = SearchsListAdapter(cities) { city ->
            showToast("You have clicked ${city.name}")
            sendDataToMap(city)
        }
    }

    override fun sendDataToMap(city: City) {
        val resultIntent = Intent()
        resultIntent.putExtra(SELECTED_CITY, Gson().toJson(city))
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    override fun showToast(message: String?) {
        Toast.makeText(this, message?: "Error", Toast.LENGTH_SHORT).show()
    }

    override fun showLoader() {
        indeterminateBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        indeterminateBar.visibility = View.INVISIBLE
    }
}
