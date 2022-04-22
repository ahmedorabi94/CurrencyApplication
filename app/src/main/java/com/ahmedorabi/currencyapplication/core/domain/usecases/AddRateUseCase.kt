package com.ahmedorabi.currencyapplication.core.domain.usecases

import com.ahmedorabi.currencyapplication.core.domain.model.RateModel
import com.ahmedorabi.currencyapplication.core.repo.RoomRepository
import javax.inject.Inject

class AddRateUseCase @Inject constructor(private val roomRepository: RoomRepository){

    suspend operator fun invoke(rateModel: RateModel) = roomRepository.insertRate(rateModel)
}