package com.rodrigotristany.mrjeff.ui.maps

import android.content.Context
import android.util.Log
import com.rodrigotristany.mrjeff.R
import com.rodrigotristany.mrjeff.data.cities.models.City
import com.rodrigotristany.mrjeff.data.weather.models.WeatherObservation
import com.rodrigotristany.mrjeff.data.weather.models.WeatherRequest
import com.rodrigotristany.mrjeff.data.weather.models.WeatherResponse
import com.rodrigotristany.mrjeff.domain.cities.SaveLastSearchUseCase
import com.rodrigotristany.mrjeff.domain.weather.GetWeatherInfoUseCase
import io.reactivex.observers.DisposableObserver

class MapsPresenter(private val getWeatherInfoUseCase: GetWeatherInfoUseCase,
                    private val saveLastSearchUseCase: SaveLastSearchUseCase,
                    private val context: Context) : MapsMVP.Presenter {

    private val TAG : String = MapsPresenter::javaClass.name
    private var view : MapsMVP.View? = null

    override fun searchCityWeather(city: City) {
        this.view?.showLoader()
        val weatherRequest = WeatherRequest(city.bbox?.north, city.bbox?.south, city.bbox?.east, city.bbox?.west)
        saveLastSearchUseCase.execute(object : DisposableObserver<Long>(){
            override fun onComplete() {
                Log.i(TAG, context.getString(R.string.data_saved_successfully))
            }

            override fun onNext(t: Long) {}

            override fun onError(e: Throwable) {
                Log.i(TAG, context.getString(R.string.data_error_during_saving))
            }
        }, city)

        getWeatherInfoUseCase.execute(object : DisposableObserver<WeatherResponse>(){
            override fun onComplete() {
                view?.hideLoader()
                Log.i(TAG, context.getString(R.string.data_loaded_successfully))
            }

            override fun onNext(weatherResponse: WeatherResponse) {
                view?.hideLoader()
                if(!weatherResponse.statusOk())
                    onError(Throwable(weatherResponse.status?.message))
                else {
                    val weatherInfo = weatherResponse.weatherObservations
                    if(weatherInfo.isEmpty()){
                        view?.showToast(context.getString(R.string.no_matching_data_from_server))
                    }
                    else {
                        val averageTemp = weatherInfo.filter { weatherInfo ->
                            !weatherInfo.temperature.isNullOrEmpty()
                        }.map {
                            it.temperature.toDouble()
                        }.average()
                        view?.displayWeatherInfo(averageTemp)
                    }
                }
                view?.displayCityInfo()
            }

            override fun onError(e: Throwable) {
                view?.hideLoader()
                var message = when(e) {
                    is java.net.UnknownHostException -> context.getString(R.string.network_error)
                    else -> e.message
                }
                view?.showToast(message)
            }
        }, weatherRequest)
    }

    override fun setView(view: MapsMVP.View) {
        this.view = view
    }
}