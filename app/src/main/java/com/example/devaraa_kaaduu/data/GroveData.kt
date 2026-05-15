package com.example.devaraa_kaaduu.data

import com.example.devaraa_kaaduu.model.Grove
import com.example.devaraa_kaaduu.R

val groveList: List<Grove> = listOf(
    Grove(
        name = "Nagarahole Devara Kaadu",
        location = "Kodagu, Karnataka",
        type = "Kaavu",
        description = "Part of the Western Ghats biodiversity hotspot, this sacred grove is one of the richest regions in India.",
        species = listOf("Teak", "Rosewood", "Elephants"),
        myth = "Protected by local deity.",
        scientificFacts = listOf("Biodiversity hotspot", "Carbon storage"),
        image = R.drawable.bg_nagarahole1,
        // 🔥 ADD LAT LNG HERE
        lat = 12.0026,
        lng = 76.1000

    ),

    Grove(
        name = "Iruppu Sacred Grove",
        location = "Kodagu, Karnataka",
        type = "KAAVU",
        description = "Managed by the Iruppu temple, this grove is home to the stunning Iruppu Falls and diverse evergreen flora.",
        species = listOf(
            "Indian mahogany",
            "Ebony",
            "Black dammar",
            "Teak",
            "Hornbills",
            "Macaques"
        ),
        myth = "Associated with Ramayana legend; Lord Lakshmana created the river by shooting an arrow into the hills.",
        scientificFacts = listOf(
            "Part of tropical evergreen forests of Western Ghats",
            "Sacred groves act as genetic reservoirs",
            "Helps in soil conservation and carbon storage",
            "Maintains biodiversity in fragmented landscapes"
        ),
        image = R.drawable.bg_iruppu,
        lat = 11.965,
        lng = 75.991
    ),

    Grove(
        name =  "Tamhini Ghat Devrai",
        location = "Pune, Maharashtra",
        type = "DEVRAI",
        description = "A critical corridor in the Western Ghats, preserving several endemic tree species and providing a sanctuary for the Giant Squirrel.",
        species = listOf(
            "Teak", "Ain", "Hirda", "Jamun"
        ),
        myth = "Known as “Devrai” (God’s forest), protected by local villagers. Cutting trees or disturbing the grove is considered a sin. Rituals and offerings are made to local deities. Cultural beliefs ensure long-term conservation.",
        scientificFacts = listOf(
            "Classified as semi-evergreen and evergreen forest ecosystem",
            "Rich in endemic and medicinal plant species",
            "Helps in carbon sequestration and water retention",
            "Sacred groves act as biodiversity conservation pockets"
        ),
        image = R.drawable.bg_tamhinighat,
        lat = 18.4136,
        lng = 73.4218
    ),

    Grove(
        name = "Khajurli Oran",
        location = "Jaisalmer, Rajasthan",
        type = "Oran",
        description = "Community-protected desert grove supporting biodiversity in arid landscapes.",
        species = listOf("Khejri", "Blackbuck", "Desert Fox"),
        myth = "Protected by Bishnoi traditions.",
        scientificFacts = listOf(
            "Improves soil fertility",
            "Supports desert biodiversity"
        ),

        image = R.drawable.bg_khajurli,
        lat = 26.9157,
        lng = 70.9083
    ),

    Grove(
        name = "Hulikal Bana",
        location = "Shivamogga District, Karnataka",
        type = "Bana",
        description = "Village sacred grove supporting local biodiversity.",
        species = listOf("Banyan", "Neem", "Peepal"),
        myth = "Protected by local rituals and deity worship.",
        scientificFacts = listOf(
            "Mini biodiversity hotspot",
            "Supports groundwater recharge"
        ),
        image = R.drawable.bg_hulikal,
        lat = 14.1907,
        lng = 75.1474
    ),

    Grove(
        name = "Sarna Sacred Groves",
        location = "Jharkhand",
        type = "Sarna",
        description = "Sacred tribal groves preserved through Sarna traditions.",
        species = listOf("Sal", "Mahua", "Birds"),
        myth = "Trees are worshipped by tribal communities.",
        scientificFacts = listOf(
            "Carbon storage",
            "Natural conservation zone"
        ),
        image = R.drawable.bg_sarna,
        lat = 23.6102,
        lng = 85.2799
    )
)

