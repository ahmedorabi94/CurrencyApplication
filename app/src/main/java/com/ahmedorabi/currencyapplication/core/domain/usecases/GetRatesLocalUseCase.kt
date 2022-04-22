package com.ahmedorabi.currencyapplication.core.domain.usecases

import com.ahmedorabi.currencyapplication.core.repo.RoomRepository
import javax.inject.Inject

class GetRatesLocalUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    suspend operator fun invoke() = roomRepository.getRates()
}