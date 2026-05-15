package com.example.devaraa_kaaduu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.devaraa_kaaduu.ui.theme.Playfair

@Composable
fun LoreDetailScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF001A0F),
                        Color(0xFF022114),
                        Color(0xFF03160E)
                    )
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.End)
        ) {

            Icon(
                Icons.Default.Close,
                contentDescription = null,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            Color(0xFF1B2B16),
                            RoundedCornerShape(14.dp)
                        )
                        .border(
                            1.dp,
                            Color(0xFF6A5B1A),
                            RoundedCornerShape(14.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "✨",
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(1.dp)
                        .background(Color(0xFF335C4A))
                )

                Spacer(modifier = Modifier.width(14.dp))

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            Color(0xFF1B2B16),
                            RoundedCornerShape(14.dp)
                        )
                        .border(
                            1.dp,
                            Color(0xFF335C4A),
                            RoundedCornerShape(14.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "0110",
                        color = Color(0xFFD8D19A),
                        fontSize = 10.sp
                    )
                }
            }

            Text(
                text = "Mythological",
                color = Color.White,
                fontFamily = Playfair,
                fontSize = 34.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Stories &",
                color = Color.White,
                fontFamily = Playfair,
                fontSize = 34.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Scientific Facts",
                color = Color.White,
                fontFamily = Playfair,
                fontSize = 34.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(26.dp))

            Text(
                text = "Descend into the oral traditions of India’s sacred forests and discover the biological engines that drive their preservation.",
                color = Color(0xFF9FB1A4),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                lineHeight = 34.sp,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        LoreSection(
            title = "Iruppu Sacred Grove",
            myth = "In the deep silence of the Brahmagiri hills, it is said that after searching for Goddess Sita, a weary Lord Rama and Lakshmana rested here. When Rama grew thirsty, Lakshmana shot an arrow into the Brahmagiri peaks, and the Lakshmana Tirtha river burst forth. The spirits of the ancestors are believed to guard the waterfalls, and any stone removed from the riverbed is said to return to its original place by nightfall, transported by the unseen hands of the forest sentinels.",
            science = "The Iruppu grove functions as a critical high-altitude water catchment. The dense root systems of the Indian Mahogany and Ebony trees act as biological sponges, filtering rainwater and releasing it slowly into the Lakshmana Tirtha river. This prevents flash floods during the monsoon and ensures a steady perennial flow for the villages downstream. Studies have shown that the soil microbial diversity here is 40% higher than in the surrounding coffee plantations.",
            traditions = listOf(
                "Deep reverence for the river deity.",
                "No footwear inside sacred zones.",
                "Ancestors believed to dwell in sacred trees."
            ),
            facts = listOf(
                "Part of tropical evergreen forests",
                "Genetic reservoirs of plant species",
                "Carbon storage support",
                "Fragment biodiversity support"
            )
        )

        LoreSection(
            title = "Tamhini Ghat Devrai",
            myth = "The elders of Tamhini speak of Shirkai Devi, the fierce guardian who took residence in these woods to protect the water spirits during the Great Drought. It is believed that the Giant Squirrel (Shekru) is her chosen messenger. To this day, any villager who accidentally cuts a branch must perform a deep penance, for the forest \"bleeds\" just as a living being does. The dense mist that often covers the Devrai is said to be the goddess’s breath, hiding the sacred paths from those with ill intent.",
            science = "Tamhini Ghat is a high-rainfall area (reaching up to 6000mm annually). The Devrai functions as a biological break against the intense monsoon downpours. The stratified canopy levels (Teak and Ain at the top, Ferns and Mosses at the bottom) prevent total soil washout. Analysis of the endemic tree \"Hirda\" shows it produces specific secondary metabolites that are found nowhere else, supporting a unique set of pollinating insects dependent exclusively on the Devrai’s micro-habitat.",
            traditions = listOf(
                "Absolute protection of sacred forest",
                "Community rituals for rain",
                "No hunting inside Devrai"
            ),
            facts = listOf(
                "Semi-evergreen ecosystem",
                "Medicinal plant richness",
                "Carbon sequestration support",
                "Biodiversity conservation pocket"
            )
        )

        LoreSection(
            title = "Khajurli Oran",
            myth = "The desert lore of Jaisalmer holds that the Khejri tree is the \"Shami\" tree of the ancients, mentioned in the Epics as a hiding place for sacred weapons. The Bishnoi communities tell of Amrita Devi, who lead 363 others to sacrifice their lives by hugging the trees to prevent them from being cut for a king's palace. It is believed that the Blackbuck and Chinkara are not just animals, but reincarnated souls of the guardians, and they are welcomed into the orans as family members. To kill an animal here is to sever the soul of the desert itself.",
            science = "The Oran functions as a \"drought-buffer\". The Khejri tree (Prosopis cineraria) has a deep taproot system that can reach groundwater at depths of 30 meters, bringing moisture to the surface layers where other vegetation can access it. During the peak of summer, the Oran acts as a temperature sink, reducing ground heat by up to 10 degrees compared to the open dunes. This allows survival for the Great Indian Bustard and other critically endangered desert fauna.",
            traditions = listOf(
                "Non-violence toward all beings",
                "No cutting sacred Khejri trees",
                "Humans are guardians of deity land"
            ),
            facts = listOf(
                "Improves soil nitrogen",
                "Provides shade and fodder",
                "Supports carbon regulation",
                "Refuge for desert biodiversity"
            )
        )

        LoreSection(
            title = "Hulikal Bana",
            myth = "Hulikal, the \"Place of Tigers,\" holds a Bana where the Banyan tree is said to be over five centuries old. Villagers tell of a time when a great fire swept through the valley but stopped exactly at the edge of the Bana as if hitting an invisible wall of water. It is believed the roots of the Peepal tree reach down to an underground river of nectar that keeps the village well from ever going dry. During the full moon, it is said the leaves of the Honne tree turn to silver for a few fleeting minutes.",
            science = "The Hulikal Bana serves as a \"Micro-Climate Modulator\". It significantly reduces the \"edge effect\" in the surrounding agricultural fields. The interlocking root systems of the Banyan create a subterranean mesh that prevents soil lateral movement on the Shimoga slopes. Microbiological analysis of the Bana soil shows presence of unique mycorrhizal fungi that facilitate nutrient sharing between different tree species, a phenomenon often described as the \"Wood Wide Web\" occurring in its purest form here.",
            traditions = listOf(
                "Banyan seen as Mother Spirit",
                "Village ritual cleaning ceremonies",
                "Medicinal plants for healing only"
            ),
            facts = listOf(
                "Native species preservation",
                "Water conservation support",
                "Pollinator ecosystem support",
                "Mini biodiversity hotspot"
            )
        )

        LoreSection(
            title = "Sarna Sacred Groves",
            myth = "The Sagara grove is whispered to be a gateway. Legends say that the \"Yakshas\" (Nature spirits) dance here on moonless nights, and their music can be heard as the wind whistling through the bamboo thickets. It is believed that the Honne tree resin, when burned, allows the elders to see into the future of the rains. The monkeys of this grove are thought to be the direct descendants of the troops that once aided the gods, and they are treated with unparalleled respect, often being offered the first fruits of the harvest.",
            science = "Located in a region of high endemicity, the Sagara grove is a \"Genetic Archive\". The Bamboo (Bambusa arundinacea) within the grove shows a synchronous flowering pattern that is unique to this preserved soil profile. The Teak specimens here exhibit a resistance to common pathogens found in commercial plantations, likely due to the natural immunity provided by the diverse fungal communities in the soil. It acts as an \"Islet of Resilience\" amidst a landscape increasingly dominated by monocultures.",
            traditions = listOf(
                "Nature worship rituals",
                "No cutting sacred Sal trees",
                "Sarhul festival traditions"
            ),
            facts = listOf(
                "Sal carbon storage",
                "Forest biodiversity support",
                "Climate regulation",
                "Natural conservation zone"
            )
        )

        LoreSection(
            title = "Nagarahole Devara Kaadu",
            myth = "Deep within Nagarahole, the \"Forest of Snakes,\" the Devara Kaadu is where the shadows are said to have weight. Tribal hunters tell of the \"Vanadevate\" (Forest Goddess) who takes the form of a golden sambar deer to lead poachers deep into the thicket until they are lost. It is believed that the roaring of the tiger is the forest's way of clearing the air of evil thoughts. The ancient Rosewood trees are thought to be the ribs of the earth, holding the spirit of the jungle together.",
            science = "Nagarahole functions as a \"Connectivity Hub\". The Devara Kaadu patches act as stepping stones for megafauna like the Asian Elephant. These groves contain a significantly higher density of fruiting trees (Ficus species) compared to the surrounding monoculture plantations, providing a year-round food supply. Monitoring data shows that these sacred patches harbor 30% more bird species than the adjoining managed forests, proving their role as \"Source Populations\" for the wider ecosystem.",
            traditions = listOf(
                "Forest guardian worship",
                "Restricted sacred access",
                "Traditional protection rituals"
            ),
            facts = listOf(
                "Tropical moist deciduous forest",
                "Western Ghats wildlife corridor",
                "High carbon storage",
                "Endemic species refuge"
            )
        )
    }
}

@Composable
fun LoreSection(
    title: String,
    myth: String,
    science: String,
    traditions: List<String>,
    facts: List<String>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 28.dp)
            .border(
                1.dp,
                Color(0xFF1B4D3E),
                RoundedCornerShape(32.dp)
            )
            .padding(28.dp)
    ) {

        Column {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .border(
                            1.dp,
                            Color(0xFF335C4A),
                            RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text("📖", fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        "THE ETERNAL WISDOM",
                        color = Color(0xFFD8D19A),
                        fontSize = 14.sp,
                        letterSpacing = 4.sp
                    )

                    Text(
                        title,
                        color = Color.White,
                        fontFamily = Playfair,
                        fontSize = 22.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                "🔥 Sacred Myth",
                color = Color.White,
                fontFamily = Playfair,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        2.dp,
                        Color(0xFF7B6400),
                        RoundedCornerShape(8.dp)
                    )
                    .padding(20.dp)
            ) {
                Text(
                    myth,
                    color = Color(0xFFE9E3C2),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    lineHeight = 30.sp,
                    fontFamily = Playfair
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                "🔬 The Science",
                color = Color.White,
                fontFamily = Playfair,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFF143021),
                        RoundedCornerShape(24.dp)
                    )
                    .padding(24.dp)
            ) {
                Text(
                    science,
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify,
                    lineHeight = 30.sp
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                "TRADITIONAL OBSERVANCES",
                color = Color(0xFFFFB800),
                fontSize = 14.sp,
                letterSpacing = 3.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            traditions.forEach {
                LoreBullet(it)
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                "SCIENTIFIC OBSERVATIONS",
                color = Color(0xFFD8D19A),
                fontSize = 11.sp,
                letterSpacing = 3.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            facts.forEach {
                LoreScientific(it)
            }
        }
    }
}

@Composable
fun LoreBullet(text: String) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text("• ", color = Color(0xFFFFB800), fontSize = 20.sp)
        Text(text, color = Color.White, fontSize = 16.sp)
    }
}

@Composable
fun LoreScientific(text: String) {
    Row(
        modifier = Modifier.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .border(
                    1.dp,
                    Color(0xFF335C4A),
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "01",
                color = Color(0xFFD8D19A),
                fontSize = 8.sp
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}