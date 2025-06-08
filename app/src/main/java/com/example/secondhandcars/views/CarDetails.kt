package com.example.secondhandcars.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.secondhandcars.models.Car
import com.example.secondhandcars.utils.Formatter
import com.example.secondhandcars.viewmodels.MainViewModel

@Composable
fun CarDetails(viewModel: MainViewModel, cId: String, modifier: Modifier = Modifier) {
    var car = remember {
        mutableStateOf(Car(cid = 0, name = "", price = 0.0, vid = 0))
    }
    LaunchedEffect(key1 = true) {
        car.value = viewModel.getCarByID(id = cId.toLong())
    }

    val formatter = Formatter()

    Column(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        Text(text = car.value.name, fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 100.dp))
        Text(text = formatter.formatDouble(value = car.value.price),
            fontSize = 24.sp)
    }
}