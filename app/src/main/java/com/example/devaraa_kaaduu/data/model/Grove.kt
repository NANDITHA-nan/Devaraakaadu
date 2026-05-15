package com.example.devaraa_kaaduu.model

data class Grove(
    val name: String,
    val location: String,
    val type: String,
    val description: String,
    val species: List<String>,
    val myth: String,
    val scientificFacts: List<String>,
    val image: Int,
    // 🔥 ADD THESE TWO
    val lat: Double,
    val lng: Double
)

