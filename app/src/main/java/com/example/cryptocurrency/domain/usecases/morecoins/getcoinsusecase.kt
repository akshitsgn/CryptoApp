package com.example.cryptocurrency.domain.usecases.morecoins


import com.example.cryptocurrency.Common.Resources
import com.example.cryptocurrency.data.remote.dto.toCoin
import com.example.cryptocurrency.domain.model.Coin

import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
){
  //this allows the instance of the class to be called as a functions
   operator fun invoke(): Flow<Resources<List<Coin>>> = flow{
       try{
           emit(Resources.Loading())
           val coins = repository.getCoins().map{ it.toCoin()}//as it requires the list of the coins thus we use map
           emit(Resources.Success(coins))
       }
       catch(e: HttpException){
           //error like internet connection
           emit(Resources.Error("check your internet connection"))
       }
       catch(e:Exception){
           //error in response
           emit(Resources.Error(e.localizedMessage?:"An unexpected error"))
       }//e.localized message cointains description of errorcan be displayed to the users

   }


}