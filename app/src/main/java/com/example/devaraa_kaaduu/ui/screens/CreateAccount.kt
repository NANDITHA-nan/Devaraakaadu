package com.example.devaraa_kaaduu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.devaraa_kaaduu.data.AppDatabase
import com.example.devaraa_kaaduu.data.User

@Composable
fun CreateAccount(navController: NavController) {

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "Create Account",
            fontSize = 32.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            placeholder = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                when {
                    username.isBlank() || password.isBlank() -> {
                        errorMessage = "Fill all fields"
                    }

                    password != confirmPassword -> {
                        errorMessage = "Passwords do not match"
                    }

                    !isValidPassword(password) -> {
                        errorMessage =
                            "Password must be 8+ chars with letters, numbers & special character"
                    }

                    else -> {
                        errorMessage = ""

                        CoroutineScope(Dispatchers.IO).launch {
                            db.userDao().insertUser(
                                User(
                                    name = username,
                                    email = username,   // keeping your current logic
                                    password = password
                                )
                            )
                        }

                        navController.navigate("home") {
                            popUpTo("signup") { inclusive = true }
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SIGN UP")
        }
    }
}