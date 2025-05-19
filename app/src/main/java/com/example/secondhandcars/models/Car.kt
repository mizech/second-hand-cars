package com.example.secondhandcars.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey(autoGenerate = true)
    val cid: Long = 0,
    var name: String,
    var price: Double,
    var vid: Long
)