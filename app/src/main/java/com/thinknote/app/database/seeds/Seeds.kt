package com.thinknote.app.database.seeds

import android.content.ContentValues
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class Seeds : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        var contentValues = ContentValues().apply {
            put("name", "Important")
        }
        db.insert("Categories", 0, contentValues)

        contentValues = ContentValues().apply {
            put("name", "Lectures")
        }
        db.insert("Categories", 0, contentValues)

        contentValues = ContentValues().apply {
            put("name", "To-do lists")
        }
        db.insert("Categories", 0, contentValues)

        contentValues = ContentValues().apply {
            put("name", "Shopping")
        }
        db.insert("Categories", 0, contentValues)

        contentValues = ContentValues().apply {
            put("name", "Others")
        }
        db.insert("Categories", 0, contentValues)
    }
}