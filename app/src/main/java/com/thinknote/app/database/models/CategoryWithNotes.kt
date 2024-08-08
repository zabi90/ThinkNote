package com.thinknote.app.database.models

import androidx.room.Embedded
import androidx.room.Relation

//Embaded object approach for complex entities
//data class CategoryWithNotes(
//    @Embedded
//    val category: Category,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "categoryId"
//    )
//    val notes: List<Note>
//)
