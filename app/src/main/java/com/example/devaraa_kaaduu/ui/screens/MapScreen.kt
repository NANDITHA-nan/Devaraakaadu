package com.example.devaraa_kaaduu.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.location.Location

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.location.LocationServices
import android.os.Looper
import com.google.android.gms.location.*
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

@Composable
fun MapScreen() {

    val context = LocalContext.current
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        3000
    ).build()

    val locationCallback = remember {
        object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let {
                    userLocation = LatLng(it.latitude, it.longitude)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }

    val cameraPositionState = rememberCameraPositionState()
    LaunchedEffect(userLocation) {
        userLocation?.let {
            cameraPositionState.position =
                CameraPosition.fromLatLngZoom(it, 10f)
        }
    }

    // 🌿 Grove list
    val groves = listOf(
        Pair("Iruppu Sacred Grove", LatLng(12.45, 75.90)),
        Pair("Tamhini Devrai", LatLng(18.46, 73.45)),
        Pair("Nagarahole", LatLng(12.15, 76.00)),
        Pair("Hulikal Bana", LatLng(13.75, 75.25)),
        Pair("Sarna Groves", LatLng(23.61, 85.27)),
        Pair("Khajurli Oran", LatLng(26.9157, 70.9083))
    )

    // 🔥 SORT + FILTER NEAREST GROVES
    val nearbyGroves = userLocation?.let { user ->

        val calculated = groves.map { grove ->
            val dist = distance(
                user.latitude, user.longitude,
                grove.second.latitude, grove.second.longitude
            )
            Pair(grove, dist)
        }

        val filtered = calculated.filter { it.second <= 2000 }

        if (filtered.isNotEmpty()) {
            filtered.sortedBy { it.second }
        } else {
            calculated.sortedBy { it.second }   // 🔥 fallback: show all
        }

    } ?: emptyList()

    Column(modifier = Modifier.fillMaxSize()) {

        // 🗺️ MAP SECTION
        Box(modifier = Modifier.weight(1f)) {
            val context = LocalContext.current

            val hasLocationPermission =
                ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED

            LaunchedEffect(Unit) {
                if (!hasLocationPermission) {
                    ActivityCompat.requestPermissions(
                        context as android.app.Activity,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        1
                    )
                }
            }



            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(
                    isMyLocationEnabled = hasLocationPermission
                )
            ) {

                // 👤 USER MARKER
                userLocation?.let {
                    Marker(
                        state = MarkerState(position = it),
                        title = "You are here"
                    )
                }

                // 🌿 GROVE MARKERS
                nearbyGroves.forEach { (grove, dist) ->
                    Marker(
                        state = MarkerState(position = grove.second),
                        title = grove.first,
                        snippet = "${"%.1f".format(dist)} km away",
                        onClick = {
                            openGoogleMaps(
                                context,
                                grove.second.latitude,
                                grove.second.longitude
                            )
                            true
                        }
                    )
                }
                // ✅ 👉 ADD HERE (ROUTE LINE)
                val nearestGrove = nearbyGroves.firstOrNull()

                nearestGrove?.let { (grove, _) ->
                    userLocation?.let { user ->

                        Polyline(
                            points = listOf(
                                user,
                                grove.second
                            ),
                            color = Color(0xFFB5E48C),
                            width = 8f
                        )
                    }
                }
            }
        }

        // 📋 LIST BELOW MAP
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF021E14))
                .padding(12.dp)
        ) {

            nearbyGroves.take(3).forEach { (grove, dist) ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            openGoogleMaps(
                                context,
                                grove.second.latitude,
                                grove.second.longitude
                            )
                        },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = grove.first,
                        color = Color.White,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "${"%.1f".format(dist)} km",
                        color = Color(0xFFB5E48C),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

// 📏 DISTANCE FUNCTION
fun distance(
    lat1: Double,
    lon1: Double,
    lat2: Double,
    lon2: Double
): Double {
    val results = FloatArray(1)
    Location.distanceBetween(lat1, lon1, lat2, lon2, results)
    return (results[0] / 1000).toDouble()
}

// 🧭 OPEN GOOGLE MAPS ROUTE
fun openGoogleMaps(context: Context, lat: Double, lng: Double) {
    val uri = Uri.parse("google.navigation:q=$lat,$lng")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("com.google.android.apps.maps")
    context.startActivity(intent)
}