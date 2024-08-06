package com.thinknote.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.thinknote.app.models.Category
import com.thinknote.app.models.CategoryWithNotes
import com.thinknote.app.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Transaction
    @Query("SELECT * FROM CATEGORIES")
    fun getCategories() : Flow<List<CategoryWithNotes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(category: Category)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note:Note)
}