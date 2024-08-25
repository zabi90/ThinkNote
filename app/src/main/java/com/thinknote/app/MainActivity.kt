package com.thinknote.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thinknote.app.ui.theme.ThinkNoteTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThinkNoteTheme() {
                AppNavigator.App(modifier = Modifier.fillMaxSize())
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ThinkNoteTheme {
        AppNavigator.App(modifier = Modifier.fillMaxSize())
    }
}