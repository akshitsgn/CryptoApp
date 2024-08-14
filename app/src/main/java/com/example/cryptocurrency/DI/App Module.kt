package com.example.cryptocurrency.DI

import com.example.cryptocurrency.Common.Constants
import com.example.cryptocurrency.data.remote.CoinApi
import com.example.cryptocurrency.data.repository.CoinRepoImpl
import com.example.cryptocurrency.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi():CoinApi{
        return Retrofit.Builder().baseUrl(Constants.BASEURL).addConverterFactory(GsonConverterFactory.create()).build()
            .create(CoinApi::class.java)
    }
  @Provides
  @Singleton
  fun ProvideCoinRepo(api: CoinApi):CoinRepository{
      return CoinRepoImpl(api)
  }
}