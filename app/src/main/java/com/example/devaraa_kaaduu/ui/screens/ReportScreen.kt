package com.example.devaraa_kaaduu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ReportScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("Report Issue")

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Describe issue") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { }) {
            Text("Submit")
        }
    }
}