package com.rodrigotristany.mrjeff.ui.searchs

import android.content.Context
import android.util.Log
import com.rodrigotristany.mrjeff.R
import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.data.cities.models.CityResponse
import com.rodrigotristany.mrjeff.domain.cities.GetCitiesUseCase
import com.rodrigotristany.mrjeff.domain.cities.GetHistorySearchUseCase
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class RecentSearchesPresenter
@Inject constructor(private val getCitiesUseCase: GetCitiesUseCase,
                    private val getHistorySearchUseCase: GetHistorySearchUseCase,
                    private val context: Context) : RecentSearchesMVP.Presenter {

    private val TAG : String = RecentSearchesPresenter::javaClass.name
    private var view : RecentSearchesMVP.View? = null

    override fun searchCity(param: String){
        view?.showLoader()
        getCitiesUseCase.execute(object : DisposableObserver<CityResponse>(){
            override fun onComplete() {
                view?.hideLoader()
                Log.i(TAG, context.getString(R.string.data_loaded_successfully))
            }

            override fun onNext(cityResponse: CityResponse) {
                view?.hideLoader()

                if(!cityResponse.statusOk())
                    onError(Throwable(cityResponse.status?.message))
                else {
                    val cities = cityResponse.cities
                    if(cities.isEmpty())
                        view?.showToast(context.getString(R.string.no_matching_data_from_server))
                    view?.showCityList(cities)
                }
            }

            override fun onError(e: Throwable) {
                view?.hideLoader()
                var message = when(e) {
                    is java.net.UnknownHostException -> context.getString(R.string.network_error)
                    else -> e.message
                }
                view?.showToast(message)
            }
        }, param)
    }

    override fun recentSearches(){
        view?.showLoader()
        getHistorySearchUseCase.execute(object : DisposableObserver<List<City>>(){
            override fun onComplete() {
                view?.hideLoader()
                Log.i(TAG, context.getString(R.string.data_loaded_successfully))
            }

            override fun onNext(cities: List<City>) {
                view?.hideLoader()
                if(cities.isEmpty())
                    view?.showToast(context.getString(R.string.no_matching_data_from_server))
                view?.showCityList(cities)
            }

            override fun onError(e: Throwable) {
                view?.hideLoader()
                view?.showToast(e.message)
            }
        })
    }

    override fun setView(view: RecentSearchesMVP.View) {
        this.view = view
    }
}