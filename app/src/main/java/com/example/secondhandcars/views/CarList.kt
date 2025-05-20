package com.example.secondhandcars.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.secondhandcars.models.Car
import com.example.secondhandcars.viewmodels.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CarList(viewModel: MainViewModel) {
    val carList = remember {
        mutableStateListOf<Car>()
    }

    LaunchedEffect(key1 = true) {
        carList.addAll(viewModel.getAllCars())
    }

    Scaffold {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {
            Text(text = "Car List-View", fontSize = 32.sp)
            LazyColumn {
                items(carList) {
                    Text(text = it.name)
                }
            }
        }
    }
}