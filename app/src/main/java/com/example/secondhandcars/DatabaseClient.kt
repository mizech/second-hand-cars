package com.example.secondhandcars

import android.content.Context
import androidx.room.Room

class DatabaseClient(val context: Context) {
    var instance = Room.databaseBuilder(context = context,
        AppDatabase::class.java,
        "SecondHandCars")
        .fallbackToDestructiveMigration(true)
        .build()
}