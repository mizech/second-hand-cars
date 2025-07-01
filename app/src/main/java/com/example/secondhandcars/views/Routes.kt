package com.example.secondhandcars.views

sealed class Routes(val name: String) {
    object CarList: Routes(name = "car_list")
    object CarDetails: Routes(name = "car_details/{cId}")
    object CarForm: Routes(name = "car_form/{cId}")
    object CreateVendor: Routes(name = "create_vendor")
}