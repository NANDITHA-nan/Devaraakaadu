package com.example.devaraa_kaaduu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.devaraa_kaaduu.navigation.Routes
import com.example.devaraa_kaaduu.ui.theme.Playfair

@Composable
fun SpeciesIntroScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF00160D),
                        Color(0xFF021D12),
                        Color(0xFF031108)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(34.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0x660E2A18),
                            Color(0x33091A10)
                        )
                    )
                )
                .border(
                    1.dp,
                    Color.White.copy(alpha = 0.08f),
                    RoundedCornerShape(34.dp)
                )
                .padding(28.dp)
        ) {

            Column {

                Box(
                    modifier = Modifier
                        .size(62.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .border(
                            1.dp,
                            Color.White.copy(alpha = 0.08f),
                            RoundedCornerShape(18.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Outlined.CameraAlt,
                        null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Species Scan",
                    color = Color.White,
                    fontFamily = Playfair,
                    fontSize = 42.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Unveil the secrets of rare flora and fauna through advanced vision.",
                    color = Color(0xFF9AB29F),
                    fontSize = 18.sp,
                    lineHeight = 28.sp
                )

                Spacer(modifier = Modifier.height(26.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.08f))
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Routes.SPECIES_SCAN)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "DISCOVER",
                        color = Color(0xFFDCECCF),
                        letterSpacing = 3.sp,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        Icons.Outlined.KeyboardArrowRight,
                        null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}