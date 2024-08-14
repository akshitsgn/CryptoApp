package com.example.cryptocurrency.domain.usecases.singlecoin

import com.example.cryptocurrency.Common.Resources
import com.example.cryptocurrency.data.remote.dto.toCoin
import com.example.cryptocurrency.data.remote.dto.toCoinDetail
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class SingleCoinUseCase @Inject constructor(
private val repository: CoinRepository
) {

    operator fun invoke(coinId:String): Flow<Resources<CoinDetail>> = flow {
        try {
            emit(Resources.Loading())
            val coins = repository.getCoinsById(coinId).toCoinDetail()
            emit(Resources.Success(coins))
        } catch (e: Exception) {
            //error in response
            emit(Resources.Error(e.localizedMessage ?: "An unexpected error"))
        }//e.localized message cointains description of errorcan be displayed to the users
        catch (e: HttpException) {
            //error like internet connection
            emit(Resources.Error("check your internet connection"))
        }
    }
}