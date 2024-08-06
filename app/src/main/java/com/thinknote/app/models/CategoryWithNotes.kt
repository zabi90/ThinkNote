package com.thinknote.app.models

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithNotes(
    @Embedded
    val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val notes: List<Note>
)
