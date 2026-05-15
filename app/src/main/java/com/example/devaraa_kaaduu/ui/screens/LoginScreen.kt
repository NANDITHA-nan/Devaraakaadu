package com.example.devaraa_kaaduu.ui.screens

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.devaraa_kaaduu.R
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.border
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.clickable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.devaraa_kaaduu.data.AppDatabase
import com.example.devaraa_kaaduu.data.User

@Composable
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    var authMode by remember { mutableStateOf("login") }

    val googleSignInClient = remember {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if (result.resultCode == Activity.RESULT_OK) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

            try {
                val account = task.getResult(ApiException::class.java)

                Log.d("LOGIN", "Google success: ${account.email}")

                CoroutineScope(Dispatchers.IO).launch {
                    db.userDao().logoutAll()

                    db.userDao().insertUser(
                        User(
                            name = account.displayName ?: "Google User",
                            email = account.email ?: "",
                            password = "",
                            isLoggedIn = true
                        )
                    )
                }

                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }

            } catch (e: ApiException) {
                Log.e("LOGIN", "Google failed", e)
                errorMessage = "Google Sign-In failed"
            }
        } else {
            errorMessage = "Google Sign-In cancelled"
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.bg_login6),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xCC021E14),
                            Color(0xAA021E14),
                            Color(0xDD021E14)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                "Welcome!",
                fontSize = 34.sp,
                color = Color.White,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("Username") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFB5E48C),
                    unfocusedBorderColor = Color.White.copy(0.5f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color(0xFFB5E48C),
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                singleLine = true,
                visualTransformation = if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFB5E48C),
                    unfocusedBorderColor = Color.White.copy(0.5f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color(0xFFB5E48C),
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFFB5E48C),
                            uncheckedColor = Color.White
                        )
                    )

                    Text(
                        text = "Remember Me",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 12.sp
                    )
                }

                Text(
                    text = "Forgot Password?",
                    color = Color(0xFFB5E48C),
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (errorMessage.isNotEmpty()) {
                Text(errorMessage, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                    when {
                        username.isBlank() || password.isBlank() -> {
                            errorMessage = "Enter username & password"
                        }

                        !isValidPassword(password) -> {
                            errorMessage =
                                "Password must be 8+ chars with letters, numbers & special character"
                        }

                        username == "admin" && password == "Admin@123" -> {
                            errorMessage = ""

                            CoroutineScope(Dispatchers.IO).launch {
                                db.userDao().logoutAll()

                                db.userDao().insertUser(
                                    User(
                                        name = "Admin",
                                        email = "admin",
                                        password = password,
                                        isLoggedIn = true
                                    )
                                )
                            }

                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        }

                        else -> {
                            CoroutineScope(Dispatchers.IO).launch {
                                val user = db.userDao().login(username, password)

                                withContext(Dispatchers.Main) {
                                    if (user != null) {

                                        db.userDao().logoutAll()

                                        db.userDao().updateUser(
                                            user.copy(isLoggedIn = true)
                                        )

                                        navController.navigate("home") {
                                            popUpTo("login") { inclusive = true }
                                        }

                                    } else {
                                        errorMessage = "Invalid credentials"
                                    }
                                }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0B3D2E)
                )
            ) {
                Text(
                    "LOGIN",
                    color = Color.White,
                    letterSpacing = 2.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.2f))
                )

                Text(
                    text = "OR",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.2f))
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = {
                    val intent = googleSignInClient.signInIntent
                    launcher.launch(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.dp, Color.White.copy(alpha = 0.3f)),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = "G",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Sign in with Google",
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Don't have an account?",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 12.sp
                )

                Text(
                    text = "Create Account",
                    color = Color(0xFFB5E48C),
                    fontSize = 12.sp,
                    letterSpacing = 1.sp,
                    modifier = Modifier.clickable {
                        navController.navigate("createaccount")
                    }
                )
            }
        }
    }
}

fun isValidPassword(password: String): Boolean {
    val regex = Regex("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$")
    return password.matches(regex)
}