package com.example.cryptocurrency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrency.Presentation.coindetail.DetailScreen
import com.example.cryptocurrency.Presentation.coindetail.NavGraph
import com.example.cryptocurrency.Presentation.coinslistscreen.CoinListScreen

import com.example.cryptocurrency.ui.theme.CryptocurrencyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptocurrencyTheme {
                val navcontroller = rememberNavController()
                NavHost(
                    navController = navcontroller,
                    startDestination = NavGraph.CoinListScreen.routes
                ) {
                    composable(route = NavGraph.CoinListScreen.routes) {
                        CoinListScreen(navController = navcontroller)
                    }
                    composable(
                        route = NavGraph.CoinDetailScreen.routes + "/{coinId}",

                    ) {
                        DetailScreen()
                    }

                }
                //issue in coinlistscreen
                CoinListScreen(navController = navcontroller)
            }
        }
    }
}

