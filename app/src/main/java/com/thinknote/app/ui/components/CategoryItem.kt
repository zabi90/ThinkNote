package com.thinknote.app.ui.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.FilterChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thinknote.app.database.models.Category
import com.thinknote.app.ui.theme.ThinkNoteTheme

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    onItemClick: (category: Category) -> Unit
) {
    FilterChip(selected = false, modifier = modifier, onClick = {
        onItemClick(category)
    }, label = {
        Text(category.name)
    })
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    ThinkNoteTheme {
        CategoryItem(
            modifier = Modifier
                .wrapContentSize(),
            category = Category(1, "Health")
        ){

        }
    }
}