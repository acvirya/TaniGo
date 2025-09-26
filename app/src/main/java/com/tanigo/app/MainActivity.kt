package com.tanigo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tanigo.app.ui.theme.TaniGoTheme
import com.tanigo.app.navigation.NavGraph
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaniGoTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        NavGraph()
    }
}

@Preview(showBackground = true)
@Composable
fun AppContentPreview() {
    TaniGoTheme {
        AppContent()
    }
}