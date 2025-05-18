package com.example.secondhandcars

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class MainViewModel(val dao: VendorDao): ViewModel() {
    suspend fun getAllVendors(): List<Vendor> {
        return dao.getAllVendors()
    }
}