package com.example.devaraa_kaaduu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.clickable
import com.example.devaraa_kaaduu.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Map
import androidx.navigation.NavController
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.border
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.draw.shadow
import androidx.compose.animation.core.*
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.delay
import androidx.compose.ui.draw.alpha
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.text.TextStyle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.CameraAlt
import com.example.devaraa_kaaduu.navigation.Routes

// 🔝 TOP OF FILE (after imports)

val premiumFont = FontFamily(
    Font(R.font.playfair_display_regular)
)
@Composable
fun GroveDirectoryScreen(navController: NavController) {

    var selectedTab by remember { mutableStateOf("ALL") }
    var searchText by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    // 🌿 PREMIUM BACKGROUND
    val bgGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF021E14),
            Color(0xFF03291F),
            Color(0xFF064E3B),
            Color(0xFF021E14)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgGradient)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            Spacer(modifier = Modifier.height(6.dp))

            // 🔙 TOP BAR
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White.copy(alpha = 0.85f),
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { navController.popBackStack() }
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Sacred Grove Directory",
                    color = Color(0xFFE6F4EA),
                    fontSize = 20.sp,
                    fontFamily = premiumFont,
                    letterSpacing = 0.5.sp
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF0B3D2E))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // 🔍 SEARCH ICON
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.6f),
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(modifier = Modifier.fillMaxWidth()) {

                        BasicTextField(
                            value = searchText,
                            onValueChange = { newText -> searchText = newText },
                            singleLine = true,
                            textStyle = TextStyle(color = Color.White, fontSize = 12.sp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        if (searchText.isEmpty()) {
                            Text(
                                "Search by name or region...",
                                color = Color.White.copy(alpha = 0.5f),
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
            // 🟢 FILTER CHIPS
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("ALL", "KAAVU", "BANA", "SARNA", "OTHERS").forEach {
                    FilterChip(
                        text = it,
                        selected = selectedTab == it,
                        onClick = { selectedTab = it }
                    )
                }
            }

            val matchesCategory: (String) -> Boolean = { category ->
                when (selectedTab) {
                    "ALL" -> true
                    "KAAVU" -> category == "KAAVU"
                    "BANA" -> category == "BANA"
                    "SARNA" -> category == "SARNA"
                    "OTHERS" -> category != "KAAVU" &&
                            category != "BANA" &&
                            category != "SARNA"

                    else -> true
                }
            }

            val hasResults =
                (
                        matchesCategory("KAAVU") &&
                                "Iruppu Sacred Grove Kodagu Karnataka".contains(searchText, true)
                        ) ||
                        (
                                matchesCategory("DEVRAI") &&
                                        "Tamhini Maharashtra".contains(searchText, true)
                                ) ||
                        (
                                matchesCategory("ORANS") &&
                                        "Khajurli Rajasthan".contains(searchText, true)
                                ) ||
                        (
                                matchesCategory("KAAVU") &&
                                        "Nagarahole Kodagu".contains(searchText, true)
                                ) ||
                        (
                                matchesCategory("BANA") &&
                                        "Hulikal Shivamogga".contains(searchText, true)
                                ) ||
                        (
                                matchesCategory("SARNA") &&
                                        "Sarna Jharkhand".contains(searchText, true)
                                ) ||
                        searchText.isEmpty()

            // 🌿 GROVE CARDS (WITH STAGGER ANIMATION)
            if (!hasResults) {

                // ❌ SHOW EMPTY STATE
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No results found",
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 14.sp
                    )
                }

            } else {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                    if (
                        matchesCategory("KAAVU") &&
                        (
                                searchText.isEmpty() ||
                                        "Iruppu Sacred Grove".contains(searchText, true) ||
                                        "Kodagu, Karnataka".contains(searchText, true)
                                )
                    )
                        GroveCard(
                            index = 0,
                            title = "Iruppu Sacred Grove",
                            location = "Kodagu, Karnataka",
                            distance = "200.3 km",
                            description = "Managed by the Iruppu temple, this grove is home to the stunning Iruppu Falls and diverse evergreen flora.",
                            image = R.drawable.bg_kodagu,
                            category = "KAAVU",
                            navController = navController
                        )


                    if (
                        matchesCategory("DEVRAI") &&
                        (
                                searchText.isEmpty() ||
                                        "Tamhini Ghat Devrai".contains(searchText, true) ||
                                        "Maharashtra".contains(searchText, true)
                                )
                    )
                        GroveCard(
                            index = 1,
                            title = "Tamhini Ghat Devrai",
                            location = "Pune, Maharashtra",
                            distance = "180.5 km",
                            description = "A critical corridor in the Western Ghats, preserving several endemic tree species and providing a sanctuary for the Giant Squirrel.",
                            image = R.drawable.bg_tamhinighat,
                            category = "DEVRAI",
                            navController = navController
                        )

                    if (
                        matchesCategory("ORANS") &&
                        (
                                searchText.isEmpty() ||
                                        "Khajurli Oran".contains(searchText, true) ||
                                        "Rajasthan".contains(searchText, true)
                                )
                    )
                        GroveCard(
                            index = 2,
                            title = "Khajurli Oran",
                            location = "Jaisalmer, Rajasthan",
                            distance = "1892.3 km",
                            description = "Community-protected desert grove centered around a localized water source, vital for local pastoralists and biodiversity.",
                            image = R.drawable.bg_oran, // 👈 add your image in drawable
                            category = "ORANS",
                            navController = navController
                        )

                    if (
                        matchesCategory("KAAVU") &&
                        (
                                searchText.isEmpty() ||
                                        "Nagarahole Devara Kaadu".contains(searchText, true) ||
                                        "Kodagu, Karnataka".contains(searchText, true)
                                )
                    )
                        GroveCard(
                            index = 3,
                            title = "Nagarahole Devara Kaadu",
                            location = "Kodagu District, Karnataka",
                            distance = "183.9 km",
                            description = "Part of the Western Ghats biodiversity hotspot, this sacred grove is one of the richest regions in India supporting diverse wildlife.",
                            image = R.drawable.bg_nagarahole1,
                            category = "KAAVU",
                            navController = navController
                        )

                    if (
                        matchesCategory("BANA") &&
                        (
                                searchText.isEmpty() ||
                                        "Hulikal Bana".contains(searchText, true) ||
                                        "Shivamogga".contains(searchText, true)
                                )
                    )
                        GroveCard(
                            index = 4,
                            title = "Hulikal Bana",
                            location = "Shivamogga District, Karnataka",
                            distance = "282.0 km",
                            description = "A small village grove maintained through local rituals. It supports local biodiversity and plays a key ecological role.",
                            image = R.drawable.bg_hulikal,
                            category = "BANA",
                            navController = navController
                        )

                    if (
                        matchesCategory("SARNA") &&
                        (
                                searchText.isEmpty() ||
                                        "Sarna Sacred Groves".contains(searchText, true) ||
                                        "Jharkhand".contains(searchText, true)
                                )
                    )
                        GroveCard(
                            index = 5,
                            title = "Sarna Sacred Groves",
                            location = "Jharkhand",
                            distance = "1412.8 km",
                            description = "Dominated by Sal forests, these groves are vital biodiversity refuges in central India and are protected by tribal communities.",
                            image = R.drawable.bg_sarna,
                            category = "SARNA",
                            navController = navController
                        )

                }
            }

            Spacer(modifier = Modifier.height(90.dp))
        }

        // 🔻 FLOATING ACTION BUTTONS
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // 🗺️ MAP BUTTON (UPDATED)
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1B4332))
                    .clickable {
                        navController.navigate("map_screen")   // ✅ ADDED
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Outlined.Map,
                    contentDescription = "Map",
                    tint = Color.White
                )
            }
        }
    }
}
@Composable
fun FilterChip(text: String, selected: Boolean, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (selected) Color(0xFFB5E48C)
                else Color.White.copy(alpha = 0.1f)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text,
            color = if (selected) Color.Black else Color.White,
            fontSize = 12.sp
        )
    }
}
@Composable
fun GroveCard(
    index: Int,
    title: String,
    location: String,
    distance: String,
    description: String,
    image: Int,
    category: String,
    navController: NavController
) {

    var pressed by remember { mutableStateOf(false) }

    val visible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(index * 150L)
        visible.value = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (visible.value) 1f else 0f,
        animationSpec = tween(500),
        label = ""
    )

    val offsetY by animateDpAsState(
        targetValue = if (visible.value) 0.dp else 40.dp,
        animationSpec = tween(500),
        label = ""
    )

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.97f else 1f,
        label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = offsetY)
            .alpha(alpha)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(RoundedCornerShape(26.dp))
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(26.dp),
                ambientColor = Color.Transparent,
                spotColor = Color.Transparent
            )
            .background(
                Color.White.copy(alpha = 0.04f)
            )
            .border(
                1.dp,
                Color.White.copy(alpha = 0.08f),
                RoundedCornerShape(26.dp)
            )
            .clickable { pressed = !pressed }
    ) {

        Column {

            // 🌿 IMAGE SECTION
            Box {

                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                )

                // 🌑 PREMIUM OVERLAY
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color(0xFF021E14).copy(alpha = 0.4f),
                                    Color(0xFF021E14).copy(alpha = 0.75f)
                                )
                            )
                        )
                )

                // 🟢 CATEGORY CHIP
                Box(
                    modifier = Modifier
                        .padding(14.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFF1B4332).copy(alpha = 0.85f))
                        .border(
                            1.dp,
                            Color.White.copy(alpha = 0.1f),
                            RoundedCornerShape(50)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        category,
                        color = Color(0xFFB5E48C),
                        fontSize = 10.sp,
                        letterSpacing = 2.sp
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {

                // 🔥 TITLE + DISTANCE
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        title,
                        color = Color(0xFFE6F4EA),
                        fontSize = 21.sp,
                        letterSpacing = 0.5.sp,
                        fontFamily = premiumFont
                    )

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFF1B4332).copy(alpha = 0.7f))
                            .border(
                                1.dp,
                                Color.White.copy(alpha = 0.08f),
                                RoundedCornerShape(50)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            Icons.Outlined.NearMe,
                            contentDescription = null,
                            tint = Color(0xFFB5E48C),
                            modifier = Modifier.size(12.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            distance,
                            color = Color(0xFFB5E48C),
                            fontSize = 10.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // 📍 LOCATION
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(
                        Icons.Outlined.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFFB5E48C),
                        modifier = Modifier.size(14.dp)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        location,
                        color = Color(0xFFB7C9BE),
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    description,
                    color = Color(0xFFA8BDB2),
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 🔘 PREMIUM BUTTON
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(50))
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF0B3D2E),
                                    Color(0xFF145A32),
                                    Color(0xFF1B4332)
                                )
                            )
                        )
                        .border(
                            1.dp,
                            Color.White.copy(alpha = 0.06f),
                            RoundedCornerShape(50)
                        )
                        .clickable {                  // ✅ ADD CLICK HERE
                            navController.navigate("detail/$title")
                        }
                        .padding(vertical = 14.dp, horizontal = 18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                        Text(
                            "VIEW SACRED DETAILS",
                            color = Color(0xFFB5E48C),
                            fontSize = 12.sp,
                            letterSpacing = 2.2.sp
                        )

                        Icon(
                            Icons.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color(0xFFB5E48C),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
