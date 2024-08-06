package com.thinknote.app.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey
    val id: Int,
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