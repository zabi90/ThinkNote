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
import com.thinknote.app.ui.theme.ThinkNoteTheme

@Composable
fun NoteItem(modifier: Modifier = Modifier, string: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Red)
    ) {
        Text(text = string, modifier = modifier)
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
            "This is test notes description"
        )
    }
}