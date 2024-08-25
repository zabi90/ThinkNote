package com.thinknote.app.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.OutlinedRichTextEditor
import com.thinknote.app.R
import com.thinknote.app.ui.components.ConfirmationAlertDialog
import com.thinknote.app.ui.theme.ThinkNoteTheme
import com.thinknote.app.ui.theme.primaryColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController?,
    modifier: Modifier = Modifier,
    noteID: Int? = -1,
    categoryID: Int? = 1
) {

    val detailViewModel: DetailViewModel = hiltViewModel()
    val richTextState = rememberRichTextState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val deleteAlertDialog = remember { mutableStateOf(false) }

    when {
        // ...
        deleteAlertDialog.value -> {
            ConfirmationAlertDialog(
                onDismissRequest = { deleteAlertDialog.value = false },
                onConfirmation = {
                    deleteAlertDialog.value = false
                    detailViewModel.deleteNote()
                    scope.launch {
                        snackbarHostState.showSnackbar("Note deleted successfully.")
                        navController?.navigateUp()
                    }
                },
                dialogTitle = "Delete",
                dialogText = "You to want to delete this note?",
                icon = Icons.Default.Info
            )
        }
    }


    LaunchedEffect(key1 = Unit) {

        categoryID?.let {
            detailViewModel.categoryId = categoryID
        }

        noteID?.let {
            detailViewModel.noteId = noteID
            detailViewModel.getNote()
        }
    }

    LaunchedEffect(key1 = detailViewModel.noteState.value) {
        detailViewModel.noteState.value?.let {
            richTextState.setHtml(it.description)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                actions = {
                    detailViewModel.noteState.value?.let {
                        IconButton(
                            onClick = {
                                deleteAlertDialog.value = true
                            },
                        ) {

                            Icon(
                                Icons.Filled.Delete,
                                contentDescription = "Localized description",
                                modifier = Modifier,
                            )
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            if (richTextState.toHtml() == "<br>" || richTextState.toString()
                                    .isBlank() || richTextState.toString()
                                    .isEmpty()
                            ) {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Please enter some think in note")
                                }
                                return@FloatingActionButton
                            }
                            if (detailViewModel.noteState.value != null) {
                                detailViewModel.updateNote(richTextState.toHtml())
                            } else {
                                detailViewModel.addNote(richTextState.toHtml())
                            }
                            navController?.navigateUp()
                        },
                        containerColor = primaryColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                        shape = CircleShape
                    ) {
                        Icon(Icons.Filled.Done, "Localized description")
                    }
                }
            )
        },
    ) { innerPadding ->
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
            Card(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .background(color = Color.White),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .background(color = Color.White)
                ) {
                    IconButton(
                        onClick = {
                            richTextState.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        },
                    ) {

                        // Get the current span style.
                        val currentSpanStyle = richTextState.currentSpanStyle
                        val isBold = currentSpanStyle.fontWeight == FontWeight.Bold

                        Icon(
                            painterResource(id = R.drawable.ic_bold),
                            contentDescription = "Localized description",
                            modifier = Modifier,
                            tint = if (isBold) Color.Black else Color.LightGray
                        )
                    }
                    IconButton(onClick = {
                        richTextState.toggleSpanStyle(
                            SpanStyle(
                                fontStyle = FontStyle.Italic
                            )
                        )
                    }) {

                        val isItalic = richTextState.currentSpanStyle.fontStyle == FontStyle.Italic

                        Icon(
                            painterResource(id = R.drawable.ic_italic),
                            contentDescription = "Localized description",
                            modifier = Modifier,
                            tint = if (isItalic) Color.Black else Color.LightGray
                        )
                    }
                    IconButton(onClick = {
                        richTextState.toggleSpanStyle(
                            SpanStyle(
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    }) {
                        val isUnderLine =
                            richTextState.currentSpanStyle.textDecoration?.contains(TextDecoration.Underline) == true
                        Icon(
                            painterResource(id = R.drawable.ic_underline),
                            contentDescription = "Localized description",
                            modifier = Modifier,
                            tint = if (isUnderLine) Color.Black else Color.LightGray
                        )
                    }

                    IconButton(onClick = {
                        richTextState.addParagraphStyle(
                            ParagraphStyle(
                                textAlign = TextAlign.Left
                            )
                        )
                    }) {
                        val isLeftAlign =
                            richTextState.currentParagraphStyle.textAlign == TextAlign.Left
                        Icon(
                            painterResource(id = R.drawable.ic_align_left),
                            contentDescription = "Localized description",
                            modifier = Modifier,
                            tint = if (isLeftAlign) Color.Black else Color.LightGray
                        )
                    }

                    IconButton(onClick = {
                        richTextState.addParagraphStyle(
                            ParagraphStyle(
                                textAlign = TextAlign.Center
                            )
                        )
                    }) {
                        val isCenterAlign =
                            richTextState.currentParagraphStyle.textAlign == TextAlign.Center
                        Icon(
                            painterResource(id = R.drawable.ic_alight),
                            contentDescription = "Localized description",
                            modifier = Modifier,
                            tint = if (isCenterAlign) Color.Black else Color.LightGray
                        )
                    }


                    IconButton(onClick = {
                        richTextState.addParagraphStyle(
                            ParagraphStyle(
                                textAlign = TextAlign.Right
                            )
                        )
                    }) {
                        val isRightAlign =
                            richTextState.currentParagraphStyle.textAlign == TextAlign.Right
                        Icon(
                            painterResource(id = R.drawable.ic_align_right),
                            contentDescription = "Localized description",
                            modifier = Modifier,
                            tint = if (isRightAlign) Color.Black else Color.LightGray
                        )
                    }
                }
            }
            OutlinedRichTextEditor(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                state = richTextState,
                placeholder = {
                    Text(text = "Title \n type here..", color = Color.LightGray)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ThinkNoteTheme {
        DetailScreen(null, modifier = Modifier.fillMaxSize())
    }
}