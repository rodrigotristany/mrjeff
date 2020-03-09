package com.rodrigotristany.mrjeff.ui.searchs

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.rodrigotristany.mrjeff.R
import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.internal.App
import com.rodrigotristany.mrjeff.ui.maps.MapsActivity
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
        initListeners()
        presenter.setView(this)
    }

    private fun initListeners() {
        search_button.setOnClickListener {
            cities_recycler_view.adapter = null
            hideKeyboard(city_text_input)
            presenter.searchCity(city_text_input.text.toString())
        }
        recents_button.setOnClickListener {
            city_text_input.clearFocus()
            hideKeyboard(city_text_input)
            presenter.recentSearches()
        }
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
            sendDataToMap(city)
        }
    }

    override fun sendDataToMap(city: City) {
        val resultIntent = Intent(this, MapsActivity::class.java)
        resultIntent.putExtra(SELECTED_CITY, Gson().toJson(city))
        startActivity(resultIntent)
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

    private fun hideKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
