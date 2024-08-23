package com.thinknote.app.ui.screens.detail

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinknote.app.database.AppDatabase
import com.thinknote.app.database.models.Category
import com.thinknote.app.database.models.Note
import com.thinknote.app.utilities.ColorCodes
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
            if (noteId == -1) {
                noteState.value = null
            } else {
                appDatabase.noteDao().getNote(noteId).let { note ->
                    noteState.value = note
                }
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

    fun updateNote(text: String){
        val note = noteState.value?.copy(description = text, updatedAt = Date())
        note?.let {
            viewModelScope.launch {
                appDatabase.noteDao().update(note)
            }
        }
    }
    fun addNote(text: String) {
        viewModelScope.launch {
            appDatabase.noteDao().add(
                Note(
                    categoryId = categoryId,
                    description = text,
                    color = ColorCodes.generateColor(),
                    createdAt = Date(),
                    updatedAt = Date()
                )
            )
        }
    }
}