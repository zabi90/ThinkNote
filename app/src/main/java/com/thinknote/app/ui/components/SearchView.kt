package com.thinknote.app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thinknote.app.ui.theme.ThinkNoteTheme

@Composable
fun SearchView(modifier: Modifier = Modifier, onSearchValueChange: (value: String) -> Unit = {}) {
    var searchValue by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = searchValue,
        placeholder = { Text(text = "Search for notes") },
        onValueChange = {
            searchValue = it
        },
        modifier = modifier,
        singleLine = true,
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ThinkNoteTheme {
        SearchView(modifier = Modifier.fillMaxWidth())
    }
}