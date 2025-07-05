package com.example.secondhandcars.views

sealed class Routes(val name: String) {
    object CarList: Routes(name = "car_list")
    object CarDetails: Routes(name = "car_details/{cId}")
    object CarForm: Routes(name = "car_form/{cId}")
    object VendorForm: Routes(name = "vendor_form")
}