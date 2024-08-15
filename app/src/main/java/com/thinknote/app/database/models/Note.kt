package com.thinknote.app.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "Notes", foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = CASCADE
        )
    ]
)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val categoryId: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "color")
    val color: Long = 0xFF9CCC65,
    @ColumnInfo(name = "createdAt")
    val createdAt: Date,
    @ColumnInfo(name = "updatedAt")
    val updatedAt: Date
)