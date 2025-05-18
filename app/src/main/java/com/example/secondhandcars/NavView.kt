package com.example.secondhandcars

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavView(viewModel: MainViewModel) {
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

                })
                DropdownMenuItem(text = {
                    Text(text = "Create car")
                }, onClick = {

                })
                DropdownMenuItem(text = {
                    Text(text = "Create vendor")
                }, onClick = {

                })
            }
        }, title = {
            Text(text = "Second-hand cars")
        })
    }) {
        val isDropDownExpanded = remember {
            mutableStateOf(false)
        }

        val itemPosition = remember {
            mutableStateOf(0)
        }

        var usernames = remember {
            mutableListOf<String>("")
        }

        LaunchedEffect(key1 = true) {
            var temp = viewModel.getAllVendors()
            temp.forEach { vendor ->
                usernames.add(vendor.name)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        isDropDownExpanded.value = true
                    }
                ) {
                    Text(text = usernames[itemPosition.value])
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
                    usernames.forEachIndexed { index, username ->
                        DropdownMenuItem(text = {
                            Text(text = username)
                        },
                            onClick = {
                                isDropDownExpanded.value = false
                                itemPosition.value = index
                            })
                    }
                }
            }

        }
    }
}