package com.thinknote.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thinknote.app.ui.theme.ThinkNoteTheme

@Composable
fun SearchView(modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = "",
        placeholder = { Text(text = "Search for notes") },
        onValueChange = {

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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