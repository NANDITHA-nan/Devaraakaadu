package com.example.devaraa_kaaduu.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.devaraa_kaaduu.R
import androidx.compose.ui.draw.alpha
import androidx.compose.foundation.shape.CircleShape

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(2500)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    val customFont = FontFamily(
        Font(R.font.playfair)
    )

    val alphaAnim by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(1500),
        label = ""
    )

    val gradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF00140C),
            Color(0xFF032418),
            Color(0xFF00140C)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 90.dp, bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.alpha(alphaAnim)
            ) {

                Box(
                    contentAlignment = Alignment.Center
                ) {

                    Box(
                        modifier = Modifier
                            .size(170.dp)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFFD4AF37).copy(alpha = 0.30f),
                                        Color.Transparent
                                    )
                                ),
                                CircleShape
                            )
                    )

                    Image(
                        painter = painterResource(id = R.drawable.tree),
                        contentDescription = null,
                        modifier = Modifier.size(72.dp),
                        colorFilter = ColorFilter.tint(Color(0xFFD8D19A))
                    )
                }

                Spacer(modifier = Modifier.height(42.dp))

                Text(
                    text = "DEVARA-KAADU",
                    fontFamily = customFont,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFFF4F4EE),
                    letterSpacing = 2.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "GUARDIANS OF THE GREEN TEMPLE",
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.55f),
                    letterSpacing = 5.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .width(220.dp)
                        .height(2.dp)
                        .background(
                            Color(0xFFD4AF37).copy(alpha = 0.45f)
                        )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "INITIALIZING SACRED GROVE",
                    fontSize = 10.sp,
                    color = Color.White.copy(alpha = 0.45f),
                    letterSpacing = 4.sp
                )
            }
        }
    }
}