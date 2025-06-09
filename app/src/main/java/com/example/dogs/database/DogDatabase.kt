package com.example.dogs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dogs.data.datasource.DogDao
import com.example.dogs.data.model.DogEntity
import com.example.dogs.extensions.ONE

@Database(entities = [DogEntity::class], version = ONE)
abstract class DogDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao
}