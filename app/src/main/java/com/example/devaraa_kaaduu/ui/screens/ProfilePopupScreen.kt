package com.example.devaraa_kaaduu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.example.devaraa_kaaduu.data.AppDatabase
import com.example.devaraa_kaaduu.data.User
import kotlinx.coroutines.*
import androidx.compose.material3.IconButton

@Composable
fun ProfilePopupScreen(navController: NavController) {

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)

    var user by remember { mutableStateOf<User?>(null) }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            user = db.userDao().getLoggedInUser()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xFF00160D),
                            Color(0xFF082817)
                        )
                    ),
                    RoundedCornerShape(28.dp)
                )
                .border(
                    1.dp,
                    Color.White.copy(alpha = 0.08f),
                    RoundedCornerShape(28.dp)
                )
                .padding(24.dp)
        ) {

            Column {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.Outlined.Close,
                            contentDescription = "Close",
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Icon(
                    Icons.Outlined.Person,
                    null,
                    tint = Color(0xFFD8D19A),
                    modifier = Modifier.size(60.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    user?.name ?: "Guest",
                    color = Color.White,
                    fontSize = 28.sp
                )

                Text(
                    "SENTINEL LEVEL 1",
                    color = Color(0xFFD8D19A),
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(28.dp))

                ProfileRow(
                    Icons.Outlined.Email,
                    "EMAIL DETAILS",
                    user?.email ?: "No email"
                )

                Spacer(modifier = Modifier.height(20.dp))

                ProfileRow(
                    Icons.Outlined.Shield,
                    "ACCESS LEVEL",
                    user?.accessLevel ?: "Standard User"
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            db.userDao().logoutAll()
                        }

                        navController.navigate("login")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(Icons.Outlined.Logout, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("SIGN OUT")
                }
            }
        }
    }
}

@Composable
fun ProfileRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Icon(icon, null, tint = Color.Gray)

        Spacer(modifier = Modifier.width(14.dp))

        Column {
            Text(title, color = Color.Gray, fontSize = 11.sp)
            Text(value, color = Color.White, fontSize = 16.sp)
        }
    }
}