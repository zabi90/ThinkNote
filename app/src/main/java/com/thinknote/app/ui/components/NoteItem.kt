package com.thinknote.app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thinknote.app.database.models.Note
import com.thinknote.app.ui.theme.ThinkNoteTheme
import java.util.Date

@Composable
fun NoteItem(modifier: Modifier = Modifier, note: Note) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(note.color))
    ) {
        Text( text = note.description, modifier = modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun NoteItemPreview() {
    ThinkNoteTheme {
        NoteItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(300.dp),
            Note(1, 1, "text descr", 0xFFD9E8FC, Date(), Date())
        )
    }
}