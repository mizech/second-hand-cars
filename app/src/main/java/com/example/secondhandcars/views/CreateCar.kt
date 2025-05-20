package com.example.secondhandcars.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.secondhandcars.viewmodels.MainViewModel
import com.example.secondhandcars.R
import com.example.secondhandcars.models.Car
import com.example.secondhandcars.models.Vendor
import kotlinx.coroutines.launch
import kotlin.Double

@Composable
fun CreateCar(viewModel: MainViewModel) {
    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    var givenName = remember {
        mutableStateOf("")
    }

    var givenPrice = remember {
        mutableDoubleStateOf(0.0)
    }

    val selectedVendor = remember {
        mutableStateOf(Vendor(name = "", country = ""))
    }

    var vendors = remember {
        mutableListOf<Vendor>(Vendor(name = "", country = ""))
    }

    LaunchedEffect(key1 = true) {
        vendors.addAll(viewModel.getAllVendors())
    }

    val rcScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = givenName.value, onValueChange = {
            givenName.value = it
        }, placeholder = {
            Text(text = "Insert car's name")
        }, modifier = Modifier.padding(bottom = 25.dp))
        OutlinedTextField(value = givenPrice.doubleValue.toString(), onValueChange = { newVal: String ->
            givenPrice.doubleValue = newVal.toDoubleOrNull() ?: 0.0
        }, placeholder = {
            Text(text = "Insert country")
        }, modifier = Modifier.padding(bottom = 25.dp))
        Box {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    isDropDownExpanded.value = true
                }.padding(bottom = 25.dp)
            ) {
                Text(text = selectedVendor.value.name)
                Image(
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                    contentDescription = "DropDown Icon"
                )
            }
            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                }) {
                vendors.forEachIndexed { index, vendor ->
                    DropdownMenuItem(text = {
                        Text(text = vendor.name)
                    },
                        onClick = {
                            isDropDownExpanded.value = false
                            selectedVendor.value = vendor
                        })
                }
            }
        }
        OutlinedButton(onClick = {
            val newCar = Car(name = givenName.value,
                price = givenPrice.doubleValue.toDouble(),
                vid = selectedVendor.value.vid)
            rcScope.launch {
                viewModel.insertCar(newCar = newCar)
            }
        }) {
            Text(text = "Insert Car")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}