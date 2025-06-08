package com.example.secondhandcars.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarDao {
    @Insert
    suspend fun insert(car: Car)

    @Query("SELECT * FROM car")
    suspend fun getAllCars(): List<Car>

    @Query("SELECT * FROM car WHERE cid = :id")
    suspend fun getCarByID(id: Long): Car
}