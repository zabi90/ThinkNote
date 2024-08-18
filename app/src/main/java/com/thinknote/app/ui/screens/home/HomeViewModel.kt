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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val appDatabase: AppDatabase) : ViewModel() {

    var selectedCategory: Category? = null
        private set

    private var _categories: MutableState<List<Category>> = mutableStateOf(emptyList())
    val categories: State<List<Category>> get() = _categories

    private var _notes: MutableState<List<Note>> = mutableStateOf(emptyList())
    val notes: State<List<Note>> get() = _notes


    fun onSelectCategory(category: Category) {
        selectedCategory = category
        val updateList = _categories.value.map {
            it.selected = it.id == category.id
            return@map it
        }
        _categories.value = updateList
        getNotes(categoryId = category.id)
    }


    fun getCategories() {
        Log.d("HomeViewModel", "getCategories: Loaded")

        if (_categories.value.isEmpty()) {
            viewModelScope.launch {

                appDatabase.categoryDao().getCategories().collect {
                    _categories.value = it
                    if (selectedCategory != null) {
                        getNotes(selectedCategory!!.id)
                    } else {
                        getNotes(it[0].id)
                    }
                }
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
}