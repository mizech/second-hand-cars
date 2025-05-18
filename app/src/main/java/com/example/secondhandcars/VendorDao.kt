package com.example.secondhandcars

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface VendorDao {
    @Insert
    suspend fun insert(vendor: Vendor)

    @Query("SELECT * FROM vendor")
    suspend fun getAllVendors(): List<Vendor>

    @Transaction
    @Query("SELECT * FROM vendor")
    fun getVendorWithCars(): List<VendorWithCars>
}