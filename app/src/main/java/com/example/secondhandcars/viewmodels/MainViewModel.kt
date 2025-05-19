package com.example.secondhandcars.viewmodels

import androidx.lifecycle.ViewModel
import com.example.secondhandcars.models.Vendor
import com.example.secondhandcars.models.VendorDao

class MainViewModel(val dao: VendorDao): ViewModel() {
    suspend fun getAllVendors(): List<Vendor> {
        return dao.getAllVendors()
    }
}