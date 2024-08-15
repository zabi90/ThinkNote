package com.thinknote.app.ui.screens.detail

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

    fun updateNote(note: Note) {}

    fun addNote(text: String) {

        viewModelScope.launch {
            appDatabase.noteDao().add(
                Note(
                    categoryId = 1,
                    description = text,
                    color = 0xFFD3E9FC,
                    createdAt = Date(),
                    updatedAt = Date()
                )
            )
        }
    }
}