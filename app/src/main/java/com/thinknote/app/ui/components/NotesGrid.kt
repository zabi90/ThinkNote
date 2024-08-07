package com.thinknote.app.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thinknote.app.models.CategoryWithNotes
import com.thinknote.app.ui.theme.ThinkNoteTheme

@Composable
fun NotesGrid(modifier: Modifier = Modifier, categoryWithNotes: List<CategoryWithNotes>) {

    // val itemsList = (0..20).toList()

    val itemModifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .wrapContentSize()

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categoryWithNotes) {
            NoteItem(modifier = itemModifier, "Item is ${it.category.name}.")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun NotesGridPreview() {
    ThinkNoteTheme {
        NotesGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), listOf()
        )
    }
}