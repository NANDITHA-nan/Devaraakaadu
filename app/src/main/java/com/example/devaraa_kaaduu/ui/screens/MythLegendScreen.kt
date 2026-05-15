package com.example.devaraa_kaaduu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.devaraa_kaaduu.ui.theme.Playfair
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.clickable
import com.example.devaraa_kaaduu.navigation.Routes

@Composable
fun MythLegendScreen(
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF021B12),
                        Color(0xFF03150F),
                        Color.Black
                    )
                )
            )
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(120.dp)
                .background(
                    Color(0xFF0B5D3B).copy(alpha = 0.15f)
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .size(54.dp)
                    .border(
                        1.dp,
                        Color(0xFF335C4A),
                        CircleShape
                    )
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        Color(0xFF1A4D3A),
                        RoundedCornerShape(32.dp)
                    )
                    .padding(34.dp)
            ) {

                Column {

                    Box(
                        modifier = Modifier
                            .size(78.dp)
                            .border(
                                1.dp,
                                Color(0xFF335C4A),
                                RoundedCornerShape(22.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "📖",
                            fontSize = 28.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "Myth & Legend",
                        color = Color(0xFFF5F5F5),
                        fontFamily = Playfair,
                        fontSize = 38.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "Explore the ancient oral traditions and sacred mysteries of the grove.",
                        color = Color(0xFF9CB8AA),
                        fontSize = 18.sp,
                        lineHeight = 32.sp
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(
                                Color(0xFF244536)
                            )
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(Routes.LORE)
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "VIEW LORE",
                                color = Color(0xFFDCECCF),
                                letterSpacing = 3.sp,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = "›",
                                color = Color.White,
                                fontSize = 28.sp
                            )
                        }
                    }
                }
            }
        }
    }
}