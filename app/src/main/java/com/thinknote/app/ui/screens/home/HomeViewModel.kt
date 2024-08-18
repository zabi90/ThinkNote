package com.thinknote.app.ui.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinknote.app.database.AppDatabase
import com.thinknote.app.database.models.Category
import com.thinknote.app.database.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val appDatabase: AppDatabase) : ViewModel() {

    init {
        Log.d(TAG, "init: HomeViewModel")
        getCategories()
    }

    var selectedCategory: Category? = null
        private set

    private var _categories: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val categories: StateFlow<List<Category>> get() = _categories

    private var _notes: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val notes: StateFlow<List<Note>> get() = _notes


    fun onSelectCategory(category: Category) {
        selectedCategory = category

        _categories.value.map { chip ->
            chip.copy(selected = chip.id == category.id)
        }.also {
            _categories.value = it
            getNotes(categoryId = category.id)
        }
    }


    private fun getCategories() {
        Log.d("HomeViewModel", "getCategories: Loaded")
        viewModelScope.launch {

            appDatabase.categoryDao().getCategories().collect {

                if (selectedCategory != null) {
                    getNotes(selectedCategory!!.id)
                } else {
                    getNotes(it[0].id)
                    selectedCategory = it[0]
                    selectedCategory!!.selected = true
                }
                _categories.value = it
            }
        }
    }

    fun getNotes(categoryId: Int) {
        viewModelScope.launch {
            appDatabase.noteDao().getNoteByCategory(categoryId).collect {
                _notes.value = it
            }
        }
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}