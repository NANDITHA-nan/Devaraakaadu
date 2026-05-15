package com.example.devaraa_kaaduu.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.devaraa_kaaduu.data.groveList
import com.example.devaraa_kaaduu.ui.components.GroveCard

@Composable
fun GroveListScreen(navController: NavController) {
    LazyColumn {
        items(groveList) { grove ->
            GroveCard(grove) {
                navController.navigate("detail/${grove.name}")
            }
        }
    }
}