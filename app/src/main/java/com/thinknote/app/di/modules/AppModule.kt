package com.thinknote.app.di.modules

import android.content.Context
import androidx.room.Room
import com.thinknote.app.database.AppDatabase
import com.thinknote.app.database.seeds.Seeds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun getNoteDatabase(@ApplicationContext context: Context): AppDatabase {
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "think-note"
        ).addCallback(Seeds())
            .build()
        return db
    }
}