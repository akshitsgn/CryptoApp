package com.example.cryptocurrency.Presentation.coinslistscreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrency.Presentation.coindetail.NavGraph


import com.example.cryptocurrency.domain.model.Coin

@Composable
fun CoinListItem(
    coin: Coin,
    onItemClick: (Coin) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.clickable {
                onItemClick(coin)
            }
        ) {
            Text(
                text = " ${coin.name}.${coin.rank}.${coin.symbol}",

                color = Color.White
            )
            Spacer(modifier = Modifier.width(60.dp))
            Text(
                text = if (coin.isActive) "active" else "NotActive",
                color = if (coin.isActive) Color.Red else Color.Green
            )
        }
    }
}

@Composable
fun CoinListScreen(
    viewModel: CoinListViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        if (state.error.isNotBlank()) {
            Text(
                state.error,
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),

                color = Color.White
            )
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            // the below coins is the parameter of coinlist state class which cointains list of state.
            items(state.coins) { coin ->
                CoinListItem(coin = coin,
                    onItemClick = {
                        navController.navigate(NavGraph.CoinDetailScreen.routes + "/${coin.id}")//code after the + operator helps us to insert the coidId directly in the url.
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Sdjs() {
    // CoinListScreen()
}