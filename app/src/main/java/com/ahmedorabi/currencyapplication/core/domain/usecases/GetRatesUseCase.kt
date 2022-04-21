package com.ahmedorabi.currencyapplication.core.domain.usecases

import com.ahmedorabi.currencyapplication.core.repo.CurrencyRepository
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) {


    suspend operator fun invoke() = currencyRepository.getRatesResponse()

}