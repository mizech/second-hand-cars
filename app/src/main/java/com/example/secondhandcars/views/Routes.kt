package com.example.secondhandcars.views

sealed class Routes(val name: String) {
    object CarsList: Routes(name = "cars_list")
    object VendorsList: Routes(name = "vendors_list")
    object VendorForm: Routes(name = "vendor_form/{vId}")
    object CarDetails: Routes(name = "car_details/{cId}")
    object VendorDetails: Routes(name = "vendor_details/{vId}")
    object CarForm: Routes(name = "car_form/{cId}")
}