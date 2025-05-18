package com.example.secondhandcars

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 2, entities = [Vendor::class,  Car::class])
abstract class AppDatabase: RoomDatabase() {

    abstract fun vendorDao(): VendorDao

    abstract fun carDao(): CarDao
}