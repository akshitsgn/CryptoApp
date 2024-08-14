package com.example.cryptocurrency.data.remote

import com.example.cryptocurrency.data.remote.dto.CoinDetailDto
import com.example.cryptocurrency.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinApi {
    @GET("/v1/coins")
    suspend fun getcoins():List<CoinDto>

    @GET("/v1/coins/{coinId}")
    //we will use the {coinId} by string
    // The path allows the users to contruct the value of the coin id manually using the input
    suspend fun getCoinById(@Path("coinId")coinId:String):CoinDetailDto
}