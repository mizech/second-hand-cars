package com.example.secondhandcars.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun VendorForm() {
    var currName = remember{
        mutableStateOf("")
    }

    var currCountry = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(value = currName.value, onValueChange = {
            currName.value = it
        })
        OutlinedTextField(value = currCountry.value, onValueChange = {
            currCountry.value = it
        })
        OutlinedButton(onClick = {}) {
            Text(text = "Submit")
        }
    }
}