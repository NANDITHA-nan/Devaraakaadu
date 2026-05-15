package com.example.devaraa_kaaduu.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.animation.core.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.devaraa_kaaduu.R
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Park
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.filled.ArrowBack
import androidx.navigation.NavController
import com.example.devaraa_kaaduu.navigation.Routes


@Composable
fun HomeScreen(navController: NavController) {

    var searchText by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {

        // 🌄 BACKGROUND
        Image(
            painter = painterResource(id = R.drawable.bg_homescreen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.85f
        )

        // 🌑 DARK OVERLAYS
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xCC021E14))
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0x88000F08),
                            Color.Transparent,
                            Color(0xCC000F08)
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0xAA000000)
                        ),
                        radius = 900f
                    )
                )
        )

        // 📜 SCROLLABLE CONTENT
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // ✅ scroll
                .padding(16.dp)
                .padding(bottom = 100.dp) // ✅ prevents overlap with nav
        ) {

            Spacer(modifier = Modifier.height(30.dp))

            // 🔝 TOP BAR
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // 👈 LEFT SIDE (BACK + TITLE)
                Row(verticalAlignment = Alignment.CenterVertically) {

                    IconButton(
                        onClick = {
                            navController.navigate("login") {
                                popUpTo("home") { inclusive = true }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        Icons.Outlined.Park,
                        contentDescription = null,
                        tint = Color(0xFFB5E48C)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        "DEVARA-KAADU",
                        color = Color.White,
                        fontSize = 16.sp,
                        letterSpacing = 2.sp,
                        fontFamily = FontFamily.Serif
                    )
                }

                // 👉 RIGHT SIDE (PROFILE)
                Box(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Routes.PROFILE)
                        }
                        .border(
                            1.dp,
                            Color(0xFFB5E48C),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        "Profile",
                        color = Color(0xFFB5E48C),
                        fontFamily = FontFamily.Serif
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            // 🌿 TITLE
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(modifier = Modifier.weight(1f)) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // ✨ LEFT LINE
                        Box(
                            modifier = Modifier
                                .width(32.dp)
                                .height(1.dp)
                                .background(Color(0xFFB5E48C).copy(alpha = 0.7f))
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        // 📝 TEXT
                        Text(
                            "THE SACRED CONNECTION",
                            color = Color(0xFFB5E48C),
                            fontSize = 10.sp,
                            letterSpacing = 2.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Explore Sacred and",
                        color = Color.White,
                        fontFamily = FontFamily.Serif,
                        fontSize = 20.sp
                    )

                    Text(
                        "Silent Groves",
                        color = Color(0xFFB5E48C),
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 24.sp
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_badge),
                    contentDescription = null,
                    modifier = Modifier
                        .offset(y = 24.dp)
                        .size(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(23.dp))

            // 📊 STATS
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem("1,248", "PROTECTED GROVES", Icons.Outlined.Park)
                StatItem("852", "ANCIENT SPECIES", Icons.Outlined.Eco)
                StatItem("3,412", "GROVE GUARDIANS", Icons.Outlined.Groups)
            }

            Spacer(modifier = Modifier.height(25.dp))

            // 🌈 CARDS
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {

                Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    PremiumCard(
                        title = "Grove Directory",
                        color = Color(0xFF1B4332),
                        icon = Icons.Outlined.Map,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            navController.navigate("grove_directory")
                        }
                    )
                    PremiumCard(
                        title = "Species Scan",
                        color = Color(0xFFFFC107),
                        icon = Icons.Default.CameraAlt,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            navController.navigate(Routes.SPECIES_INTRO)
                        }
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    PremiumCard(
                        title = "Myths & Legends",
                        color = Color(0xFF2979FF),
                        icon = Icons.AutoMirrored.Filled.MenuBook,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            navController.navigate(Routes.MYTH)
                        }
                    )
                    PremiumCard(
                        title = "Report Issue",
                        color = Color(0xFFD50000),
                        icon = Icons.Default.Warning,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            navController.navigate(Routes.REPORT_ISSUE)
                        }
                    )
                }
            }
        }

        // 🔻 FIXED BOTTOM NAV
        // 🔻 FIXED BOTTOM NAV
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFD9FFB5),
                                Color.Transparent
                            ),
                            radius = 120f
                        ),
                        CircleShape
                    )
            )

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        Color(0xFFD9FFB5),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Outlined.Park,
                    contentDescription = null,
                    tint = Color(0xFF0A1F14),
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

@Composable
fun PremiumCard(
        title: String,
        color: Color,
        icon: ImageVector,
        modifier: Modifier,
        onClick: () -> Unit   // ✅ ADD THIS
    ) {

        var pressed by remember { mutableStateOf(false) }

        val scale by animateFloatAsState(
            targetValue = if (pressed) 0.96f else 1f,
            label = ""
        )

        Box(
            modifier = modifier
                .height(160.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
                .clip(RoundedCornerShape(24.dp))
                .background(
                    Brush.linearGradient(
                        listOf(
                            color.copy(alpha = 0.45f),
                            Color.Transparent
                        )
                    )
                )
                .border(1.dp, Color.White.copy(0.15f), RoundedCornerShape(24.dp))
                .clickable {
                    pressed = !pressed
                    onClick()
                }
                .padding(16.dp),
        ) {
            Column {

                // 🌟 ICON GLOW
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .background(
                            Brush.radialGradient(
                                listOf(color.copy(alpha = 0.7f), Color.Transparent)
                            ),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = Color.White)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    title,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    "DISCOVER",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    letterSpacing = 1.sp
                )
            }
        }
    }


@Composable
fun Stat(number: String, label: String) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(number, color = Color.White, fontWeight = FontWeight.Bold)
            Text(label, color = Color.Gray, fontSize = 12.sp)
        }
    }

@Composable
fun StatItem(number: String, label: String, icon: ImageVector) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 🌿 ICON
            Icon(
                icon,
                contentDescription = null,
                tint = Color(0xFFB5E48C),
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 🔢 NUMBER
            Text(
                number,
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(6.dp))

            // 📝 LABEL
            Text(
                label,
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 10.sp,
                letterSpacing = 2.sp
            )
        }
    }