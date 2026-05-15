package com.example.devaraa_kaaduu.data.model

data class PlantResponse(
    val results: List<Result>
)

data class Result(
    val species: Species
)

data class Species(
    val scientificNameWithoutAuthor: String
)