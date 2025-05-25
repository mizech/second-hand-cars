package com.example.secondhandcars.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.secondhandcars.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavView(viewModel: MainViewModel) {
    val navController = rememberNavController()

    var isActionMenuShown = remember {
        mutableStateOf(false)
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(actions = {
            IconButton(onClick = {
                isActionMenuShown.value = !isActionMenuShown.value
            }) {
                Icon(Icons.Default.Menu,
                    contentDescription = "")
            }
            DropdownMenu(expanded = isActionMenuShown.value,
                onDismissRequest = {
                    isActionMenuShown.value = false
                }) {
                DropdownMenuItem(text = {
                    Text(text = "Car list")
                }, onClick = {
                    navController.navigate(route = Routes.CarList.name)
                })
                DropdownMenuItem(text = {
                    Text(text = "Create car")
                }, onClick = {
                    navController.navigate(route = Routes.CreateCar.name)
                })
                DropdownMenuItem(text = {
                    Text(text = "Create vendor")
                }, onClick = {
                    navController.navigate(route = Routes.CreateVendor.name)
                })
            }
        }, title = {
            Text(text = "Second-hand cars")
        })
    }) { innerPadding ->
        NavHost(navController = navController,
            startDestination = Routes.CreateCar.name) {
            composable(route = Routes.CreateCar.name) {
                CreateCar(viewModel = viewModel,
                    navController = navController)
            }
            composable(route = Routes.CreateVendor.name) {
                CreateVendor()
            }
            composable(route = Routes.CarList.name) {
                CarList(viewModel = viewModel, innerPadding = innerPadding)
            }
        }
    }
}