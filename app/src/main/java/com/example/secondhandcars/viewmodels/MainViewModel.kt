package com.example.secondhandcars.viewmodels

import androidx.lifecycle.ViewModel
import com.example.secondhandcars.models.Car
import com.example.secondhandcars.models.CarDao
import com.example.secondhandcars.models.Vendor
import com.example.secondhandcars.models.VendorDao

class MainViewModel(val vendorDao: VendorDao, val carDao: CarDao): ViewModel() {
    suspend fun getAllVendors(): List<Vendor> {
        return vendorDao.getAllVendors()
    }

    suspend fun getAllCars(): List<Car> {
        return carDao.getAllCars()
    }

    suspend fun getCarByID(id: Long): Car {
        return carDao.getCarByID(id = id)
    }

    suspend fun insertCar(newCar: Car) {
        return carDao.insert(car = newCar)
    }

    suspend fun deleteCarById(id: Long) {
        return carDao.deleteCarByID(id = id)
    }
}