package com.rodrigotristany.mrjeff.ui.searchs

import android.content.Context
import android.util.Log
import com.rodrigotristany.mrjeff.R
import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.domain.cities.GetCitiesUseCase
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class RecentSearchesPresenter
@Inject constructor(private val getHistorySearchUseCase: GetCitiesUseCase,
                    private val context: Context) : RecentSearchesMVP.Presenter {

    private val TAG : String = RecentSearchesPresenter::javaClass.name
    private var view : RecentSearchesMVP.View? = null

    override fun initialize(){
        view?.showLoader()
        getHistorySearchUseCase.execute(object : DisposableObserver<List<City>>(){
            override fun onComplete() {
                view?.hideLoader()
                Log.i(TAG, context.getString(R.string.data_loaded_successfully))
            }

            override fun onNext(cities: List<City>) {
                view?.hideLoader()
                view?.showCityList(cities)
            }

            override fun onError(e: Throwable) {
                view?.hideLoader()
                view?.showToast(e.message)
            }
        }, "Mad")
    }

    override fun setView(view: RecentSearchesMVP.View) {
        this.view = view
    }
}