package com.example.devaraa_kaaduu.utils

fun calculateDistance(
    userLat: Double,
    userLng: Double,
    groveLat: Double,
    groveLng: Double
): String {

    val result = FloatArray(1)

    android.location.Location.distanceBetween(
        userLat, userLng,
        groveLat, groveLng,
        result
    )

    val km = result[0] / 1000
    return String.format("%.1f km", km)
}