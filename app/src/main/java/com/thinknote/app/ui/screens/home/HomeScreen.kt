package com.thinknote.app.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thinknote.app.AppNavigator
import com.thinknote.app.R
import com.thinknote.app.database.models.Category
import com.thinknote.app.ui.components.CategoryList
import com.thinknote.app.ui.components.NotesGrid
import com.thinknote.app.ui.components.SearchView
import com.thinknote.app.ui.theme.ThinkNoteTheme

@Composable
fun HomeScreen(navigationController: NavController?, modifier: Modifier = Modifier) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val categories: State<List<Category>> = homeViewModel.categories.collectAsState()

    LaunchedEffect(key1 = Unit) {
        homeViewModel.selectedCategory?.let {
            homeViewModel.getNotes(it.id)
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigationController?.navigate(
                        AppNavigator.Routes.DETAILS.replace("{noteId}", "-1").replace(
                            "{categoryId}", homeViewModel.selectedCategory?.id.toString()
                        )
                    )
                },
                shape = CircleShape
                ) {
                Icon(Icons.Filled.Add, contentDescription = "add notes")
            }
        }) { innerPadding ->


        Column(
            modifier = modifier
                .padding(innerPadding)
                .clipToBounds()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.think)
            )

            SearchView(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), onSearchValueChange = {
            })

            CategoryList(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),

                categories = categories.value, onCategoryClick = { category ->
                    homeViewModel.onSelectCategory(category)
                }
            )
            NotesGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                categoryWithNotes = homeViewModel.notes.collectAsState().value
            ) { note ->

                navigationController?.navigate(
                    AppNavigator.Routes.DETAILS.replace("{noteId}", note.id.toString()).replace(
                        "{categoryId}", note.categoryId.toString()
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ThinkNoteTheme {
        HomeScreen(null, modifier = Modifier.fillMaxSize())
    }
}