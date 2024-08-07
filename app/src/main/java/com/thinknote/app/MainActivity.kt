package com.thinknote.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thinknote.app.models.CategoryWithNotes
import com.thinknote.app.ui.components.NotesGrid
import com.thinknote.app.ui.components.SearchView
import com.thinknote.app.ui.screens.home.HomeViewModel
import com.thinknote.app.ui.theme.ThinkNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            ThinkNoteTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                            }) {
                            Icon(Icons.Filled.Add, contentDescription = "add notes")
                        }
                    }) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding),

                        )
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier) {
    val homeViewModel: HomeViewModel = viewModel()
    val categories: State<List<CategoryWithNotes>> = homeViewModel.categories

    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            painter = painterResource(id = R.drawable.logo), contentDescription = "Think"
        )

        SearchView(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), onSearchValueChange = {

        })
        NotesGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            categoryWithNotes = categories.value
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ThinkNoteTheme {
        App(modifier = Modifier.fillMaxSize())
    }
}