package com.example.secondhandcars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.secondhandcars.ui.theme.SecondHandCarsTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val database = DatabaseClient(context = applicationContext)
        val dao = database.instance.vendorDao()
        val viewModel by viewModels<MainViewModel>(
            factoryProducer = {
                object: ViewModelProvider.Factory {
                    override fun <T: ViewModel> create(modelClass: Class<T>): T {
                        return MainViewModel(dao = dao) as T
                    }
                }
            }
        )

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecondHandCarsTheme {
                NavView(viewModel = viewModel)
            }
        }
    }
}
