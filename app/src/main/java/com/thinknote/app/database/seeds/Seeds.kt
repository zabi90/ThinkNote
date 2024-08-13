package com.thinknote.app.database.seeds

import android.content.ContentValues
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.Date

class Seeds : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)



        var contentValues = ContentValues().apply {
            put("name", "All")
        }
        db.insert("Categories", 0, contentValues)


        contentValues = ContentValues().apply {
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


        var noteContentValues = ContentValues().apply {
            put("description", "Product Meeting \n" +
                    "Review of Previous Action Items\n" +
                    "Product Development Update\n" +
                    "User Feedback and Customer Insights\n" +
                    "Competitive Analysis\n" +
                    "Roadmap Discussion  ")
            put("categoryId", 1)
            put("color", 0xFFD9E8FC)
            put("createdAt", Date().time)
            put("updatedAt", Date().time)
        }
        db.insert("Notes", 0, noteContentValues)
    }
}