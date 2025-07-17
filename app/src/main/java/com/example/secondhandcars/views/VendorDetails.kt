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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.secondhandcars.models.Vendor
import com.example.secondhandcars.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendorDetails(viewModel: MainViewModel, vId: String,
                  navController: NavController) {
    Column(verticalArrangement = Arrangement.SpaceAround,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize()) {
        var vendor = remember {
            mutableStateOf(Vendor(vid = 0, name = "", country = ""))
        }
        var isDeleteActive = remember {
            mutableStateOf(false)
        }

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

        Text(text = vendor.value.name, fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 28.dp))
        Text(text = "",
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
                    oldValue = "{vId}",
                    newValue = vendor.value.vid.toString()))
            }, modifier = Modifier.weight(2f)) {
                Text(text = "Edit",
                    fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}