package com.example.devaraa_kaaduu.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.core.content.ContextCompat
import com.example.devaraa_kaaduu.ui.theme.Playfair
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import java.util.Locale
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.asImageBitmap
import coil.compose.AsyncImage
import android.widget.Toast

@Composable
fun ReportIssueScreen(navController: NavController) {

    var selectedViolation by remember { mutableStateOf("Tree Cutting") }
    var description by remember { mutableStateOf("") }
    var locationText by remember { mutableStateOf("FETCH AUTO-LOCATION") }
    var currentLat by remember { mutableStateOf(12.9716) }
    var currentLng by remember { mutableStateOf(77.5946) }
    var showMap by remember { mutableStateOf(false) }
    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->

            if (granted) {
                fetchCurrentLocation(
                    context = context,
                    fusedLocationClient = fusedLocationClient
                ) { lat, lng, address ->

                    currentLat = lat
                    currentLng = lng
                    locationText = address
                    showMap = true
                }
            }
        }

    var capturedBitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }

    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) { bitmap ->
            bitmap?.let {
                capturedBitmap = it
            }
        }

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                cameraLauncher.launch(null)
            }
        }
    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->
            selectedImageUri = uri
        }

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
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp, vertical = 26.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .border(
                                1.dp,
                                Color.White.copy(alpha = 0.08f),
                                RoundedCornerShape(50.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color(0xFF7E9486)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Text(
                        text = "ABORT",
                        color = Color(0xFF9CB2A4),
                        fontSize = 11.sp,
                        letterSpacing = 2.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Outlined.Shield,
                        contentDescription = null,
                        tint = Color(0xFFFF2D55),
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "SENTINEL ALERT",
                        color = Color(0xFFFF2D55),
                        fontSize = 11.sp,
                        letterSpacing = 2.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.padding(horizontal = 42.dp)
            ) {

                Text(
                    text = "Report an Incident",
                    color = Color.White,
                    fontFamily = Playfair,
                    fontSize = 38.sp,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Record environmental threats or violations within sacred boundaries. Your anonymous report contributes to national conservation efforts.",
                    color = Color(0xFF7E9486),
                    fontSize = 16.sp,
                    lineHeight = 34.sp
                )

                Spacer(modifier = Modifier.height(48.dp))

                SectionLabel("1. TYPE OF VIOLATION")

                Spacer(modifier = Modifier.height(22.dp))

                val violations = listOf(
                    "Tree Cutting",
                    "Waste Dumping",
                    "Encroachment",
                    "Illegal Hunting",
                    "Path Obstruction",
                    "Other"
                )

                violations.chunked(3).forEach { row ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        row.forEach { item ->
                            ViolationChip(
                                text = item,
                                selected = selectedViolation == item,
                                modifier = Modifier.weight(1f)
                            ) {
                                selectedViolation = item
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                }

                Spacer(modifier = Modifier.height(34.dp))

                SectionLabel("2. PRECISE LOCATION")

                Spacer(modifier = Modifier.height(18.dp))

                if (!showMap) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(118.dp)
                            .border(
                                1.dp,
                                Color.White.copy(alpha = 0.08f),
                                RoundedCornerShape(28.dp)
                            )
                            .clickable {
                                permissionLauncher.launch(
                                    Manifest.permission.ACCESS_FINE_LOCATION
                                )
                            }
                            .padding(22.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .border(
                                    1.dp,
                                    Color.White.copy(alpha = 0.08f),
                                    RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Outlined.LocationOn,
                                    contentDescription = null,
                                    tint = Color(0xFF8DA596)
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Text(
                                    text = "FETCH AUTO-LOCATION",
                                    color = Color(0xFF9CB2A4),
                                    fontSize = 13.sp,
                                    letterSpacing = 2.sp
                                )
                            }
                        }
                    }

                } else {

                    Column {

                        GoogleMap(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp)
                                .clip(RoundedCornerShape(28.dp)),
                            cameraPositionState = rememberCameraPositionState {
                                position = CameraPosition.fromLatLngZoom(
                                    LatLng(currentLat, currentLng),
                                    15f
                                )
                            }
                        ) {
                            Marker(
                                state = MarkerState(
                                    position = LatLng(currentLat, currentLng)
                                ),
                                title = "Current Location"
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = locationText,
                            color = Color(0xFF9CB2A4),
                            fontSize = 13.sp,
                            lineHeight = 22.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(42.dp))

                SectionLabel("3. DETAILED DESCRIPTION")

                Spacer(modifier = Modifier.height(18.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = {
                        Text(
                            "Describe the incident, observed individuals, or scale of damage...",
                            color = Color(0xFF4E6458)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White.copy(alpha = 0.08f),
                        unfocusedBorderColor = Color.White.copy(alpha = 0.08f),
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(26.dp)
                )

                Spacer(modifier = Modifier.height(40.dp))

                SectionLabel("4. VISUAL EVIDENCE")

                Spacer(modifier = Modifier.height(18.dp))

                if (capturedBitmap == null && selectedImageUri == null) {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        EvidenceButton(
                            icon = Icons.Outlined.CameraAlt,
                            text = "OPEN LIVE CAMERA",
                            modifier = Modifier.weight(1f),
                            onClick = {
                                cameraPermissionLauncher.launch(
                                    Manifest.permission.CAMERA
                                )
                            }
                        )

                        EvidenceButton(
                            icon = Icons.Outlined.Upload,
                            text = "UPLOAD GALLERY",
                            modifier = Modifier.weight(1f),
                            onClick = {
                                galleryLauncher.launch("image/*")
                            }
                        )

                    }

                } else {

                    Column {

                        if (capturedBitmap != null) {
                            Image(
                                bitmap = capturedBitmap!!.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(260.dp)
                                    .clip(RoundedCornerShape(24.dp))
                            )
                        }

                        if (selectedImageUri != null) {
                            AsyncImage(
                                model = selectedImageUri,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(260.dp)
                                    .clip(RoundedCornerShape(24.dp))
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        EvidenceButton(
                            icon = Icons.Outlined.CameraAlt,
                            text = "CHANGE IMAGE",
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                galleryLauncher.launch("image/*")
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(44.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(84.dp)
                        .background(
                            Brush.radialGradient(
                                listOf(
                                    Color(0x552DFF8A),
                                    Color.Transparent
                                )
                            ),
                            RoundedCornerShape(26.dp)
                        )
                        .border(
                            1.dp,
                            Color.White.copy(alpha = 0.05f),
                            RoundedCornerShape(26.dp)
                        )
                        .clickable {

                            when {
                                !showMap -> {
                                    Toast.makeText(
                                        context,
                                        "Please fetch your current location",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                capturedBitmap == null && selectedImageUri == null -> {
                                    Toast.makeText(
                                        context,
                                        "Please capture or upload evidence image",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                description.isBlank() -> {
                                    Toast.makeText(
                                        context,
                                        "Please enter incident description",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                else -> {
                                    Toast.makeText(
                                        context,
                                        "$selectedViolation report submitted successfully",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    // reset form
                                    selectedViolation = "Tree Cutting"
                                    description = ""
                                    locationText = "FETCH AUTO-LOCATION"
                                    showMap = false
                                    capturedBitmap = null
                                    selectedImageUri = null
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "SUBMIT INCIDENT REPORT",
                            color = Color.White,
                            fontSize = 15.sp,
                            letterSpacing = 2.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.width(14.dp))

                        Text(
                            text = "→",
                            color = Color.White,
                            fontSize = 24.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun SectionLabel(text: String) {
    Text(
        text = text,
        color = Color(0xFFD8D19A),
        fontSize = 12.sp,
        letterSpacing = 3.sp
    )
}

@Composable
fun ViolationChip(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(64.dp)
            .border(
                1.dp,
                if (selected) Color(0xFFFF2D55)
                else Color.White.copy(alpha = 0.08f),
                RoundedCornerShape(20.dp)
            )
            .background(
                if (selected) Color(0x33FF2D55)
                else Color.Transparent,
                RoundedCornerShape(20.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            color = if (selected) Color.White else Color(0xFF7E9486),
            modifier = Modifier.padding(start = 18.dp)
        )
    }
}

@Composable
fun EvidenceButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(82.dp)
            .clickable {
                onClick()
            }
            .border(
                1.dp,
                Color.White.copy(alpha = 0.08f),
                RoundedCornerShape(20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, null, tint = Color(0xFF8DA596))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                color = Color(0xFF9CB2A4),
                fontSize = 12.sp,
                letterSpacing = 2.sp
            )
        }
    }
}

@SuppressLint("MissingPermission")
fun fetchCurrentLocation(
    context: android.content.Context,
    fusedLocationClient: com.google.android.gms.location.FusedLocationProviderClient,
    onLocationFetched: (Double, Double, String) -> Unit
) {
    if (
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                try {
                    val geocoder = Geocoder(context, Locale.getDefault())

                    @Suppress("DEPRECATION")
                    val addresses = geocoder.getFromLocation(
                        it.latitude,
                        it.longitude,
                        1
                    )

                    val address =
                        if (!addresses.isNullOrEmpty())
                            addresses[0].getAddressLine(0)
                        else
                            "Current Location"

                    onLocationFetched(
                        it.latitude,
                        it.longitude,
                        address
                    )

                } catch (e: Exception) {
                    onLocationFetched(
                        it.latitude,
                        it.longitude,
                        "Current Location"
                    )
                }
            }
        }
    }
}