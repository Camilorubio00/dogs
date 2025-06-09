package com.example.dogs.di

import android.content.Context
import androidx.room.Room
import com.example.dogs.data.datasource.DogDao
import com.example.dogs.database.DogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DogDatabase {
        return Room.databaseBuilder(
            context,
            DogDatabase::class.java,
            "dog_database"
        ).build()
    }

    @Provides
    fun provideDogDao(database: DogDatabase): DogDao {
        return database.dogDao()
    }
}