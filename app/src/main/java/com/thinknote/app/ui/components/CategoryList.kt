package com.thinknote.app.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thinknote.app.database.models.Category
import com.thinknote.app.ui.theme.ThinkNoteTheme

@Composable
fun CategoryList(modifier: Modifier = Modifier, categories: List<Category>) {
    LazyRow(modifier = modifier) {
        items(categories) {
            CategoryItem(modifier = Modifier.padding(horizontal = 4.dp), category = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryListPreview() {
    ThinkNoteTheme {
        CategoryList(
            modifier = Modifier
                .fillMaxSize(),
            categories = listOf(
                Category(1, "Health"),
                Category(2, "Fashion"),
                Category(3, "Weather"),
                Category(4, "Sports"),
                Category(5, "Fashion"),

            )
        )
    }
}
