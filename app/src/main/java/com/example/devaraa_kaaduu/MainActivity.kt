package com.example.devaraa_kaaduu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.devaraa_kaaduu.navigation.NavGraph
import com.example.devaraa_kaaduu.ui.theme.DevaraaKaaduuTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // App Theme (auto-created by Android Studio)
            DevaraaKaaduuTheme {

                // Surface gives proper background
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph()
                }
            }
        }
    }
}