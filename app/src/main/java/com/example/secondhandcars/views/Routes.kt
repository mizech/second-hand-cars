package com.example.secondhandcars.views

sealed class Routes(val name: String) {
    object CarList: Routes(name = "car_list")
    object CreateCar: Routes(name = "create_car")
    object CreateVendor: Routes(name = "create_vendor")
}