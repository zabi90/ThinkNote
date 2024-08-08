package com.thinknote.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thinknote.app.database.dao.CategoryDao
import com.thinknote.app.database.dao.NoteDao
import com.thinknote.app.database.models.Category
import com.thinknote.app.database.models.Note
import com.thinknote.app.database.converters.Converters


@Database(entities = [Note::class, Category::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun noteDao(): NoteDao
}