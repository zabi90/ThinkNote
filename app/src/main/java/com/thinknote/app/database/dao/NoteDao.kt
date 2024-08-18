package com.thinknote.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.thinknote.app.database.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM NOTES WHERE categoryId=:categoryId Order By updatedAt DESC ")
    fun getNoteByCategory(categoryId: Int): Flow<List<Note>>

    @Query("SELECT * FROM NOTES WHERE id=:noteId")
    suspend fun getNote(noteId: Int):Note?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(note: Note)
}