package com.rodrigotristany.mrjeff.domain.cities

import com.rodrigotristany.mrjeff.data.cities.models.CityResponse
import com.rodrigotristany.mrjeff.data.cities.repository.CityRepository
import com.rodrigotristany.mrjeff.domain.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetCitiesUseCase
@Inject constructor(private val cityRepository: CityRepository,
                    subscribeScheduler: Scheduler,
                    postExecutionScheduler: Scheduler) : UseCase<CityResponse, String>(subscribeScheduler, postExecutionScheduler) {
    override fun buildUseCaseSingle(params: String?): Observable<CityResponse> = cityRepository.cities(params!!)
}