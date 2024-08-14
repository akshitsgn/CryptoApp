package com.example.cryptocurrency.data.repository

import com.example.cryptocurrency.data.remote.CoinApi
import com.example.cryptocurrency.data.remote.dto.CoinDetailDto
import com.example.cryptocurrency.data.remote.dto.CoinDto
import com.example.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject


class CoinRepoImpl @Inject constructor(
    private val api: CoinApi
):CoinRepository{

    override suspend fun getCoins(): List<CoinDto> {
       return api.getcoins()
    }

    override suspend fun getCoinsById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}