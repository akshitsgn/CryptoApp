package com.example.cryptocurrency.Presentation.coinslistscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.Common.Resources
import com.example.cryptocurrency.domain.usecases.morecoins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase//this is usecase
):ViewModel(){

private val _state= mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init{
        getCoins()
    }
    private fun getCoins(){
        getCoinsUseCase().onEach {result->
            when(result){
                is Resources.Success->{
                    _state.value= CoinListState(coins = result.data ?: emptyList())
                }
                is Resources.Error->{
                    _state.value = CoinListState(
                        error = result.message ?:"An unexpected error"
                    )
                }
                is Resources.Loading->{
           _state.value= CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}