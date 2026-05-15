package com.example.devaraa_kaaduu.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.devaraa_kaaduu.model.Grove
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.text.font.FontWeight
import com.example.devaraa_kaaduu.ui.theme.Playfair
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.platform.LocalContext
import com.example.devaraa_kaaduu.utils.calculateDistance
import com.google.android.gms.location.LocationServices
import com.google.maps.android.compose.*
import com.google.android.gms.maps.model.*
import androidx.compose.ui.draw.clip
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.border
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
@SuppressLint("MissingPermission")
@Composable
fun GroveDetailScreen(grove: Grove, navController: NavController) {

    val context = LocalContext.current

    var distanceText by remember { mutableStateOf("...") }

    val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context)

    // 🔥 LOCATION
    LaunchedEffect(Unit) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                distanceText = calculateDistance(
                    it.latitude,
                    it.longitude,
                    grove.lat,
                    grove.lng
                )
            }
        }
    }

    // 🔥 FIXED: animation state MUST be here
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    // 🔥 FIXED: animation wraps UI
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(1000))
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            // 🌄 TOP IMAGE (FIXED)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {

                Image(
                    painter = painterResource(id = grove.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color(0xFF062B1F),
                                    Color(0xFF041E14)
                                ),
                                startY = 300f
                            )
                        )
                )

                // 🔙 BACK BUTTON
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(16.dp)
                        .size(42.dp)
                        .background(Color.White.copy(alpha = 0.1f), CircleShape)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                // 🌿 TITLE ON IMAGE
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {

                    Text(
                        text = "${grove.type.uppercase()} SANCTUARY",
                        color = Color(0xFFB5E48C),
                        fontSize = 11.sp,
                        letterSpacing = 3.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(20))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = grove.name,
                        color = Color(0xFFF1F5F2),
                        fontSize = 30.sp,
                        fontFamily = Playfair
                    )
                }
            }

            // 📜 SCROLLABLE CONTENT
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.7f),
                        modifier = Modifier.size(14.dp)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = grove.location,
                        color = Color(0xFFB7C9BE),
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "$distanceText from you",
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                // ==============================
// HISTORY & IMPORTANCE
// ==============================

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(1.dp)
                            .background(Color.White.copy(alpha = 0.3f))
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "History & Importance",
                        color = Color(0xFFF1F5F2),
                        fontFamily = Playfair,
                        fontSize = 22.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text =
                        when {

                            grove.name.contains("Iruppu") -> {
                                "Managed by the Iruppu temple, this grove is home to the stunning Iruppu Falls and diverse evergreen flora. It is part of the Western Ghats biodiversity hotspot, preserving ancient traditions and rare species."
                            }

                            grove.name.contains("Tamhini") -> {
                                "A critical corridor in the Western Ghats, preserving several endemic tree species and providing a sanctuary for the Giant Squirrel."
                            }

                            grove.name.contains("Khajurli") -> {
                                "Community-protected desert grove centered around a localized water source, vital for local pastoralists and wildlife. These species are well adapted to arid desert conditions."
                            }

                            grove.name.contains("Hulikal") -> {
                                "A small village grove maintained through local rituals. It supports local biodiversity in village ecosystems and acts as a mini biodiversity hotspot in the rural landscape."
                            }

                            grove.name.contains("Sarna") -> {
                                "Dominated by Sal forests, these groves are vital biodiversity refuges in central India. They are protected by the Sarna religion and tribal communities as sacred spaces for rituals and nature worship."
                            }

                            else -> {
                                "Part of the Western Ghats biodiversity hotspot, this sacred grove is one of the richest regions in India. It supports large wildlife populations and preserves ancient forest ecosystems through community protection."
                            }
                        },

                    color = Color(0xFFB7D6C2),
                    fontSize = 16.sp,
                    lineHeight = 34.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(35.dp))

                // ==============================
// LOCATION PREVIEW
// ==============================

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "LOCATION PREVIEW",
                    color = Color(0xFF8FAF9B),
                    fontSize = 12.sp,
                    letterSpacing = 3.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                GoogleMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(RoundedCornerShape(28.dp)),

                    cameraPositionState = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(
                            LatLng(grove.lat, grove.lng),
                            12f
                        )
                    }
                ) {

                    Marker(
                        state = MarkerState(
                            position = LatLng(grove.lat, grove.lng)
                        ),
                        title = grove.name
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))


// ==============================
// SPECIES BIODIVERSITY
// ==============================

                val treeSpecies =
                    when {

                        grove.name.contains("Iruppu") -> {
                            listOf("Indian mahogany", "Ebony", "Black dammar", "Teak")
                        }

                        grove.name.contains("Tamhini") -> {
                            listOf("Teak", "Ain", "Hirda", "Jamun")
                        }

                        grove.name.contains("Khajurli") -> {
                            listOf("Khejri", "Neem", "Babool")
                        }

                        grove.name.contains("Hulikal") -> {
                            listOf("Banyan", "Neem", "Peepal", "Honne")
                        }

                        grove.name.contains("Sarna") -> {
                            listOf("Sal", "Mahua", "Neem")
                        }

                        else -> {
                            listOf("Rosewood", "Sandalwood")
                        }
                    }

                val floraSpecies =
                    when {

                        grove.name.contains("Iruppu") -> {
                            listOf("Orchids", "Ferns")
                        }

                        grove.name.contains("Tamhini") -> {
                            listOf("Mosses", "Climbers", "Medicinal Herbs")
                        }

                        grove.name.contains("Khajurli") -> {
                            listOf("Desert Shrubs", "Medicinal Plants", "Grasses")
                        }

                        grove.name.contains("Hulikal") -> {
                            listOf("Shrubs & Grasses", "Medicinal Herbs")
                        }

                        grove.name.contains("Sarna") -> {
                            listOf("Grasses", "Shrubs", "Medicinal Herbs")
                        }

                        else -> {
                            listOf("Medicinal Herbs", "Shrubs & Climbers")
                        }
                    }

                val faunaSpecies =
                    when {

                        grove.name.contains("Iruppu") -> {
                            listOf("Hornbills", "Macaques")
                        }

                        grove.name.contains("Tamhini") -> {
                            listOf("Malabar Giant Squirrel", "Langurs", "Frogs", "Butterflies")
                        }

                        grove.name.contains("Khajurli") -> {
                            listOf("Blackbuck", "Chinkara", "Peacocks", "Desert Fox")
                        }

                        grove.name.contains("Hulikal") -> {
                            listOf("Small Mammals", "Birds", "Reptiles", "Insects")
                        }

                        grove.name.contains("Sarna") -> {
                            listOf("Birds", "Insects", "Small Mammals")
                        }

                        else -> {
                            listOf("Tiger", "Hornbills")
                        }
                    }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    ChipColumn("TREES", treeSpecies)

                    ChipColumn("PLANTS & FLORA", floraSpecies)

                    ChipColumn("FAUNA & BIRDS", faunaSpecies)

                }

                Spacer(modifier = Modifier.height(30.dp))


// ==============================
// WATER SOURCES
// ==============================

                InfoBlock(
                    title = "WATER SOURCES",
                    icon = "💧",
                    text =
                        when {

                            grove.name.contains("Iruppu") -> {
                                "Lakshmana Tirtha River originates here; Iruppu Falls; Natural streams and groundwater recharge."
                            }

                            grove.name.contains("Tamhini") -> {
                                "Seasonal streams and waterfalls (especially during monsoon); Natural springs within forest patches; Contributes to local river systems and groundwater recharge."
                            }

                            grove.name.contains("Khajurli") -> {
                                "Rainwater-dependent ecosystem; Small ponds (seasonal); Groundwater recharge through tree cover."
                            }

                            grove.name.contains("Hulikal") -> {
                                "Small ponds within or near the grove; Rain-fed water collection; Helps in groundwater recharge."
                            }

                            grove.name.contains("Sarna") -> {
                                "Seasonal streams; Rain-fed water systems; Helps in groundwater recharge."
                            }

                            else -> {
                                "Kabini River and its tributaries; forest streams and wetlands; groundwater recharge zones."
                            }
                        }
                )

                Spacer(modifier = Modifier.height(24.dp))


// ==============================
// ECOLOGICAL IMPORTANCE
// ==============================

                InfoBlock(
                    title = "ECOLOGICAL IMPORTANCE",
                    icon = "🌍",
                    text =
                        when {

                            grove.name.contains("Iruppu") -> {
                                "Part of the Western Ghats biodiversity hotspot; Maintains micro-climate and humidity; Supports wildlife corridors in Brahmagiri Sanctuary."
                            }

                            grove.name.contains("Tamhini") -> {
                                "Maintains high biodiversity including rare flora and fauna; Acts as a micro-climate regulator; Prevents soil erosion; Supports pollinators."
                            }

                            grove.name.contains("Khajurli") -> {
                                "Prevents soil erosion and desertification; Supports desert biodiversity and wildlife; Maintains soil fertility in dry regions."
                            }

                            grove.name.contains("Hulikal") -> {
                                "Prevents soil erosion in rural areas; Maintains local micro-climate; Supports native plant and animal species."
                            }

                            grove.name.contains("Sarna") -> {
                                "Conserves native forest species; Maintains soil fertility; Prevents erosion; Supports tribal biodiversity."
                            }

                            else -> {
                                "One of the richest biodiversity regions in India.Supports large wildlife populations and food chains."
                            }
                        }
                )

                Spacer(modifier = Modifier.height(24.dp))


// ==============================
// MYTH & BELIEF
// ==============================

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color(0xFF1B4D3E),
                            shape = RoundedCornerShape(28.dp)
                        )
                        .padding(24.dp)
                ) {

                    InfoBlock(
                        title = "MYTH & BELIEF",
                        icon = "📖",
                        text =
                            when {

                                grove.name.contains("Iruppu") -> {
                                    "Associated with Ramayana legend; Lord Lakshmana created the river by shooting an arrow into the hills. The water is believed to cleanse sins."
                                }

                                grove.name.contains("Tamhini") -> {
                                    "Known as Devrai (God's forest), protected by villagers. Disturbing the grove is considered a sin."
                                }

                                grove.name.contains("Khajurli") -> {
                                    "Protected by Bishnoi community traditions. Cutting trees or harming animals is strictly forbidden."
                                }

                                grove.name.contains("Hulikal") -> {
                                    "Dedicated to local village deity. Rituals and festivals are performed regularly. Cutting trees is prohibited."
                                }

                                grove.name.contains("Sarna") -> {
                                    "Sacred to tribal communities (Sarna religion). Trees are worshipped and protected."
                                }

                                else -> {
                                    "Considered sacred and protected by local communities."
                                }
                            }
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))


// ==============================
// SCIENTIFIC FACTS
// ==============================

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color(0xFF1B4D3E),
                            shape = RoundedCornerShape(28.dp)
                        )
                        .padding(24.dp)
                ) {

                    Column {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "01\n10",
                                color = Color(0xFFB5E48C),
                                fontSize = 10.sp,
                                lineHeight = 10.sp
                            )

                            Spacer(modifier = Modifier.width(14.dp))

                            Text(
                                text = "Scientific Facts",
                                color = Color(0xFFF1F5F2),
                                fontFamily = Playfair,
                                fontSize = 20.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(28.dp))

                        when {

                            grove.name.contains("Iruppu") -> {
                                ScientificBullet("Part of tropical evergreen forests of Western Ghats")
                                ScientificBullet("Sacred groves act as genetic reservoirs")
                                ScientificBullet("Helps in soil conservation")
                                ScientificBullet("Maintains biodiversity")
                            }

                            grove.name.contains("Tamhini") -> {
                                ScientificBullet("Semi-evergreen ecosystem")
                                ScientificBullet("Rich in endemic species")
                                ScientificBullet("Carbon sequestration")
                                ScientificBullet("Conservation pocket")
                            }

                            grove.name.contains("Khajurli") -> {
                                ScientificBullet("Khejri trees improve soil nitrogen levels")
                                ScientificBullet("Supports local ecology")
                                ScientificBullet("Carbon storage and climate regulation")
                                ScientificBullet("Biodiversity refuge in desert")
                            }

                            grove.name.contains("Hulikal") -> {
                                ScientificBullet("Preserves native species diversity")
                                ScientificBullet("Supports water conservation")
                                ScientificBullet("Supports pollinators")
                                ScientificBullet("Mini biodiversity hotspot")
                            }

                            grove.name.contains("Sarna") -> {
                                ScientificBullet("Sal trees play major role in carbon storage")
                                ScientificBullet("Supports forest ecosystem")
                                ScientificBullet("Climate regulation")
                                ScientificBullet("Natural conservation zones")
                            }

                            else -> {
                                ScientificBullet("High carbon storage capacity")
                                ScientificBullet("Wildlife corridor")
                                ScientificBullet("Endangered species support")
                                ScientificBullet("Forest conservation")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScientificBullet(text: String) {

    Row(
        modifier = Modifier.padding(bottom = 20.dp),
        verticalAlignment = Alignment.Top
    ) {

        Text(
            text = "•",
            color = Color(0xFFB5E48C),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            text = text,
            color = Color(0xFF9FB7AA),
            fontSize = 15.sp,
            lineHeight = 24.sp,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun InfoBlock(
    title: String,
    icon: String,
    text: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {

        // 🔥 TITLE ROW
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(34.dp)
                    .background(
                        Color(0xFF123524),
                        RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = icon,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = title,
                color = Color(0xFFB5E48C),
                fontSize = 13.sp,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 🔥 DESCRIPTION TEXT
        Text(
            text = text,
            color = Color(0xFFB7D6C2),
            fontSize = 14.sp,
            lineHeight = 26.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Composable
fun ChipColumn(title: String, items: List<String>) {

    Column {

        Text(
            text = title,
            color = Color(0xFF7FAF95),
            fontSize = 12.sp,
            letterSpacing = 1.5.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            items.forEach {
                Box(
                    modifier = Modifier
                        .background(
                            Color(0xFF0F3D2E),
                            RoundedCornerShape(14.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(it, color = Color.White, fontSize = 13.sp)
                }
            }
        }
    }
}