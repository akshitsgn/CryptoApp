package com.example.cryptocurrency.Presentation.coindetail

sealed class NavGraph(
    val routes:String
){
    object CoinListScreen:NavGraph("coin_list_screen")
    object CoinDetailScreen: NavGraph("coin_detail_screen")
}
