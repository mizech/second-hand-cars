package com.example.secondhandcars.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.secondhandcars.viewmodels.MainViewModel

@Composable
fun VendorsList(viewModel: MainViewModel, navController: NavController) {
    Column() {
        Text(text = "Vendor's List")
    }
}