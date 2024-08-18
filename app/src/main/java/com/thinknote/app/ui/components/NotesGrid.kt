package com.thinknote.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thinknote.app.database.models.Category
import com.thinknote.app.database.models.Note
import com.thinknote.app.ui.theme.ThinkNoteTheme

@Composable
fun NotesGrid(
    modifier: Modifier = Modifier,
    categoryWithNotes: List<Note>,
    onNoteItemClick: (note: Note) -> Unit
) {

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
            NoteItem(modifier = itemModifier, it, onItemClick = onNoteItemClick)
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
                .padding(16.dp), listOf(),
            {}
        )
    }
}