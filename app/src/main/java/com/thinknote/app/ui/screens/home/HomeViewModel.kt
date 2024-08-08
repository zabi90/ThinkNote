package com.thinknote.app.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinknote.app.database.AppDatabase
import com.thinknote.app.database.models.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val appDatabase: AppDatabase) : ViewModel() {

    private var _categories : MutableState<List<Category>> = mutableStateOf(emptyList())
    val categories : State<List<Category>> get() = _categories

    init {
        getCategories()
    }


    fun getCategories(){
        viewModelScope.launch {
            appDatabase.categoryDao().getCategories().collect{
                _categories.value = it
            }
        }
    }
}