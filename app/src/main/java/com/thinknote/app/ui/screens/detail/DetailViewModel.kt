package com.thinknote.app.ui.screens.detail

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinknote.app.database.AppDatabase
import com.thinknote.app.database.models.Category
import com.thinknote.app.database.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val appDatabase: AppDatabase) : ViewModel() {

    var categoryId: Int = 1
    var noteId: Int = -1

    val noteState = mutableStateOf<Note?>(null)

    fun getNote() {
        viewModelScope.launch {
            appDatabase.noteDao().getNote(noteId).let { note ->
                noteState.value = note
            }
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            noteState.value?.let { note ->
                appDatabase.noteDao().delete(note)
            }
        }
    }

    fun addNote(text: String) {

        viewModelScope.launch {
            appDatabase.noteDao().add(
                Note(
                    id = noteId,
                    categoryId = categoryId,
                    description = text,
                    color = 0xFFD3E9FC,
                    createdAt = Date(),
                    updatedAt = Date()
                )
            )
        }
    }
}