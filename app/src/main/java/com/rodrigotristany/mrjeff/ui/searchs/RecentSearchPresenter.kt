package com.rodrigotristany.mrjeff.ui.searchs

import android.content.Context
import android.util.Log
import com.rodrigotristany.mrjeff.R
import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.domain.cities.GetHistorySearchUseCase
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class RecentSearchPresenter
@Inject constructor(private val view : RecentSearchMVP.View,
                    private val getHistorySearchUseCase: GetHistorySearchUseCase,
                    private val context: Context) : RecentSearchMVP.Presenter {

    private val TAG : String = RecentSearchPresenter::javaClass.name

    override fun initialize(){
        view.showLoader()
        getHistorySearchUseCase.execute(object : DisposableObserver<List<City>>(){
            override fun onComplete() {
                view.hideLoader()
                Log.i(TAG, context.getString(R.string.data_loaded_successfully))
            }

            override fun onNext(cities: List<City>) {
                view.showCityList(cities)
            }

            override fun onError(e: Throwable) {
                view.hideLoader()
                view.showSnackBar(e.message)
            }
        })
    }
}