package com.example.cryptocurrency.Presentation.coindetail

import android.telecom.Call.Details
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrency.Presentation.coinslistscreen.CoinListItem
import com.example.cryptocurrency.Presentation.coinslistscreen.CoinListViewModel
import com.example.cryptocurrency.data.remote.dto.Tag
import com.example.cryptocurrency.data.remote.dto.TeamMember
import com.example.cryptocurrency.domain.model.CoinDetail

@Composable
fun Tag(
    tag:String
){
    Box(modifier = Modifier
        .border(
            width = 1.dp,
            color = Color.Red,
            shape = RoundedCornerShape(100.dp)
        )
        .padding(100.dp)){

        Text(text = tag,
            color = Color.Green)
    }
}
@Composable
fun TeamListItem(
    teamMember: TeamMember,
    modifier: Modifier
){
   Column(modifier=Modifier,
       verticalArrangement = Arrangement.Center) {
Text(
    text=teamMember.name,
    color = Color.Green
)
       Spacer(modifier = Modifier.height(4.dp))
       Text(
           text=teamMember.id,
           color = Color.Green,
           fontStyle = FontStyle.Italic
       )
   }
}

@Composable
fun DetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
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
        state.coin?.let { coin->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item { 
                    Text(text = "${coin.description}")
                }
                }
            }
        }
    }