package com.thinknote.app.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val lightScheme = lightColorScheme(
    primary = primaryColor,
    primaryContainer = primaryColor
)

@Composable
fun ThinkNoteTheme(
    content: @Composable() () -> Unit
) {


  MaterialTheme(
    colorScheme = lightScheme,
    typography = AppTypography,
    content = content
  )
}

