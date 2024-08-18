package com.thinknote.app.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @Ignore
    var selected: Boolean = false
) {
    // Secondary constructor for Room
    constructor(id: Int, name: String) : this(id, name, false)
}