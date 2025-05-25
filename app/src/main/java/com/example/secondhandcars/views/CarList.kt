package com.example.secondhandcars.views

import android.annotation.SuppressLint
import android.icu.util.Currency
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.secondhandcars.models.Car
import com.example.secondhandcars.viewmodels.MainViewModel
import java.text.NumberFormat

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CarList(viewModel: MainViewModel, innerPadding: PaddingValues) {
    val carList = remember {
        mutableStateListOf<Car>()
    }

    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.currency = java.util.Currency.getInstance("EUR")

    LaunchedEffect(key1 = true) {
        carList.addAll(viewModel.getAllCars())
    }

    Scaffold {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)
                .padding(vertical = innerPadding.calculateTopPadding())) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Name",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp))
                Text(text = "Price",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
            }
            LazyColumn {
                items(carList) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()) {
                        Text(text = it.name,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp))
                        Text(text = format.format(it.price))
                    }
                }
            }
            VerticalDivider()
        }
    }
}