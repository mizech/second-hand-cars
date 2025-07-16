package com.example.secondhandcars.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.secondhandcars.models.Car
import com.example.secondhandcars.utils.Formatter
import com.example.secondhandcars.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDetails(viewModel: MainViewModel, cId: String,
               navController: NavController, modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()

    var car = remember {
        mutableStateOf(Car(cid = 0, name = "", price = 0.0, vid = 0))
    }
    var isDeleteActive = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true) {
        car.value = viewModel.getCarByID(id = cId.toLong())
    }

    fun deleteCar() {
        coroutineScope.launch {
            viewModel.deleteCarById(id = cId.toLong())
            navController.navigate(Routes.CarsList.name)
        }
    }

    val formatter = Formatter()

    Column(verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        if (isDeleteActive.value == true) {
            BasicAlertDialog(onDismissRequest = {
                isDeleteActive.value = false
            }) {
                Surface(shape = RoundedCornerShape(size = 12.dp)) {
                    Column(modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Car becomes deleted.",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold)
                        Text(text = "Are you sure?",
                            fontSize = 18.sp)
                        Spacer(modifier = Modifier.padding(bottom = 12.dp))
                        OutlinedButton(onClick = {
                            deleteCar()
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Delete",
                                color = Color.Red,
                                fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.padding(bottom = 12.dp))
                        OutlinedButton(onClick = {
                            isDeleteActive.value = false
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Cancel")
                        }
                    }
                }
            }
        }

        Text(text = car.value.name, fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 28.dp))
        Text(text = formatter.formatDouble(value = car.value.price),
            fontSize = 24.sp)
        Spacer(modifier = Modifier)

        Row {
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                isDeleteActive.value = true
            }, modifier = Modifier.weight(2f)) {
                Text(text = "Delete",
                    fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                navController.navigate(Routes.CarForm.name.replace(
                    oldValue = "{cId}",
                    newValue = car.value.cid.toString()))
            }, modifier = Modifier.weight(2f)) {
                Text(text = "Edit",
                    fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


