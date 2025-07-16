package com.example.secondhandcars.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.secondhandcars.models.Vendor
import com.example.secondhandcars.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun VendorForm(viewModel: MainViewModel, navController: NavController) {
    var cScope = rememberCoroutineScope()

    var currName = remember{
        mutableStateOf("")
    }

    var currCountry = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize().padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(value = currName.value, onValueChange = {
            currName.value = it
        }, modifier = Modifier.padding(bottom = 25.dp),
            placeholder = {
                Text("Insert vendor's name")
            })
        OutlinedTextField(value = currCountry.value, onValueChange = {
            currCountry.value = it
        }, modifier = Modifier.padding(bottom = 25.dp),
            placeholder = {
                Text("Insert vendor's country")
            })
        OutlinedButton(onClick = {
            if (currName.value.isEmpty() || currCountry.value.isEmpty()) {
                return@OutlinedButton
            }

            cScope.launch {
                viewModel.insert(newVendor = Vendor(name = currName.value,
                    country = currCountry.value))
                navController.navigate(Routes.CarsList.name)
            }
        }) {
            Text(text = "Submit")
        }
    }
}