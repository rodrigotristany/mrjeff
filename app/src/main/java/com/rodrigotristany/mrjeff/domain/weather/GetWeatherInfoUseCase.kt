package com.rodrigotristany.mrjeff.domain.weather

import com.rodrigotristany.mrjeff.data.weather.models.WeatherObservation
import com.rodrigotristany.mrjeff.data.weather.models.WeatherRequest
import com.rodrigotristany.mrjeff.data.weather.models.WeatherResponse
import com.rodrigotristany.mrjeff.data.weather.repository.WeatherRepository
import com.rodrigotristany.mrjeff.domain.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetWeatherInfoUseCase
@Inject constructor(private val weatherRepository: WeatherRepository,
                    subscribeScheduler: Scheduler,
                    postExecutionScheduler: Scheduler) : UseCase<WeatherResponse, WeatherRequest>(subscribeScheduler, postExecutionScheduler) {
    override fun buildUseCaseSingle(params: WeatherRequest?): Observable<WeatherResponse> = weatherRepository.weatherObservation(params!!)
}