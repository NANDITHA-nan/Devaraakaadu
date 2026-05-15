package com.example.devaraa_kaaduu.ui.screens

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.devaraa_kaaduu.ui.theme.Playfair
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

@Composable
fun SpeciesScanScreen(navController: NavController) {

    val context = LocalContext.current

    var capturedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var isScanning by remember { mutableStateOf(false) }
    var scanResult by remember { mutableStateOf<SpeciesResult?>(null) }

    val labeler = ImageLabeling.getClient(
        ImageLabelerOptions.DEFAULT_OPTIONS
    )

    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) { bitmap ->
            bitmap?.let {
                capturedBitmap = it
                selectedImageUri = null
                scanResult = null
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
            uri?.let {
                selectedImageUri = it
                capturedBitmap = null
                scanResult = null
            }
        }

    fun scanBitmap(bitmap: Bitmap) {
        isScanning = true

        val image = InputImage.fromBitmap(bitmap, 0)

        labeler.process(image)
            .addOnSuccessListener { labels ->

                if (labels.isNotEmpty()) {
                    val best = labels.first()

                    scanResult = SpeciesResult(
                        name = best.text,
                        category = when {
                            best.text.contains("plant", true) -> "Flora"
                            best.text.contains("tree", true) -> "Flora"
                            best.text.contains("flower", true) -> "Flora"
                            else -> "Fauna"
                        },
                        habitat = "Detected from image",
                        status = "${(best.confidence * 100).toInt()}% confidence",
                        description = "AI detected ${best.text} from uploaded image."
                    )
                } else {
                    Toast.makeText(
                        context,
                        "No recognizable species found",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                isScanning = false
            }
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "Scan failed",
                    Toast.LENGTH_SHORT
                ).show()

                isScanning = false
            }
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
                .padding(26.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    null,
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    "BACK",
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                "Species Scan",
                color = Color.White,
                fontFamily = Playfair,
                fontSize = 34.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                "Capture or upload an image to identify sacred grove flora and fauna.",
                color = Color(0xFF8DA596),
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(34.dp))

            if (capturedBitmap == null && selectedImageUri == null) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    ScanButton(
                        icon = Icons.Outlined.CameraAlt,
                        text = "OPEN CAMERA",
                        modifier = Modifier.weight(1f)
                    ) {
                        cameraPermissionLauncher.launch(
                            Manifest.permission.CAMERA
                        )
                    }

                    ScanButton(
                        icon = Icons.Outlined.Upload,
                        text = "UPLOAD",
                        modifier = Modifier.weight(1f)
                    ) {
                        galleryLauncher.launch("image/*")
                    }
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

                    Spacer(modifier = Modifier.height(20.dp))

                    ScanButton(
                        icon = Icons.Outlined.Search,
                        text = if (isScanning) "SCANNING..." else "SCAN SPECIES",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        capturedBitmap?.let {
                            scanBitmap(it)
                        }
                    }
                }
            }

            scanResult?.let { result ->

                Spacer(modifier = Modifier.height(30.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.dp,
                            Color.White.copy(alpha = 0.08f),
                            RoundedCornerShape(24.dp)
                        )
                        .padding(20.dp)
                ) {

                    Column {
                        ResultRow("Species", result.name)
                        ResultRow("Category", result.category)
                        ResultRow("Habitat", result.habitat)
                        ResultRow("Status", result.status)

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            result.description,
                            color = Color(0xFF9CB2A4),
                            lineHeight = 24.sp
                        )
                    }
                }
            }
        }
    }
}

data class SpeciesResult(
    val name: String,
    val category: String,
    val habitat: String,
    val status: String,
    val description: String
)

@Composable
fun ScanButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(78.dp)
            .border(
                1.dp,
                Color.White.copy(alpha = 0.08f),
                RoundedCornerShape(20.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = Color(0xFF8DA596))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ResultRow(label: String, value: String) {
    Column {
        Text(
            label.uppercase(),
            color = Color(0xFFD8D19A),
            fontSize = 11.sp,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            value,
            color = Color.White,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(14.dp))
    }
}